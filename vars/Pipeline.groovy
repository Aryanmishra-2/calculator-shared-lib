import org.ci_cd.git.*

def call(Map config = [:]) {

    String gitUrl         = config.get('gitUrl', '')
    String branch         = config.get('branch', '')
    String credentialsId  = config.get('credentialsId', '')
    String emailRecipient = config.get('emailRecipient', '')

    try {

        stage('Clean Workspace') {
            script {
                new CleanWorkspace(this).run()
            }
        }

        stage('Git Clone') {
            script {
                Clone.repo(
                    this,
                    gitUrl,
                    branch,
                    credentialsId
                )
            }
        }

        stage('Generate Clone Report') {
            script {

                sh """
                    mkdir -p report
                    cat <<'EOF' > report/clone-report.html
                    <html>
                    <body>
                        <h2>Clone Report</h2>

                        <p><b>Repository:</b> __GIT_URL__</p>
                        <p><b>Branch:</b> __BRANCH__</p>

                        <p><b>Job:</b> __JOB_NAME__</p>
                        <p><b>Build Number:</b> __BUILD_NUMBER__</p>
                        <p><b>Build URL:</b> __BUILD_URL__</p>

                        <p><b>Timestamp:</b> $(date)</p>
                    </body>
                    </html>
EOF
                """

                sh """
                    sed -i 's|__GIT_URL__|${gitUrl}|g' report/clone-report.html
                    sed -i 's|__BRANCH__|${branch}|g' report/clone-report.html
                    sed -i 's|__JOB_NAME__|${env.JOB_NAME}|g' report/clone-report.html
                    sed -i 's|__BUILD_NUMBER__|${env.BUILD_NUMBER}|g' report/clone-report.html
                    sed -i 's|__BUILD_URL__|${env.BUILD_URL}|g' report/clone-report.html
                """
            }
        }

        stage('Success Email') {
            script {
                if (emailRecipient)
                    new PublishReport(this).send("SUCCESS", emailRecipient)
            }
        }

    } catch (Exception e) {

        stage('Failure Email') {
            script {
                if (emailRecipient)
                    new PublishReport(this).send("FAILURE", emailRecipient)
            }
        }

        throw e
    }
}

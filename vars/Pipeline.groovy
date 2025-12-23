import org.ci_cd.git.*

def call(Map config = [:]) {

    String gitUrl         = config.get('gitUrl', '')
    String branch         = config.get('branch', 'main')
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

        stage('Success Email') {
            when { expression { emailRecipient != '' } }
            steps {
                script {
                    new PublishReport(this)
                        .send("SUCCESS", emailRecipient)
                }
            }
        }

    } catch (Exception e) {

        stage('Failure Email') {
            when { expression { emailRecipient != '' } }
            steps {
                script {
                    new PublishReport(this)
                        .send("FAILURE", emailRecipient)
                }
            }
        }

        throw e
    }
}

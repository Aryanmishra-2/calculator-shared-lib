import org.ci_cd.git.*

def call(Map config = [:]) {

    String gitUrl         = config.get('gitUrl', '')
    String branch         = config.get('branch', 'master')
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

        if (emailRecipient) {
            stage('Success Email') {
                script {
                    new PublishReport(this).send("SUCCESS", emailRecipient)
                }
            }
        }

    } catch (Exception e) {

        if (emailRecipient) {
            stage('Failure Email') {
                script {
                    new PublishReport(this).send("FAILURE", emailRecipient)
                }
            }
        }

        throw e
    }
}

import org.ci_cd.git.*

def call(Map config = [:]) {

    if (!config.gitUrl) {
        error "gitUrl is mandatory"
    }

    String branch         = config.get('branch', '')
    String credentialsId  = config.get('credentialsId', '')
    String emailRecipient = config.get('emailRecipient', '')

    stage('Clean Workspace') {
        new CleanWorkspace(this).run()
    }

    stage('Git Clone') {
        script {
            Clone.repo(
                this,
                config.gitUrl,
                branch,
                credentialsId
            )
        }
    }

    stage('Email Success Report') {
        when { expression { emailRecipient != '' } }
        steps {
            script {
                new PublishReport(this).send("SUCCESS", emailRecipient)
            }
        }
    }
}

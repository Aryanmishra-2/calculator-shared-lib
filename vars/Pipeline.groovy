import com.*

def call(Map config) {
    def reportUrl = ""

    try {
        stage('Clean Workspace') {
            new WorkspaceCleaner(this).clean()
        }

        stage('Checkout Repository') {
            new RepoCheckout(this).cloneRepo(
                config.repoUrl,
                config.branch ?: 'main',
                config.credentialsId
            )
        }

        stage('Generate Report') {
            reportUrl = new ReportGenerator(this).generate("SUCCESS")
        }

        stage('Send Success Notification') {
            new EmailNotifier(this).send(
                "SUCCESS",
                config.email,
                reportUrl
            )
        }

    } catch (err) {
        reportUrl = new ReportGenerator(this).generate("FAILURE")

        new EmailNotifier(this).send(
            "FAILURE",
            config.email,
            reportUrl
        )

        throw err
    }
}

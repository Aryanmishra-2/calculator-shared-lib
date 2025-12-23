package /org.ci_cd.git.*

class PublishReport implements Serializable {
    def script

    PublishReport(script) {
        this.script = script
    }

    def send(String status, String emailRecipient) {

        def reportContent = """
JOB_NAME=${script.env.JOB_NAME}
BUILD_NUMBER=${script.env.BUILD_NUMBER}
STATUS=${status}
BUILD_URL=${script.env.BUILD_URL}
TIME=${new Date()}
"""

        script.writeFile(
            file: 'report.txt',
            text: reportContent
        )

        script.archiveArtifacts artifacts: 'report.txt', allowEmptyArchive: false

        Boolean isSuccess = (status == "SUCCESS")
        new Email(script).mail(isSuccess, emailRecipient)
    }
}

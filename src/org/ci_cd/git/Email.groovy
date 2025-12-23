package org.ci_cd.git

class Email implements Serializable {
    def script
    Email(script) { this.script = script }

    def mail(Boolean success, String recipient) {
        def buildUrl = script.env.BUILD_URL
        def reportUrl = "${buildUrl}artifact/htmlcov/index.html"
        def status = success ? "SUCCESS" : "FAILURE"

        def message = """Build #${script.env.BUILD_NUMBER} - ${status}
Job: ${script.env.JOB_NAME}
Job URL: ${buildUrl}
Report: ${reportUrl}"""

        script.echo message

        script.mail(
            to: recipient,
            subject: "${status}: ${script.env.JOB_NAME} #${script.env.BUILD_NUMBER}",
            body: message
        )
    }
}

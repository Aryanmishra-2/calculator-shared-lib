package org.cloud_ops_crew.utils

class EmailNotifier implements Serializable {
    def script

    EmailNotifier(script) {
        this.script = script
    }

    void send(String status, String email, String reportUrl) {
        script.mail(
            to: email,
            subject: "${status}: ${script.env.JOB_NAME} #${script.env.BUILD_NUMBER}",
            body: """
Job Name: ${script.env.JOB_NAME}
Build Number: ${script.env.BUILD_NUMBER}
Status: ${status}

Report URL:
${reportUrl}
"""
        )
    }
}

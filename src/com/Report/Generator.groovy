package com.Report

class ReportGenerator implements Serializable {
    def script

    ReportGenerator(script) {
        this.script = script
    }

    String generate(String status) {
        script.sh """
        mkdir -p reports
        cat <<EOF > reports/index.html
        <html>
          <body>
            <h2>Pipeline Execution Report</h2>
            <p>Status: <b>${status}</b></p>
            <p>Job Name: ${script.env.JOB_NAME}</p>
            <p>Build Number: ${script.env.BUILD_NUMBER}</p>
          </body>
        </html>
        EOF
        """
        script.archiveArtifacts artifacts: 'reports/**'
        return "${script.env.BUILD_URL}artifact/reports/index.html"
    }
}

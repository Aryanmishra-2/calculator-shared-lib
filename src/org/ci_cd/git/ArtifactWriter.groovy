package com.company.utils

class ArtifactWriter implements Serializable {
    def script
    ArtifactWriter(script) { this.script = script }

    def saveResult(String content) {

        def safeContent = content.replace('"','\\"')

        script.sh """
            mkdir -p artifacts
            echo "${safeContent}" > artifacts/result.txt
        """

        script.archiveArtifacts artifacts: 'artifacts/result.txt', allowEmptyArchive: false
    }
}

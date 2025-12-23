package com.company.utils

class ArtifactWriter implements Serializable {
    def script
    ArtifactWriter(script) { this.script = script }

    def saveResult(String content) {
        script.sh """
            mkdir -p artifacts
            echo "${content}" > artifacts/result.txt
        """
    }
}

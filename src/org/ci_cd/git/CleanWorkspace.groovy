package org.ci_cd.git

class CleanWorkspace implements Serializable {
    def script
    CleanWorkspace(script) { this.script = script }

    def run() {
        script.deleteDir()
    }
}

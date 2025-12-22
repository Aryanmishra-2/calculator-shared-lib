package com.Repo

class RepoCheckout implements Serializable {
    def script

    RepoCheckout(script) {
        this.script = script
    }

    void cloneRepo(String repoUrl, String branch, String credId = null) {
        script.git url: repoUrl, branch: branch, credentialsId: credId
    }
}

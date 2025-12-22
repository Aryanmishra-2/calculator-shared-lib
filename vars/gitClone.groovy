import org.ci_cd.git.Clone

def call(Map config = [:]) {

    // ================== VALIDATION ==================
    if (!config.gitUrl) {
        error " gitUrl is mandatory"
    }

    // ================== DEFAULTS ==================
    String branch = config.get('branch', 'main')
    String credentialsId = config.get('credentialsId', '')

    // ================== STAGES ==================
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
}

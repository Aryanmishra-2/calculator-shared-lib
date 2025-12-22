import org.ci_cd.git.Clone

def call(Map config = [:]) {

    if (!config.gitUrl) {
        error "gitUrl is mandatory"
    }

    def branch = config.get('branch', 'main')
    def creds  = config.get('credentialsId', '')

    stage('Git Clone') {
        script {
            Clone.repo(
                this,
                config.gitUrl,
                branch,
                creds
            )
        }
    }
}

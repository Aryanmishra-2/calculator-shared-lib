import org.ci_cd.git.*
def call(Map config = [:]) {

    if (!config.gitUrl)  { error "gitUrl is mandatory" }
    if (!config.email)   { error "email is mandatory" }

    String branch = config.get('branch', 'main')
    String credentialsId = config.get('credentialsId', '')
    String buildStatus = "SUCCESS"

    try {

        stage('Clean Workspace') {
            new org.ci_cd.git.CleanWorkspace(this).run()
        }

        stage('Git Clone') {
            org.ci_cd.git.Clone.repo(
                this,
                config.gitUrl,
                branch,
                credentialsId
            )
        }

        stage('Write Artifact') {
            new org.ci_cd.git.ArtifactWriter(this).saveResult("Build number: ${env.BUILD_NUMBER}")
        }

    } catch (Exception e) {
        buildStatus = "FAILURE"
        throw e
    } finally {

        stage('Publish Report') {
            new org.ci_cd.git.PublishReport(this).send(buildStatus, config.email)
        }
    }
}

package org.ci_cd

class gitClone implements Serializable {

    static void cloneRepo(script, String url, String branch, String credentialsId) {

        script.checkout([
            $class: 'GitSCM',
            branches: [[name: branch]],
            userRemoteConfigs: [[
                url: url,
                credentialsId: credentialsId
            ]]
        ])
    }
}

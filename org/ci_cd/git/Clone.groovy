package org.ci_cd.git

class Clone implements Serializable {

    static void repo(script, String url, String branch, String credentialsId) {

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

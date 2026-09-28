def call(){
    withCredentials([string(credentialsId: 'nvd-api-key', variable: 'NVD_API_KEY')]) {
        withEnv(['JAVA_OPTS=-Xmx384m']) {
            dependencyCheck(
                additionalArguments: "--scan ./ --nvdApiKey ${NVD_API_KEY}",
                odcInstallation: 'OWASP'
            )
        }
    }

    dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}

def CONTAINER_NAME="leaveword"
def CONTAINER_TAG="latest"
def DOCKER_HUB_USER="libw521"
def HTTP_PORT="8081"
def NETWORK_NAME="leaveword_leaveword-network"
def IMAGE_NAME = "${DOCKER_HUB_USER}/${CONTAINER_NAME}:${CONTAINER_TAG}"

pipeline =  {
    stage ("Initialize") {
        def mavenHome = tool "mvn-3.6.2"
        def dockerHome = tool "myDocker"
        env.PATH = "${mavenHome}/bin:${dockerHome}:${env.PATH}"
    }

    stage ("Check Out") {
        checkout scm
    }

    stage ("Build") {
        sh "mvn clean package -P test -DskipTests"
        sh "docker build -t ${IMAGE_NAME} ."
    }

    stage ("Test") {
        // 执行单元测试和pmd静态代码检测
        sh "mvn test -P test"
        sh "mvn pmd:pmd -P test"
    }

    stage ("Deploy") {
        try {
            sh "docker stop ${CONTAINER_NAME}"
            sh "docker rm ${CONTAINER_NAME}"
        }
        catch (exc) {
            echo "There is no container: ${CONTAINER_NAME}"
        }
        sh "docker run -d -p ${HTTP_PORT}:${HTTP_PORT} --name ${CONTAINER_NAME} --network ${NETWORK_NAME} ${IMAGE_NAME}"
    }
}

postFailure = {
    echo 'Stages Failed'
}

postAlways = {
    // 在Jenkins中报告单元测试结果
    junit testResults: "**/target/surefire-reports/*.xml"

    // 在Jenkins中报告pmd静态代码检测结果
    def pmd = scanForIssues tool: pmdParser(pattern: '**/target/pmd.xml')
    publishIssues issues: [pmd]

    // 在Jenkins中报告jacoco检测结果
    jacoco(
        execPattern: 'target/jacoco.exec',
        classPattern: 'target/classes',
        sourcePattern: 'src/main/java',
        exclusionPattern: 'src/test*',
        changeBuildStatus: true
        //minimumMethodCoverage:'1',maximumMethodCoverage:'70',
        //minimumClassCoverage:'1',maximumClassCoverage:'70'
        )
}

node {
    try {
        pipeline()
    }
    catch (e) {
        postFailure()
        throw e
    }
    finally {
        postAlways()
    }
}



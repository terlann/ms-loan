@Library('jenkins-libs') _

env.GRADLE_CMD='./gradlew build -x test -Pjenkins -Pprofiles=$APP_PROFILE --no-daemon && ./gradlew test -Pjenkins -Pprofiles=$APP_PROFILE --no-daemon'
env.PROJECT_KEY="ld"
env.K8S_ZONE="k8s_bank"
env.K8S_NS="lead"
env.DEBUG_MODE='#'

jarvis()

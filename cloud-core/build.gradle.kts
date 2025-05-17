dependencies {
    implementation(project(mapOf("path" to ":core")))
    api("org.springframework.cloud:spring-cloud-starter-config")
    api("org.springframework.cloud:spring-cloud-starter-bus-amqp")
    api("org.springframework.cloud:spring-cloud-starter")
    api("org.springframework.cloud:spring-cloud-starter-bootstrap")
}
dependencies {
    implementation(project(mapOf("path" to ":cloud-core")))
    // https://mvnrepository.com/artifact/com.auth0/java-jwt
    implementation("com.auth0:java-jwt:4.5.0")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    testImplementation("org.springframework.security:spring-security-test")
}


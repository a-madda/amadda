dependencies {
    implementation(project(mapOf("path" to ":cloud-core")))
    // https://mvnrepository.com/artifact/com.github.ulisesbocchio/jasypt-spring-boot-starter
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
    implementation("org.springframework.cloud:spring-cloud-config-server")
}

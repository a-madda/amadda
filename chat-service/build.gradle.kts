dependencies {
    implementation(project(mapOf("path" to ":cloud-core")))
    implementation(project(mapOf("path" to ":jpa-core")))
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    /*implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")*/
    // https://mvnrepository.com/artifact/org.webjars/bootstrap
    implementation("org.webjars:bootstrap:5.3.5")
    // https://mvnrepository.com/artifact/org.webjars.npm/vue
    implementation("org.webjars.npm:vue:3.5.14")
    // https://mvnrepository.com/artifact/org.webjars.npm/axios
    implementation("org.webjars.npm:axios:1.9.0")
    // https://mvnrepository.com/artifact/org.webjars/sockjs-client
    implementation("org.webjars:sockjs-client:1.5.1")
    // https://mvnrepository.com/artifact/org.webjars/stomp-websocket
    implementation("org.webjars:stomp-websocket:2.3.4")
    "developmentOnly"("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")

}
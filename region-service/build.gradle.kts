dependencies {
    implementation(project(mapOf("path" to ":cloud-core")))
    implementation(project(mapOf("path" to ":jpa-core")))
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.hibernate.orm:hibernate-spatial:6.6.13.Final")
}
plugins {
    java
}

description = "Ryns Sample Application"

dependencies {

    val rynsVersion: String by project
    implementation(files("../libs/ryns-platform-common-${rynsVersion}.jar"))
    implementation(files("../libs/ryns-platform-app-server-${rynsVersion}.jar"))
    testImplementation(files("../libs/ryns-platform-test-framework-${rynsVersion}.jar"))

    runtimeOnly("com.zaxxer:HikariCP")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.reflections:reflections")
    runtimeOnly("com.nimbusds:nimbus-jose-jwt")
    runtimeOnly("io.jsonwebtoken:jjwt")
    runtimeOnly("io.jsonwebtoken:jjwt-api")
    runtimeOnly("io.jsonwebtoken:jjwt-impl")
    runtimeOnly("io.jsonwebtoken:jjwt-gson")

    runtimeOnly("io.opentracing:opentracing-api")
    runtimeOnly("io.opentracing:opentracing-util")
    runtimeOnly("org.springframework.security:spring-security-crypto")
    runtimeOnly("commons-logging:commons-logging")

    runtimeOnly("javax.xml.bind:jaxb-api")
    runtimeOnly("javax.activation:activation")
    runtimeOnly("com.sun.mail:javax.mail")
    runtimeOnly("io.jsonwebtoken:jjwt-api")
    runtimeOnly("io.jsonwebtoken:jjwt-impl")
    add("implementation", "io.jsonwebtoken:jjwt-api:0.11.5")
    add("implementation", "io.jsonwebtoken:jjwt-impl:0.11.5")
    add("implementation", "io.jsonwebtoken:jjwt-gson:0.11.5")
    add("implementation", "com.nimbusds:nimbus-jose-jwt:9.8.1")



}
tasks.jar {
    manifest {
        // Set the executable jar's main class
        attributes["Main-Class"] = "io.ryns.app.sample.SampleApplication"
    }
}
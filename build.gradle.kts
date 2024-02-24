import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

plugins {
    idea
    `maven-publish`
    id("com.github.johnrengelman.shadow")
    java
}

group = "io.ryns.sample"
version = "1.0-SNAPSHOT"
//Version matching with java version.


allprojects {
    version = this.rootProject.version

    val doRelease = project.findProperty("doRelease")
    logger.quiet("doRelease: $doRelease")
    if ("true" == doRelease) {
        var releaseVersion = project.findProperty("releaseVersion")
        logger.quiet("releaseVersion: $releaseVersion")
        if (releaseVersion == null) {
            //Simple solution to create a unique build no without persisting/reading it from a file/persistence
            val utc = ZoneId.of("UTC")
            val buildNumber = LocalDateTime.now(utc).format(DateTimeFormatter.ofPattern("yyMMddHHmm"))
//            project.ext.releaseVersion = version.toString().replace("-SNAPSHOT", ".$buildNumber")
            releaseVersion = version.toString().replace("-SNAPSHOT", ".$buildNumber")
            logger.quiet("releaseVersion: $releaseVersion")
//            project.setProperty("releaseVersion", releaseVersion)
            logger.quiet("New Version to be released:  $releaseVersion")
        }
        version = releaseVersion
        logger.quiet("Releasing new artifact $group:$name:$version")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    group = buildString {
        append(parent?.group)
    }

    repositories {
        mavenCentral()
        mavenLocal()
        flatDir {
            dirs ("$rootProject.projectDir/libs")
        }
    }

    dependencies {
        add("testImplementation", "org.junit.jupiter:junit-jupiter-api")
        add("testRuntimeOnly", "org.junit.jupiter:junit-jupiter-engine")

        add("runtimeOnly", "ch.qos.logback:logback-classic")

        constraints {

            val rynsVersion: String by project
            add("implementation", "io.ryns.platforms:ryns-platform-common:${rynsVersion}")
            add("implementation", "io.ryns.platforms:ryns-platform-app-server:$rynsVersion")
            add("implementation", "io.ryns.platforms:ryns-platform-test-framework:$rynsVersion")

            add("implementation", "com.google.code.gson:gson:2.8.9")
            add("implementation", "com.zaxxer:HikariCP:4.0.3")

            add("implementation", "javax.xml.bind:jaxb-api:2.3.1")
            add("implementation", "javax.activation:activation:1.1.1")
            add("implementation", "com.sun.mail:javax.mail:1.6.2")


            add("implementation", "io.jsonwebtoken:jjwt:0.7.0")
            add("implementation", "javax.xml.bind:jaxb-api:2.3.1")

            add("implementation", "org.hamcrest:hamcrest-all:1.3")
            add("implementation", "org.junit.jupiter:junit-jupiter-api:5.8.0")
            add("implementation", "org.junit.jupiter:junit-jupiter-engine:5.8.0")
            add("implementation", "org.mockito:mockito-core:3.9.0")
            add("implementation", "org.mockito:mockito-inline:3.9.0")
            add("implementation", "org.postgresql:postgresql:42.3.3")
            add("implementation", "org.reflections:reflections:0.10.2")
            add("implementation", "org.slf4j:slf4j-api:2.0.7")
            add("implementation", "ch.qos.logback:logback-classic:1.4.8")
            add("implementation", "org.jetbrains:annotations:13.0")
            add("implementation", "org.springframework.security:spring-security-crypto:5.8.1")
            add("implementation", "commons-logging:commons-logging:1.2")
            add("implementation", "com.google.zxing:javase:3.5.1")
            add("implementation", "com.datadoghq:dd-trace-api:1.14.0")
            add("implementation", "io.opentracing:opentracing-api:0.33.0")
            add("implementation", "io.opentracing:opentracing-util:0.33.0")
            add("implementation", "io.sentry:sentry:6.21.0")
            add("implementation", "io.jsonwebtoken:jjwt-api:0.11.5")
            add("implementation", "io.jsonwebtoken:jjwt-impl:0.11.5")
            add("implementation", "io.jsonwebtoken:jjwt-gson:0.11.5")
            add("implementation", "com.nimbusds:nimbus-jose-jwt:9.8.1")

            add("implementation", "io.grpc:grpc-api:1.48.0")
            add("implementation", "io.grpc:grpc-netty:1.48.0")
            add("implementation", "io.grpc:grpc-protobuf:1.48.0")
            add("implementation", "io.grpc:grpc-stub:1.48.0")
            add("implementation", "io.grpc:grpc-services:1.48.0")
        }
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.named("shadowJar") {
        dependsOn(tasks.jar)
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
            }
        }
    }
}
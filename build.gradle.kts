plugins {
    // Apply the java-library plugin to add support for Java Library
    `java-library`
     id("com.github.spotbugs") version "4.6.0"
    jacoco
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:26.0-jre")

    // Use JUnit test framework
    testImplementation("junit:junit:4.12")
}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("gradle.plugin.com.github.spotbugs.snom:spotbugs-gradle-plugin:4.6.0")
    }
}

apply(plugin = "com.github.spotbugs")

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    dependsOn(tasks.jacocoTestCoverageVerification)
}


jacoco {
    toolVersion = "0.8.6"
    reportsDirectory.set(file("$buildDir/customJacocoReportDir"))
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.destination = file("${buildDir}/jacocoHtml")
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.95".toBigDecimal()
            }
        }
    }
}

configurations.all {
    resolutionStrategy {
        // Fail eagerly on version conflict (includes transitive dependencies)
        // e.g. multiple different versions of the same dependency (group and name are equal)
        failOnVersionConflict()
    }
}

import dev.detekt.gradle.extensions.FailOnSeverity
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
    id("multiplatform-module")
    id("org.jetbrains.dokka")
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.kotlinx.kover")
    id("dev.detekt")
}

group = "io.github.mapvina.spatialk"
version = System.getenv("VERSION") ?: "1.0.0"

kotlin {
    explicitApi()
    compilerOptions {
        freeCompilerArgs =
            listOf(
                // Will be the default soon: https://youtrack.jetbrains.com/issue/KT-11914
                "-Xconsistent-data-class-copy-visibility"
            )
    }
    abiValidation {
        @OptIn(ExperimentalAbiValidation::class)
        enabled = true
    }
}

detekt {
    source.setFrom("src/commonMain/kotlin")
    config.setFrom(rootProject.file("detekt.yml"))
    failOnSeverity = FailOnSeverity.Warning
    basePath.set(rootProject.rootDir)
}

dokka {
    dokkaSourceSets {
        configureEach {
            includes.from("MODULE.md")
            sourceLink {
                remoteUrl("https://github.com/mapvina/spatial-k/tree/v${project.version}/")
                localDirectory = rootDir
            }
            externalDocumentationLinks {
                create("kotlinx-serialization") {
                    url("https://kotlinlang.org/api/kotlinx.serialization/")
                }
            }
        }
    }
    pluginsConfiguration {
        html { customStyleSheets.from(rootProject.file("docs/styles/dokka-extra.css")) }
    }
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    signAllPublications()

    pom {
        url = "https://mapvina.com/spatial-k/"

        scm {
            url = "https://github.com/mapvina/spatial-k"
            connection = "scm:git:git://github.com/mapvina/spatial-k.git"
            developerConnection = "scm:git:git://github.com/mapvina/spatial-k.git"
        }

        licenses {
            license {
                name = "MIT"
                url = "https://opensource.org/licenses/MIT"
                distribution = "repo"
            }
        }

        developers {
            developer {
                id = "mapvina"
                name = "MapVina"
            }
        }
    }
}

val privateKeyFile = file("/Volumes/DATA/MapVina/private-key.asc")
if (privateKeyFile.exists()) {
    val privateKey = privateKeyFile.readText()
    val password = project.findProperty("signing.password") as String? ?: System.getenv("SIGNING_PASSWORD")
    val keyId = project.findProperty("signing.keyId") as String? ?: "8B10EF76"
    extensions.configure<SigningExtension> {
        useInMemoryPgpKeys(keyId, privateKey, password)
    }
}

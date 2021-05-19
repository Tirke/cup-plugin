import org.jetbrains.changelog.closure
import org.jetbrains.changelog.markdownToHTML

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.intellij") version "0.7.3"
    id("org.jetbrains.changelog") version "1.1.2"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

repositories {
    mavenCentral()
    jcenter()
}

intellij {
    pluginName = properties("pluginName")
    version = properties("platformVersion")
    type = properties("platformType")
    downloadSources = true
    updateSinceUntilBuild = true

    setPlugins("com.intellij.java")
}

changelog {
    version = properties("pluginVersion")
}


tasks {
    patchPluginXml {
        version(properties("pluginVersion"))
        sinceBuild(properties("pluginSinceBuild"))

        pluginDescription(
                closure {
                    File(projectDir, "README.md").readText().lines().run {
                        val start = "<!-- Plugin description -->"
                        val end = "<!-- Plugin description end -->"

                        if (!containsAll(listOf(start, end))) {
                            throw GradleException("Missing plugin description section in README")
                        }
                        subList(indexOf(start) + 1, indexOf(end))
                    }.joinToString("\n").run { markdownToHTML(this) }
                }
        )

        changeNotes(
                closure {
                    changelog.getLatest().toHTML()
                }
        )
    }

    runPluginVerifier {

    }
}
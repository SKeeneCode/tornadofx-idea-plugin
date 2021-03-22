import org.jetbrains.intellij.tasks.RunIdeTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.intellij.tasks.PublishTask

plugins {
    idea apply true
    java
    kotlin("jvm") version "1.4.0"
    id("org.jetbrains.intellij") version "0.4.21"
    id("org.openjfx.javafxplugin") version "0.0.9"
}

group = "no.tornado"
version = "1.7.20"

val publishUsername: String by rootProject.extra
val publishPassword: String by rootProject.extra

repositories {
    mavenCentral()
}

intellij {
    version = "2020.3"
    //updateSinceUntilBuild = false
    setPlugins("java", "properties", "org.jetbrains.kotlin:1.4.0-release-IJ2020.2-1")
}

javafx {
    version = "16"
    modules = mutableListOf(
        "javafx.controls",
        "javafx.graphics",
        "javafx.fxml",
    "javafx.swing")
}

tasks.runIde {
    jvmArgs("--add-exports", "java.base/jdk.internal.vm=ALL-UNNAMED")
}
tasks.buildSearchableOptions {
    jvmArgs("--add-exports", "java.base/jdk.internal.vm=ALL-UNNAMED")
}

tasks {
    // withType<PatchPluginXmlTask> { }


    withType<PublishTask> {
        username(publishUsername)
        password(publishPassword)
    }

    withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}


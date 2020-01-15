plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://maven.fabric.io/public")
    maven("https://plugins.gradle.org/m2/")
    maven("https://ci.android.com/builds/submitted/5837096/androidx_snapshot/latest/repository")
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object PluginsVersions {
    const val GRADLE_ANDROID = "4.0.0-alpha06"
    const val GRADLE_VERSIONS = "0.27.0"
    const val KOTLIN = "1.3.61"
    const val NAVIGATION = "2.1.0"
    const val FABRIC = "1.31.2"
    const val KTLINT = "0.36.0"
    const val GOOGLE_SERVICES = "4.3.2"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginsVersions.GRADLE_VERSIONS}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jetbrains.kotlin:kotlin-allopen:${PluginsVersions.KOTLIN}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
    implementation("io.fabric.tools:gradle:${PluginsVersions.FABRIC}")
    implementation("com.pinterest:ktlint:${PluginsVersions.KTLINT}")
    implementation("com.google.gms:google-services:${PluginsVersions.GOOGLE_SERVICES}")
}
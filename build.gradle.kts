plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation ("com.code-intelligence:jazzer-api:0.10.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    ignoreFailures = true
}

tasks.koverMergedHtmlReport {
    isEnabled = true
    htmlReportDir.set(layout.projectDirectory.dir("results/coverage"))
}

tasks.koverMergedXmlReport {
    isEnabled = true
    xmlReportFile.set(layout.projectDirectory.file("results/coverage/coverage.xml"))
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = ""
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    configurations.compileClasspath.get().forEach {
        from(if (it.isDirectory) it else zipTree(it))
    }
}

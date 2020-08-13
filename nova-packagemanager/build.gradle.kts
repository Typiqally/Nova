plugins {
    java
}

group = "org.nova"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation("junit", "junit", "4.12")
    implementation("org.spigotmc", "spigot-api", "1.16.1-R0.1-SNAPSHOT")
    implementation(project(":nova-framework"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    register("fatJar", Jar::class.java) {
        archiveClassifier.set("all")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        val classPaths = configurations.runtimeClasspath.get()
        val filteredClassPaths = classPaths.filter {
            it.name.contains("nova-framework")
        }.map {
            if (it.isDirectory) it else zipTree(it)
        }

        from(filteredClassPaths)
        from(sourceSets.main.get().output)
    }
}

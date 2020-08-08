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
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
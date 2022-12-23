plugins {
    val kotlinVersion = "1.7.22"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("org.liquibase.gradle") version "2.1.0"
}

group = "com.jubel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.sparkjava:spark-kotlin:1.0.0-alpha")
    implementation("org.slf4j:slf4j-simple:2.0.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.google.inject:guice:5.1.0")
    implementation ("org.xerial:sqlite-jdbc:3.40.0.0")
    liquibaseRuntime ("org.liquibase:liquibase-core:4.18.0")
    liquibaseRuntime ("org.yaml:snakeyaml:1.33")
    liquibaseRuntime ("info.picocli:picocli:4.7.0")
    liquibaseRuntime ("org.xerial:sqlite-jdbc:3.40.0.0")

    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("io.rest-assured:kotlin-extensions:5.3.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.mockito:mockito-core:4.10.0")
    testImplementation("org.mockito:mockito-inline:4.10.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

liquibase{
    activities.register("main") {
        this.arguments = mapOf(
            "changeLogFile" to "src/main/resources/dbchangelog.yaml",
            "url" to "jdbc:sqlite:db/jubelind.db",
            "driver" to "org.sqlite.JDBC"
        )
    }
    runList = "main"
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
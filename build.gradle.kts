plugins {
  `java-library`
  kotlin("jvm") version "1.3.60"
  kotlin("kapt") version "1.3.60"
}

repositories {
  jcenter()
}

dependencies {
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  api("com.squareup.moshi:moshi:1.9.1")
  kapt("com.squareup.moshi:moshi-kotlin-codegen:1.9.1")

  testImplementation("com.squareup.okio:okio:2.4.1")
  testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
  testImplementation(platform("org.junit:junit-bom:5.5.2"))
}

tasks.named<Test>("test") {
  useJUnitPlatform()
  testLogging {
    events("failed")
  }
}

kotlin.target.compilations.configureEach {
  kotlinOptions.jvmTarget = "1.8"
}
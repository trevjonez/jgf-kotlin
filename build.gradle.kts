/*
 *    Copyright 2019 Trevor Jones
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */


plugins {
  `java-library`
  kotlin("jvm") version "1.3.60"
  kotlin("kapt") version "1.3.60"
  `maven-publish`
  id("com.jfrog.bintray") version "1.8.4"
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

java {
  withSourcesJar()
  withJavadocJar()
}

kotlin.target.compilations.configureEach {
  kotlinOptions.jvmTarget = "1.8"
}

publishing {
  publications {
    register<MavenPublication>("jar") {
      from(components["java"])
    }
  }
}

bintray {
  user = project.findProperty("bintray_user") as? String
  key = project.findProperty("bintray_api_key") as? String
  setPublications("jar")
  pkg.apply {
    repo = "maven"
    name = project.name
    desc = project.description
    githubReleaseNotesFile = "CHANGELOG.md"
    setLicenses("Apache-2.0")
    setLabels("JsonGraphFormat", "jay-gee-eff", "jgf", "kotlin")
    githubRepo = "trevjonez/${project.name}"
    vcsUrl = "git@github.com:trevjonez/${project.name}.git"
    version.apply {
      name = "${project.version}"
    }
  }
}
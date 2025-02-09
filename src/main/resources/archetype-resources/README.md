# {{cookiecutter.project_name}}

{{cookiecutter.project_short_description}}

## Overview

* **Plugin type**: {{cookiecutter.plugin_type}}
* **Guess supported**: no
* Embulk 0.10 or later


## Example

## Configuration

## Install

1. install plugin
   ```
   $ mvn dependency:get -Dartifact={{cookiecutter.package_name}}:embulk-{{cookiecutter.plugin_type}}-plugin:{{cookiecutter.project_version}}
   ```

2. add setting to $HOME/.embulk/embulk.properties
   ```
   plugins.parser.poi_excel=maven:{{cookiecutter.package_name}}:plugin:{{cookiecutter.project_version}}
   ```


## Build

```
$ ./gradlew test
$ ./gradlew package
```

### Build to local Maven repository

```
./gradlew generatePomFileForMavenJavaPublication
mvn install -f build/publications/mavenJava/pom-default.xml
./gradlew publishToMavenLocal
```

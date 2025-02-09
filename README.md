# Introduction

Maven archetype to help you create new embulk(=>0.11) input plugin.

## Usage
- Clone this repository;
- Install the archetype locally:
```mvn clean install```

- After you do that, you can go to the target directory and perform the following command:


```
mvn archetype:generate -DarchetypeCatalog=local -DarchetypeGroupId=org.embulk -DarchetypeArtifactId=embulk-archetype-plugin -Dtype=databricks -DgroupId=org.moo
```


import java.nio.file.*

println "Debug: Properties available:"
request.properties.each { k,v -> 
    println "  ${k} = ${v}"
}

def sourceDir = Paths.get(
    request.outputDirectory,
    request.properties.artifactId,
    "src/main/java/${request.properties.package.replace('.','/')}"
)

println "Source directory: ${sourceDir}"

// Source is just InputPlugin.java
Path sourcePath = sourceDir.resolve("InputPlugin.java")
println "Source file exists: ${Files.exists(sourcePath)}"

// Create target name using type
def type = request.properties.type
def capitalizedType = type.substring(0,1).toUpperCase() + type.substring(1)
Path targetPath = sourceDir.resolve(capitalizedType + "InputPlugin.java")
println "Target path: ${targetPath}"

// Use temp file approach for case-sensitivity
Path tempPath = sourceDir.resolve("TEMP_" + System.currentTimeMillis() + ".java")
Files.move(sourcePath, tempPath)
Files.move(tempPath, targetPath)
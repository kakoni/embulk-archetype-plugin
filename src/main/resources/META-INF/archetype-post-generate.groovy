import java.nio.file.*

def artifactId = request.properties.get('artifactId')
def sanitized = artifactId.replaceAll('-', '')
def capitalized = sanitized.substring(0,1).toUpperCase() + sanitized.substring(1)

// Original path where Maven IT expects files
Path sourceDir = Paths.get(
  request.outputDirectory, 
  artifactId,
  "src/main/java/${request.properties.package.replace('.','/')}"
)

// Create Path objects for source and target files
Path sourcePath = sourceDir.resolve(artifactId + "InputPlugin.java")
Path targetPath = sourceDir.resolve(capitalized + "InputPlugin.java")

// Create a temporary file with a completely different name
Path tempPath = sourceDir.resolve("TEMP_" + System.currentTimeMillis() + ".java")
Files.move(sourcePath, tempPath)

// Then move from temp to final destination
Files.move(tempPath, targetPath)
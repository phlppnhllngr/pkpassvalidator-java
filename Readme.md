# pkpassvalidator-java
This is a Java program that validates pkpass-files.<br>
The validation code was mostly taken from <https://github.com/tomasmcguinness/pkpassvalidator> (🙏) and converted to Java.<br>
Unlike @tomasmcguinness' repository, this one does not include any server code.<br>
There is just one `main` method that expects a path to a pkpass file as input, prints the validation result to the console and exits with code 1 when there is a validation error.

## Compile
```
mvn clean package
```

## Run
Pass a valid `java.nio.file.Path` to the main method.<br>
Example (Windows):
```
java --class-path "target\pkpassvalidator-java-1.0.0.jar;target\lib\*" phlppnhllngr.pkpassvalidator.Main "C:\path\to\some.pkpass" 
```

## Maven dependency
So far no `pkpassvalidator-java` package has been released.<br>
Use [JitPack](https://jitpack.io/) to fetch a jar file built from this repository and use as library in your own project.
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.phlppnhllngr</groupId>
        <artifactId>pkpassvalidator-java</artifactId>
        <!-- latest commit hash -->
        <version>ac328e1</version>
    </dependency>
</dependencies>
```

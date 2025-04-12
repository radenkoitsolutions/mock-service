MockService

MockService is a class that provides a method to load JSON data from files within a directory, which is configured via a property. This service uses Jackson to parse the JSON and throws a custom MissingFileException if the file is not found.
Description

The MockService class provides a getResponse method that loads JSON data from a file located in a directory specified in the configuration. If the file is not found, a custom MissingFileException is thrown, returning a message with the path of the missing file.
Requirements

    Java 17 or higher

    Spring Boot project with configuration for loading parameters from application.properties or application.yml

    Jackson library for JSON processing

Dependencies

If you're using Maven, add the following dependencies to your pom.xml file:

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>
</dependencies>

Configuration

Add the necessary parameters for the directory where the JSON files are located in your application.properties or application.yml file.

Example for application.properties:

json.response.file.dir=/path/to/json/files

Usage
Example of using MockService:

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    private final MockService mockService;

    @Autowired
    public MockController(MockService mockService) {
        this.mockService = mockService;
    }

    @GetMapping("/get-response")
    public Object getResponse(String fileName) {
        return mockService.getResponse(fileName);
    }
}

Example Request:

GET /get-response?fileName=example.json

If the example.json file exists in the specified directory, its content will be returned as a JSON response. If the file is not found, an exception will be thrown.
Method Workflow

    getResponse(String fileName):

        Checks if the file exists in the directory specified in the configuration.

        If the file is not found, it throws a MissingFileException with a specific error message.

        If the file exists, it parses the JSON content using Jackson and returns it as a Java object.

Example of MissingFileException

public class MissingFileException extends Exception {
    public MissingFileException(String message) {
        super(message);
    }
}

This custom exception is thrown when a file does not exist in the specified directory.
Conclusion

MockService provides a convenient way to load JSON files from a local directory while handling errors when the file is not found. This approach can be used for mocking responses or loading configuration files in test environments.
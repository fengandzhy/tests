- **Technology Stack:** The project is developed using Spring Boot for the backend, React and Bootstrap for the frontend. Maven is used as the build and dependency management tool.
- **Objective:** The goal of the project is to create a functioning demo application mainly focus on number transfering to words
- **Unit Testing:** A significant emphasis has been placed on unit testing to ensure the robustness of the application.

**Design and Programming Techniques:**

1. **Spring Boot:** Spring Boot is utilized as the backend framework. It simplifies the setup and configuration of the application and provides various features like auto-configuration and embedded web servers.
3. **Unit Testing:** The use of unit testing is a best practice for ensuring the reliability and maintainability of the codebase. These tests validate that individual components or functions work correctly.
5. **MVC Architecture:** Spring Boot follows the Model-View-Controller (MVC) architectural pattern, which separates the application into three interconnected components, facilitating modularity and maintainability.
6. **Dependency Injection:** Spring Boot's dependency injection is utilized to manage dependencies between components, promoting loose coupling and facilitating easier testing and maintenance.
7. **Maven:** Maven is a project management tool that can be used for building and managing dependencies for Java projects.

**Install and run:** 

1.Enter the project directory from the cmd command line and run below command and wait for it success 

```
mvn clean package
```

![image-20230903133053162](C:\Users\zhouhongyuan\AppData\Roaming\Typora\typora-user-images\image-20230903133053162.png)



3 come to the target directory  and run below command 

```
java -jar transfer-react-1.0-SNAPSHOT.jar
```

**Other technical note:** 

1. **Define a consistent response format,** 

   In this project, the returned data follows a standardized format, which is as follows: `code` represents the response status code, where "200" typically indicates a successful response. However, we can also define custom response codes for different response states as needed. `message` provides a brief description of the response, usually used to briefly explain the outcome of the response. `data` contains the actual data, which can be the information we require or detailed error messages. One of the purpose of the unified response format is that the unified response format contributes to improved code maintainability. When multiple developers work together, they can rely on a consistent format without worrying about inconsistent responses

```
{
    "code": 200,
    "message": "OK",
    "data": {
        "username": "John Smith",
        "moneyWords": "TWENTY-TWO DOLLARS"
    }
}
```

2. **The steps for this project to implement unified response format.** 
   1. defined below annotation, this annotation is used to mark which controller and which method need the unified response formate  

```
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseResultBody {
}
```

2. create `ResponseHandler` and `GlobalExceptionHandler` , `ResponseHandler` is response for wrap a data which response correctly and `GlobalExceptionHandler` is used to process exceptions. 
3. create `Result` and `ResultStatus`, `Result` is used to defined the unified response format and  `ResultStatus` is used to defined some response status



3. **Custom data validation**

   This is a frontend-backend separated project, so data coming from the frontend requires secondary validation on the backend. The validation logic here ensures that the amount is a positive number with a maximum of two decimal places. The specific approach involves defining a custom annotation, specifying validation logic within the MoneyValidator, and then applying this annotation to specific fields

```
@Documented
@Constraint(validatedBy = MoneyValidator.class)
@Target( { METHOD, FIELD, TYPE })
@Retention(RUNTIME)
public @interface Money {
    
    String message() default "please input correct money";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

	@Money
    private String money;
```



4 **Cross-Origin Resource Sharing**

This is a frontend-backend separated project, so our backend needs to handle cross-origin access requests. There are many methods to handle this, and I have chosen to handle it on the Spring Boot backend. 

```
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") 
                .allowCredentials(true) 
                .allowedOrigins("http://localhost:3000")    
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Origin", "Accept", "Content-Type", "Authorization") 
                .allowCredentials(true);
    }
}
```

Since in my local testing environment, the frontend service URL is 'http://localhost:3000/', I have used `.allowedOrigins("http://localhost:3000/")` here. However, I'm not sure if it's the same in your environment, so to ensure it works, I will change `.allowedOrigins("http://localhost:3000/")` in the code I provide to `.allowedOrigins("*").`



5. **Configure logging**

   In a typical project, configuring logging is essential. Typically, the log configuration differs between the production and development environments. Generally, the log level is set lower for development environments and higher for production environments. Therefore, in this project, different log configurations are employed to accommodate the varying requirements of the development and production environments. This means, "We use two different YAML files to configure the log levels for the production and development environments."

```
server:
  port: 8080  
logging:
  level:
    root: info 
    com.cucumber.test: info 
  file:
    name: logs/dev.log 
    
server:
  port: 8080
logging:
  level:
    root: info
    com.cucumber.test: warn
  file:
    name: logs/prod.log    
```


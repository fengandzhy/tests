- **Technology Stack:** The project is developed using Spring Boot for the backend and Thymeleaf with Bootstrap for the frontend. Maven is used as the build and dependency management tool.
- **Objective:** The goal of the project is to create a functioning demo application mainly focus on number transfering to words
- **Unit Testing:** A significant emphasis has been placed on unit testing to ensure the robustness of the application.

**Design and Programming Techniques:**

1. **Spring Boot:** Spring Boot is utilized as the backend framework. It simplifies the setup and configuration of the application and provides various features like auto-configuration and embedded web servers.
2. **Thymeleaf:** Thymeleaf is used for server-side rendering of HTML templates. It allows dynamic content rendering in the frontend and seamless integration with Spring Boot.
3. **Bootstrap:** Bootstrap is employed for frontend development. It offers a responsive and visually appealing user interface, making it easier to create a user-friendly web application.
4. **Unit Testing:** The use of unit testing is a best practice for ensuring the reliability and maintainability of the codebase. These tests validate that individual components or functions work correctly.
5. **MVC Architecture:** Spring Boot follows the Model-View-Controller (MVC) architectural pattern, which separates the application into three interconnected components, facilitating modularity and maintainability.
6. **Dependency Injection:** Spring Boot's dependency injection is utilized to manage dependencies between components, promoting loose coupling and facilitating easier testing and maintenance.
7. **Maven:** Maven is a project management tool that can be used for building and managing dependencies for Java projects.

**install and run:** 1. Enter the project directory from the cmd command line

<img src="C:\Users\zhouhongyuan\AppData\Roaming\Typora\typora-user-images\image-20230901214152618.png" alt="image-20230901214152618" style="zoom:50%;" />

2 run below command and wait for it success 

```
mvn clean package
```

![image-20230901215059025](C:\Users\zhouhongyuan\AppData\Roaming\Typora\typora-user-images\image-20230901215059025.png)

3 come to the target directory 

```
cd target
```

4 run below command 

```
java -jar transfer-test-1.0-SNAPSHOT.jar
```

5 visit http://localhost:8080/ 
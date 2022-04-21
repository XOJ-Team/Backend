# Introduction 
Backend System of XOJ

# Getting Started
<!-- 1.	Installation process -->
## Software dependencies
- OpenJDK 1.8.0
- MySQL 8.0.28
- Elasticsearch 7.6.2
- Redis 5.0.7
- Apache Maven 3.6.3
- Spring Boot 2.4.12
- MyBatis 2.2.0
<!-- 3.	Latest releases
4.	API references -->

# Build and Test
1. Install the version compliant MySQL, Maven, Redis and Elasticsearch.
2. Start MySQL service. Then cd to the Redis folder, run redis-server in the terminal to start Redis service.
3. To deploy the Elasticsearch:
- Add the following configuration to the elasticsearch.yml in config directory of Elasticsearch:
    ```
    cluster.name: xoj-elasticsearch
    node.name: node-1
    cluster.initial master nodes: [”node-1”]
    http.cors.enabled: true
    http.cors.allow-origin: /.*/
    node.master: true
    node.data: true
    network.host: 0.0.0.0
    ```
- Can change the following configuration in the jvm.options in config directory of Elasticsearch if you don’t have a lot of memory:
    ```
    -Xms4G
    -Xmx4G
    ```
- Make a directory named ik in the plugins directory of Elasticsearch, and put ik-analysis in this directory.
- Start the Elasticsearch in bin directory (For linux systems, you need to start with a non-root user).
4. To connect to the services of MySQL and Redis, modify the password in the ```src/main/resources/application.yml```.
5. Find the file ```src/main/java/com/xoj/backend/BackendApplication.java```, run it, then the backend service will run in port 8081.

## Process to deploy Production env:
1. The product environment configuration the process of starting MySQL, Redis
and Elasticsearch services are the same as the development environment.
2. cd to the folder XOJ/Backend.
3. run mvn clean package in the terminal to create jar file.
4. run java -jar target/Backend-0.0.1-SNAPSHOT.jar to run the jar file.

<!-- # Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore) -->


# method of using mybatis-generator plugin
step 1: modify the file(generatorConfig.xml)
1. jdbcConnection: use your local database to connect
2. targetProject: use your local path of project
3. table: modify the table name and domainObjectName

step 2: double-click the maven plugin -- mybatis generator

step 3: find the file in entity, then delete all the construction method, then use Lombok annotation
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
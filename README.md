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

# Description of code directory structure
**Backend/sql**    
store database table structure

**Backend/src/main/java/com/xoj/backend/aspect**   

**Backend/src/main/java/com/xoj/backend/base**   
store response and session class

**Backend/src/main/java/com/xoj/backend/common**      
store some common constants and enumerations   

**Backend/src/main/java/com/xoj/backend/config**     
store basic configurations

**Backend/src/main/java/com/xoj/backend/controller**     
preparing a model Map with data, select a view name and complete the request

**Backend/src/main/java/com/xoj/backend/dto**      
data transfer object passed into the controller layer

**Backend/src/main/java/com/xoj/backend/entity**   
store all the entities

**Backend/src/main/java/com/xoj/backend/exception**  
deal with the exception in business logic

**Backend/src/main/java/com/xoj/backend/mapper**   
mapping the entities to the database tables

**Backend/src/main/java/com/xoj/backend/model**   
object to respond the frontend

**Backend/src/main/java/com/xoj/backend/notation**  
notations to control the authority of users

**Backend/src/main/java/com/xoj/backend/param**

**Backend/src/main/java/com/xoj/backend/service**      
implement the business logic

**Backend/src/main/java/com/xoj/backend/util**  
some tool classes

**Backend/src/main/java/com/xoj/backend/BackendApplication**   
entry of the project

**Backend/src/main/resources**   
xml, yml files
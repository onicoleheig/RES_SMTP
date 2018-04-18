# Teaching-HEIGVD-RES-2018-Labo-SMTP

Project done during RES course in HEIG-VD

Authors :
	Nanchen Lionel
	Nicole Olivier



# Description

This GitHub repo put tu use a source code that simulate a SMTP Client to send prank mail to several receivers. To configure the e-mail addresses, messages and other SMTP connexion informations please follow the Configuration part of this README.



#How to set up mock SMTP server (with Docker)

To use our prank generator with a mock SMTP server running on Docker, you have to install Docker at first :

​	https://www.docker.com/

When your docker is working, you can open the docker terminal and go on the "docker-server" directory (which contains Dockerfile) and excute following commands :

````
$ docker build -t mockserver2 . 
$ docker run -p 2525:2525 -p 8080:8080 mockserver2
````

After that, you can acces to mock server on your favorite browser like that 

​	http://YOUR_DOCKER_IP:8080/ 

​	exemple : http://192.168.99.100:8080/

You can now use the prank mailer that is already set to work.



# How to use our prank mailer

Brief description for configuring and running a prank compaign.

### Config.txt:

Inside the folder, `Teaching-HEIGVD-RES-2018-Labo-SMTP/src/main/resources `, there is a text file config.txt.

The information are arranged as follow:

```
<ip address>
<port>
<group number>
<addresses file name>
<messages file name>
```

### Addresses file:

You can eather modify the `addresses.txt` file or create your own. This text file must be located in the resources folder and be arranged as follow:

```
<first valid email address>
<second valid email address>
<...>
```

### Messages file:

You can eather modify the `messages.txt` file or create your own. This text file must be located in the resources folder and be arranged as follow:

```
Subject:
<your subject>
Message:
<your message>
-.-
<...>
```



# Implementation

Soon...
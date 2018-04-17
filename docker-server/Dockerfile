FROM java:openjdk-8-jre-alpine

ADD src /opt/src

WORKDIR /opt/src

CMD ["java", "-jar", "MockMock.jar", "-p", "2525", "-h", "8080"]

# commandes docker : 
# docker build -t mockserver2 . 
# docker run -p 2525:2525 -p 8080:8080 mockserver2
# dans le navigateur : http://192.168.99.100:8080/
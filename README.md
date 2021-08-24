# udp-server-example

## Contents

``src`` contains the source code for UDP ``EchoServer.java`` and ``EchoClient.java`` 
``out`` contains the compiled java classes ``EchoServer.class`` and ``EchoClient.class`` and ``Dockerfile`` to build the Docker image for the ``EchoServer`` and the YAML files to deploy the Docker image on Oracle Kubernetes Engine (OKE) to be used with a Network Load Balancer (NLB).

## Testing UDP Server locally on port 30007

Run the following commands locally:

```
java EchoServer
```

```
java EchoClient Hello 127.0.0.1 30007
```

You should see output on the client (and on the server output once it's been called):

```
Hello , 5
```

## Build Docker Image of the EchoServer

On the ``out`` dir, run the ``docker build .``command as follows:


```
docker build .
Sending build context to Docker daemon  11.26kB
Step 1/5 : FROM openjdk:16-alpine3.13
 ---> 2aa8569968b8
Step 2/5 : EXPOSE 4445/udp
 ---> Using cache
 ---> e5a2de92376b
Step 3/5 : WORKDIR ./
 ---> Using cache
 ---> 0b4994dd47f7
Step 4/5 : COPY EchoServer.class ./
 ---> 601903d33f04
Step 5/5 : CMD java EchoServer
 ---> Running in 0fd526c24591
Removing intermediate container 0fd526c24591
 ---> 058375df994d
Successfully built 058375df994d
```

The run it with Docker passing the created image ``058375df994d`` and ports ``-p 30007:30007 -p 30007:30007/udp`` from command line:

```
docker run -it -p 30007:30007 -p 30007:30007/udp 058375df994d
```

Then run the client as previously:

```
java EchoClient Hello-from-docker 127.0.0.1 30007
```

And see the output (also on server):

```
Hello-from-docker , 17
```

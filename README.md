# udp-server-example

## Contents

``src`` contains the source code for UDP ``EchoServer.java`` and ``EchoClient.java`` 
``out`` contains the compiled java classes ``EchoServer.class`` and ``EchoClient.class`` and ``Dockerfile`` to build the Docker image for the ``EchoServer`` and the YAML files to deploy the Docker image on Oracle Kubernetes Engine (OKE) to be used with a Network Load Balancer (NLB).

## Testing UDP Server locally

Run the following commands locally and you should see the output:

``
java EchoServer
Hello , 5
``

``
java EchoClient Hello 127.0.0.1 30007
Hello , 5
``


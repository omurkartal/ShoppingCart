# Shopping Cart App
This is a simple shopping-cart application.

## How to dockerize app

 - execute docker-build.sh. Script will create a new docker image. <br>
    $ ./docker-build.sh


 - Container will be run by executing below command. <br>
    $ docker run --network="host" -p 7001:7001 omurka/shopping-cart

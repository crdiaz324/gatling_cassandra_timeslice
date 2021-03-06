# Running the stress test within a docker container

## To run the simulation:  
```
docker run crdiaz324/timeslice_stress run InsertMinuteByTimesliceBlobsSimulation
```
## To list the available simulations:
```
docker run crdiaz324/timeslice_stress listSims
```
## To view your configured options:
```
docker run crdiaz324/timeslice_stress showConf
```


The application.conf file in the root directory contains all of the options that the simulation will use. You can either directly modify the application.conf file with your specific setting and mount it on your container or you can change each option via environment variables.  

## Mounting the application.conf
If you want to mount the application.conf file, first create a directory called conf on the host machine.  Next, place the application.conf file inside that directroy.  Then run the container with the following options:
```
docker run -v `pwd`/conf:/conf crdiaz324/timeslice_stress run InsertMinuteByTimesliceBlobsSimulation
```

## Passing in opitons via environment
The other option is to use environment variables to modify those options:
```
docker run -e JAVA_OPTS="-Dcassandra.hosts=192.168.99.205" crdiaz324/timeslice_stress run InsertMinuteByTimesliceBlobsSimulation
```

To verify that your configured options are set correctly, you can run:
```
docker run crdiaz324/timeslice_stress showConf
```

## Options
Below is an explanation of the options that you will most likely have to modify for your specific environment:

|Option| Description|
|---|---|
|cassandra.hosts|  This is the ip address (or addresses) of the inital contact point for establishing the connection to the cluster|
|cassandra.dcName|  The name of the data center|
|cassandra.auth.username|  If you have authentication enabled, enter the username used to authenticate|
|cassandra.auth.password|  The password for the user|
|simulations.insertMinuteByTimesliceSim. InsertMinuteByTimeSliceBlobsScenario.usersConstantCnt|  How many simultanous users to run on the cluster|
|simulations.insertMinuteByTimesliceSim. InsertMinuteByTimeSliceBlobsScenario.usersConstantTime|  How long to run the simulation for.  This parameter must be a int followed by one of s (seconds), m (minutes), h (hours)|
|simulations.insertMinuteByTimesliceSim. InsertMinuteByTimeSliceBlobsScenario.usersRampTime| How long to take to ramp up to usersConstantCnt.  This parameter must be a int followed by one of s (seconds), m (minutes), h (hours)|


## To rebuild the docker container

1 - Clone this repo:  
```
git clone https://github.com/crdiaz324/gatling_cassandra_timeslice.git
```

2 - checkout the nr-docker branch:  
```
git checkout nr-docker
```

3 - cd into gatling_cassandra_stress and Build the docker image:  
```
> cd gatling_cassandra_timeslice && docker build -t timeslice_stress .
```


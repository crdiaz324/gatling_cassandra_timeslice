 # Gatling Cassandra Stress Simulation
The goal of the repo is to provide a tailored version of [Gatling DSE Stress](https://github.com/datastax/gatling-dse-stress/). 

# Using the Examples

## Building
To build a jar run `sbt clean assemblyLauncher`.  The compiled jar will be found in `target/scala-2.12/gatling-dse-simcatalog-assembly-1.3.0-SNAPSHOT.jar` and the executable app will be at `target/gatling-dse-sims`

## Running a Simulation
There is a custom shell script to kick off the simulations.  This script simply sets up the environment and allocates heap to the JVM that will run the gatling simulation.  This will need to be modified based on the amount of memory available to the server running the simulations.  

The exacutable app can be rebuilt by running `sbt clean compile` then run the app with the path or name of wanted sim name `./startsim.sh run {SimName}`.  

Example: `./startsim.sh run sims.timeslice_store.cql.InsertMinuteByTimesliceBlobsSimulation`

## Available Sims
To view the available sims, simply run: `./startsim.sh listSims`


## Configuration
Inside the root folder of this project, you can find a file called `application.conf`.  This file will need to be modified to include things like:

* Initial host ip address
* DC Name
* Cluster Name
* Keyspace name
* How long to run the simulation for (usersConstantTime)
* How many users to load load the cluster with (usersConstantCnt)

Please review this file and adjust it accordingly before running the simulations.

See: [Gatling DSE Stress Wiki](https://github.com/datastax/gatling-dse-stress/wiki) for more specific docs for usage.

# Requirements
- Java 1.8+
- SBT

Running `sbt assemblyLauncher` will download all of the needed libraries including Scala to your local machine.


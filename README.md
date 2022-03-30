URL Hash Service
================

This Java spring-boot application has the following capabilities:

* Accept a long URL as input and return a short hash value generated from the URL
* Accept a hash value as input and return the long URL corresponding to it

This application uses:

* CRC32 algorithm for hashing
* In-memory H2 database for persistence
* Spring scheduler to remove older entries from the database

## Generate Short Url Service

This service accepts a URL as input and returns a CRC32 hash value. The hash value and long URL are also saved in an H2 database (in-memory).

### Usage

Pass the URL as a query parameter to the /short endpoint. The parameter name to be used is 'url'. 

For example:

http://localhost:50000/short?url=https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json

Sample requests are available in test/resources/URL Hahser.postman_collection.json.

### Response

* Successful response - Returns the short hash value with an HTTP response code of 201
* Missing or invalid URL - Returns an error message with an HTTP response code of 400
* Duplicate URL - Returns an error message with an HTTP response code of 400

## Retrieve Long Url Service

This service accepts a hashed value as input and returns the corresponding long URL value. The long URL is retrieved from the H2 database (in-memory).

### Usage

Pass the hashed value as a query parameter to the /long endpoint. The parameter name to be used is 'tiny'.

For example:

http://localhost:50000/long?tiny=c10dd26f

Sample requests are available in test/resources/URL Hahser.postman_collection.json.

### Response

* Successful response - Returns the long URL value with an HTTP response code of 200
* Missing hashed value - Returns an error message with an HTTP response code of 400
* No matching URL - Returns an error message with an HTTP response code of 404

## Remove stale URL entries from DB - Scheduled Task

This scheduled task runs every second and deletes any H2 records that are older than 30 minutes. The retention time is configurable via scheduled.task.delete-time in the application.yml file.

## Build and Deployment

The pom.xml file includes a profile named 'docker-image'. This profile can be used to build and push a docker container image named 'url-hasher' to the local Docker daemon. If needed, the profile can be modified to push the image to an online container registry like Docker Hub, GCP Container Registry etc.

Below command generates the image as configured in the profile.

mvn clean install -Pdocker-image

Once the image is available in the registry, it can be run and exposed on port 50000 using below command.

docker run -it -p 50000:50000 url-hasher


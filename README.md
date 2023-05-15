# Review Service

Review Service connects to a MongoDB and exposes CRUD operations via a simple REST API.


It includes:
- **REST API endpoint** `/api/review`
  - Integration test suite with the whole application context
  - Exception handling
- **Security layer**
  - for the sake of the example, just **basic auth for users**
  - **service authentication** for the client (product-service)
- **MongoDB** as a datastore
  - **Seeded** **data** for a few products (see `seed.json` and `seed.sh` in folder `mongo`)
- **Caching** (because performance was a NFR)
  - **Distributed** **caching** with embedded **Hazelcast** cluster (`highly available`, `scalable`)
- **Dockerized**
  - `Dockerfile` for building the image
  - `docker-compose.yml` (both `review-service` & `mongo`)
  - `docker-run.sh` - a shell script for building everything and running it

## Running it locally
### Quick Way - shell script
There is a bash script in the root of the project that will package the app and build an image for it. 

Use it by running:

    ./docker-run.sh


### Manual steps
For running it manually though, check the script for the exact steps, but roughly these steps need to be done:

- `maven package` - create a jar
- build an image using the Dockerfile `docker build -t review-service .`
- use that image in `docker-compose.yml`
- create a docker network and configure both containers to use it
- run `docker-compose up`

```
NOTE:
Script isn't tested on Windows or Mac, only on Linux, but should work out of the box.
```
---

### Requirements Notes
#### Functional

- [x] a. implement CRUD operations for the resource:
- [x] b. /review/{product_id} , (e.g. AB1234), and the response is a JSON with
  the following data: Product ID, Average Review Score, Number of
  Reviews.
- [x] c. In order to protect the service and prohibit data tampering, authentication
  is needed to protect the write operations.
- [x] d. Choose any datastore for persisting the data – that can be easily deployed
  or installed with the application. The datastore should contain seeded data
  for a few products


#### Non-Functional
- [x] dockerized - _yes, Dockerfile,_ _docker-compose.yml_
- [x] Reusability - _yes_
- [x] secure - _yes, spring boot security with sample basic auth_
- [x] tested - _yes, integration tests_
- [x] cost (db operations etc.) - _added hazelcast caching layer_

---
### Stack used
- **Java** 17
- **Spring Boot** 2.7.11
- **MongoDB** 4.4.0
- **Docker version** 20.10.22, build 3a2c30b
- **docker-compose** version 1.29.2, build unknown
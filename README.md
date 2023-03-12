# test-containers-templates

This repository will demonstrate how to run test-containers such as messagers, database or other custome services to test your software.               
[doc](https://www.testcontainers.org/)

# Introduction

This repository contains a simplified implementation of an end-to-end system with four modules: order-service, notification-service, delivery-service, and e2e-test. The purpose of the code is to demonstrate the use of test containers in end-to-end testing.

# Overview

The order-service receives an order ID and approves it by assigning a driver ID. To assign the driver, the order-service talks to
the delivery-service. The delivery-service fetches a driver and assigns them to the order ID. Once a driver is assigned, an email is sent to the driver.

The services communicate via HTTP and use Consul as a discovery service with OpenFeign. 
When a driver is assigned, the order-service publishes an event via RabbitMQ. The notification-service listens to the event and sends an email
to the assigned driver. Note that the email is a simple log since we are not focusing on the email implementation.

# Test Containers

The most important part of this code is the use of test containers. The e2e-test module runs test containers that include RabbitMQ, Consul, Postgres, 
and the three domain services (order-service, notification-service, and delivery-service) in containers.

Test containers are lightweight, stand-alone containers that are spun up and torn down during testing.
They are useful for testing systems that have external dependencies, such as databases, message brokers, 
or third-party services. Test containers help ensure that the test environment is identical to the production environment, 
which increases the reliability of the tests.

In our case, the e2e-test module uses test containers to test the system end-to-end. The test sends an actual request to the 
containerized services using Rest Assured and asserts on the responses. The use of test containers ensures that the test environment 
is identical to the production environment and that the test results are reliable.

# Running the Tests
### Requirements
Docker docker daemon

```
mvn clean install 
```
Then run the tests under `e2e-test`

This command will spin up the test containers, run the tests, and tear down the containers. 
Note that this command may take several minutes to run, as it needs to download the necessary Docker images and start the containers.

# Conclusion
The use of test containers in end-to-end testing helps ensure that the test environment is identical to the 
production environment, which increases the reliability of the tests. In our simplified example, we demonstrated how test containers can be used to test a system with external dependencies.
By running our tests in containers, 
we can be confident that our system will work as expected in production.

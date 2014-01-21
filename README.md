spring-rest
===========

Samples of RESTful web services using Spring MVC

- <b>rest_test</b>: Implementation of RESTful web services using Spring MVC. The application controller contains several CRUD operations that are tested using the restTemplate class.

  Blog post related to this project:
  http://xpadro.blogspot.com.es/2013/02/create-and-test-rest-services-with.html


- <b>rest-converters</b>: This project shows how message converters work when using the RestTemplate class. Depending on which Object is returned by the controller, a different converter will be applied to the response body.

  Blog post related to this project:
  http://xpadro.blogspot.com.es/2013/02/accessing-restful-services-http-message.html


- <b>rest-controlleradvice</b>: Shows how to centralize exception handling from different controllers using the @ControllerAdvice annotation.

  Blog post related to this project:
  http://xpadro.blogspot.com.es/2013/03/centralize-validation-and-exception.html


- <b>rest-subresources</b>: This is a JAX-RS project that uses a subresource locator which decide at runtime what subresource will be returned.

  Blog post related to this project:
  http://xpadro.blogspot.com.es/2013/05/handling-different-subresources-with.html


- <b>spring-rest-api-v32</b>: A Spring MVC RESTful web application. This app implements a REST API which accesses a MongoDB database in order to handle data from TV series. It also contains examples on how to implement integration tests (mockMvc) and functional tests (restTemplate). This project has been implemented in Spring 3.2.3 version and has been used as a base to implement the <i>spring-rest-api-v4</i> project (also in this repository).

  Blog post related to this project: N/A


- <b>spring-rest-api-v4</b>: This project is the result of taking <i>spring-rest-api-v32</i> (also in this repository) and upgrading it to version 4.0.0.RELEASE. It also contains some of the improvements included in this version of Spring, as commented in the blog entry.

  Blog post related to this project: 
  http://xpadro.blogspot.com.es/2014/01/migrating-spring-mvc-restful-web.html

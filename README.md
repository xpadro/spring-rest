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

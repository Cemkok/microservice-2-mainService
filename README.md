# Main microservice

This project was made with **Spring Boot** as a **Microservice**. 
 
 - Project version,                               **Java  8**.
 - package management tool,                      **Maven**,

 - for database access and ORM,                  **JPA Hibernate** 

 - for security,  								**Spring Security** 

 - for authorization, 							**JWT**

 - for communication beetwen microservices,      **Retrofit**


This microservice acts as a gateway, It basically has two functions as user management and API management. Therefore requests to other microservices go through this microservice first.


##  Project details

 <p> In this project, n-tier architecture was used. Inter-layer communication was carried out with the interface of each layer. </p>
 

- **UI layer (Controller)**

 Controller processes requests from the client and returns responses. I injected the interface of the business layer using the dependecy injection pattern and in this way I provided the communication between the two layers.I wrapped all the methods with ResponseEntity. Incoming requests are routed to the business layer, and responses come from the business layer.I added the @RestController annotation to make this layer a restful service.I added @RequestMapping annotation to get incoming requests a common pre-path.I assigned custom routes based on the type of incoming request. According to the type of incoming request, I added @GetMapping @PostMapping @DeleteMapping to the methods and assigned special routes to them.I added a global exception handler to catch validation exceptions. 
 

 
 - **Business layer**
 
 It is the layer where other operations such as logical, arithmetic operations, data manipulation, and control take place. In addition, this layer provides communication between the user interface (controller) and the data access layer.It sends the requests from the controller to the data access layer and returns if there is a response.This layer has a manager class and an interface. It uses the interface to enable communication and as a template.I injected the interface of the data access layer, and PasswordEncoder which uses Bycryp hashing algorithm  using the dependecy injection pattern and in this way I provided the communication between the two layers and I declared it to spring with @Autowired annotation to create a bean.I added an @Service annotation to the manager class to indicate that it is a service class.I wrapped the methods with the result object or subclasses, which is a generic class that I created.Result object; returns data, success status, information message. Customized subclasses of this object are available. All methods can be tracked by log.
 
 
 -  **Data Access layer**
 
  It provides access to the database. It does this by extending the data access layer's existing interface, UserDao, with   JpaRepository .
 		
 > **JpaRepository** also provides us with basic crud operations as well as pagination and 	sorting 	methods.
 
 
 
 -  **Entity layer**
 
 It is available in all layers as the program manipulates the entity class. In this layer, I created a class that contains user information as an entity class. An entity represents an object and a table in a database in Object Oriented Programming. The properties of the entity represent the database columns. I used @Entity to declare it as an entity class, @Table to represent the table, and @Column for columns.I used the @Id annotation to declare Spring Data Jpa that the id property is a database id, and the @GeneratedValue annotation to specify the increment format. I used Lombok's @Data annotation to define constructor getter setter hashcode equals.
  The properties of the entity object may not be given directly to the client, or certain properties may be requested from the client. For example ; Properties such as "id" if the client enters a property, and "password" if the client requests properties should be removed from the object.
 These constrained objects are called **DTO** (data transfer objects). For these objects I created a dtos package for the response data in the entity package and a request package for the request data.
 These objects are mapped in the business layer after passing through the controller. I implemented this mapping with the **ModelMapper** library.
  	> In this project, I provided the Object and database relationship with **Spring Data JPA**.
  	
  	
 - **Core layer**
 
  The core layer is usually the code layer that we can use in common in most projects. This 
 
 layer usually provides us with the **DRY** principle and **clean code**, i.e. simpler code. 
 In this layer, I created the **results** package to increase the readability of the API outputs, the **mapping** package for the modelMapper library that does the object-dto-object conversion, the constants package for fixed messages and logging

****************************************************************************************************************************************************************************************************************************************************************************************
### Security

****************************************************************************************************************************************************************************************************************************************************************************************

This is the web security configuration that gives us transaction security.  The spring security library was used to secure the Spring application. Spring security stands between client and application, providing the possibility to configure which data and operations are served to which users.I created security package for security configuration.
 
  <br>
 I created the SecurityConfig class in the security suite and extended it with the WebSecurityConfigurerAdapter class provided by Spring Security. I used @EnableWebSeucirty to enable web security and @Configuration annotation to define bean objects. and I overrode the config method that takes parameters of type **AuthenticationManagerBuilder** to identify this user. 

 <br>
 
 In this method we can define the user and its role and also encode the password. I encoded the password in type  
 **BycrypPasswordEncoder**.  I also configured the authorization using the HttpSecurity object with the configuration method that takes HttpSecurity as a parameter. All requests require security and I implemented this by calling the super.configure() method with it set by default, I disabled csrf, , authorized the requests, set http sessions. I have configured cors configuration using WebMvcConfigurer object.
 	
 <br>
 The default user service in Spring Security is the UserDetailsService interface. I implemented this interface by creating the CustomerUserDetailsService concrete class. I added @Service annotation so that it can be a spring service.I overrode the loadUserByUsername method to authorize by user name, injected the user service interface using the dependency injection pattern to access user information, declared spring with @Autowired annotation for this interface to create a bean object. Spring provides UserDetails interface that contains user details, since this interface is abstract, I created a concrete class named UserPrincipal and implemented UserDetails.
  
  <br>
  
************************************************************************************************************************************************************

### JSON Web Token

****************************************************************************************************************************************************************************************************************************************************************************************
 
 > In this package, I created the JwtProvider class and its interface. I used @Component annotation to declare that there is a spring bean.
 I assigned the public and private keys that I defined in the application.properties file to the constructor variable and the expiration date to the JwtProvider field with @Value annotation.I declared that I will create an RSA algorithm by creating a KeyFactory method. I created methods for token generation, client validation, token validation, extracting token from Http header.
 
 > A special filter is required for Spring security, I created JwtAuthorizationFilter class for it. A database call is required to get the principal or authenticated user so I extended the class with OncePerRequestFilter. OncePerRequestFilter's doFilterInternal method filters the unfiltered request, ignoring the filtered ones.

****************************************************************************************************************************************************************************************************************************************************************************************

### Communication between microservices.

****************************************************************************************************************************************************************************************************************************************************************************************

- **Retrofit** 

 > Retrofit is a wrapper library for converting interfaces to okHttp calls. When the interface calls it, it returns the corresponding call object, but if we don't call the enqueue or execute, it will not make a request. To summarize, the connection between the microservices and the gateway is provided by using a retrofit on the gateway. I used Gson converter to convert json object to Pojo classes.I added the Retrofit properties to the application.properties file. A default request is created to connect the microservices. 
 
 > I created the RetrofitConfiguration class inside the request package and added the @Configuration annotation to be able to define the spring beans in the class. I assigned the time-out period to the field with the @value annotation from the application.properties file.
 
 >I created OkHttpClient.Builder method to assign read, write, handshake values. Then I created your OkHttpClient method to create a secure http client.Finally, I created the Retrofit.builder method to create a wrapper retrofit structure.
 
 > I created the IExternalServiceRequest interface inside the request package to define the retrofit endpoints. Call methods created in the IExternalServiceRequest interface are called from the service class and send to the controller in the service class.
 
 
 
 - **Gson**
 
 I used Gson library to serialize and deserialize json data. I created GsonHttpMessageConfig class inside the config package. I added @Configuration annotation to define Spring beans. I defined the GsonBuilder method for date serialization and deserialization. I created a Gson object to use from GsonBuilder. Finally, I added a Gson object as a parameter to the GsonMessageConverter.
 


 
 
 
```mermaid

flowchart RL
 subgraph SECURITY
 direction LR
subgraph  sec [N-Tier]
 db[(Database)]--->|"Java Persistance API -> JPA" |dao["Data Access Layer (DAO)"]  ---> |Interface CustomerDao|service["Business Layer (Service)"] ---> |Interface CustomerService|UI["UI Layer (Controller)"] 
    dao--->|"Java Persistance API -> JPA" |db
    service--->|Interface CustomerDao|dao
    UI--->|Interface CustomerService|service
    
    end


subgraph util [Core Layer]
core{"Results
Constants
Mapping"}

end
subgraph entity[Entity Layer]
ent[/ Customer \]


end
end

   
 core -.-> dao
core -.->service
core -.->UI
ent -.->service
ent -.->UI
 ent -.-> dao



    
```



 	
 
***********************************************************************************************


# What technologies were used?

***********************************************************************************************

  <p align="start">
  <img src="https://bilisim.io/wp-content/uploads/2019/11/spring-boot.jpg" width="400" 	height="200">
   </p>	
<br>

- **Why did I use Spring Boot ?**
<br>

 > Java Spring Boot (Spring Boot) is a tool that makes developing web application and microservices with Spring Framework faster and easier through three core capabilities: Autoconfiguration. An opinionated approach to configuration. The ability to create standalone applications.
   <br>
   
**************************************************************************************
 <br>
  <p align="start">
  <img src="https://miro.medium.com/max/743/1*M7gYkSDwC5ny9SxxBwzozA.png" width="400" 	height="200">
   </p>	
  
- **Why did I use Apache Maven ?** 
	> Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.
  <br>
  
**************************************************************************************
<br>
<p align="start">
  <img src="https://miro.medium.com/max/1000/1*CsWbbOxOG3QrlHQoE4ApVQ.png" width="400" 	height="200">
   </p>	
  
  

- **Why did I use Spring Security ?** 
 
 > Spring Security is a powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications.

  > Spring Security is a framework that focuses on providing both authentication and authorization to Java applications. Like all Spring projects, the real power of Spring Security is found in how easily it can be extended to meet custom requirements

  **Features**
- Comprehensive and extensible support for both Authentication and Authorization

- Protection against attacks like session fixation, clickjacking, cross site request forgery, etc

- Servlet API integration

 - Optional integration with Spring Web MVC
    > Much more…
  <br>
  
**************************************************************************************

 <br>
<p align="start">
  <img src="http://jwt.io/img/logo-asset.svg" width="400" 	height="200">
   </p>	
  
  

- **Why did I use JWT ?** 
 
 
JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret (with the HMAC algorithm) or a public/private key pair using RSA or ECDSA.

Although JWTs can be encrypted to also provide secrecy between parties, we will focus on signed tokens. Signed tokens can verify the integrity of the claims contained within it, while encrypted tokens hide those claims from other parties. When tokens are signed using public/private key pairs, the signature also certifies that only the party holding the private key is the one that signed it.
  

> - **Authorization**: This is the most common scenario for using JWT. Once the user is logged in, each subsequent request will include the JWT, allowing the user to access routes, services, and resources that are permitted with that token. Single Sign On is a feature that widely uses JWT nowadays, because of its small overhead and its ability to be easily used across different domains.

> - **Information Exchange**: JSON Web Tokens are a good way of securely transmitting information between parties. Because JWTs can be signed—for example, using public/private key pairs—you can be sure the senders are who they say they are. Additionally, as the signature is calculated using the header and the payload, you can also verify that the content hasn't been tampered with.
 
  <br>
  
************************************************************************************** 
  



<br>

 <p align="start">
  
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTCeAeYeCtoCahoZmQmr5roffNlXlgFhBw9aA&usqp=CAU" width="400" height="200" >
   </p>
  
  - **Why did I use Gson ?** 
  
 > Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. Gson can work with arbitrary Java objects including pre-existing objects that you do not have source-code of.

 > There are a few open-source projects that can convert Java objects to JSON. However, most of them require that you place Java annotations in your classes; something that you can not do if you do not have access to the source-code. Most also do not fully support the use of Java Generics. Gson considers both of these as very important design goals.
   
   
******************************************************************************************	
<p align="start">
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-0LWP-kGB-BLfv9SIpeB4HO22fzXvNN25ug&usqp=CAU" width="400" 	height="200">
   </p>	
<br>   
 	
 - **Why did I use Spring Data JPA ?** 
 
 > Speaking precisely, Spring Data JPA is an add-on for **JPA**. It provides a framework that works with JPA and provides a complete abstraction over the **Data Access Layer**.

 > **Spring Data JPA** brings in the concept of JPA Repositories, a set of Interfaces that defines query methods. The Repository and Entity Bean represent the DAO layer in the application. No need to write native queries anymore. Sometimes we need to write queries or part of queries, but those are **JPQL** queries and not native database queries. 

 > **DAO Abstraction (Repositories)**
With Spring Data JPA, we don’t have to write a Data Access Layer or write any SQL statement. Based on JPA specification, the underlying JPA implementation enables the Entity objects and their metadata mapping. It also allows an entity manager responsible for persisting and retrieving entities from the database.

 > The Spring Data JPA defines Repository interfaces with methods for storing and finding the entity beans from the database. While the interface only defines query methods, Spring at runtime provides proxy implementations for the specified interfaces. 
************************************************************************************

 <p align="start">
  <img src="https://upload.wikimedia.org/wikipedia/commons/6/6b/SLF4J_Logo.jpg" width="400" height="200" >
   </p>
   
   - **Why did I use SLF4J ?** 
   
 >  The Simple Logging Facade for Java (SLF4J) serves as a simple facade or abstraction for various logging frameworks (e.g. java.util.logging, logback, log4j) allowing the end user to plug in the desired logging framework at deployment time.
      <br>
      

<p align="start">
  <img src="https://www.konakart.com/wp-content/uploads/2014/11/javadoc.png" width="400" height="200" >
   </p>
   
   - **Why did I use JavaDoc ?** 
   
 > JavaDoc tool is a document generator tool in Java programming language for generating standard documentation in HTML format. It generates API documentation. It parses the declarations ad documentation in a set of source file describing classes, methods, constructors, and fields.
   <br>
   
************************************************************************************************
    
    
  <p align="start">
  <img src="https://i0.wp.com/ngdeveloper.com/wp-content/uploads/2018/05/modelmapper.png?fit=781%2C253&ssl=1" width="400" height="200" >
   </p>
   
   - **Why did I use ModelMapper ?** 
   
   > The goal of ModelMapper is to make object mapping easy, by automatically determining how one object model maps to another, based on conventions, in the same way that a human would - while providing a simple, refactoring-safe API for handling specific use cases.    
       <br>
       
******************************************************************************************
   
   <p align="start">
  <img src="https://miro.medium.com/max/1400/1*C_u3RtbZnYzRI10IUWbPfg.png" width="400" height="200" >
   </p>
   
      
   - **Why did I use Swagger ?**
   
  > Swagger allows you to describe the structure of your APIs so that machines can read them. The ability of APIs to describe their own structure is the root of all awesomeness in Swagger.           
  > Why is it so great? Well, by reading your API’s structure, we can automatically build beautiful and interactive API documentation.
  > We can also automatically generate client libraries for your API in many languages and explore other possibilities like automated testing. 
  > Swagger does this by asking your API to return a YAML or JSON that contains a detailed description of your entire API. 
  > This file is essentially a resource listing of your API which adheres to OpenAPI Specification. The specification asks you to include information like:
What are all the operations that your API supports?
What are your API’s parameters and what does it return?
Does your API need some authorization?
And even fun things like terms, contact information and license to use the API.     
 
 <br>
 
******************************************************************************************
               
   <p align="start">
  <img src="https://miro.medium.com/proxy/1*WMf1XcyKU98dOMlNnn-Agg.png" width="400" height="200" >
   </p>
   
    
  - **Why did I use Retrofit ?** 
     
      Retrofit is a type-safe HTTP client for Java and Android which was developed by Square. With Retrofit, all you need to do is declare a Java interface to represent your API. Then, you can pass the API configuration to Retrofit, and you will get back a Java class implementation of your interface.One cool feature introduced by Retrofit is that it allows you to modify your API request through annotation on methods in your interface.

Retrofit allows you to make both synchronous and asynchronous request to APIs.
       
******************************************************************************************
   

	
 <p align="start">
  <img src="https://cdn.wmaraci.com/nedir/PostgreSQL.png" width="400" height="200" >
   </p>
   
   
   
   - **Why did I use PostgreSQL ?**  
   
   

 > PostgreSQL is an advanced, enterprise-class, and open-source relational database system. PostgreSQL supports both SQL (relational) and JSON (non-relational) querying.

 > PostgreSQL is a highly stable database backed by more than 20 years of development by the open-source community.

 > PostgreSQL is used as a primary database for many web applications as well as mobile and analytics applications.   
 
  

 
           
*********************************************************************************************
******************************************************************************************
#Endpoints   

*******************************************************************************
*******************************************************************************    

##### 

 ## **Sign-up** ##
 
 
*******************************************************************************

- **Request**

 ` localhost:5555/api/authentication/sign-up `

POST /api/authentication/sign-up HTTP/1.1

Host: localhost:5555

Content-Type: application/json

Content-Length: 74


{

    "username":"test4",
    
    "password":"test4",
    
    "name":"cem"

}

--------------------------------------------------------
   
  - **Response : SuccesDataResult Object** 
  

	{
	
    "data": {
    
        "id": 4,
        
        "username": "test4",
        
        "password": "$2a$10$U4AG/.f2R0s05Qq3Ixmv9e9H.wsFqG3uX7j.lny4IUFU.2tM7ZKua",
        
        "name": "cem",
        
        "createTime": "2022-07-25T11:16:16.7421279"
        
    },
    
    "success": true,
    
    "message": "a.new.user.added"
}
  
*********************************************************************************
 
  ## **Sign-in** ##
 
 
*******************************************************************************

- **Request**



	
 
 ` localhost:5555/api/authentication/sign-in `


POST /api/authentication/sign-in HTTP/1.1

Host: localhost:5555

Authorization: Basic MzJCaXQ6MzJCaXQ=

Content-Type: application/json

Content-Length: 53



{
    "username":"test4",
    "password":"test4"
}

--------------------------------------------------------  

 - **Response : TOKEN** 

eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0NCIsInVzZXJJZCI6NCwicm9sZXMiOiJVU0VSIiwiZXhwIjoxNjU4ODIzNTQ0fQ.i75KmY0z2P3M_EEHaGgYCArtDgXye0KCnZNiqDLBFLUEjU5ZeSNYEZcAHii7NEwdxnoFLhEEVKAvjto-SDUH5n0CVeA3roRw8gH6pjJ-UfW-ZJ5IGr8Jf5Q9imyOiE5NC2TdAy-uLEKc_5TkFgAtTP4k7BBTZpLnXleYR0AGB_NMVt6b8HjO36G7Jnt7AKupeeVxV5iQT06AIkCZE_ZRcEq-1w65kb2zoh1KBAOBgjZkRViSFOisTVcnzwXOHq2s3wxCmPmvXc4P_rYnMd9KxbL76Tmz2UXF9PzDZilEkHeDbVe-YVtvHVAECs6H5IS2zNBYp0H5gH8IQl3-L2HPfQ

*********************************************************************************
 
  ## **add customer** ##
 
 
*******************************************************************************

- **Request**


	
	
 
 ` localhost:5555/gateway/customer/add `


POST /gateway/customer/add HTTP/1.1
Host: localhost:5555
Authorization: Bearer eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0NCIsInVzZXJJZCI6NCwicm9sZXMiOiJVU0VSIiwiZXhwIjoxNjU4ODIzNTQ0fQ.i75KmY0z2P3M_EEHaGgYCArtDgXye0KCnZNiqDLBFLUEjU5ZeSNYEZcAHii7NEwdxnoFLhEEVKAvjto-SDUH5n0CVeA3roRw8gH6pjJ-UfW-ZJ5IGr8Jf5Q9imyOiE5NC2TdAy-uLEKc_5TkFgAtTP4k7BBTZpLnXleYR0AGB_NMVt6b8HjO36G7Jnt7AKupeeVxV5iQT06AIkCZE_ZRcEq-1w65kb2zoh1KBAOBgjZkRViSFOisTVcnzwXOHq2s3wxCmPmvXc4P_rYnMd9KxbL76Tmz2UXF9PzDZilEkHeDbVe-YVtvHVAECs6H5IS2zNBYp0H5gH8IQl3-L2HPfQ
Content-Type: application/json
Content-Length: 56

{
    "customerName":"Cem",
    "companyName":"THY"
}

--------------------------------------------------------  

 - **Response : SuccessDataResult** 

{
    
    "success": true,
    
    "message": "customer.added",
    
    "data": {
    
        "id": 10,
        
        "customerName": "Cem",
        
        "companyName": "THY",
        
        "createTime": "2022-07-25T11:32:13.8113855"
        
    }
    
}

*********************************************************************************
 
  ## **delete customer** ##
 
 
*******************************************************************************

- **Request**


	
	
 
 ` localhost:5555/gateway/customer/deleteCustomerById/10 `

DELETE /gateway/customer/deleteCustomerById/10 HTTP/1.1

Host: localhost:5555

Authorization: Bearer 

eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0NCIsInVzZXJJZCI6NCwicm9sZXMiOiJVU0VSIiwiZXhwIjoxNjU4ODIzNTQ0fQ.i75KmY0z2P3M_EEHaGgYCArtDgXye0KCnZNiqD

BFLUEjU5ZeSNYEZcAHii7NEwdxnoFLhEEVKAvjto-SDUH5n0CVeA3roRw8gH6pjJ-UfW-ZJ5IGr8Jf5Q9imyOiE5NC2TdAy-

uLEKc_5TkFgAtTP4k7BBTZpLnXleYR0AGB_NMVt6b8HjO36G7Jnt7AKupeeVxV5iQT06AIkCZE_ZRcEq-

1w65kb2zoh1KBAOBgjZkRViSFOisTVcnzwXOHq2s3wxCmPmvXc4P_rYnMd9KxbL76Tmz2UXF9PzDZilEkHeDbVe-YVtvHVAECs6H5IS2zNBYp0H5gH8IQl3-L2HPfQ


--------------------------------------------------------  

 - **Response : SuccessResult** 


   {
   
    "success": true,
    
    "message": "customer.deleted.by.id : 10"
    
}

*********************************************************************************
 
  ## **get all customer  by pagination** ##
 
 
*******************************************************************************

- **Request**


	
	
 
 ` localhost:5555/gateway/customer/getAll/1/5 `

GET /gateway/customer/getAll/1/5 

HTTP/1.1

Host: localhost:5555

Authorization: Bearer 

eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0NCIsInVzZXJJZCI6NCwicm9sZXMiOiJVU0VSIiwiZXhwIjoxNjU4ODIzNTQ0fQ.i75KmY0z2P3M_EEHaGgYCArtDgXye0KCnZNiqDLB

FLUEjU5ZeSNYEZcAHii7NEwdxnoFLhEEVKAvjto-SDUH5n0CVeA3roRw8gH6pjJ-UfW-ZJ5IGr8Jf5Q9imyOiE5NC2TdAy-

uLEKc_5TkFgAtTP4k7BBTZpLnXleYR0AGB_NMVt6b8HjO36G7Jnt7AKupeeVxV5iQT06AIkCZE_ZRcEq-

1w65kb2zoh1KBAOBgjZkRViSFOisTVcnzwXOHq2s3wxCmPmvXc4P_rYnMd9KxbL76Tmz2UXF9PzDZilEkHeDbVe-YVtvHVAECs6H5IS2zNBYp0H5gH8IQl3-L2HPfQ

--------------------------------------------------------  

 - **Response : SuccessResult** 


   {
   
    "success": true,
    
    "message": "All customers have listed",
    
    "data": [
    
        {
        
            "id": 1,
            
            "customerName": "Cem",
            
            "companyName": "32Bit"
            
        },
        
        {
        
            "id": 2,
            
            "customerName": "Cem",
            
            "companyName": "32Bit"
            
        },
        
        {
        
            "id": 3,
            
            "customerName": "Cem",
            
            "companyName": "Oracle"
            
        },
        
        {
            "id": 4,
            
            "customerName": "Cem",
            
            "companyName": "Oracle"
            
        },
        
        {
        
            "id": 5,
            
            "customerName": "Cem",
            
            "companyName": "Microsoft"
            
        }
    ]
}

*********************************************************************************
 
  ## **get customer by id** ##
 
 
*******************************************************************************

- **Request**



 ` localhost:5555/gateway/customer/getByCustomerId/5 `

GET /gateway/customer/getByCustomerId/5 

HTTP/1.1

Host: localhost:5555

Authorization: Bearer 

eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0NCIsInVzZXJJZCI6NCwicm9sZXMiOiJVU0VSIiwiZXhwIjoxNjU4ODIzNTQ0fQ.i75KmY0z2P3M_EEHaGgYCArtDgXye0KCnZNiqDLB

FLUEjU5ZeSNYEZcAHii7NEwdxnoFLhEEVKAvjto-SDUH5n0CVeA3roRw8gH6pjJ-UfW-ZJ5IGr8Jf5Q9imyOiE5NC2TdAy-

uLEKc_5TkFgAtTP4k7BBTZpLnXleYR0AGB_NMVt6b8HjO36G7Jnt7AKupeeVxV5iQT06AIkCZE_ZRcEq-

1w65kb2zoh1KBAOBgjZkRViSFOisTVcnzwXOHq2s3wxCmPmvXc4P_rYnMd9KxbL76Tmz2UXF9PzDZilEkHeDbVe-YVtvHVAECs6H5IS2zNBYp0H5gH8IQl3-L2HPfQ

--------------------------------------------------------  

 - **Response : SuccessDataResult** 



  {
  
    "success": true,
    
    "message": "customer.found.by.id",
    
    "data": {
    
        "id": 5,
        "customerName": "Cem",
        
        "companyName": "Microsoft",
        
        "createTime": "2022-07-17T01:24:06.895497"
        
    }
    
}


*********************************************************************************
 
  ## **filter by company name** ##
 
 
*******************************************************************************

- **Request**



 ` localhost:5555/gateway/customer/THY `

GET /gateway/customer/THY

 HTTP/1.1

Host: localhost:5555

Authorization: Bearer 


eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0NCIsInVzZXJJZCI6NCwicm9sZXMiOiJVU0VSIiwiZXhwIjoxNjU4ODIzNTQ0fQ.i75KmY0z2P3M_EEHaGgYCArtDg

Xye0KCnZNiqDLBFLUEjU5ZeSNYEZcAHii7NEwdxnoFLhEEVKAvjto-SDUH5n0CVeA3roRw8gH6pjJ-UfW-ZJ5IGr8Jf5Q9imyOiE5NC2TdAy-

uLEKc_5TkFgAtTP4k7BBTZpLnXleYR0AGB_NMVt6b8HjO36G7Jnt7AKupeeVxV5iQT06AIkCZE_ZRcEq-

1w65kb2zoh1KBAOBgjZkRViSFOisTVcnzwXOHq2s3wxCmPmvXc4P_rYnMd9KxbL76Tmz2UXF9PzDZilEkHeDbVe-YVtvHVAECs6H5IS2zNBYp0H5gH8IQl3-L2HPfQ

--------------------------------------------------------  

 - **Response : SuccessDataResult** 


{
    "success": true,
    
    "message": "customer.found.by.companyName",
    
    "data": [
    
        {
        
            "id": 11,
            
            "customerName": "Jeff",
            
            "companyName": "THY"
            
        },
        
        {
        
            "id": 12,
            
            "customerName": "Elon",
            
            "companyName": "THY"
            
        },
        
        {
        
            "id": 13,
            
            "customerName": "Bill",
            
            "companyName": "THY"
            
        }
        
    ]
    
}

*********************************************************************************
 
  ## **filter by company name** ##
 
 
*******************************************************************************

- **Request**



 ` localhost:5555/gateway/customer/THY `

GET /gateway/customer/THY

 HTTP/1.1

Host: localhost:5555

Authorization: Bearer 


eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0NCIsInVzZXJJZCI6NCwicm9sZXMiOiJVU0VSIiwiZXhwIjoxNjU4ODIzNTQ0fQ.i75KmY0z2P3M_EEHaGgYCArtDg

Xye0KCnZNiqDLBFLUEjU5ZeSNYEZcAHii7NEwdxnoFLhEEVKAvjto-SDUH5n0CVeA3roRw8gH6pjJ-UfW-ZJ5IGr8Jf5Q9imyOiE5NC2TdAy-

uLEKc_5TkFgAtTP4k7BBTZpLnXleYR0AGB_NMVt6b8HjO36G7Jnt7AKupeeVxV5iQT06AIkCZE_ZRcEq-

1w65kb2zoh1KBAOBgjZkRViSFOisTVcnzwXOHq2s3wxCmPmvXc4P_rYnMd9KxbL76Tmz2UXF9PzDZilEkHeDbVe-YVtvHVAECs6H5IS2zNBYp0H5gH8IQl3-L2HPfQ

--------------------------------------------------------  

 - **Response : SuccessDataResult** 


{
    "success": true,
    
    "message": "customer.found.by.companyName",
    
    "data": [
    
        {
        
            "id": 11,
            
            "customerName": "Jeff",
            
            "companyName": "THY"
            
        },
        
        {
        
            "id": 12,
            
            "customerName": "Elon",
            
            "companyName": "THY"
            
        },
        
        {
        
            "id": 13,
            
            "customerName": "Bill",
            
            "companyName": "THY"
            
        }
        
    ]
    
}

*********************************************************************************
 
  ## **sort by company name** ##
 
 
*******************************************************************************

- **Request**



 ` localhost:5555/gateway/customer/getAllSortedByCompanyName `

GET /gateway/customer/getAllSortedByCompanyName 

HTTP/1.1

Host: localhost:5555

Authorization: Bearer
 
 eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0NCIsInVzZXJJZCI6NCwicm9sZXMiOiJVU0VSIiwiZXhwIjoxNjU4ODIzNTQ0fQ.i75KmY0z2P3M_EEHaGgYCArtDg
 
 Xye0KCnZNiqDLBFLUEjU5ZeSNYEZcAHii7NEwdxnoFLhEEVKAvjto-SDUH5n0CVeA3roRw8gH6pjJ-UfW-ZJ5IGr8Jf5Q9imyOiE5NC2TdAy-
 
 uLEKc_5TkFgAtTP4k7BBTZpLnXleYR0AGB_NMVt6b8HjO36G7Jnt7AKupeeVxV5iQT06AIkCZE_ZRcEq-
 
 1w65kb2zoh1KBAOBgjZkRViSFOisTVcnzwXOHq2s3wxCmPmvXc4P_rYnMd9KxbL76Tmz2UXF9PzDZilEkHeDbVe-YVtvHVAECs6H5IS2zNBYp0H5gH8IQl3-L2HPfQ

--------------------------------------------------------  

 - **Response : SuccessDataResult** 


{

    "success": true,
    
    "message": "customer.descending.sorted.by.company.name",
    
    "data": [
    
        {
        
            "id": 11,
            
            "customerName": "Jeff",
            
            "companyName": "THY",
            
            "createTime": "2022-07-25T13:04:38.11926"
            
        },
        
        {
        
            "id": 12,
            
            "customerName": "Elon",
            
            "companyName": "THY",
            
            "createTime": "2022-07-25T13:04:45.995129"
            
        },
        
        {
        
            "id": 3,
            
            "customerName": "Cem",
            
            "companyName": "Oracle",
            
            "createTime": "2022-07-17T00:56:42.569654"
            
        },
        
        
        {
        
        
            "id": 4,
            
            "customerName": "Cem",
            
            "companyName": "Oracle",
            
            "createTime": "2022-07-17T00:56:43.578356"
            
        },
        
        {
        
            "id": 5,
            
            "customerName": "Cem",
            
            "companyName": "Microsoft",
            
            "createTime": "2022-07-17T01:24:06.895497"
            
        },
        
        {
        
            "id": 6,
            
            "customerName": "Cem",
            
            "companyName": "IBM",
            
            "createTime": "2022-07-20T13:47:51.030206"
            
        },
        
        {
        
            "id": 8,
            
            "customerName": "Cem",
            
            "companyName": "BMW",
            
            "createTime": "2022-07-21T16:07:01.159526"
            
        },
        
        {
        
            "id": 1,
            
            "customerName": "S2K Cem",
            
            "companyName": "32Bit",
            

            "createTime": "2022-07-17T00:55:49.813837"
            
        },
        
        {
        
            "id": 2,
            
            "customerName": "Cem",
            
            "companyName": "32Bit",
            "createTime": "2022-07-17T00:55:50.927043"
        },
        {
            "id": 13,
            "customerName": "Bill",
            "companyName": "THY",
            "createTime": "2022-07-25T13:05:12.150278"
        }
    ]
}



















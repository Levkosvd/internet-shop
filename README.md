#StoreCore
Table of contents
* [Describe](####what)
* [Technologies](#tech)
* [Settings for start](#sett)

#### <a name="what">Describe</a> 
Simplified implementation  basic functions of online store with Java EE. 
New users registration, theirs  authentication and authorization, 
adding the item to the cart and confirming the order are available. 
Opportunities for different users are separated by role
<hr>


#### <a name="tech"></a>Project structure
The technology used in this project:
* Java 11
* Maven 3.8.0
* Servlets 3.1.0
* JDBC
* MySQL 8.0.18
* Tomcat 8.5.50
<hr>

#### <a name="sett">To run project</a>
* Configure Tomcat:
    * add artifact internetshop:war exploded;
    * add Java SDK11
* Use SQL script from file init_db.sql in directory src/main/resources to create data base.
* In src.main.java.internetshop.factory.Factory class change username and password  to create a Connection.
* Change a path in src.main.java.resources.log4j.properties to properly write logs on the disc.

<hr>


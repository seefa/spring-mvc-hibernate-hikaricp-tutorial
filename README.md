# spring-hibernate-hikaricp-tutorial

Spring MVC features explained in [spring-mvc-jdbc-tutorial](https://github.com/seefa/spring-jdbc-mvc-tutorial) and Hibernate mentioned in [spring-mvc-hibernate-dbcp2-tutorial](https://github.com/seefa/spring-mvc-hibernate-dbcp2-tutorial) codes in my repository

This tutorial code used to explain Spring with Hibernate for persistence layer and Web MVC to call CRUD operations and specially adding HikariCP API for improvement database connection pool management by following tasks:

* add **spring-context**, **mysql-connector-java**, **spring-orm** **spring-webmvc** and dependencies
* add **javax.servlet.jsp-api**, **javax.servlet-api** and **jstl** dependencies for implementing Java server-side UI(.jsp files).
* add **spring-data-jpa**, **hibernate-core**, **hibernate-entitymanager** and **hibernate-validator** dependencies for implementing Persistence ORM layer.
* add **HikariCP** and **hibernate-hikaricp** dependencies for supporting Database Connection Pool feature.
* using Entity bean to add new DAO object and using @Entity and @Table annotations plus @GeneratedValue for generating table key value automatically.
* support using GET/POST HTTP requests to access CRUD services.
* using tag libs in JSP view pages to iterate/post and formatting Java objects to HTML contents and pass HTML input forms to Java Object.
* using @InitBinder to convert String date input to java.util.Date format.

_TIP:_ Database DDL and DML SQL queries is added to **db_files** folder

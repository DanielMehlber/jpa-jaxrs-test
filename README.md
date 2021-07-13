### Example for JPA and JAX-RS

This example shows how JPA and JAX-RS can be embedded into your JEE Application.

## JPA

JPA itself is just a set of interfaces (just like JDBC). Some providers provide these implementations.
Such providers are:
* OpenJPA (used by this project)
* EclipseLink
* Hibernate

In order to change the projects provider you need to change the used jar-file (in pom.xml) and alter all providers in 
`persistence.xml`

### Configuration
JPA uses xml- and annotation-based configuration. In this example annotations are used to define the ORM-Mapping and 
a the `persistence.xml` defines the used providers and classes. Place the `persistence.xml` in a source folder such as `META-INF/persistence.xml` IN THE CLASSPATH!!!

### Connection
The connection is established via connection pooling by the container. In `persistence.xnl` a datasource is defined for every perstistence unit.
This datasource (JDBC driver, url, username, password) must exist in the container context. Some Containers want you to deploy the JDBC Driver and then use it.

## JAX-RS
You just need to mention the JAX-RS Application class `javax.ws.rs.core.Application` as servlet in `web.xml`. Don't use static methods!!!


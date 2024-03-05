# TranslateApp
**TranslateApp is a Spring Boot application that provides translation services using either a local database or an external API.**

# Features
1. **Translation Service:** Translate text from one language to another.
2. **Profile-based Configuration:** Switch between using a local database or an external API for translations.
3. **Authentication:** Secure endpoints with basic authentication.
# Technologies Used
1. Java
2. Spring Boot
3. Spring Security
4. JPA/Hibernate
5. RESTful API

# Prerequisites
- JDK 17
- PostgreSQL

# Configuration
*The application can be configured to use either a local database or an external API for translations.*

- **To use the local database, ensure that the appropriate database configurations are set in *application.properties*.**
- **To use the external API, set the corresponding API key and endpoint URL in *applicationExternal.properties*.**

# Endpoints
- **/hello-rest**
- **/hello**
- **/translate**
- **/secure/hello**
- **/secure/admin**
- **/translator**

# Security
The application uses Spring Security for authentication. By default, all endpoints require authentication except the few from the above

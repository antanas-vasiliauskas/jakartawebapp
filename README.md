# jakartawebapp
1. Download and install PostgreSQL
2. Download Payera 6 server
3. Download postgresql-42.7.5.jar (driver) and paste it in Payara\payara6\glassfish\lib
4. Add Payera server on eclipse. Run it.
5. Open http://localhost:4848 admin panel, create JDBC connection pool (User, Password, DatabaseName, PortNumber, ServerName) (CASE SENSITIVE!)
6. Create JDBC Resource that uses that pool.
7. Make sure persistence.xml is configured correctly and uses that JDBC Resource.
8. Run Project on server.
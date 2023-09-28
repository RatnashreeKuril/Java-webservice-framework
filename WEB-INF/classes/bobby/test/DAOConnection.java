package bobby.test;
import java.sql.*;
public class DAOConnection
{
public static Connection getConnection() throws DAOException
{
Connection c=null;
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
c=DriverManager.getConnection("jdbc:mysql://localhost:3306/tmp","tmpuser","tmpuser");
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return c;
}
}
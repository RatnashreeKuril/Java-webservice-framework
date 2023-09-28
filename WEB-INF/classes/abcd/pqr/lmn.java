package abcd.pqr;
import com.thinking.machines.webrock.pojo.*;
public class lmn
{
public void efgh(SessionScope sessionScope)
{
String name=(String)sessionScope.getAttribute("name");
if(name==null || name.length()==0) throw new SecurityException("Invalid access to service");
String password=(String)sessionScope.getAttribute("password");
if(password==null || password.length()==0) throw new SecurityException("Invalid access to service");
if(name.equalsIgnoreCase(password)==false) throw new SecurityException("Invalid access to service");
}
}
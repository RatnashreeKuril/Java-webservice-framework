package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
@Path("/login")
@InjectSessionScope
public class Login
{
private SessionScope sessionScope;
public void setSessionScope(SessionScope sessionScope)
{
this.sessionScope=sessionScope;
}
@Path("/checkPassword")
public void checkPassword(@RequestParameter("name") String name,@RequestParameter("password") String password)
{
if(name==null || name.length()==0) throw new SecurityException("Invalid username/password");
if(password==null || password.length()==0) throw new SecurityException("Invalid username/password");
System.out.println(name);
System.out.println(password);
if(!(name.equalsIgnoreCase(password))) throw new SecurityException("Invalid username/password");
sessionScope.setAttribute("name",name);
sessionScope.setAttribute("password",password);
}
}
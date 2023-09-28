package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.io.*;
@Path("/processData2")
@InjectApplicationScope
@InjectSessionScope
public class ProcessData2
{
private ApplicationScope applicationScope;
private SessionScope sessionScope;
public void setApplicationScope(ApplicationScope applicationScope)
{
this.applicationScope=applicationScope;
}
public void setSessionScope(SessionScope sessionScope)
{
this.sessionScope=sessionScope;
System.out.println("setSessionScope got called");
}
@Path("/add")
public void add()
{
System.out.println("Add method of ProcessData2 got called");
this.sessionScope.setAttribute("name2","Bittu Kuril");
this.sessionScope.setAttribute("name3","Bittu1 Kuril");
this.sessionScope.setAttribute("name4","Bittu2 Kuril");
this.sessionScope.setAttribute("name5","Bittu3 Kuril");
}
@Path("/getName")
public void getName()
{
String name=(String)this.sessionScope.getAttribute("name");
System.out.println(name);
}

}
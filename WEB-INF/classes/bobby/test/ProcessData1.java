package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.io.*;
@Path("/processData1")
@InjectSessionScope
public class ProcessData1
{
private SessionScope sessionScope;

public void setSessionScope(SessionScope sessionScope)
{
System.out.println("***************setSessionScope got called");
this.sessionScope=sessionScope;
}
@FORWARD("/processData1/getName")
@Path("/add")
public void add()
{
System.out.println("Add method of ProcessData1 got called");
this.sessionScope.setAttribute("name1","Ratna Kuril");
}
@Path("/getName")
public void getName()
{
String name=(String)this.sessionScope.getAttribute("name1");
System.out.println("name1 : "+name);
}
@Path("/getById")
public void getById()
{
String name2=(String)this.sessionScope.getAttribute("name2");
String name3=(String)this.sessionScope.getAttribute("name3");
String name4=(String)this.sessionScope.getAttribute("name4");
String name5=(String)this.sessionScope.getAttribute("name5");
System.out.println("name2 : "+name2);
System.out.println("name3 : "+name3);
System.out.println("name4 : "+name4);
System.out.println("name5 : "+name5);

}
}
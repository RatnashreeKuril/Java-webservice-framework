package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.io.*;
import java.util.*;
@Path("/processData5")
@InjectApplicationScope
@SecuredAccess(checkPost="abcd.pqr.lmn",guard="efgh")
public class ProcessData5
{
private ApplicationScope applicationScope;
@InjectRequestParameter("xyz")
private int x;
@InjectRequestParameter("student")
private Student student;

public void setX(int x)
{
this.x=x;
}
public void setStudent(Student student)
{
this.student=student;
}
@Path("/getX")
public void getX()
{
System.out.println(this.x);
}
@Path("/getStudent")
public void getStudent()
{
System.out.println(student.getRollNumber());
System.out.println(student.getName());
}

public void setApplicationScope(ApplicationScope applicationScope)
{
this.applicationScope=applicationScope;
}
@OnStartup(priority=0)
@Path("/populateDS")
public void populateDS()
{
List<String> list=new ArrayList<>();
list.add("Ujjain");
list.add("Indore");
list.add("Dewas");
list.add("Bhopal");
list.add("Goa");
this.applicationScope.setAttribute("cities",list);
}
@FORWARD("/processData6/getString")
@Path("/getString")
public String getString()
{
return "abcd";
}

@FORWARD("/processData6/getInteger")
@Path("/getInteger")
public int getInteger()
{
return 10;
}

@FORWARD("/processData6/getNothing")
@Path("/getNothing")
public void getNothing()
{
System.out.println("getNothing got called");
}

@FORWARD("/processData6/getBoolean")
@Path("/getBoolean")
public boolean getBoolean()
{
return true;
}
@Path("/acceptByte")
public void acceptByte(@RequestParameter("b") byte b)
{
System.out.println(b);
}
@Path("/acceptShort")
public void acceptShort(@RequestParameter("b") short b)
{
System.out.println(b);
}
@Path("/acceptInt")
public void acceptInt(@RequestParameter("b") int num)
{
System.out.println(num);
}
@Path("/acceptLong")
public void acceptLong(@RequestParameter("b") long b)
{
System.out.println(b);
}
@Path("/acceptFloat")
public void acceptFloat(@RequestParameter("b") float b)
{
System.out.println(b);
}
@Path("/acceptDouble")
public void acceptDouble(@RequestParameter("b") double b)
{
System.out.println(b);
}
@Path("/acceptBoolean")
public void acceptBoolean(@RequestParameter("b") boolean b)
{
System.out.println(b);
}
@Path("/acceptChar")
public void acceptChar(@RequestParameter("b") char c)
{
System.out.println(c);

}
@Path("/acceptString")
public void acceptString(@RequestParameter("b") String str)
{
System.out.println(str);
}
@Path("/getRequestScope")
public void acceptRequestScope(@RequestParameter("b") String str,ApplicationDirectory ad,RequestScope req1, SessionScope ss, RequestScope req2)
{
System.out.println(str);
req1.setAttribute("attr1","attr1");
ss.setAttribute("attr2","attr2");
String a=(String)req2.getAttribute("attr1");
System.out.println(a);
}
@Path("/getAttribute")
public void getAttribute(SessionScope ss)
{
String a=(String)ss.getAttribute("attr2");
System.out.println(a);
}
@POST
@Path("/sendJson")
public Student sendJson(ApplicationScope applicationScope,RequestScope requestScope,SessionScope ss,Student s1,@RequestParameter("b") String str)
{
return student;
}
@Path("/putIdInSessionScope")
public void putIdInSessionScope(SessionScope ss)
{
ss.setAttribute("id","Ratna");
}
@Path("/securedServiceTest")
public void securedServiceTest()
{
System.out.println("This is a valid access to this service");
}
}
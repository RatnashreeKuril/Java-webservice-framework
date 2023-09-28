package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.io.*;
@InjectRequestScope
@Path("/processData4")
public class ProcessData4
{
private RequestScope requestScope;
@AutoWired(name="name2")
private String name2;
@AutoWired(name="name3")
private String name3;
@AutoWired(name="name4")
private String name4;
@AutoWired(name="name5")
private String name5;

public void setName2(String name2)
{
this.name2=name2;
}
public void setName3(String name3)
{
this.name3=name3;
}
public void setName4(String name4)
{
this.name4=name4;
}
public void setName5(String name5)
{
this.name5=name5;
}

public void setRequestScope(RequestScope requestScope)
{
System.out.println("setRequestScope got called");
this.requestScope=requestScope;
}
@FORWARD("/processData3/add")
@Path("/add")
public void add()
{
Student student=new Student();
student.setRollNumber(101);
student.setName("Priya");
requestScope.setAttribute("xyz",student);

}
@Path("/edit")
public void update()
{
System.out.println(this.name2);
System.out.println(this.name3);
System.out.println(this.name4);
System.out.println(this.name5);
}
}
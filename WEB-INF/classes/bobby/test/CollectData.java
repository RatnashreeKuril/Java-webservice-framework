package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.io.*;
import java.util.*;
@InjectRequestScope
@Path("/collectData")
public class CollectData
{
private RequestScope requestScope;
public void setRequestScope(RequestScope requestScope)
{
this.requestScope=requestScope;
}
@FORWARD("/studentManager/add")
@Path("/getData")
public void getData()
{
Student student=new Student();
student.setRollNumber(102);
student.setName("Teena");
requestScope.setAttribute("student",student);
}
@FORWARD("/studentManager/edit")
@Path("/getStudent")
public Student getStudent()
{
Student student=new Student();
student.setRollNumber(105);
student.setName("Rahul");
return student;
}
@Path("/getNumber")
public void getNumber(int number)
{
System.out.println(number);
}
{}
}
package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.io.*;
import java.util.*;
@Path("/processData6")
public class ProcessData6
{
@AutoWired(name="cities")
private List<String> cities;
public void setCities(List<String> cities)
{
this.cities=cities;
}
@SecuredAccess(checkPost="abcd.pqr.lmn",guard="efgh")
@Path("/getCities")
public void getCities()
{
for(String s:this.cities)
{
System.out.println(s);
}
}
@Path("/getString")
public void getString(String s)
{
System.out.println(s);
}
@Path("/getInteger")
public void getInteger(int num)
{
System.out.println(num);
}
@Path("/getStudent")
public void getStudent(Student student)
{
System.out.println(student.getRollNumber());
System.out.println(student.getName());
}
@Path("/getNothing")
public void getNothing()
{
System.out.println("got nothing");
}
@Path("/getSomeEntity")
public void getSomeEntity(SomeEntity someEntity)
{
System.out.println(someEntity);
}
@Path("/getBoolean")
public void getBoolean()
{
System.out.println("This method has no parameters");
}
}
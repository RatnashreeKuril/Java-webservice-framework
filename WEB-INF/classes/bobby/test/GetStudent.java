package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.util.*;
@InjectApplicationScope
@Path("/getStudent")
public class GetStudent
{
private ApplicationScope applicationScope;
public void setApplicationScope(ApplicationScope applicationScope)
{
this.applicationScope=applicationScope;
}
@Path("/get")
public void getAddedStudent(List<Student> studentsList)
{
System.out.println("Students List size is : "+studentsList);
applicationScope.setAttribute("studentsList",studentsList);

}
}
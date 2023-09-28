package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.util.*;
@InjectApplicationScope
@Path("/getAllStudents")
public class GetAllStudents
{
private ApplicationScope applicationScope;
private List<Student> studentsList;
@OnStartup(priority=0)
@Path("/setAll")
public void setAllStudents()
{
studentsList=new ArrayList<Student>();
applicationScope.setAttribute("studentsList",studentsList);
}
public void setApplicationScope(ApplicationScope applicationScope)
{
this.applicationScope=applicationScope;
}
@Path("/getAll")
public List<Student> getAll()
{
System.out.println("getAllStudents got called");
List<Student> list=(List<Student>)applicationScope.getAttribute("studentsList");
System.out.println(list.size());
return list;
}
}
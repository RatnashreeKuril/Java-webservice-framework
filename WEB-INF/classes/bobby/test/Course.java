package bobby.test;
import com.thinking.machines.webrock.annotations.*;
@GET
@Path("/course")
public class Course
{
@FORWARD("/eg1.html")
@Path("/add")
public void add()
{
System.out.println("Add Course got called");
}
@FORWARD("/student/add")
@POST
@Path("/update")
public void update()
{
System.out.println("Update Course got called");
}
@GET
@Path("/getAll")
public void getAll()
{
System.out.println("Get all Course got called");
}
@Path("/getByName")
public void getByName()
{

}
}
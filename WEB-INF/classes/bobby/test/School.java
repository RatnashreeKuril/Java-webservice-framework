package bobby.test;
import com.thinking.machines.webrock.annotations.*;
@Path("/school")
public class School
{
@OnStartup(priority=1)
@Path("/add")
public void add()
{
System.out.println("Add school got called");
}
@OnStartup(priority=0)
@Path("/update")
public void update()
{
System.out.println("Update school got called");
}
@OnStartup(priority=2)
@Path("/getAll")
public void getAll()
{
System.out.println("Get all school got called");
}
}
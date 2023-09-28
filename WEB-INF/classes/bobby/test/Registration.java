package bobby.test;
import com.thinking.machines.webrock.annotations.*;
@Path("/registration")
public class Registration
{
@OnStartup(priority=1)
@Path("/add")
public void add()
{
System.out.println("Add registration got called");
}
@OnStartup(priority=0)
@Path("/update")
public void update()
{
System.out.println("Update registration got called");
}
@OnStartup(priority=0)
@Path("/getAll")
public void getAll()
{
System.out.println("Get all registration got called");
}

@OnStartup(priority=7)
@Path("/delete")
public void delete()
{
System.out.println("Delete registration got called");
}
@OnStartup(priority=5)
@Path("/getByCode")
public void getByCode()
{
System.out.println("Get registration by code got called");
}
}

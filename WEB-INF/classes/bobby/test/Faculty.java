package bobby.test;
import com.thinking.machines.webrock.annotations.*;

@Path("/faculty")
public class Faculty
{
@FORWARD("/faculty/update")
@Path("/add")
public void add()
{
System.out.println("Add faculty got called");
}
@FORWARD("/eg1.html")
@Path("/update")
public void update()
{
System.out.println("Update faculty got called");
}
}
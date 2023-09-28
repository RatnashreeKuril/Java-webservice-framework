package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.io.*;
@Path("/doSomething")
@InjectApplicationDirectory
public class DoSomething
{
private ApplicationDirectory applicationDirectory;
public void setApplicationDirectory(ApplicationDirectory applicationDirectory)
{
System.out.println("***************setApplicationDirectory got called");
this.applicationDirectory=applicationDirectory;
}
@Path("/add")
public void add()
{
System.out.println("Add method of doSomething got called");
}
}
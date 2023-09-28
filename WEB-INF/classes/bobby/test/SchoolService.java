package bobby.test;
import java.sql.*;
import java.util.*;
import com.thinking.machines.webrock.annotations.*;
@ServiceClass
@Path("/schoolService")
public class SchoolService
{
@Path("/add")
public void add(School school)
{

}
@Path("/update")
public void update(School school)
{

}
@Path("/delete")
public void delete(School school)
{

}
@Path("/getByCode")
public School getByCode(int code)
{
return null;
}
@Path("/getAll")
public List<School> getAll()
{
return null;
}

}
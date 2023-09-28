package bobby.bank.employee.designation;
import java.sql.*;
import java.util.*;
import com.thinking.machines.webrock.annotations.*;
@ServiceClass
@Path("/designationService")
public class DesignationService
{
@Path("/add")
public void add(Designation designation)
{

}
@Path("/update")
public void update(Designation designation)
{

}
@Path("/delete")
public void delete(@RequestParameter("code") int code)
{

}
@Path("/getByCode")
public Designation getByCode(@RequestParameter("code") int code)
{
return null;
}
@Path("/getAll")
public List<Designation> getAll()
{
return null;
}
@Path("/codeExists")
public boolean codeExists(@RequestParameter("code") int code)
{
return false;
}
}
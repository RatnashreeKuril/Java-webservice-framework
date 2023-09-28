package bobby.bank.employee;
import java.sql.*;
import java.util.*;
import com.thinking.machines.webrock.annotations.*;
@ServiceClass
@Path("/employeeService")
public class EmployeeService
{
@Path("/add")
public void add(Employee employee)
{

}
@Path("/update")
public void update(Employee employee)
{

}
@Path("/delete")
public void delete(@RequestParameter("code") int code)
{

}
@Path("/getByCode")
public Employee getByCode(@RequestParameter("code") int code)
{
return null;
}
@Path("/getAll")
public List<Employee> getAll()
{
return null;
}

}
package bobby.bank.customer;
import java.sql.*;
import java.util.*;
import com.thinking.machines.webrock.annotations.*;
@POST
@ServiceClass
@Path("/customerService")
public class CustomerService
{
@POST
@Path("/add")
public void add(Customer customer)
{

}
@Path("/update")
public void update(Customer customer)
{

}
@Path("/delete")
public void delete(@RequestParameter("code") int code)
{

}
@Path("/getByCode")
public Customer getByCode(@RequestParameter("code") int code)
{
return null;
}
@Path("/getByRegistrationNumber")
public Customer getByRegistrationNumber(@RequestParameter("registrationNo.") int registrationNumber)
{
return null;
}
@Path("/getAll")
public List<Customer> getAll()
{
return null;
}
@Path("/getCustomers")
public List<Customer> getCustomers()
{
return null;
}
public Customer getCustomerById(@RequestParameter("id") String id)
{
return null;
}
}
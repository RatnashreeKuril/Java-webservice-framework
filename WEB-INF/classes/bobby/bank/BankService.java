package bobby.bank;
import java.sql.*;
import java.util.*;
import com.thinking.machines.webrock.annotations.*;
@POST
@ServiceClass
@Path("/bankService")
public class BankService
{
@POST
@Path("/add")
public void add(Bank bank)
{

}
@Path("/update")
public void update(Bank bank)
{

}
@Path("/delete")
public void delete(@RequestParameter("code") int code)
{

}
@Path("/getByCode")
public Bank getByCode(@RequestParameter("code") int code)
{
return null;
}
@Path("/getByRegistrationNumber")
public Bank getByRegistrationNumber(@RequestParameter("registrationNo.") int registrationNumber)
{
return null;
}
@Path("/getAll")
public List<Bank> getAll()
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
package bobby.test;
import java.sql.*;
import java.util.*;
import com.thinking.machines.webrock.annotations.*;
@ServiceClass
@Path("/courseService")
public class CourseService
{
@Path("/add")
public void add(Course course)
{

}
@Path("/update")
public void update(Course course)
{

}
@Path("/delete")
public void delete(Course course)
{

}
@Path("/getByCode")
public Course getByCode(int code)
{
return null;
}
@Path("/getAll")
public List<Course> getAll()
{
return null;
}

}
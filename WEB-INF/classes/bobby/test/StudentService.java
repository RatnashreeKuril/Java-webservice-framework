package bobby.test;
import java.sql.*;
import java.util.*;
import com.thinking.machines.webrock.annotations.*;
@ServiceClass
@Path("/studentService")
public class StudentService
{
@Path("/add")
public void add(Student student) throws DAOException
{
System.out.println("Student service add got called");
String name=student.getName();
String gender=student.getGender();
System.out.println(name);
System.out.println(gender);
if(name.equalsIgnoreCase("Amit")) throw new DAOException("Name cannot be amit");
try
{
Connection c=DAOConnection.getConnection();
PreparedStatement ps=c.prepareStatement("insert into student (name,gender) values(?,?)");
ps.setString(1,name);
ps.setString(2,gender);
ps.executeUpdate();
ps.close();
c.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
System.out.println("Student service add ends here");
}
@Path("/update")
public void update(Student student) throws DAOException
{
int rollNumber=student.getRollNumber();
try
{
Connection c=DAOConnection.getConnection();
PreparedStatement ps=c.prepareStatement("select gender from student where roll_number=?");
ps.setInt(1,rollNumber);
ResultSet rs=ps.executeQuery();
if(!(rs.next()))
{
rs.close();
ps.close();
c.close();
throw new DAOException("Invalid roll number : "+rollNumber);
}
rs.close();
ps.close();
String name=student.getName();
String gender=student.getGender();
ps=c.prepareStatement("update student set name=?, gender=? where roll_number=?");
ps.setString(1,name);
ps.setString(2,gender);
ps.setInt(3,rollNumber);
ps.executeUpdate();
ps.close();
c.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}

}
@Path("/delete")
public void delete(@RequestParameter("rollNumber") int rollNumber) throws DAOException
{
try
{
Connection c=DAOConnection.getConnection();
PreparedStatement ps=c.prepareStatement("select gender from student where roll_number=?");
ps.setInt(1,rollNumber);
ResultSet rs=ps.executeQuery();
if(!(rs.next()))
{
rs.close();
ps.close();
c.close();
throw new DAOException("Invalid roll number : "+rollNumber);
}
rs.close();
ps.close();
ps=c.prepareStatement("delete from student where roll_number=?");
ps.setInt(1,rollNumber);
ps.executeUpdate();
ps.close();
c.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}

}
@Path("/getByRollNumber")
public Student getByRollNumber(@RequestParameter("rollNumber") int rollNumber) throws DAOException
{
Student student=new Student();
try
{
Connection c=DAOConnection.getConnection();
PreparedStatement ps=c.prepareStatement("select * from student where roll_number=?");
ps.setInt(1,rollNumber);
ResultSet rs=ps.executeQuery();
if(!(rs.next()))
{
rs.close();
ps.close();
c.close();
throw new DAOException("Invalid roll number : "+rollNumber);
}
student.setRollNumber(rs.getInt("roll_number"));
student.setName(rs.getString("name"));
student.setGender(rs.getString("gender"));
rs.close();
ps.close();
c.close();

}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return student;
}
@Path("/getAll")
public List<Student> getAll() throws DAOException
{
List<Student> students=new ArrayList<>();
try
{
Connection c=DAOConnection.getConnection();
Statement s=c.createStatement();
ResultSet rs=s.executeQuery("select * from student");
Student student;
while(rs.next())
{
student=new Student();
student.setRollNumber(rs.getInt("roll_number"));
student.setName(rs.getString("name"));
student.setGender(rs.getString("gender"));
students.add(student);
}
rs.close();
s.close();
c.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return students;
}
}
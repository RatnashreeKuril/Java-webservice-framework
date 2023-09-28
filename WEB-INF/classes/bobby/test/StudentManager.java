package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.io.*;
import java.util.*;
@Path("/studentManager")
public class StudentManager
{
@AutoWired(name="student")
private Student student;
public void setStudent(Student student)
{
this.student=student;
}
@Path("/add")
public void add()
{
System.out.println(student.getRollNumber());
System.out.println(student.getName());
}
@Path("/edit")
public void update(Student student)
{
System.out.println(student.getRollNumber());
System.out.println(student.getName());
}
@FORWARD("/collectData/getNumber")
@Path("/setNumber")
public int setNumber()
{
return 10;
}

}
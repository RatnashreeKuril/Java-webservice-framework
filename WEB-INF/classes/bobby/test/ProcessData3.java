package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.io.*;
@Path("/processData3")
public class ProcessData3
{
@AutoWired(name="xyz")
private Student student;
public void setStudent(Student student)
{
System.out.println("setStudent got called");
this.student=student;
}
public Student getStudent()
{
return this.student;
}
@Path("/add")
public void add()
{
System.out.println(student.getRollNumber());
System.out.println(student.getName());
}
}
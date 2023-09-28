package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.util.*;
@Path("/addStudent")
public class AddStudent
{
@AutoWired(name="studentsList")
private List<Student> studentsList;
public void setStudentsList(List<Student> studentsList)
{
System.out.println("setStudentsList got called");
this.studentsList=studentsList;
}
@FORWARD("/getStudent/get")
@Path("/add")
public List<Student> add()
{
Student student=new Student();
student.setRollNumber(104);
student.setName("Ravi");
this.studentsList.add(student);
return this.studentsList;
}
}
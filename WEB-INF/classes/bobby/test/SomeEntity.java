package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
@Path("/someEntity")
public class SomeEntity
{
@FORWARD("/processData5/getX")
@Path("/keepXInRequestScope")
public void keepXInRequestScope(RequestScope req)
{
req.setAttribute("xyz",1000);
}
@FORWARD("/processData5/getStudent")
@Path("/keepStudentInRequestScope")
public void keepStudentInRequestScope(RequestScope req)
{
Student student=new Student();
student.setRollNumber(106);
student.setName("Joy");
req.setAttribute("student",student);
}
}
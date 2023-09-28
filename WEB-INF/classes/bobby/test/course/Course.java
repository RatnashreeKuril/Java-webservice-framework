package bobby.test.course;
import com.thinking.machines.webrock.annotations.*;
@Pojo
public class Course implements java.io.Serializable
{
private int code;
private String name;
public Course()
{
this.code=0;
this.name="";
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}

}
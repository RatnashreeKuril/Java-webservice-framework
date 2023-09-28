# Java-webservice-framework
The Web Services Framework transforms server-side web service development. It liberates programmers from dealing with complex URL patterns and creating servlets. This server allows developers to focus on building web applications without getting tangled in the details of low-level server-side programming.
## Installation
To install, place this repository in the 'webapps' directory of your Tomcat server.
Specify the name of your project folder in the 'web.xml' file under 'param-value' for the 'param-name' SERVICE_PACKAGE_PREFIX in the servlet mapping section of TMWebRockStarter. 
```
<servlet>
<servlet-name>TMWebRockStarter</servlet-name>
<servlet-class>com.thinking.machines.webrock.TMWebRockStarter</servlet-class>
<init-param>
<param-name>SERVICE_PACKAGE_PREFIX</param-name>
<param-value>bobby</param-value>
</init-param>
<load-on-startup>0</load-on-startup>
</servlet>
<servlet-mapping>
<servlet-name>TMWebRockStarter</servlet-name>
<url-pattern>/tmWebRockStarter</url-pattern>
</servlet-mapping>
```
Specify the desired URL pattern as shown in the web.xml file.
```
<servlet>
<servlet-name>TMWebRock</servlet-name>
<servlet-class>com.thinking.machines.webrock.TMWebRock</servlet-class>
<load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
<servlet-name>TMWebRock</servlet-name>
<url-pattern>/schoolService/*</url-pattern>
</servlet-mapping>
```
You are required to create your project folder within the 'WEB-INF/classes' directory. An example folder named 'booby' already resides in the 'classes' directory.
## Useful annotations
**@Path(“/urlPattern”) :** This annotation is used to specify the service classes and methods to the webservice framework. You need to apply the annotation on class and it’s respective method which you want the framework to serve as following
```
@Path(“/someClass”)
class Whatever
{
@Path(“/someMethod”)
public void sam()
{
}
}
```
In this example the complete url pattern to the service sam is /someClass/someMethod.\
\
**@GET :** This annotation is used to specify the type of request for a particular service. One can use it like this : 
```
@Path(“/someClass”)
class Whatever
{
@GET
@Path(“/someMethod”)
public void sam()
{
}
}
```
Here the user is specifying that only get type request is allowed for this service. If POST type request is sent for this service it will cause 404 error.
If you want all the methods inside a class to be of GET type then you just simply need to apply this annotation on the class. In such condition all the methods declared in the class are of GET type, you don’t need to apply the annotation on each and every method.\
\
**@POST :** The working of this annotation is same as of GET.\
\
**@FORWARD(“/urlPattern”) :** Use this annotation when you want to redirect a request from one resource to another. You must specify the URL of the resource to which you want to forward the request.\
*Example :*
```
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
```
In this example, after the processing of the add method, the request will be forwarded to /getStudent/get.\
\
**@OnStartup(priority=1) :** This annotation is useful when you want to load a service on startup
Simply apply this annotation on the desired service method and specify the priority of this service. The services will be loaded based on their priority factor, with 0 being the highest priority.\
\
**@InjectApplicationDirectory :**  When you want the reference of application directory use this annotation. Apply this annotations on the class, declare a property of type ApplicationDirectory in the class and define setter method for this property. The framework will initialize the property using the setter method.\
*Example :*
```
@Path("/doSomething")
@InjectApplicationDirectory
public class DoSomething
{
private ApplicationDirectory applicationDirectory;
public void setApplicationDirectory(ApplicationDirectory applicationDirectory)
{
this.applicationDirectory=applicationDirectory;
}
@Path("/add")
public void add()
{
System.out.println("Add method of doSomething got called");
}
}
```
**@InjectApplicationScope** : The working is same as of InjectApplicationDirectory.\
\
**@InjectSessionScope** : The working is same as of InjectSessionDirectory.\
\
**@InjectRequestScope** : The working is same as of InjectRequestDirectory.\
\
**@AutoWired(name=”name”)** : The @Autowired annotation is used to automatically wire or inject dependencies into a class. It can be applied to properties of a class.\
*Example :*
```
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
```
\
**@RequestParameter(“name”)** : This annotation can be used to bind the request parameters to method parameters. This annotation is useful when you need to retrieve data from an incoming request and pass it as parameters to a method for further processing.\
*Example :*
```
public void printString(@RequestParameter("b") String b)
{
System.out.println(b);
}
```
## Useful annotation for Secured Access : 
**SecuredAccess(checkPost=”abcd.pqr.someClass”,guard=”someMethod”) :** You can apply this annotation on those methods which you want to keep secured. In such case you have to specify the name of the class with package against the attribute checkPost and name of the method against guard as arguments to the annotations.\
*Example :*
```
@SecuredAccess(checkPost="abcd.pqr.lmn",guard="efgh")
@Path("/getCities")
public void getCities()
{
for(String s:this.cities)
{
System.out.println(s);
}
}
```
In this example, prior to executing the getCities method, the server will initially run the efgh method of the abcd.pqr.lmn class to authenticate access to this method. If the access is invalid, the server will respond with a 404 error. However, if the access is valid, the server will proceed to execute the intended method.

package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.thinking.machines.webrock.pojo.*;
import com.thinking.machines.webrock.model.*;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.exceptions.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import java.util.*;
public class TMWebRockStarter extends HttpServlet
{
private static List<Service> onStartupServices=new ArrayList<>();
private static List<Class> pojoClasses=new ArrayList<>();
private static List<ServiceClassInfo> serviceClasses=new ArrayList<>();
private void traverseDirectory(WebRockModel webRockModel,File [] files,String servicePackage)
{
Service service;
Class c;
int i,e,found,index;
String className,name,fileName;
Annotation an;
Annotation [] annotations;
Path path;
String fullPath,packageName;
String pathOnClass,pathOnMethod;
File parentFile;
boolean isGetAllowed,isPostAllowed;
String forwardToOnClass;
String forwardToOnMethod;
FORWARD forward;
OnStartup onStartup;
int priority;
boolean injectApplicationDirectory;
boolean injectApplicationScope;
boolean injectSessionScope;
boolean injectRequestScope;
Field []fields;
AutoWired autoWired;
AutoWiredProperty autoWiredProperty;
List<AutoWiredProperty> autoWiredProperties;
List<RequestParameterInfo> requestParameters;
List<ClassFieldInfo> classFields;
List<AutoWiredProperty> autoWiredPropertiesOfSecurityService;
List<ClassFieldInfo> classFieldsOfSecurityService;
List<RequestParameterInfo> requestParametersOfSecurityService;
List<Service> serviceClassMethods;
ClassFieldInfo classFieldInfo;
RequestParameter requestParameter;
RequestParameterInfo requestParameterInfo;
Parameter []parameters;
InjectRequestParameter injectRequestParameter;
boolean isSecuredService;
String checkPostName;
String guardName;
Service securityService;
Class checkPost;
Method securityServiceMethod;
Method m[];
SecuredAccess securedAccess;
String parentName;
List<String> list;
ServiceClassInfo serviceClassInfo;
boolean isServiceClass;
for(File f : files)
{
if(f.isDirectory()) 
{
traverseDirectory(webRockModel,f.listFiles(),servicePackage);
}

else
{
if(f.getName().endsWith(".class"))
{
list=new ArrayList<>();
parentFile=f.getParentFile();
packageName="";
fileName=f.getName();
while(!(parentFile.getName().equals(servicePackage)))
{
list.add(parentFile.getName());
parentFile=parentFile.getParentFile();
if(parentFile==null) break;
}
list.add(servicePackage);
for(e=list.size()-1;e>=0;e--)
{
packageName+=list.get(e);
if(e>0) packageName=packageName+".";
}
i=f.getName().indexOf('.');
className=f.getName().substring(0,i);
forwardToOnClass="";
autoWiredProperties=new ArrayList<>();
classFields=new ArrayList<>();
isSecuredService=false;
checkPostName="";
guardName="";
isGetAllowed=false;
isPostAllowed=false;
isServiceClass=false;
serviceClassMethods=null;
serviceClassInfo=null;
try
{
c=Class.forName(packageName+"."+className);
if(c.isAnnotationPresent(Pojo.class))
{
pojoClasses.add(c);
}
if(c.isAnnotationPresent(ServiceClass.class))
{
isServiceClass=true;
serviceClassInfo=new ServiceClassInfo();
serviceClassInfo.setServiceClass(c);
serviceClassMethods=new ArrayList<>();
}
an=c.getAnnotation(Path.class);
if(an==null) continue;
path=(Path)an;
pathOnClass=path.value();
annotations=c.getAnnotations();
injectApplicationDirectory=false;
injectApplicationScope=false;
injectSessionScope=false;
injectRequestScope=false;

for(Annotation a: annotations)
{
if(a instanceof SecuredAccess)
{
isSecuredService=true;
securedAccess=(SecuredAccess)a;
checkPostName=securedAccess.checkPost();
guardName=securedAccess.guard();
}
if(a instanceof GET) isGetAllowed=true;
if(a instanceof POST) isPostAllowed=true;
if(a instanceof FORWARD)
{
forward=(FORWARD)a;
forwardToOnClass=forward.value();
}
if(a instanceof InjectApplicationDirectory)
{
injectApplicationDirectory=true;
}
if(a instanceof InjectApplicationScope)
{
injectApplicationScope=true;
}
if(a instanceof InjectSessionScope)
{
injectSessionScope=true;
}
if(a instanceof InjectRequestScope)
{
injectRequestScope=true;
}

}
fields=c.getDeclaredFields();

for(Field field:fields)
{
annotations=field.getAnnotations();
for(Annotation a:annotations)
{
if(a instanceof AutoWired)
{
autoWired=(AutoWired)a;
autoWiredProperty=new AutoWiredProperty();
autoWiredProperty.setName(autoWired.name());
autoWiredProperty.setField(field);
autoWiredProperties.add(autoWiredProperty);
}
if(a instanceof InjectRequestParameter)
{
injectRequestParameter=(InjectRequestParameter)a;
name=injectRequestParameter.value();
classFieldInfo=new ClassFieldInfo();
classFieldInfo.setName(name);
classFieldInfo.setField(field);
classFields.add(classFieldInfo);

}




}
}



Method []methods=c.getDeclaredMethods();

// loop for method starts here
for(Method method : methods)
{
forwardToOnMethod="";
an=method.getAnnotation(Path.class);
if(an==null) continue;
path=(Path)an;
pathOnMethod=path.value();
fullPath=pathOnClass+pathOnMethod;
service=new Service();
service.setServiceClass(c);
service.setPath(fullPath);
service.setService(method);
service.setAutoWiredProperties(autoWiredProperties);
service.setClassFields(classFields);
annotations=method.getAnnotations();
for(Annotation a: annotations)
{
if(a instanceof SecuredAccess)
{
isSecuredService=true;
securedAccess=(SecuredAccess)a;
checkPostName=securedAccess.checkPost();
guardName=securedAccess.guard();
}
if(a instanceof GET)
{
service.setIsGetAllowed(true);
}
if(a instanceof POST)
{
service.setIsPostAllowed(true);
}
if(a instanceof FORWARD)
{
forward=(FORWARD)a;
forwardToOnMethod=forward.value();
}

if(a instanceof OnStartup)
{
if(method.getReturnType()!=void.class && method.getParameterCount()>0)
{
throw new ServiceException("Method : "+method.getName()+" cannot be considered as a startup method as return type is not void");
}
if(method.getParameterCount()>0)
{
throw new ServiceException("Method : "+method.getName()+" cannot be considered as a startup method as parameters are not zero");
}

onStartup=(OnStartup)a;
service.setOnStartup(true);
service.setPriority(onStartup.priority());

for(e=0;e<onStartupServices.size();e++)
{
if(onStartupServices.get(e).getPriority()>service.getPriority())
{
onStartupServices.add(e,service);
break;
}
}

if(e==onStartupServices.size())
{
onStartupServices.add(service);
}





}
}

if(isSecuredService)
{
try
{
checkPost=Class.forName(checkPostName);
m=checkPost.getMethods();
found=0;
index=0;
for(e=0;e<m.length;e++)
{
if(m[e].getName().equals(guardName))
{
index=e;
found++;
}
}
if(found==0) throw new ServiceException("Method "+guardName+"not found in class "+checkPost);
if(found>1) throw new ServiceException("Referance to method : "+guardName+" is ambiguous in class "+checkPost);
service.setIsSecuredService(true);
securityServiceMethod=m[index];
// analyze the annotations applied on class
securityService=new Service();
securityService.setServiceClass(checkPost);
securityService.setService(securityServiceMethod);
annotations=checkPost.getAnnotations();
for(Annotation a:annotations)
{
if(a instanceof FORWARD)
{
forward=(FORWARD)a;
securityService.setForwardTo(forward.value());
}
if(a instanceof InjectApplicationDirectory)
{
securityService.setInjectApplicationDirectory(true);
}
if(a instanceof InjectApplicationScope)
{
securityService.setInjectApplicationScope(true);
}
if(a instanceof InjectSessionScope)
{
securityService.setInjectSessionScope(true);
}
if(a instanceof InjectRequestScope)
{
securityService.setInjectRequestScope(true);
}

}
// analyze the annotations applied on Fields
fields=checkPost.getDeclaredFields();
autoWiredPropertiesOfSecurityService=new ArrayList<>();
classFieldsOfSecurityService=new ArrayList<>();
for(Field field:fields)
{
annotations=field.getAnnotations();
for(Annotation a:annotations)
{
if(a instanceof AutoWired)
{
autoWired=(AutoWired)a;
autoWiredProperty=new AutoWiredProperty();
autoWiredProperty.setName(autoWired.name());
autoWiredProperty.setField(field);
autoWiredPropertiesOfSecurityService.add(autoWiredProperty);
}
if(a instanceof InjectRequestParameter)
{
injectRequestParameter=(InjectRequestParameter)a;
name=injectRequestParameter.value();
classFieldInfo=new ClassFieldInfo();
classFieldInfo.setName(name);
classFieldInfo.setField(field);
classFieldsOfSecurityService.add(classFieldInfo);
}
}
}
securityService.setAutoWiredProperties(autoWiredPropertiesOfSecurityService);
securityService.setClassFields(classFieldsOfSecurityService);
// analyze the annotations applied on Method

annotations=securityServiceMethod.getAnnotations();
for(Annotation a: annotations)
{
if(a instanceof FORWARD)
{
forward=(FORWARD)a;
securityService.setForwardTo(forward.value());
}
}


parameters=securityServiceMethod.getParameters();
requestParametersOfSecurityService=new ArrayList<>();
for(e=0;e<parameters.length;e++)
{
an=parameters[e].getAnnotation(RequestParameter.class);
if(an!=null)
{
requestParameter=(RequestParameter)an;
name=requestParameter.value();
requestParameterInfo=new RequestParameterInfo();
requestParameterInfo.setName(name);
requestParameterInfo.setType(parameters[e].getType());
requestParameterInfo.setIndex(e);
requestParametersOfSecurityService.add(requestParameterInfo);


}
}

securityService.setRequestParameters(requestParametersOfSecurityService);

service.setSecurityService(securityService);
}catch(ClassNotFoundException classNotFoundException)
{
throw new ServiceException(classNotFoundException.getMessage());
}

}// if condition for securedService ends here
if(isGetAllowed==false && isPostAllowed==false && service.getIsGetAllowed()==false && service.getIsPostAllowed()==false )
{
service.setIsGetAllowed(true);
service.setIsPostAllowed(true);
}
else
{
if(service.getIsGetAllowed()==false) service.setIsGetAllowed(isGetAllowed);
if(service.getIsPostAllowed()==false) service.setIsPostAllowed(isPostAllowed);
}

if(forwardToOnMethod.length()>0) service.setForwardTo(forwardToOnMethod);
else if(forwardToOnClass.length()>0) service.setForwardTo(forwardToOnClass);
service.setInjectApplicationDirectory(injectApplicationDirectory);
service.setInjectApplicationScope(injectApplicationScope);
service.setInjectSessionScope(injectSessionScope);
service.setInjectRequestScope(injectRequestScope);

// analyze method parameters

parameters=method.getParameters();
requestParameters=new ArrayList<>();
for(e=0;e<parameters.length;e++)
{
an=parameters[e].getAnnotation(RequestParameter.class);
if(an!=null)
{
requestParameter=(RequestParameter)an;
name=requestParameter.value();
requestParameterInfo=new RequestParameterInfo();
requestParameterInfo.setName(name);
requestParameterInfo.setType(parameters[e].getType());
requestParameterInfo.setIndex(e);
requestParameters.add(requestParameterInfo);

}
}
service.setRequestParameters(requestParameters);
webRockModel.pathMappings.put(fullPath,service);

if(isServiceClass) serviceClassMethods.add(service);


}// loop for method ends here

}catch(Exception exception)
{
System.out.println(exception);
}


if(isServiceClass)
{
serviceClassInfo.setServices(serviceClassMethods);
serviceClasses.add(serviceClassInfo);
}
}// if condition for class



} // else condition

} // for loop ends


}
public void init()
{
try
{
String name=getInitParameter("SERVICE_PACKAGE_PREFIX");
String absolutePath=getServletContext().getRealPath(File.separator);
String servicePath=absolutePath+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+name;
File file=new File(servicePath);
File files[]=file.listFiles();
WebRockModel webRockModel=new WebRockModel();
traverseDirectory(webRockModel,files,name);
ServletContext servletContext=getServletContext();
servletContext.setAttribute("webRockModel",webRockModel);
Class c;
Object obj;
Method m;
Object arguments[]={};
Object arg[];
Class parameters[];
File f;
for(Service s: onStartupServices)
{
c=s.getServiceClass();
obj=c.newInstance();
if(s.getInjectApplicationDirectory()==true)
{
try
{
parameters=new Class[1];
arguments=new Object[1];
parameters[0]=ApplicationDirectory.class;
m=c.getMethod("setApplicationDirectory",parameters);
f=new File(servletContext.getRealPath(File.separator));
arguments[0]=new ApplicationDirectory(f);
m.invoke(obj,arguments);
}catch(NoSuchMethodException noSuchMethodException)
{
// do nothing
}
}
if(s.getInjectApplicationScope()==true)
{
try
{
parameters=new Class[1];
arguments=new Object[1];
parameters[0]=ApplicationScope.class;
m=c.getMethod("setApplicationScope",parameters);
arguments[0]=new ApplicationScope(servletContext);
m.invoke(obj,arguments);
}catch(NoSuchMethodException noSuchMethodException)
{
// do nothing
}
}

arguments=new Object[0];
m=s.getService();
m.invoke(obj,arguments);

}

// code to generate js file starts here
String jsFileName=getInitParameter("JsFile");
boolean isJsFileSpecified=false;
if(jsFileName!=null && jsFileName.length()>0) isJsFileSpecified=true;


String dirPath=absolutePath+File.separator+"WEB-INF"+File.separator+"js";
File dir=new File(dirPath);
if(!(dir.exists()))
{
dir.mkdirs();
}
File jsFile=null;
RandomAccessFile randomAccessFile=null;
if(isJsFileSpecified)
{
jsFile=new File(dirPath+File.separator+jsFileName);
if(jsFile.exists()) jsFile.delete();
}
List<String> list=new ArrayList<>();
String fieldName,tmp,setterName,getterName;
String className;
Field []fields;
for(Class cls:pojoClasses)
{

className=cls.getSimpleName();
if(!(isJsFileSpecified))
{
jsFile=new File(dirPath+File.separator+className+".js");
if(jsFile.exists()) jsFile.delete();
list=new ArrayList<>();
}

fields=cls.getDeclaredFields();



// code to generate pojo starts here

list.add("class "+className);
list.add("{");
list.add("constructor()");
list.add("{");
for(Field field:fields)
{
fieldName=field.getName();
list.add("this."+fieldName+"=null;");
}
list.add("}"); // constructor
// setter getter
for(Field field:fields)
{
fieldName=field.getName();
if(fieldName.charAt(0)>=97 && fieldName.charAt(0)<=122)
{
tmp=fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
}
else
{
tmp=fieldName;
}

setterName="set"+tmp;
list.add(setterName+"("+fieldName+")");
list.add("{");
list.add("this."+fieldName+"="+fieldName+";");
list.add("}");

getterName="get"+tmp;
list.add(getterName+"()");
list.add("{");
list.add("return this."+fieldName+";");
list.add("}");

}

// setter getter 
list.add("}");

if(!(isJsFileSpecified))
{
randomAccessFile=new RandomAccessFile(jsFile,"rw");
for(String g:list)
{
randomAccessFile.writeBytes(g+"\r\n");
}
randomAccessFile.close();
}



// code to generate pojo ends here
}


Class serviceClass;
List<Service> serviceClassMethods;
String url,str;
String methodType;
Method method;
String contentType;
String urlMapping;
int parameterCount;
int requestParameterCount;
boolean sendData=false;
boolean sendJson=false;
List<RequestParameterInfo> requestParameters;
ServletRegistration servletRegistration=servletContext.getServletRegistration("TMWebRock");
urlMapping="";
if(servletRegistration!=null)
{
Collection<String> urlPatterns=servletRegistration.getMappings();
int sp,ep;
for(String ss:urlPatterns)
{
sp=ss.indexOf("/");
ep=ss.lastIndexOf("/");
urlMapping=ss.substring(sp+1,ep);
}
}// if condition for servletRegistration


for(ServiceClassInfo s:serviceClasses)
{
serviceClass=s.getServiceClass();
serviceClassMethods=s.getServices();

className=serviceClass.getSimpleName();
if(!(isJsFileSpecified))
{
jsFile=new File(dirPath+File.separator+className+".js");
if(jsFile.exists()) jsFile.delete();
list=new ArrayList<>();
}



list.add("class "+className);
list.add("{");

for(Service ser:serviceClassMethods)
{
sendData=false;
sendJson=false;
contentType="";
methodType="POST";
method=ser.getService();
parameterCount=method.getParameterCount();
if(parameterCount==0)
{
if(ser.getIsGetAllowed()) methodType="GET";
else methodType="POST";
sendData=false;
}
else
{
sendData=true;
requestParameters=ser.getRequestParameters();
requestParameterCount=0;
if(requestParameters!=null) requestParameterCount=requestParameters.size();
if(requestParameterCount>0)
{
if(ser.getIsPostAllowed()) methodType="POST";
else methodType="GET";
contentType="application/x-www-form-urlencoded";
sendJson=false;
}
else
{
if(ser.getIsPostAllowed()) methodType="POST";
else methodType="GET";
contentType="application/json";
sendJson=true;
}
}



url=urlMapping+ser.getPath();
str=method.getName()+"(data";

str=str+")";
list.add(str);
list.add("{");
str="var promise=new Promise(function(resolve,reject){";
list.add(str);
list.add("$.ajax({");

list.add("\"url\" : \""+url+"\",");
list.add("\"method\" : \""+methodType+"\",");
if(sendData)
{
list.add("\"contentType\" : \""+contentType+"\",");
if(sendJson) list.add("\"data\" : JSON.stringify(data),");
else list.add("\"data\" : data,");
}
list.add("\"success\" : function(responseData){");
list.add("if(responseData.success==true)");
list.add("{");
list.add("resolve(responseData.result);");
list.add("}");
list.add("else");
list.add("{");
list.add("reject(responseData.error.detailMessage);");
list.add("}");
list.add("},");
list.add("\"failure\" : function(){");
list.add("reject(\"Some problem\");");
list.add("}");



list.add("});");
list.add("});");
list.add("return promise;");
list.add("}");

}

list.add("}");
if(!(isJsFileSpecified))
{
randomAccessFile=new RandomAccessFile(jsFile,"rw");
for(String k:list)
{
randomAccessFile.writeBytes(k+"\r\n");
}
randomAccessFile.close();
}

}

if(isJsFileSpecified)
{
randomAccessFile=new RandomAccessFile(jsFile,"rw");
for(String s:list)
{
randomAccessFile.writeBytes(s+"\r\n");
}
randomAccessFile.close();
}





}catch(Exception exception)
{
try
{
System.out.println(exception.getMessage());
}catch(Exception e)
{

}
}
}
public void doGet(HttpServletRequest request, HttpServletResponse response)
{

}
public void doPost(HttpServletRequest request, HttpServletResponse response)
{

}

}
package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import com.thinking.machines.webrock.model.*;
import com.thinking.machines.webrock.pojo.*;
import com.thinking.machines.webrock.exceptions.*;
import com.thinking.machines.webrock.annotations.*;
import java.lang.reflect.*;
import java.io.*;
import com.google.gson.*;
public class TMWebRock extends HttpServlet
{
private static WebRockModel webRockModel=null;
public void init()
{
ServletContext servletContext=getServletContext();
webRockModel=(WebRockModel)servletContext.getAttribute("webRockModel");
}
private void processRequest(Service service,HttpServletRequest request,HttpServletResponse response,ServletContext servletContext,Object ...args) throws ServiceException
{
ServiceObject serviceObject=null;
PrintWriter pw=null;
Gson gson=null;
try
{
Class c=service.getServiceClass();
Object obj=c.newInstance();
Method m;
Object result=null;
Class parameters[];
Object arguments[];
Object jsonData;
Object argumentValue;
HttpSession httpSession;
httpSession=request.getSession();
int argLen,parameterIndex,index,x,e;
String value;
RequestScope requestScope=new RequestScope(request);
SessionScope sessionScope=new SessionScope(httpSession);
ApplicationScope applicationScope=new ApplicationScope(servletContext);
ApplicationDirectory applicationDirectory=new ApplicationDirectory(new File(servletContext.getRealPath(File.separator)));
Class parameterType=null;
gson=new Gson();
List<RequestParameterInfo> requestParameters;
List<ClassFieldInfo> classFields;
pw=response.getWriter();
response.setContentType("application/json");
Class propertyClass=null;
Class securityServiceClass;
Method securityServiceMethod;
Service securityService;
List<AutoWiredProperty> autoWiredProperties;
String name;
Field field;
Object property,securityServiceObject;
String fieldName;
String methodName;
String tmp;
Class fieldClass;

if(service.getIsSecuredService())
{
securityService=service.getSecurityService();
securityServiceClass=securityService.getServiceClass();
securityServiceObject=securityServiceClass.newInstance();
if(securityService.getInjectApplicationDirectory()==true)
{
try
{
parameters=new Class[1];
arguments=new Object[1];
parameters[0]=ApplicationDirectory.class;
m=securityServiceClass.getMethod("setApplicationDirectory",parameters);
arguments[0]=applicationDirectory;
m.invoke(securityServiceObject,arguments);
}catch(NoSuchMethodException noSuchMethodException)
{
// do nothing

}
}
// error
if(securityService.getInjectApplicationScope()==true)
{
try
{
parameters=new Class[1];
arguments=new Object[1];
parameters[0]=ApplicationScope.class;
m=securityServiceClass.getMethod("setApplicationScope",parameters);
arguments[0]=applicationScope;
m.invoke(securityServiceObject,arguments);
}catch(NoSuchMethodException noSuchMethodException)
{
// do nothing
}
}
if(securityService.getInjectSessionScope()==true)
{
try
{
parameters=new Class[1];
arguments=new Object[1];
parameters[0]=SessionScope.class;
m=securityServiceClass.getMethod("setSessionScope",parameters);
arguments[0]=sessionScope;
m.invoke(securityServiceObject,arguments);
}catch(NoSuchMethodException noSuchMethodException)
{
// do nothing
}
}
if(securityService.getInjectRequestScope()==true)
{
try
{
parameters=new Class[1];
arguments=new Object[1];
parameters[0]=RequestScope.class;
m=securityServiceClass.getMethod("setRequestScope",parameters);
arguments[0]=requestScope;
m.invoke(securityServiceObject,arguments);
}catch(NoSuchMethodException noSuchMethodException)
{
// do nothing
}
}

autoWiredProperties=securityService.getAutoWiredProperties();
for(AutoWiredProperty autoWiredProperty:autoWiredProperties)
{
name=autoWiredProperty.getName();
field=autoWiredProperty.getField();
fieldName=field.getName();
if(fieldName.charAt(0)>=97 && fieldName.charAt(0)<=122)
{
tmp=fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
}
else
{
tmp=fieldName;
}
methodName="set"+tmp;
fieldClass=field.getType();
parameters=new Class[1];
parameters[0]=fieldClass;
arguments=new Object[1];
try
{
m=securityServiceClass.getMethod(methodName,parameters);
}catch(NoSuchMethodException noSuchMethodExcetion)
{
throw new ServiceException("Setter not found for : "+fieldName+" of class : "+c.getName());
}


property=request.getAttribute(name);
if(property==null)
{
property=httpSession.getAttribute(name);
if(property==null)
{
property=servletContext.getAttribute(name);
}
}
if(property!=null) 
{
if(fieldClass.isAssignableFrom(property.getClass()))
{
arguments[0]=property;
m.invoke(securityServiceClass,arguments);
}
else
{
throw new ServiceException("Argument type mismatch for auto wired property : "+field.getName());
}
}
}

classFields=securityService.getClassFields();
for(ClassFieldInfo classField:classFields)
{
name=classField.getName();
property=request.getAttribute(name);
if(property==null) continue;
field=classField.getField();
fieldName=field.getName();
if(fieldName.charAt(0)>=97 && fieldName.charAt(0)<=122)
{
tmp=fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
}
else
{
tmp=fieldName;
}
methodName="set"+tmp;
fieldClass=field.getType();
parameters=new Class[1];
parameters[0]=fieldClass;
arguments=new Object[1];
arguments[0]=property;
propertyClass=property.getClass();
if(fieldClass.isPrimitive())
{
if(fieldClass==byte.class && propertyClass==java.lang.Byte.class) propertyClass=byte.class;
if(fieldClass==short.class && propertyClass==java.lang.Short.class) propertyClass=short.class;
if(fieldClass==int.class && propertyClass==java.lang.Integer.class) propertyClass=int.class;
if(fieldClass==long.class && propertyClass==java.lang.Long.class) propertyClass=long.class;
if(fieldClass==float.class && propertyClass==java.lang.Float.class) propertyClass=float.class;
if(fieldClass==double.class && propertyClass==java.lang.Double.class) propertyClass=double.class;
if(fieldClass==char.class && propertyClass==java.lang.Character.class) propertyClass=char.class;
}
try
{
m=securityServiceClass.getMethod(methodName,parameters);
if(fieldClass.isAssignableFrom(propertyClass)==false)
{
throw new ServiceException("Argument type mismatch for field : "+fieldName+" of class : "+c.getName());
}
m.invoke(securityServiceObject,arguments);

}catch(NoSuchMethodException noSuchMethodExcetion)
{
throw new ServiceException("Method : "+methodName+" not found");
}catch(InvocationTargetException invocationTargetException)
{
throw new ServiceException(invocationTargetException.getCause().getMessage());
}
}
securityServiceMethod=securityService.getService();
parameters=securityServiceMethod.getParameterTypes();
arguments=new Object[parameters.length];
for(e=0;e<parameters.length;e++)
{
if(parameters[e]==RequestScope.class)
{
arguments[e]=requestScope;
}
else if(parameters[e]==SessionScope.class)
{
arguments[e]=sessionScope;
}
else if(parameters[e]==ApplicationScope.class)
{
arguments[e]=applicationScope;
}
else if(parameters[e]==ApplicationDirectory.class)
{
arguments[e]=applicationDirectory;
}
}
try
{
securityServiceMethod.invoke(securityServiceObject,arguments);
}catch(InvocationTargetException invocationTargetException)
{
throw new ServiceException(invocationTargetException.getCause().getMessage());
}

} // if condition for security service ends here




if(service.getInjectApplicationDirectory()==true)
{
try
{
parameters=new Class[1];
arguments=new Object[1];
parameters[0]=ApplicationDirectory.class;
m=c.getMethod("setApplicationDirectory",parameters);
arguments[0]=applicationDirectory;
m.invoke(obj,arguments);
}catch(NoSuchMethodException noSuchMethodException)
{
// do nothing
}
}
if(service.getInjectApplicationScope()==true)
{
try
{
parameters=new Class[1];
arguments=new Object[1];
parameters[0]=ApplicationScope.class;
m=c.getMethod("setApplicationScope",parameters);
arguments[0]=applicationScope;
m.invoke(obj,arguments);
}catch(NoSuchMethodException noSuchMethodException)
{
// do nothing
}
}
if(service.getInjectSessionScope()==true)
{
try
{
parameters=new Class[1];
arguments=new Object[1];
parameters[0]=SessionScope.class;
m=c.getMethod("setSessionScope",parameters);
arguments[0]=sessionScope;
m.invoke(obj,arguments);
}catch(NoSuchMethodException noSuchMethodException)
{
// do nothing
}
}
if(service.getInjectRequestScope()==true)
{
try
{

parameters=new Class[1];
arguments=new Object[1];
parameters[0]=RequestScope.class;
m=c.getMethod("setRequestScope",parameters);
arguments[0]=requestScope;
m.invoke(obj,arguments);
}catch(NoSuchMethodException noSuchMethodException)
{
// do nothing
}
}
autoWiredProperties=service.getAutoWiredProperties();
for(AutoWiredProperty autoWiredProperty:autoWiredProperties)
{
name=autoWiredProperty.getName();
field=autoWiredProperty.getField();
fieldName=field.getName();
if(fieldName.charAt(0)>=97 && fieldName.charAt(0)<=122)
{
tmp=fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
}
else
{
tmp=fieldName;
}
methodName="set"+tmp;
fieldClass=field.getType();
parameters=new Class[1];
parameters[0]=fieldClass;
arguments=new Object[1];
try
{
m=c.getMethod(methodName,parameters);
}catch(NoSuchMethodException noSuchMethodExcetion)
{
throw new ServiceException("Method : "+methodName+" not found");
}

property=request.getAttribute(name);
if(property==null)
{
property=httpSession.getAttribute(name);
if(property==null)
{
property=servletContext.getAttribute(name);
}
}
if(property!=null) 
{
if(fieldClass.isAssignableFrom(property.getClass()))
{
arguments[0]=property;
m.invoke(obj,arguments);
}
else
{
throw new ServiceException("Argument type mismatch for auto wired property : "+field.getName());
}
}

}



classFields=service.getClassFields();
for(ClassFieldInfo classField:classFields)
{
name=classField.getName();
property=request.getAttribute(name);
if(property==null) continue;
field=classField.getField();
fieldName=field.getName();
if(fieldName.charAt(0)>=97 && fieldName.charAt(0)<=122)
{
tmp=fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
}
else
{
tmp=fieldName;
}
methodName="set"+tmp;
fieldClass=field.getType();
parameters=new Class[1];
parameters[0]=fieldClass;
arguments=new Object[1];
arguments[0]=property;
propertyClass=property.getClass();
if(fieldClass.isPrimitive())
{
if(fieldClass==int.class && propertyClass==java.lang.Integer.class) propertyClass=int.class;
}

try
{
m=c.getMethod(methodName,parameters);
if(fieldClass.isAssignableFrom(propertyClass)==false)
{
throw new ServiceException("Argument type mismatch for field : "+fieldName+" of class : "+c.getName());
}
m.invoke(obj,arguments);

}catch(NoSuchMethodException noSuchMethodExcetion)
{
throw new ServiceException("Method : "+methodName+" not found");
}catch(InvocationTargetException invocationTargetException)
{
throw new ServiceException(invocationTargetException.getCause().getMessage());
}
}
boolean returnType;
Method method=service.getService();
if(method.getReturnType()==void.class) returnType=false;
else returnType=true;
parameters=method.getParameterTypes();
arguments=new Object[parameters.length];
for(e=0;e<parameters.length;e++)
{
if(parameters[e]==RequestScope.class)
{
arguments[e]=requestScope;
}
else if(parameters[e]==SessionScope.class)
{
arguments[e]=sessionScope;
}
else if(parameters[e]==ApplicationScope.class)
{
arguments[e]=applicationScope;
}
else if(parameters[e]==ApplicationDirectory.class)
{
arguments[e]=applicationDirectory;
}

else if(args.length>0)
{
if(parameters[e].isPrimitive())
{
if(parameters[e]==boolean.class) parameterType=java.lang.Boolean.class;
if(parameters[e]==int.class) parameterType=java.lang.Integer.class;
if(parameters[e]==short.class) parameterType=java.lang.Short.class;
if(parameters[e]==byte.class) parameterType=java.lang.Byte.class;
if(parameters[e]==char.class) parameterType=java.lang.Character.class;
if(parameters[e]==float.class) parameterType=java.lang.Float.class;
if(parameters[e]==double.class) parameterType=java.lang.Double.class;
if(parameters[e]==long.class) parameterType=java.lang.Long.class;
if(parameterType!=args[e].getClass())
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
throw new ServiceException("Argument type mismatch for parameter : "+parameters[e]+" of class : "+c.getName());
}
}
else if(parameters[e].isAssignableFrom(args[e].getClass())==false)
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
throw new ServiceException("Argument type mismatch for parameter : "+parameters[e]+" of class : "+c.getName());
}
arguments[e]=args[e];
}
}
String contentType=request.getContentType();
// if condition for json data
if(contentType!=null && contentType.equalsIgnoreCase("application/json") && request.getContentLength()>0)
{
BufferedReader bufferedReader=request.getReader();
StringBuffer stringBuffer=new StringBuffer();
String s;
while(true)
{
s=bufferedReader.readLine();
if(s==null) break;
stringBuffer.append(s);
}
String rawData=stringBuffer.toString();
if(parameters.length==0) {}
else if(parameters.length==1)
{
if(parameters[0]==RequestScope.class || parameters[0]==SessionScope.class || parameters[0]==ApplicationScope.class || parameters[0]==ApplicationDirectory.class)
{
// do nothing
throw new ServiceException("Method parameter type is not compatible with json object");
}
else
{
if(parameters[0].isAnnotationPresent(RequestParameter.class))
{
throw new ServiceException("RequestParameter annotation should not be present on a parameter for accepting json object");
}
else
{
try
{
jsonData=gson.fromJson(rawData,parameters[0]);
}catch(JsonSyntaxException jsonSyntaxException)
{
throw new ServiceException(jsonSyntaxException.getMessage());
}
arguments[0]=jsonData;
}
}
}
else if(parameters.length>1)
{
x=0;
index=0;
for(e=0;e<parameters.length;e++)
{
if(parameters[e]==RequestScope.class || parameters[e]==SessionScope.class || parameters[e]==ApplicationScope.class || parameters[e]==ApplicationDirectory.class || parameters[e].isAnnotationPresent(RequestParameter.class))
{
x++;
}
else
{
index=e;
}
}

if(x!=parameters.length-1)
{
throw new ServiceException("Method should have one parameter to accept json object as argument");
}
else
{
if(parameters[index].isAnnotationPresent(RequestParameter.class))
{
throw new ServiceException("RequestParameter annotation should not be present on a parameter for accepting json object");
}
else
{
try
{
jsonData=jsonData=gson.fromJson(rawData,parameters[index]);
}catch(JsonSyntaxException jsonSyntaxException)
{
throw new ServiceException(jsonSyntaxException.getMessage());
}
arguments[index]=jsonData;
}
}
}
}// if condition for json data ends



requestParameters=service.getRequestParameters();
argumentValue=null;
for(e=0;e<requestParameters.size();e++)
{
parameterIndex=requestParameters.get(e).getIndex();
System.out.println(requestParameters.get(e).getName());
System.out.println(request.getQueryString());
value=request.getParameter(requestParameters.get(e).getName());
System.out.println(value);
if(value==null)
{
System.out.println("1111111111111111111111111111111");
argumentValue=null;
}
else
{
if(parameters[parameterIndex]==int.class || parameters[parameterIndex]==java.lang.Integer.class)
{
System.out.println("If condition for int ");
try
{
argumentValue=Integer.parseInt(value);
}catch(NumberFormatException numberFormatException)
{
argumentValue=null;
System.out.println("catch for int");
}
}

if(parameters[parameterIndex]==byte.class || parameters[parameterIndex]==java.lang.Byte.class)
{
try
{
argumentValue=Byte.parseByte(value);
}catch(NumberFormatException numberFormatException)
{
argumentValue=null;
throw new ServiceException(numberFormatException.getMessage());
}
}

if(parameters[parameterIndex]==short.class || parameters[parameterIndex]==java.lang.Short.class)
{
try
{
argumentValue=Short.parseShort(value);
}catch(NumberFormatException numberFormatException)
{
argumentValue=null;
}
}

if(parameters[parameterIndex]==long.class || parameters[parameterIndex]==java.lang.Long.class)
{
try
{
argumentValue=Long.parseLong(value);
}catch(NumberFormatException numberFormatException)
{
argumentValue=null;
}
}

if(parameters[parameterIndex]==float.class || parameters[parameterIndex]==java.lang.Float.class)
{
try
{
argumentValue=Float.parseFloat(value);
}catch(NumberFormatException numberFormatException)
{
argumentValue=null;
}
}

if(parameters[parameterIndex]==double.class || parameters[parameterIndex]==java.lang.Double.class)
{
try
{
argumentValue=Double.parseDouble(value);
}catch(NumberFormatException numberFormatException)
{
argumentValue=null;
}
}

if(parameters[parameterIndex]==boolean.class || parameters[parameterIndex]==java.lang.Boolean.class)
{
argumentValue=Boolean.parseBoolean(value);
}

if(parameters[parameterIndex]==char.class || parameters[parameterIndex]==java.lang.Character.class)
{
argumentValue=value.charAt(0);
}
if(parameters[parameterIndex]==java.lang.String.class)
{
argumentValue=value;
}
arguments[parameterIndex]=argumentValue;
}
}

try
{
result=method.invoke(obj,arguments);
serviceObject=new ServiceObject();
serviceObject.setSuccess(true);
serviceObject.setResult(result);
serviceObject.setError(null);
}catch(InvocationTargetException invocationTargetException)
{
System.out.println("Invocation target exception");
throw new ServiceException(invocationTargetException.getCause().getMessage());
}
String forwardTo=service.getForwardTo();
File file;
int parameterCount=0;
if(forwardTo!=null)
{
Service forwardToService=webRockModel.pathMappings.get(forwardTo);
if(forwardToService==null)
{
file=new File(servletContext.getRealPath(forwardTo));
if(!file.exists())
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
return;
}
else
{
RequestDispatcher requestDispatcher=request.getRequestDispatcher(forwardTo);
requestDispatcher.forward(request,response);
return;
}
}
else
{
parameterCount=forwardToService.getService().getParameterCount();
if(returnType==true && parameterCount==1)processRequest(forwardToService,request,response,servletContext,result);
else processRequest(forwardToService,request,response,servletContext);
}

}
else
{
System.out.println("666666666666666666666666666666");
pw.print(gson.toJson(serviceObject));
pw.flush();
}


}catch(Exception exception)
{
try
{
System.out.println(exception);
serviceObject=new ServiceObject();
serviceObject.setSuccess(false);
serviceObject.setResult(null);
serviceObject.setError(exception);
pw.print(gson.toJson(serviceObject));
pw.flush();
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception e)
{

}
}

}

public void doPost(HttpServletRequest request, HttpServletResponse response)
{
try
{
String path=request.getPathInfo();
ServletContext servletContext=getServletContext();
Service service=webRockModel.pathMappings.get(path);
if(service==null)
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
return;
}
if(service!=null)
{
System.out.println("Service path from map : "+service.getPath());
}
if(service.getIsPostAllowed()==false) 
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
processRequest(service,request,response,servletContext);
}catch(Exception exception)
{
try
{
System.out.println("22222222222222222222222222");
System.out.println(exception);
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception e)
{

}
}
}



public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try
{
String path=request.getPathInfo();
ServletContext servletContext=getServletContext();
WebRockModel webRockModel=(WebRockModel)servletContext.getAttribute("webRockModel");
Service service=webRockModel.pathMappings.get(path);
if(service==null)
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
return;
}
if(service!=null)
{
System.out.println("Serivce path from map : "+service.getPath());
System.out.println("Is Get Allowed "+service.getIsGetAllowed());
System.out.println("Is Post Allowed "+service.getIsPostAllowed());
}
if(service.getIsGetAllowed()==false) 
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
processRequest(service,request,response,servletContext);
}catch(Exception exception)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception e)
{

}
}
}

}
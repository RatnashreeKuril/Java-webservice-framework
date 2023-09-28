package com.thinking.machines.webrock.pojo;
import java.lang.reflect.*;
import java.util.*;
public class Service
{
private Class serviceClass;
private String path;
private Method service;
private boolean isGetAllowed;
private boolean isPostAllowed;
private String forwardTo;
private boolean onStartup;
private int priority;
private boolean injectSessionScope;
private boolean injectApplicationDirectory;
private boolean injectApplicationScope;
private boolean injectRequestScope;
private List<AutoWiredProperty> autoWiredProperties;
private List<RequestParameterInfo> requestParameters;
private List<ClassFieldInfo> classFields;
private boolean isSecuredService;
private Service securityService;
public Service()
{
this.serviceClass=null;
this.path="";
this.service=null;
this.isGetAllowed=false;
this.isPostAllowed=false;
this.forwardTo=null;
this.onStartup=false;
this.priority=-1;
this.injectSessionScope=false;
this.injectApplicationDirectory=false;
this.injectApplicationScope=false;
this.injectRequestScope=false;
this.autoWiredProperties=new ArrayList<>();
this.requestParameters=new ArrayList<>();
this.classFields=new ArrayList<>();
this.isSecuredService=false;
this.securityService=null;
}
public void setServiceClass(java.lang.Class serviceClass)
{
this.serviceClass=serviceClass;
}
public java.lang.Class getServiceClass()
{
return this.serviceClass;
}
public void setPath(java.lang.String path)
{
this.path=path;
}
public java.lang.String getPath()
{
return this.path;
}
public void setService(java.lang.reflect.Method service)
{
this.service=service;
}
public java.lang.reflect.Method getService()
{
return this.service;
}
public void setIsGetAllowed(boolean isGetAllowed)
{
this.isGetAllowed=isGetAllowed;
}
public boolean getIsGetAllowed()
{
return this.isGetAllowed;
}
public void setIsPostAllowed(boolean isPostAllowed)
{
this.isPostAllowed=isPostAllowed;
}
public boolean getIsPostAllowed()
{
return this.isPostAllowed;
}
public void setForwardTo(String forwardTo)
{
this.forwardTo=forwardTo;
}
public String getForwardTo()
{
return this.forwardTo;
}
public void setOnStartup(boolean onStartup)
{
this.onStartup=onStartup;
}
public boolean getOnStartup()
{
return this.onStartup;
}
public void setPriority(int priority)
{
this.priority=priority;
}
public int getPriority()
{
return this.priority;
}
public void setInjectSessionScope(boolean injectSessionScope)
{
this.injectSessionScope=injectSessionScope;
}
public boolean getInjectSessionScope()
{
return this.injectSessionScope;
}
public void setInjectApplicationDirectory(boolean injectApplicationDirectory)
{
this.injectApplicationDirectory=injectApplicationDirectory;
}
public boolean getInjectApplicationDirectory()
{
return this.injectApplicationDirectory;
}
public void setInjectApplicationScope(boolean injectApplicationScope)
{
this.injectApplicationScope=injectApplicationScope;
}
public boolean getInjectApplicationScope()
{
return this.injectApplicationScope;
}
public void setInjectRequestScope(boolean injectRequestScope)
{
this.injectRequestScope=injectRequestScope;
}
public boolean getInjectRequestScope()
{
return this.injectRequestScope;
}
public void setAutoWiredProperties(List<AutoWiredProperty> autoWiredProperties)
{
this.autoWiredProperties=autoWiredProperties;
}
public List<AutoWiredProperty> getAutoWiredProperties()
{
return this.autoWiredProperties;
}
public void setRequestParameters(List<RequestParameterInfo> requestParameters)
{
this.requestParameters=requestParameters;
}
public List<RequestParameterInfo> getRequestParameters()
{
return this.requestParameters;
}
public void setClassFields(List<ClassFieldInfo> classFields)
{
this.classFields=classFields;
}
public List<ClassFieldInfo> getClassFields()
{
return this.classFields;
}
public void setIsSecuredService(boolean isSecuredService)
{
this.isSecuredService=isSecuredService;
}
public boolean getIsSecuredService()
{
return this.isSecuredService;
}
public void setSecurityService(Service securityService)
{
this.securityService=securityService;
}
public Service getSecurityService()
{
return this.securityService;
}

}
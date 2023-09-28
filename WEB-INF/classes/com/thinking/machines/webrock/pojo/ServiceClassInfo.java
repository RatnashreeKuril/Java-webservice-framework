package com.thinking.machines.webrock.pojo;
import java.util.*;
public class ServiceClassInfo 
{
private Class serviceClass;
private List<Service> services;
public ServiceClassInfo()
{
this.serviceClass=null;
this.services=null;
}
public void setServiceClass(java.lang.Class serviceClass)
{
this.serviceClass=serviceClass;
}
public java.lang.Class getServiceClass()
{
return this.serviceClass;
}
public void setServices(java.util.List services)
{
this.services=services;
}
public java.util.List getServices()
{
return this.services;
}

}
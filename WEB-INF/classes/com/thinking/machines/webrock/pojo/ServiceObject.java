package com.thinking.machines.webrock.pojo;
public class ServiceObject implements java.io.Serializable
{
private Object result;
private boolean success;
private Exception error;
public ServiceObject()
{
this.result=null;
this.success=false;
this.error=null;
}
public void setResult(java.lang.Object result)
{
this.result=result;
}
public java.lang.Object getResult()
{
return this.result;
}
public void setSuccess(boolean success)
{
this.success=success;
}
public boolean getSuccess()
{
return this.success;
}
public void setError(java.lang.Exception error)
{
this.error=error;
}
public java.lang.Exception getError()
{
return this.error;
}

}
package com.thinking.machines.webrock.pojo;
import java.lang.reflect.*;
public class RequestParameterInfo
{
private String name;
private Class type;
private int index;
private Object value;
public RequestParameterInfo()
{
this.name="";
this.type=null;
this.index=0;
this.value=null;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setType(java.lang.Class type)
{
this.type=type;
}
public java.lang.Class getType()
{
return this.type;
}
public void setIndex(int index)
{
this.index=index;
}
public int getIndex()
{
return this.index;
}
public void setValue(java.lang.Object value)
{
this.value=value;
}
public java.lang.Object getValue()
{
return this.value;
}

}
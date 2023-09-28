package com.thinking.machines.webrock.pojo;
import java.lang.reflect.*;
public class ClassFieldInfo
{
private Field field;
private String name;
public ClassFieldInfo()
{
this.field=null;
this.name="";
}
public void setField(java.lang.reflect.Field field)
{
this.field=field;
}
public java.lang.reflect.Field getField()
{
return this.field;
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
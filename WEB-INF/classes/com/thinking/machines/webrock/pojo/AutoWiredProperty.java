package com.thinking.machines.webrock.pojo;
import java.lang.reflect.*;
public class AutoWiredProperty
{
private String name;
private Field field;
public AutoWiredProperty()
{
this.name="";
this.field=null;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setField(java.lang.reflect.Field field)
{
this.field=field;
}
public java.lang.reflect.Field getField()
{
return this.field;
}

}
package com.thinking.machines.webrock.tools;
import java.lang.reflect.*;
public class ServiceInfo
{
public String path;
public Class c;
public Method method;
public Parameter[] parameters;
public Class returnType;
public boolean injectApplicationDirectory;
public boolean injectApplicationScope;
public boolean injectSessionScope;
public boolean injectRequestScope;
}
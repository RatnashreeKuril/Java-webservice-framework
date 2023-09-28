package com.thinking.machines.webrock.pojo;
import javax.servlet.*;
public class ApplicationScope
{
private ServletContext servletContext;
public ApplicationScope(ServletContext servletContext)
{
this.servletContext=servletContext;
}
public void setAttribute(String name,Object attribute)
{
this.servletContext.setAttribute(name,attribute);
}
public Object getAttribute(String name)
{
return this.servletContext.getAttribute(name);
}
}
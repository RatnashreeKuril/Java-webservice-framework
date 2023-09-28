package com.thinking.machines.webrock.pojo;
import javax.servlet.http.*;
public class RequestScope
{
private HttpServletRequest httpServletRequest;
public RequestScope(HttpServletRequest httpServletRequest)
{
this.httpServletRequest=httpServletRequest;
}
public void setAttribute(String name,Object attribute)
{
this.httpServletRequest.setAttribute(name,attribute);
}
public Object getAttribute(String name)
{
return this.httpServletRequest.getAttribute(name);
}
}
package com.thinking.machines.webrock.pojo;
import javax.servlet.http.*;
public class SessionScope
{
private HttpSession httpSession;
public SessionScope(HttpSession httpSession)
{
this.httpSession=httpSession;
}
public void setAttribute(String name,Object attribute)
{
this.httpSession.setAttribute(name,attribute);
}
public Object getAttribute(String name)
{
return this.httpSession.getAttribute(name);
}
}
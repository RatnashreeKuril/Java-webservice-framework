package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class GetJS extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try
{
String jsFileName=request.getParameter("name");
System.out.println("JS file name is : "+jsFileName);
ServletContext servletContext=getServletContext();
String realPath=servletContext.getRealPath(File.separator);
System.out.println("Real path is : "+realPath);

File file=new File(realPath+File.separator+"WEB-INF"+File.separator+"js"+File.separator+jsFileName);
if(!(file.exists())) 
{
System.out.println(jsFileName+" does not exists");
return;
}
PrintWriter pw=response.getWriter();
response.setContentType("text/javascript");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
pw.println(randomAccessFile.readLine());
pw.flush();
//System.out.println(randomAccessFile.readLine());
}


randomAccessFile.close();

}catch(Exception exception)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception e)
{

}
}
}
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
doGet(request,response);
}

}
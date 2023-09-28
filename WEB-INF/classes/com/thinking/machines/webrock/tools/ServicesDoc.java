package com.thinking.machines.webrock.tools;
import java.io.*;
import java.util.*;
import java.util.jar.*;
import java.io.*;
import java.util.*;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.pojo.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.kernel.font.*;
import com.itextpdf.io.font.constants.*;
import com.itextpdf.layout.property.*;
public class ServicesDoc
{
public static java.util.List<ServiceInfo> pdfList=new java.util.ArrayList<>();
private static void analyzeClass(Class c)
{
ServiceInfo service;
int i,e,found,index;
String className,name,fileName;
Annotation an;
Annotation [] annotations;
Path path;
String fullPath,packageName;
String pathOnClass,pathOnMethod,str;
File parentFile;
boolean isGetAllowed,isPostAllowed;
String forwardToOnClass;
String forwardToOnMethod;
FORWARD forward;
OnStartup onStartup;
int priority;
boolean injectApplicationDirectory;
boolean injectApplicationScope;
boolean injectSessionScope;
boolean injectRequestScope;
Field []fields;
AutoWired autoWired;
AutoWiredProperty autoWiredProperty;
java.util.List<AutoWiredProperty> autoWiredProperties;
java.util.List<RequestParameterInfo> requestParameters;
java.util.List<ClassFieldInfo> classFields;
java.util.List<AutoWiredProperty> autoWiredPropertiesOfSecurityServiceInfo;
java.util.List<ClassFieldInfo> classFieldsOfSecurityServiceInfo;
java.util.List<RequestParameterInfo> requestParametersOfSecurityServiceInfo;
java.util.List<ServiceInfo> serviceClassMethods;
ClassFieldInfo classFieldInfo;
RequestParameter requestParameter;
RequestParameterInfo requestParameterInfo;
Parameter []parameters;
InjectRequestParameter injectRequestParameter;
boolean isSecuredServiceInfo;
String checkPostName;
String guardName;
ServiceInfo securityServiceInfo;
Class checkPost;
Method securityServiceInfoMethod;
Method m[];
SecuredAccess securedAccess;
String parentName;
java.util.List<String> list;
ServiceClassInfo serviceClassInfo;
boolean isServiceInfoClass;
Method[] methods;
try
{
System.out.println(c.getName());
an=c.getAnnotation(Path.class);
if(an==null) return;
path=(Path)an;
pathOnClass=path.value();
annotations=c.getAnnotations();
injectApplicationDirectory=false;
injectApplicationScope=false;
injectSessionScope=false;
injectRequestScope=false;
for(Annotation a:annotations)
{
if(a instanceof InjectApplicationDirectory)
{
injectApplicationDirectory=true;
}
if(a instanceof InjectApplicationScope)
{
injectApplicationScope=true;
}
if(a instanceof InjectSessionScope)
{
injectSessionScope=true;
}
if(a instanceof InjectRequestScope)
{
injectRequestScope=true;
}

}
methods=c.getDeclaredMethods();
for(Method method:methods)
{
an=method.getAnnotation(Path.class);
if(an==null) continue;
path=(Path)an;
pathOnMethod=path.value();
/*
System.out.println("Path : "+pathOnClass+pathOnMethod);
System.out.println("Class : "+c.getName());
System.out.println("Method : "+method.getName());
System.out.println("Return type : "+method.getReturnType());
*/
service=new ServiceInfo();
//pdfList.add("Path : "+pathOnClass+pathOnMethod);
service.path=pathOnClass+pathOnMethod;
//pdfList.add("Class : "+c.getName());
service.c=c;
//pdfList.add("Method : "+method.getName());
service.method=method;
//pdfList.add("Return type : "+method.getReturnType());
service.returnType=method.getReturnType();
service.parameters=method.getParameters();
/*
//System.out.print("Parameters : ");
str="Parameters : ";
e=0;
for(Parameter parameter:parameters)
{
str=str+parameter.getType();
e++;
if(e<parameters.length) str+=",";
}
list.add(str);
*/
//pdfList.add("Injecting application directory : "+injectApplicationDirectory);
service.injectApplicationDirectory=injectApplicationDirectory;
//pdfList.add("Injecting application scope: "+injectApplicationScope);
service.injectApplicationScope=injectApplicationScope;
//pdfList.add("Injecting session scope : "+injectSessionScope);
service.injectSessionScope=injectSessionScope;
//pdfList.add("Injecting request scope : "+injectRequestScope);
service.injectRequestScope=injectRequestScope;
//pdfList.add("------------------------------------------------------------------------------------------");
pdfList.add(service);
}



}catch(Exception exception)
{
System.out.println(exception);
}



}


private static void traverseDirectory(File [] files,String servicePackage)
{
ServiceInfo service;
Class c;
int i,e,found,index;
String className,name,fileName;
Annotation an;
Annotation [] annotations;
Path path;
String fullPath,packageName;
String pathOnClass,pathOnMethod,str;
File parentFile;
boolean isGetAllowed,isPostAllowed;
String forwardToOnClass;
String forwardToOnMethod;
FORWARD forward;
OnStartup onStartup;
int priority;
boolean injectApplicationDirectory;
boolean injectApplicationScope;
boolean injectSessionScope;
boolean injectRequestScope;
Field []fields;
AutoWired autoWired;
AutoWiredProperty autoWiredProperty;
java.util.List<AutoWiredProperty> autoWiredProperties;
java.util.List<RequestParameterInfo> requestParameters;
java.util.List<ClassFieldInfo> classFields;
java.util.List<AutoWiredProperty> autoWiredPropertiesOfSecurityServiceInfo;
java.util.List<ClassFieldInfo> classFieldsOfSecurityServiceInfo;
java.util.List<RequestParameterInfo> requestParametersOfSecurityServiceInfo;
java.util.List<ServiceInfo> serviceClassMethods;
ClassFieldInfo classFieldInfo;
RequestParameter requestParameter;
RequestParameterInfo requestParameterInfo;
Parameter []parameters;
InjectRequestParameter injectRequestParameter;
boolean isSecuredServiceInfo;
String checkPostName;
String guardName;
ServiceInfo securityServiceInfo;
Class checkPost;
Method securityServiceInfoMethod;
Method m[];
SecuredAccess securedAccess;
String parentName;
java.util.List<String> list;
ServiceClassInfo serviceClassInfo;
boolean isServiceInfoClass;
Method[] methods;
for(File f : files)
{
if(f.isDirectory()) 
{
//System.out.println(f.getName());
traverseDirectory(f.listFiles(),servicePackage);
}

else
{
if(f.getName().endsWith(".class"))
{
injectApplicationDirectory=false;
injectApplicationScope=false;
injectSessionScope=false;
injectRequestScope=false;
list=new java.util.ArrayList<>();
parentFile=f.getParentFile();
packageName="";
//list.add(servicePackage);
fileName=f.getName();
while(!(parentFile.getName().equals(servicePackage)))
{
list.add(parentFile.getName());
parentFile=parentFile.getParentFile();
if(parentFile==null) break;
}
list.add(servicePackage);
for(e=list.size()-1;e>=0;e--)
{
//System.out.println(list.get(e));
packageName+=list.get(e);
if(e>0) packageName=packageName+".";
}
i=f.getName().indexOf('.');
className=f.getName().substring(0,i);
try
{
c=Class.forName(packageName+"."+className);
analyzeClass(c);
}catch(Exception exception)
{
System.out.println(exception);
}
}// if condition for class
} // else condition
} // for loop ends

}
public static void main(String gg[])
{
try
{
int e;
String str;
String fileNameWithPath=gg[0];
String pdfFileName=gg[1];
String className;
Class cls;
JarFile jarFile;
int x,y;
String classPath;
Enumeration<JarEntry> entries;
JarEntry jarEntry;
String jarFileName;

File file=new File(fileNameWithPath);
if(!(file.exists()))
{
System.out.println(fileNameWithPath+" does not exists");
return;
}
if(file.isFile())
{
// code to analyze jar file starts here

if(file.getName().endsWith(".jar"))
{
jarFile=new JarFile(file.getName());
entries=jarFile.entries();
while(entries.hasMoreElements())
{
jarEntry=entries.nextElement();
if(jarEntry.getName().endsWith(".class"))
{

jarFileName=jarEntry.getName();
x=jarFileName.lastIndexOf(".");
y=jarFileName.indexOf("classes/");
classPath=jarFileName.substring(y+8,x);
className=classPath.replace('/','.');

// code to analyse annotations on class starts here
cls=Class.forName(className);
//System.out.println(cls.getName());
analyzeClass(cls);
// code to analyse annotations on class ends here
}// if condition for class
}
} // if condition for jar file
// code to analyze jar file ends here
else
{
System.out.println(fileNameWithPath+" does not represent a jar file or directory");
return;
}
}
if(file.isDirectory())
{
x=fileNameWithPath.lastIndexOf(File.separator);
y=fileNameWithPath.length();
String packageName=fileNameWithPath.substring(x+1,y);
System.out.println("Package Name : "+packageName);
traverseDirectory(file.listFiles(),packageName);
}// if condition for directory




PdfWriter pdfWriter=new PdfWriter(new File(pdfFileName));
PdfDocument pdfDocument=new PdfDocument(pdfWriter);
Document document=new Document(pdfDocument);
document.setMargins(15,15,15,15);

float columnWidths[]={2,5};
Table table;

PdfFont titleFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
PdfFont dataFont=PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
Paragraph p1=new Paragraph("ServiceInfo classes");
p1.setFont(titleFont);
p1.setFontSize(16);
document.add(p1);
Object data[][];

for(ServiceInfo s:pdfList)
{
table=new Table(UnitValue.createPercentArray(columnWidths));
//table.setKeepTogether(true);
if(s.parameters.length>0)
{
data=new Object[9][2];
data[0][0]="Path";
data[0][1]=s.path;

data[1][0]="Class";
data[1][1]=s.c.getName();

data[2][0]="Method";
data[2][1]=s.method.getName();

data[3][0]="Parameters";
str="";
e=0;
for(Parameter p:s.parameters)
{
str+=(e+1)+") "+p.getType().getSimpleName();
e++;
if(e<s.parameters.length) str+="\n";
}

data[3][1]=str;

data[4][0]="Return Type";
data[4][1]=s.method.getReturnType();

data[5][0]="Inject application directory";
data[5][1]=s.injectApplicationDirectory;

data[6][0]="Inject application scope";
data[6][1]=s.injectApplicationScope;

data[7][0]="Inject session scope";
data[7][1]=s.injectSessionScope;

data[8][0]="Inject request scope";
data[8][1]=s.injectRequestScope;
}
else
{
data=new Object[8][2];
data[0][0]="Path";
data[0][1]=s.path;

data[1][0]="Class";
data[1][1]=s.c.getName();

data[2][0]="Method";
data[2][1]=s.method.getName();

data[3][0]="Return Type";
data[3][1]=s.method.getReturnType();

data[4][0]="Inject application directory";
data[4][1]=s.injectApplicationDirectory;

data[5][0]="Inject application scope";
data[5][1]=s.injectApplicationScope;

data[6][0]="Inject session scope";
data[6][1]=s.injectSessionScope;

data[7][0]="Inject request scope";
data[7][1]=s.injectRequestScope;
}

System.out.println("Path : "+s.path);
System.out.println("Class : "+s.c.getName());
System.out.println("Method : "+s.method.getName());

if(s.parameters.length>0)
{
System.out.print("Parameters : ");
e=0;
for(Parameter p:s.parameters)
{
System.out.print(p.getType());
e++;
if(e<s.parameters.length) System.out.print(",");
}
System.out.println();
}
System.out.println("Return Type : "+s.returnType.getClass());
System.out.println("Inject application directory : "+s.injectApplicationDirectory);
System.out.println("Inject application scope : "+s.injectApplicationScope);
System.out.println("Inject session scope : "+s.injectSessionScope);
System.out.println("Inject request scope : "+s.injectRequestScope);
System.out.println("----------------------------------------------------------------------------");

Paragraph cellPara;
Cell cell;
for(int r=0;r<data.length;r++)
{
for(int c=0;c<data[r].length;c++)
{
cellPara=new Paragraph(data[r][c].toString());
if(c==0) cellPara.setFont(titleFont);
else cellPara.setFont(dataFont);
cellPara.setFontSize(12);
cell=new Cell();
cell.add(cellPara);
table.addCell(cell);



}

}

document.add(table);
document.add(new Paragraph("                                                      "));
document.add(new Paragraph("                                                      "));
}

Paragraph p2=new Paragraph("Errors");
p2.setFont(titleFont);
p2.setFontSize(16);
document.add(p2);

float errorTableColumnWidth[]={1,10,7};
UnitValue.createPercentArray(columnWidths);
table=new Table(UnitValue.createPercentArray(errorTableColumnWidth));

Paragraph title1=new Paragraph("S.No.");
title1.setFont(titleFont);
title1.setFontSize(12);
title1.setTextAlignment(TextAlignment.CENTER);

Paragraph title2=new Paragraph("Error");
title2.setFont(titleFont);
title2.setFontSize(12);
title2.setTextAlignment(TextAlignment.CENTER);

Paragraph title3=new Paragraph("Description");
title3.setFont(titleFont);
title3.setFontSize(12);
title3.setTextAlignment(TextAlignment.CENTER);


table.addHeaderCell(new Cell().add(title1));
table.addHeaderCell(new Cell().add(title2));
table.addHeaderCell(new Cell().add(title3));

Cell cell=new Cell();
cell.add(new Paragraph("1."));
cell.setTextAlignment(TextAlignment.RIGHT);
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("Method : methodName cannot be considered as a startup method as return type is not void"));
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("It is raised when a method with return type other than void is specified as startup method"));
table.addCell(cell);


cell=new Cell();
cell.add(new Paragraph("2."));
cell.setTextAlignment(TextAlignment.RIGHT);
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("Method : methodName cannot be considered as a startup method as parameters are not zero"));
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("It is raised when a method with parameters is specified as startup method"));
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("3."));
cell.setTextAlignment(TextAlignment.RIGHT);
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("Setter not found for : propertyName of class : className with package"));
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("It is raised when setter is not defined for an auto wired property of a class"));
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("4."));
cell.setTextAlignment(TextAlignment.RIGHT);
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("Argument type mismatch for auto wired property : propertyName"));
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("This exception occurs when there is a mismatch between the type of a property that has been auto-wired and the argument type of its corresponding setter method."));
table.addCell(cell);


cell=new Cell();
cell.add(new Paragraph("5."));
cell.setTextAlignment(TextAlignment.RIGHT);
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("Method parameter type is not compatible with json object"));
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("This exception occurs when the type of a method parameter is not compatible with the JSON object being provided."));
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("6."));
cell.setTextAlignment(TextAlignment.RIGHT);
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("RequestParameter annotation should not be present on a parameter for accepting json object"));
table.addCell(cell);

cell=new Cell();
cell.add(new Paragraph("The RequestParameter annotation should not be used on a parameter intended for accepting a JSON object."));
table.addCell(cell);







document.add(table);
document.close();
System.out.println("Pdf generated");
}catch(Exception exception)
{
System.out.println(exception);
}
}
}
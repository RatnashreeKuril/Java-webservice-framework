class Bank
{
constructor()
{
}
}
class Customer
{
constructor()
{
this.id=null;
this.firstName=null;
this.lastName=null;
this.accountNo=null;
}
setId(id)
{
this.id=id;
}
getId()
{
return this.id;
}
setFirstName(firstName)
{
this.firstName=firstName;
}
getFirstName()
{
return this.firstName;
}
setLastName(lastName)
{
this.lastName=lastName;
}
getLastName()
{
return this.lastName;
}
setAccountNo(accountNo)
{
this.accountNo=accountNo;
}
getAccountNo()
{
return this.accountNo;
}
}
class Designation
{
constructor()
{
this.code=null;
this.title=null;
}
setCode(code)
{
this.code=code;
}
getCode()
{
return this.code;
}
setTitle(title)
{
this.title=title;
}
getTitle()
{
return this.title;
}
}
class Employee
{
constructor()
{
}
}
class Course
{
constructor()
{
this.code=null;
this.name=null;
}
setCode(code)
{
this.code=code;
}
getCode()
{
return this.code;
}
setName(name)
{
this.name=name;
}
getName()
{
return this.name;
}
}
class Entity
{
constructor()
{
this.name=null;
this.code=null;
}
setName(name)
{
this.name=name;
}
getName()
{
return this.name;
}
setCode(code)
{
this.code=code;
}
getCode()
{
return this.code;
}
}
class Registration
{
constructor()
{
this.code=null;
this.id=null;
this.date=null;
this.state=null;
}
setCode(code)
{
this.code=code;
}
getCode()
{
return this.code;
}
setId(id)
{
this.id=id;
}
getId()
{
return this.id;
}
setDate(date)
{
this.date=date;
}
getDate()
{
return this.date;
}
setState(state)
{
this.state=state;
}
getState()
{
return this.state;
}
}
class School
{
constructor()
{
this.code=null;
this.name=null;
this.numberOfStudents=null;
this.city=null;
this.state=null;
}
setCode(code)
{
this.code=code;
}
getCode()
{
return this.code;
}
setName(name)
{
this.name=name;
}
getName()
{
return this.name;
}
setNumberOfStudents(numberOfStudents)
{
this.numberOfStudents=numberOfStudents;
}
getNumberOfStudents()
{
return this.numberOfStudents;
}
setCity(city)
{
this.city=city;
}
getCity()
{
return this.city;
}
setState(state)
{
this.state=state;
}
getState()
{
return this.state;
}
}
class Student
{
constructor()
{
this.rollNumber=null;
this.name=null;
this.gender=null;
}
setRollNumber(rollNumber)
{
this.rollNumber=rollNumber;
}
getRollNumber()
{
return this.rollNumber;
}
setName(name)
{
this.name=name;
}
getName()
{
return this.name;
}
setGender(gender)
{
this.gender=gender;
}
getGender()
{
return this.gender;
}
}
class BankService
{
add(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/bankService/add",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
update(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/bankService/update",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
delete(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/bankService/delete",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getAll(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/bankService/getAll",
"method" : "POST",
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getByCode(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/bankService/getByCode",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getByRegistrationNumber(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/bankService/getByRegistrationNumber",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
}
class CustomerService
{
add(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/customerService/add",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
update(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/customerService/update",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
delete(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/customerService/delete",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getAll(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/customerService/getAll",
"method" : "POST",
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getCustomers(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/customerService/getCustomers",
"method" : "POST",
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getByCode(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/customerService/getByCode",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getByRegistrationNumber(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/customerService/getByRegistrationNumber",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
}
class DesignationService
{
add(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/designationService/add",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
update(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/designationService/update",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
delete(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/designationService/delete",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getAll(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/designationService/getAll",
"method" : "GET",
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
codeExists(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/designationService/codeExists",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getByCode(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/designationService/getByCode",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
}
class EmployeeService
{
add(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/employeeService/add",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
update(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/employeeService/update",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
delete(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/employeeService/delete",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getAll(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/employeeService/getAll",
"method" : "GET",
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getByCode(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/employeeService/getByCode",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
}
class CourseService
{
add(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/courseService/add",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
update(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/courseService/update",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
delete(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/courseService/delete",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getAll(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/courseService/getAll",
"method" : "GET",
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getByCode(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/courseService/getByCode",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
}
class SchoolService
{
add(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/schoolService/add",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
update(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/schoolService/update",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
delete(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/schoolService/delete",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getAll(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/schoolService/getAll",
"method" : "GET",
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getByCode(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/schoolService/getByCode",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
}
class StudentService
{
add(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/studentService/add",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
update(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/studentService/update",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(data),
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
delete(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/studentService/delete",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getAll(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/studentService/getAll",
"method" : "GET",
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
getByRollNumber(data)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/studentService/getByRollNumber",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : data,
"success" : function(responseData){
if(responseData.success==true)
{
resolve(responseData.result);
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
reject("Some problem");
}
});
});
return promise;
}
}

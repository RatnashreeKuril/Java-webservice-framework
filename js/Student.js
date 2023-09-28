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



class StudentService
{
add(student)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/studentService/add",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(student),
"success" : function(responseData){
if(responseData.success==true)
{
resolve("Student added");
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
alert("Some problem");
}
});
});

return promise;
}
update(student)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/studentService/update",
"method" : "POST",
"contentType" : "application/json",
"data" : JSON.stringify(student),
"success" : function(responseData){
if(responseData.success==true)
{
resolve("Student updated");
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
alert("Some problem");
}
});
});

return promise;
}
delete(formData)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/studentService/delete",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : formData,
"success" : function(responseData){
if(responseData.success==true)
{
resolve("Student deleted");
}
else
{
reject(responseData.error.detailMessage);
}
},
"failure" : function(){
alert("Some problem");
}
});
});

return promise;

}
getByRollNumber(formData)
{
var promise=new Promise(function(resolve,reject){
$.ajax({
"url" : "schoolService/studentService/getByRollNumber",
"method" : "POST",
"contentType" : "application/x-www-form-urlencoded",
"data" : formData,
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
alert("Some problem");
}
});
});

return promise;
}
getAll()
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
alert("Some problem");
}
});
});
return promise;
}
}










function addStudent()
{
var student=new Student();
student.name=$("input[name='name']:eq(0)").val();
student.gender=$("input[name='gender']:checked").val();
var studentService=new StudentService();
var prm=studentService.add(student);
prm.then(function(result){
alert(result);
},function(error){
alert(error);
});
}
function updateStudent()
{
var formArray=$("#updateForm").serializeArray();
var student={};
for(var e=0;e<formArray.length;e++)
{
student[formArray[e].name]=formArray[e].value;
}
var studentService=new StudentService();
var prm=studentService.update(student);
prm.then(function(result){
alert(result);
},function(error){
alert(error);
});

}
function deleteStudent()
{
var formData=$("#deleteForm").serialize();
var studentService=new StudentService();
var prm=studentService.delete(formData);
prm.then(function(result){
alert(result);
},function(error){
alert(error);
});
}

function getStudentByRollNumber()
{
var formData=$("#getByRollNumberForm").serialize();
var studentService=new StudentService();
var prm=studentService.getByRollNumber(formData);
prm.then(function(result){
$("#studentInfo").text("");
$("#studentInfo").append(result.rollNumber+"<br>");
$("#studentInfo").append(result.name+"<br>");
$("#studentInfo").append(result.gender+"<br>");
},function(error){
alert(error);
});
}

function getAllStudents()
{
var studentService=new StudentService();
var prm=studentService.getAll();
prm.then(function(result){
var table=document.getElementsByTagName("tbody")[0];
table.innerHTML="";
var row;
var cell;
for(var e=0;e<result.length;e++)
{
row=document.createElement("tr");
cell=document.createElement("td");
cell.innerHTML=result[e].rollNumber;
row.appendChild(cell);

cell=document.createElement("td");
cell.innerHTML=result[e].name;
row.appendChild(cell);

cell=document.createElement("td");
cell.innerHTML=result[e].gender;
row.appendChild(cell);

table.appendChild(row);
}
},function(error){
alert(error);
});
}



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view</title>
</head>
<body>
	<h1 align="center">This is my Show Data Page</h1>
	
	<form action="updateById" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${e.id}"><br>
		<input type="hidden" name="oldFileName" value="${e.filename}"><br>
		
		<input type="file" name="multipartFile"><br>
		<input type="text" name="title" value="${e.title}"><br>
		<textarea name="description">${e.description}</textarea><br>
		<button>Update</button>
	</form>
	
	
	
	<a href="home">Go to Home</a>
</body>
</html>
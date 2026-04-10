<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registration</title>
</head>
<body>
	${notupload}
	${fileError}
	<h1 align="center">This is my Registration Page</h1>
	
	<form action="registration" method="post" enctype="multipart/form-data">
		<input type="file" name="multipartFile"><br>
		<input type="text" name="title" placeholder="enter title"><br>
		<textarea name="description" placeholder="something write here ........"></textarea><br>
		<button>Submit</button>
	</form>
	
	
	
	<a href="home">Go to Home</a>
</body>
</html>
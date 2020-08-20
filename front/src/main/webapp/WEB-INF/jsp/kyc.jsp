<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kyc</title>
</head>
<body>
	${msg}<br>
	<form action="kyc" enctype="multipart/form-data" method="post">
		file1:<input type="file" name="file1"><br><br>
		file2:<input type="file" name="file2"><br><br>
		file3:<input type="file" name="file3"><br><br>
		file4:<input type="file" name="file3"><br><br>
		<input type="submit" value="upload">
		<input type="reset" value="reset">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>如果不写xxxxxScope,则按默认顺序到域里找</h1>
request:${requestScope.name } <br/>
session1:${sessionScope.session1 }	<br/>
session2:${sessionScope.session2 }	<br/>
application:${applicationScope.application }		<br/>


<h1>Map的值，存在request的域中</h1>
map:${requestScope.map }<br/>

<h1>ModelAndView的值，存在request的域中</h1>
mav:${mav }<br/>
</body>
</html>
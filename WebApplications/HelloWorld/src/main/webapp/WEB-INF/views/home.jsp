<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
  <table>

  </table>
  <table>
		<tr><td><font color=blue>The basPath is: </font></td><td>${basePath}</td></tr>
		<tr><td><font color=blue>The cPath is: </font></td><td>${cPath}</td></tr>
		<tr><td><font color=blue>The sPath is: </font></td><td>${sPath}</td></tr>
		<tr><td><font color=blue>The sessionId is: </font></td><td>${sessionId}</td></tr>
		<tr><td><font color=blue>The sessionObj is: </font></td><td>${session}</td></tr>
		<tr><td><font color=blue>The nodeId is: </font></td><td>${nodeId}</td></tr>
		<tr><td><font color=blue>The server hostName is: </font></td><td>${hostName}</td></tr>
		<tr><td><font color=blue># of requests placed on session: </font></td><td>${count}</td></tr>
  </table>
</body>
</html>

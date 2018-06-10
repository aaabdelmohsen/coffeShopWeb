<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rest Client</title>
</head>
<body>
<h1>Rest Client API</h1>

<label>URL:</label>
<input type="text" width="50"/>

<label>Request body</label>
<input type="text" width="50"/>

<input type="submit" value="Submit"/>

<label>Respose</label>
<input type="text" width="50"/>

</body>
</html>
<%-- 
    Document   : loggedin
    Created on : 30-Sep-2022, 7:16:58 PM
    Author     : ivorl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>

        <h1>Home Page</h1>
        <h2>Hello ${username}</h2>

        <a href="login?logout" name="logout" value="logout"> Log out</a>

    </body>
</html>

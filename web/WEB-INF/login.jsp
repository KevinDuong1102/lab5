<%-- 
    Document   : login
    Created on : Oct 10, 2021, 6:42:21 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post" action="login">
            
            <label>Username:</label>
            <input type ="text" name="username_input" value= "${username_attr}" />
            <br/>
            <label>Password:</label>
            <input type ="password" name="password_input"/>
            <br/>
            <input type="submit" value ="login"/>
            
        </form>
        ${invalidMessage}
        ${logout}
    </body>
</html>

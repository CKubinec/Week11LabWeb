<%-- 
    Document   : reset
    Created on : 24-Nov-2020, 11:53:04 PM
    Author     : Craig
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your email address to reset your password.</p><br>
        <form action="reset" method="post">
            Email Address: <input type="text" name="email"><br>
            <input type="submit" value="Submit">
            <input type="hidden" name="uuid">
            <input type="hidden" name="action" value="reset"> 
        </form>
        <a href="login">Cancel</a>
        <p>${clicked}</p>
    </body>
</html>

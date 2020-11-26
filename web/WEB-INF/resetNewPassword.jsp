<%-- 
    Document   : resetNewPassword
    Created on : 25-Nov-2020, 9:45:01 PM
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
        <h1>Enter a new password</h1>
        <form action="reset" method="post">
            <input type="text" name="password"><br>
            <input type="submit" value="Submit"><input type="hidden" name="action" value="reset"> 
        </form>
        <a href="login">Cancel</a>
        <p>${clicked}</p>
    </body>
</html>

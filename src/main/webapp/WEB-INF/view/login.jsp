<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
    <head>
        <title>Simple Home Insurance Login Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login-page-style.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400&display=swap"/>
    </head>
    <body>
        <div class="login-center">
            <div class="company-logo">
                <img src="${pageContext.request.contextPath}/resources/images/logo-transparent-blue.png" alt=""/>
            </div>
            <h1>Please Login</h1>
            <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
                <div class="message-field">
                    <c:if test="${param.error != null}">
                        <p style="color:red;">Invalid username/password.</p>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <p style="color:green;">You have been logged out.</p>
                    </c:if>
                </div>
                <div class="text-field">
                    <label>Username</label>
                    <input type="text" name="username" required />         		
                </div>
                <div class="text-field">
                    <label>Password</label>
                    <input type="password" name="password" required />       		 
                </div>
                <div class="submit-button-container">
                    <input type="submit" value="Login" />
                </div>
            </form:form>
        </div>
    </body>
</html>

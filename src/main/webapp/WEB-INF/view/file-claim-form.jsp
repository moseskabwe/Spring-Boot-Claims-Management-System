<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Claim Form</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400&display=swap"/>
    </head>
    <body>
        <section id="sidebar">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/resources/images/logo-transparent.png" alt=""/>
            </div>
            <div class="items">
                <ul>
                    <li><span class="material-symbols-outlined">dashboard</span>
                        <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
                    </li>
                    <security:authorize access="hasRole('ADJUSTER')">
                        <li><span class="material-symbols-outlined">summarize</span>
                            <a href="${pageContext.request.contextPath}/dashboard/myClaims">My claims</a> <br>
                        </li>
                    </security:authorize>
                    <li><span class="material-symbols-outlined">location_away</span>
                        <a href="${pageContext.request.contextPath}/dashboard/searchPolicyholders">Search Policyholders</a>
                    </li>
                    <li><span class="material-symbols-outlined">home_work</span>
                        <a href="${pageContext.request.contextPath}/dashboard/listClaims">All Claims</a>
                    </li>
                    <li><span class="material-symbols-outlined">attach_money</span>
                        <a href="${pageContext.request.contextPath}/dashboard/showPayments">All Payments</a>
                    </li>
                    <li>
                        <span class="material-symbols-outlined">logout</span>             
                        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                            <input type="submit" value="Logout" class="logout-button"/>
                        </form:form>
                    </li>
                </ul>
            </div>
        </section>
        <section id="main-page">
            <div class="navigation-bar">
                <div class="profile">
                    <span class="material-symbols-outlined">account_circle</span>
                    <div class="user-details">
                        <h4>${user.firstName} ${user.lastName}</h4>
                        <p class="role">
                            <security:authorize access="hasRole('ADJUSTER')">
                                Adjuster
                            </security:authorize>
                            <security:authorize access="hasRole('CSR')">
                                Customer Service Representative
                            </security:authorize>
                        </p>
                    </div>
                </div>
            </div>
            <div class="claim-details-container">
                <div class="details">
                    <h2>Claim Form</h2>
                    <br>
                    <form:form action="#" modelAttribute="claim" method="POST">
                        <form:hidden path="claimNumber"/>
                        <h4>Policyholder Number</h4>
                        ${policyHolder.policyHolderNumber} 
                        <br><br>
                        <h4>Policyholder Name</h4>
                        ${policyHolder.firstName} ${policyHolder.lastName}
                        <br><br>
                        <h4>Policy Number</h4>
                        ${policy.policyNumber}
                        <br><br>
                        <h4>Loss Type:</h4>
                        <br>
                        <form:select path="lossType">
                            <form:option value="Damage">Damage</form:option>
                            <form:option value="Fire">Fire</form:option>
                            <form:option value="Theft">Theft</form:option>
                        </form:select>
                        <br><br>
                        <h4>Incident Date:</h4>
                        <br>
                        <form:input type="date" path="incidentDate"/>
                        <form:errors path="incidentDate" class="error"></form:errors>
                        <br><br>
                        <h4>Filing Date:</h4>
                        <br>
                        <form:input type="date" path="filingDate"/>
                        <form:errors path="filingDate" class="error"></form:errors>
                        <br><br>
                        <h4>Additional Details:</h4>
                        <br>
                        <form:textarea path="notes"/>
                        <br><br>	
                        <form:hidden path="status" value="First Notice"/>
                        <input type="submit" value="Save Claim"/> 
                    </form:form>
                </div>
            </div>
        </section>
    </body>
</html>

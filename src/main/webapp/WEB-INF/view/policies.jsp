<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Policies</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
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
                    <h1>Policy List for Policyholder ${policyholder.policyHolderNumber}</h1>
                    <br>
                    <h4>Policyholder Number</h4>
                    ${policyholder.policyHolderNumber}
                    <br><br>
                    <h4>Name</h4>
                    ${policyholder.firstName} ${policyholder.lastName}
                    <br><br>
                    <h4>Email</h4>
                    ${policyholder.email}
                    <br><br>
                    <h4>Phone</h4>
                    ${policyholder.phoneNumber}
                    <br><br>
                    <h4>Address</h4>
                    ${policyholder.address}
                    <br><br>
                    <h2>Policies</h2>
                    <br>
                    <table style="margin-bottom:0px;">
                        <thead>
                            <tr>
                                <td>Policy number</td>
                                <td>Policy type</td>
                                <td>Property address</td>
                                <td>Inception date</td>
                                <td>Select</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="policy" items="${policyList}">
                                <c:url var="selectPolicy" 
                                    value="/dashboard/addClaimDetails/">
                                    <c:param name="policyNumber" value="${policy.policyNumber}"/>
                                    <c:param name="policyHolderNumber" value="${policyholder.policyHolderNumber}"/>
                                </c:url>
                                <tr>
                                    <td>
                                        <p>${policy.policyNumber}</p>
                                    </td>
                                    <td>
                                        <p>${policy.policyType}</p>
                                    </td>
                                    <td>
                                        <p>${policy.property.propertyAddress}</p>
                                    </td>
                                    <td>
                                        <p>${policy.inceptionDate}</p>
                                    </td>
                                    <td>
                                        <p><a href="${selectPolicy}">Select</a></p>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    </body>
</html>

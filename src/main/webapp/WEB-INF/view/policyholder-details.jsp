<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Policyholder Details</title>
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
                    <h1>Policyholder Details</h1>
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
                    <security:authorize access="hasRole('CSR')">
                        <c:url var="showPolicyDetails" value="/policyholders/showPolicyDetails">
                            <c:param name="policyHolderNumber" value="${policyholder.policyHolderNumber}"/>
                        </c:url>
                        <a href="${showPolicyDetails}">File a claim</a>
                        <br><br>
                    </security:authorize>
                    <h2>Claims History</h2>
                    <br>
                    <table style="margin-bottom:0px;">
                        <thead>
                            <tr>
                                <td>Claim number</td>
                                <td>Status</td>
                                <td>Loss Type</td>
                                <td>Incident Date</td>
                                <td>Report Date</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="claim" items="${claimList}">
                                <c:url var="showClaimDetails" value="/dashboard/listClaims/showClaimDetails">
                                    <c:param name="claimNumber" value="${claim.claimNumber}"/>
                                </c:url>
                                <tr>
                                    <td>
                                        <p><a href="${showClaimDetails}">${claim.claimNumber}</a></p>
                                    </td>
                                    <td>
                                        <p>${claim.status}</p>
                                    </td>
                                    <td>
                                        <p>${claim.lossType}</p>
                                    </td>
                                    <td>
                                        <p>${claim.incidentDate}</p>
                                    </td>
                                    <td>
                                        <p>${claim.filingDate}</p>
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

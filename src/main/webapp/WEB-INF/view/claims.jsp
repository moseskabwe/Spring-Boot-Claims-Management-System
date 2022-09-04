<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Claims</title>
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
                    <li style="border-left: 4px solid white;"><span class="material-symbols-outlined">home_work</span>
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
            <div class="claims-table-container">
                <div class="table-heading" style="margin-top:70px;">
                    <h3 class="heading">All Claims</h3>
                </div>
                <table>
                    <thead>
                        <tr>
                            <td>Claim Number</td>
                            <td>Policyholder Number</td>
                            <td>Policy Type</td>
                            <td>Loss Type</td>
                            <td>Incident Date</td>
                            <td>Report Date</td>
                            <td>Adjuster</td>
                            <td>Status</td>
                            <td>Action</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="claim" items="${claimsList}">
                            <c:url var="showClaimDetails" value="/dashboard/listClaims/showClaimDetails">
                                <c:param name="claimNumber" value="${claim.claimNumber}"/>
                            </c:url>
                            <tr>
                                <td>
                                    <p>${claim.claimNumber}</p>
                                </td>
                                <td>
                                    <p>${claim.policyHolder.policyHolderNumber}</p>
                                </td>
                                <td>
                                    <p>${claim.policy.policyType}</p>
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
                                <td>
                                    <p>${claim.adjuster.firstName} ${claim.adjuster.lastName}</p>
                                </td>
                                <td>
                                    <p>${claim.status}</p>
                                </td>
                                <td>
                                    <p><a href="${showClaimDetails}">View</a></p>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </body>
</html>

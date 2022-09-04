<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <title>Dashboard</title>
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
                    <li style="border-left: 4px solid white;"><span class="material-symbols-outlined">dashboard</span>
                        <a href="dashboard">Dashboard</a>
                    </li>
                    <security:authorize access="hasRole('ADJUSTER')">
                        <li><span class="material-symbols-outlined">summarize</span>
                            <a href=dashboard/myClaims>My claims</a> <br>
                        </li>
                    </security:authorize>
                    <li><span class="material-symbols-outlined">location_away</span>
                        <a href="dashboard/searchPolicyholders">Search Policyholders</a>
                    </li>
                    <li><span class="material-symbols-outlined">home_work</span>
                        <a href="dashboard/listClaims">All Claims</a>
                    </li>
                    <li><span class="material-symbols-outlined">attach_money</span>
                        <a href=dashboard/showPayments>All Payments</a>
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
            <div class="stats">
                <div class="stats-box">
                    <div>
                        <span>New Claims</span>
                        <h3>${stats[3]}</h3>
                    </div>
                    <span class="material-symbols-outlined">note_add</span>
                </div>
                <div class="stats-box">
                    <div>
                        <span>In Progress</span>
                        <h3>${stats[4]}</h3>
                    </div>
                    <span class="material-symbols-outlined">edit_document</span>
                </div>
                <div class="stats-box">
                    <div>
                        <span>Finalised Claims</span>
                        <h3>${finalisedAverage}%</h3>
                    </div>
                    <span class="material-symbols-outlined">task</span>
                </div>
                <div class="stats-box">
                    <div>
                        <span>Fire Claims</span>
                        <h3>${stats[0]}</h3>
                    </div>
                    <span class="material-symbols-outlined">local_fire_department</span>
                </div>
                <div class="stats-box">
                    <div>
                        <span>Damage Claims</span>
                        <h3>${stats[1]}</h3>
                    </div>
                    <span class="material-symbols-outlined">handyman</span>
                </div>
                <div class="stats-box">
                    <div>
                        <span>Theft Claims</span>
                        <h3>${stats[2]}</h3>
                    </div>
                    <span class="material-symbols-outlined">emergency_home</span>
                </div>
            </div>
            <security:authorize access="hasRole('ADJUSTER')">
                <div class="claims-table-container">
                    <div class="table-heading">
                        <h3 class="heading">My Outstanding Claims</h3>
                    </div>
                    <table style="margin-bottom: 10px;">
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
                            <c:forEach var="myClaim" items="${myClaims}">
                                <c:url var="showClaimDetails" value="/dashboard/listClaims/showClaimDetails">
                                    <c:param name="claimNumber" value="${myClaim.claimNumber}"/>
                                </c:url>
                                <tr>
                                    <td>
                                        <p>${myClaim.claimNumber}</p>
                                    </td>
                                    <td>
                                        <p>${myClaim.policyHolder.policyHolderNumber}</p>
                                    </td>
                                    <td>
                                        <p>${myClaim.policy.policyType}</p>
                                    </td>
                                    <td>
                                        <p>${myClaim.lossType}</p>
                                    </td>
                                    <td>
                                        <p>${myClaim.incidentDate}</p>
                                    </td>
                                    <td>
                                        <p>${myClaim.filingDate}</p>
                                    </td>
                                    <td>
                                        <p>${myClaim.adjuster.firstName} ${myClaim.adjuster.lastName}</p>
                                    </td>
                                    <td>
                                        <p>${myClaim.status}</p>
                                    </td>
                                    <td>
                                        <p><a href="${showClaimDetails}">View</a></p>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </security:authorize>
            <div class="claims-table-container">
                <div class="table-heading">
                    <h3 class="heading">Outstanding Claims</h3>
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
                        <c:forEach var="claim" items="${dashboardClaimsList}">
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

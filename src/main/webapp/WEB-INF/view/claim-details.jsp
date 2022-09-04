<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <title>Claim Details</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400&display=swap"/>
        <c:url var="finaliseClaim" value="/dashboard/finaliseClaim">
            <c:param name="claimNumber" value="${claim.claimNumber}"/>
        </c:url>
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
                    <h2>Claim Number ${claim.claimNumber}</h2>
                    <br>
                    <table class="details-table">
                        <tr>
                            <td>
                                <h4>Status</h4>
                                ${claim.status}
                            </td>
                            <td>
                                <c:if test="${claim.adjuster != null}">
                                    <h4>Adjuster</h4>
                                    ${claim.adjuster.firstName} ${claim.adjuster.lastName}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h4>Loss Type</h4>
                                ${claim.lossType}
                            </td>
                            <td>
                                <h4>Date reported</h4>
                                ${claim.filingDate}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h4>Incident Date</h4>
                                ${claim.incidentDate}
                            </td>
                            <td>
                                <c:if test = "${claim.notes != null}">
                                    <h4>Additional Details</h4>
                                    ${claim.notes}
                                    <br><br>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                    <c:if test = "${claim.status == 'Finalised'}">
                        <h2>Decision</h2>
                        <br>
                        Claim Approved
                        <br><br>
                        <h3>Payment Details</h3>
                        <br><br>
                        <table style="margin-bottom:0px;">
                            <thead>
                                <tr>
                                    <td>Date</td>
                                    <td>Amount</td>
                                    <td>Notes</td>
                                </tr>
                            </thead>
                            </tbody>
                            <c:forEach var="payment" items="${paymentsList}">
                                <tr>
                                    <td>
                                        <p>${payment.paymentDate}</p>
                                    </td>
                                    <td>
                                        <p>${payment.paymentAmount}</p>
                                    </td>
                                    <td>
                                        <p>${payment.notes}</p>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>				
                        </table>
                        <br><br>
                        <security:authorize access="hasRole('ADJUSTER')">
                            <a href="${finaliseClaim}">Add a payment</a>	
                        </security:authorize>
                    </c:if>
                    <c:if test = "${claim.status == 'Declined'}">
                        <h2>Decision</h2>
                        <br>
                        Claim Declined
                        <br><br>
                        <h4>Date</h4>
                        ${declinedClaim.declinedDate}
                        <br><br>
                        <h4>Reason</h4>
                        ${declinedClaim.reason}		
                        <br><br>
                    </c:if>
                    <c:if test = "${claim.status == 'In Progress'}">
                        <security:authorize access="hasRole('ADJUSTER')">
                            <c:url var="declineClaim" value="/declineClaim">
                                <c:param name="claimNumber" value="${claim.claimNumber}"/>
                            </c:url>
                            <h2>Decision</h2>
                            <br>
                            <a href="${finaliseClaim}">Finalise Claim</a> | <a href="${declineClaim}">Decline Claim</a>			
                        </security:authorize>
                    </c:if>
                </div>
                <div class="details-2">
                    <div>
                        <h2>Policyholder Details</h2>
                        <br>
                        <table class="details-table">
                            <tr>
                                <td>
                                    <h4>Policyholder Number</h4>
                                    ${claim.policyHolder.policyHolderNumber}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h4>Name</h4>
                                    ${claim.policyHolder.firstName} ${claim.policyHolder.lastName}
                                </td>
                                <td>
                                    <h4>Phone</h4>
                                    ${claim.policyHolder.phoneNumber}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h4>Email</h4>
                                    ${claim.policyHolder.email}
                                </td>
                                <td>
                                    <h4>Address</h4>
                                    ${claim.policyHolder.address}
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div>
                        <h2>Policy Details</h2>
                        <br>
                        <table class="details-table">
                            <tr>
                                <td>
                                    <h4>Policy Number</h4>
                                    ${claim.policy.policyNumber}
                                </td>
                                <td>
                                    <h4>Inception Date</h4>
                                    ${claim.policy.inceptionDate}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h4>Policy Type</h4>
                                    ${claim.policy.policyType}
                                </td>
                                <td>
                                    <c:if test = "${claim.policy.inForce == 1}">
                                        <p style="color: green;">Policy in force</p>
                                    </c:if>
                                    <c:if test = "${claim.policy.inForce == 0}">
                                        <p style="color: red;">Policy not in force</p>
                                        <br>
                                        <h4>End Date</h4>
                                        ${claim.policy.endDate}
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h4>Property Address</h4>
                                    ${claim.policy.property.propertyAddress}
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>

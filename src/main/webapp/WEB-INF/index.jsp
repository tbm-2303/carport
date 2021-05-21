<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>


        <c:if test="${sessionScope.role == 'employee' }">
            <h1>Admin email: ${sessionScope.email} </h1>
            <p><a href="${pageContext.request.contextPath}/fc/viewrequestspage">process requests as admin</a>
        </c:if>






        <c:if test="${sessionScope.role == 'customer' }">
            <p><a href="${pageContext.request.contextPath}/fc/sendrequestpage">order a custom carport</a>
        </c:if>

        <c:if test="${sessionScope.role == 'customer' }">
            <p><a href="${pageContext.request.contextPath}/fc/standardcarportpage">order a standard carport</a>
        </c:if>

        <c:if test="${sessionScope.role == 'customer' }">
            <p><a href="${pageContext.request.contextPath}/fc/viewmyrequest">View my requests </a>
        </c:if>





    </jsp:body>
</t:genericpage>
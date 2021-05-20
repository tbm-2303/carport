<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Demo Page for Employee Roles
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Admin email: ${sessionScope.email} </h1>
        <c:if test="${sessionScope.role == 'employee' }">
            <p><a href="${pageContext.request.contextPath}/fc/viewrequestspage">process request</a>


        </c:if>
    </jsp:body>
</t:genericpage>

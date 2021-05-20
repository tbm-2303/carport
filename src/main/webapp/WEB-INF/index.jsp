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

        <c:if test="${sessionScope.role == 'customer' }">
            <p><a href="${pageContext.request.contextPath}/fc/sendrequestpage">order a custom carport</a>
        </c:if>


        <c:if test="${sessionScope.user.role == 'customer'}">
            <form action="${pageContext.request.contextPath}/fc/updateCommand" method="post">
            <table class="table table-success table-striped">

                <thead>
                <tr>
                    <th scope="col">Request ID</th>
                    <th scope="col">User email</th>
                    <th scope="col">Width</th>
                    <th scope="col">Length</th>
                    <th scope="col">ShedLength</th>
                    <th scope="col">ShedWidth</th>
                    <th scope="col">Status</th>
                    <th scope="col">Price</th>
                </tr>
                </thead>
                <c:forEach var="var" items="${sessionScope.requestList}" varStatus="status">
                    <c:if test="${sessionScope.requestList.get(status.index).user.id == sessionScope.user.id}">
                        <tr>
                            <td>${var.request_id}</td>
                            <td>${var.user.email}</td>
                            <td>${var.carport.width}</td>
                            <td>${var.carport.length}</td>
                            <td>${var.carport.shed_length}</td>
                            <td>${var.carport.shed_width}</td>
                            <td>${var.status}</td>
                            <td>${var.carport.price}</td>
                            <c:if test="${var.status == 'processed'}">
                                <td>
                                    <button type="submit" class=" btn btn-danger" name="remove"
                                            value="${var.request_id}">Remove
                                    </button>
                                </td>
                                <td>
                                    <button type="submit" class=" btn btn-danger" name="confirm"
                                            value="${var.request_id}">confirm
                                    </button>
                                </td>
                            </c:if>

                        </tr>
                    </c:if>
                </c:forEach>
            </table>

        </c:if>

        <c:if test="${requestScope.error != null}">
            <p style="color: red">${requestScope.error}</p>
        </c:if>
        </form>
    </jsp:body>
</t:genericpage>
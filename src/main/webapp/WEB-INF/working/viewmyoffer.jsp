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

        <c:if test="${sessionScope.user.offerList != null}">


                <table class="table table-success table-striped">
                    <thead>
                    <tr>
                        <th scope="col">User email</th>
                        <th scope="col">Width</th>
                        <th scope="col">Length</th>
                        <th scope="col">ShedLength</th>
                        <th scope="col">ShedWidth</th>
                        <th scope="col">Price</th>
                    </tr>
                    </thead>
                    <c:forEach var="var" items="${applicationScope.offerList}" varStatus="status">
                        <c:if test="${applicationScope.offerList.get(status.index).user.id == sessionScope.user.id}">
                            <tr>
                                <td>${var.user.email}</td>
                                <td>${var.width}</td>
                                <td>${var.length}</td>
                                <td>${var.shed_length}</td>
                                <td>${var.shed_width}</td>
                                <td>${var.price}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>


            <div class="card">
                <a href="${pageContext.request.contextPath}/fc/ConfirmOrder" class="btn btn-outline-success"
                   role="button">Accept Offer</a>
            </div>

            <div class="card">
                <a href="${pageContext.request.contextPath}/fc/CancelOrder" class="btn btn-outline-success"
                   role="button">Reject offer</a>
            </div>


        </c:if>

        <c:if test="${requestScope.error != null}">
            <p style="color: red">${requestScope.error}</p>
        </c:if>

    </jsp:body>
</t:genericpage>
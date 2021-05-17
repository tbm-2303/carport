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


        <form action="${pageContext.request.contextPath}/fc/updateCommand" method="post">
            <table class="table table-success table-striped">
                <thead>
                <tr>
                    <th scope="col">User email</th>
                    <th scope="col">Width</th>
                    <th scope="col">Length</th>
                    <th scope="col">ShedLength</th>
                    <th scope="col">ShedWidth</th>
                    <th scope="col">Price</th>
                    <th scope="col">Profit</th>
                    <th scope="col">Contact Info</th>


                    <th></th>
                </tr>
                </thead>
                <c:forEach var="var" items="${applicationScope.requestList}" varStatus="status">
                    <tr>
                        <td>${var.user.email}</td>
                        <td>${var.width}</td>
                        <td>${var.length}</td>
                        <td>${var.shed_length}</td>
                        <td>${var.shed_width}</td>
                        <td>${var.price}</td>
                        <td>
                            <label for="price"></label><input type="number" class="" id="price" name="price" min="0" step="1"
                                                               value="${var.profit}">
                        </td>
                        <td>${var.user.number}, ${var.user.contactPing}</td>

                        <td>
                            <button type="submit" class=" btn btn-danger" name="remove" value="${status.index}">Remove
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>


            <div class="card">
                <c:if test="${applicationScope.requestList != null}">
                    <input type="submit" class="btn btn-outline-primary" name="updateCommand"
                           value="UpdateRequestCommand">
                </c:if>

            </div>
            <c:if test="${requestScope.error != null}">
                <p style="color: red">${requestScope.error}</p>
            </c:if>
        </form>
    </jsp:body>
</t:genericpage>
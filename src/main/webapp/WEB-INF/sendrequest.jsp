<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>
        <div>

            <form id="form" method="post" action="${pageContext.request.contextPath}/fc/sendRequest">
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label class="form-check-label" for="length">Length:</label>
                            <select class="form-control" name="length" id="length">
                                <c:forEach var="var" begin="240" end="780" step="30">
                                    <option value="${var}">${var}cm</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="col">
                        <div class="form-group">
                            <label class="form-check-label" for="width"> Width</label>
                            <select class="form-control" name="width" id="width">
                                <c:forEach var="var" begin="240" end="750" step="30">
                                    <option value="${var}">${var}cm</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>


                <div class="col">
                    <div class="form-group">
                        <label class="form-check-label" for="shed_length">Length:</label>
                        <select class="form-control" name="shed_length" id="shed_length">
                            <option value="0">No Shed</option>
                            <c:forEach var="var" begin="150" end="690" step="30">
                                <option value="${var}">${var}cm</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>


                <div class="col">
                    <div class="form-group">
                        <label class="form-check-label" for="shed_width">Length:</label>
                        <select class="form-control" name="shed_width" id="shed_width">
                            <option value="0">No Shed</option>
                            <c:forEach var="var" begin="210" end="720" step="30">
                                <option value="${var}">${var}cm</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>


                <div>
                    <button type="submit" class="btn btn-lg btn btn-outline-success">send forespørgsel</button>
                </div>
            </form>
        </div>


    </jsp:body>
</t:genericpage>>
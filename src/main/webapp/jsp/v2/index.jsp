<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../tags/tags.jsp"/>
<html>
    <body>
        <header class="header">
           <h1 class="title">Панель пользователя</h1>
        </header>
        <div class="container">
            <a href="<c:url value="/core/user"/>" class="button">Учетная запись</a>
            <c:if test="${user.adminFlag}">
                <a href="<c:url value="/core/users"/>" class="button">Список пользователей</a>
            </c:if>
            <a href="<c:url value="/info"/>" class="button">Информация о программе</a>
            <a href="<c:url value="/logout"/>" class="button">Выйти</a>
        </div>
    </body>
</html>
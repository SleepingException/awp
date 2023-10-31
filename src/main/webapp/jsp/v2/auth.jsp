<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../tags/tags.jsp"/>
<html>
    <body>
        <header class="header">
            <h1 class="title">Страница авторизации</h1>
        </header>
        <div class="container flex-column center-items">
            <form class="flex-column center-items" action="<c:url value="/auth"/>" method="post">
                <div>
                    <p class="input__label">Логин</p>
                    <input name="login" type="text" class="input">
                </div>
                <div style="margin-top: 15px">
                    <p class="input__label">Пароль</p>
                    <input name="password" type="password" class="input">
                </div>
                <div style="display: flex; justify-content: center;">
                    <button class="button" type="submit">Войти</button>
                </div>
            </form>
            <c:if test="${notRegistered}">
                <div style="font-size: 16px; margin-top: 10px;">
                    <p>Неправильный логин или пароль.<br>
                        Обратитесь к системному администратору.</p>
                </div>
            </c:if>
        </div>
    </body>
</html>

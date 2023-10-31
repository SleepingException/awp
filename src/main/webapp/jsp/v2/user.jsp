<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../tags/tags.jsp"/>
<html>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", () => {
        const passwordInput = document.getElementById('password-input');
        const againPasswordInput = document.getElementById('password-input1');
        const submitButton = document.getElementById('submit-button');

        function validate(event) {
            const reg1 = /[a-zA-Z]+/;
            const reg2 = /[-+=*/]+/;

            if(passwordInput.value !== "${user.password}" && passwordInput.value !== againPasswordInput.value) {
                event.preventDefault();
                alert("Пароли не совпадают!")
                return false;
            }

            if ("${user.restrictionFlag}" === "true" && (!reg1.test(passwordInput.value) && !reg2.test(passwordInput.value))) {
                event.preventDefault();
                alert("Пароль не соответствует ограничениям!")
                return false;
            }
            else {
                return true;
            }
        }

        submitButton.addEventListener('click', (event) => {
            validate(event)
        })
    });
</script>
<body>
    <header class="header">
        <h1 class="title">Учётная запись</h1>
    </header>

    <div class="container">
        <h2 style="text-align: center; margin-bottom: 10px; font-size: 22px;">
            Здравствуйте, ${currentUser.name}!
        </h2>
        <p style="text-align: center; margin-bottom: 10px;">Здесь вы можете изменить ФИО и пароль.</p>
        <c:if test="${(currentUser.id != user.id && currentUser.adminFlag)}">
            <p>Для пользователя ${user.name}</p>
        </c:if>
        <form class="flex-column center-items" action="<c:url value="/core/user"/>" method="post">
            <div style="margin-bottom: 15px;">
                <label for="name-input" class="input__label">ФИО</label>
                <input id="name-input" type="text" name="name" value="${user.name}" class="input" required>
            </div>
            <div>
                <label for="password-input" class="input__label">Пароль</label>
                <input id="password-input" type="password" name="password" value="${user.password}" class="input" required>
            </div>
            <div>
                <label for="password-input1" class="input__label">Введите пароль еще раз</label>
                <input id="password-input1" type="password" class="input">
            </div>
            <input type="hidden" name="id" value="${user.id}">
            <input type="hidden" name="login" value="${user.login}">
            <c:if test="${currentUser.adminFlag}">
                <div style="display: flex; align-items: center; margin-top: 15px;">
                    <label class="input__label" for="checkbox-input" style="margin: 0">Ограничение на пароль</label>
                    <input id="checkbox-input" style="margin-left: 10px" type="checkbox" name="restrictionFlag" value="true"
                           <c:if test="${user.restrictionFlag}">checked</c:if>>
                </div>
            </c:if>
            <div style="display: flex; justify-content: space-between; width: 100%;">
                <button id="submit-button" class="button" type="submit">Сохранить</button>
                <div>
                    <a href="<c:url value="/core"/> " class="button">На главную</a>
                </div>
            </div>
        </form>
        <c:if test="${currentUser.adminFlag}">
            <form class="flex-column center-items" method="post" action="<c:url value="/core/user/delete"/>">
                <input type="hidden" name="id" value="${user.id}">
                <button class="button" type="submit">Удалить пользователя</button>
            </form>
        </c:if>
    </div>
</body>
</html>

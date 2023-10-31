<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../tags/tags.jsp"/>
<html>
<body>
    <header class="header">
        <h1 class="title">Список пользователей</h1>
    </header>
    <div class="container">
        <div class="flex-column center-items">
            <table class="table">
                <thead class="table__head">
                <tr>
                    <td>
                        Пользователь
                    </td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr style="cursor: pointer" onclick="getUser(this)">
                        <td>
                            <form method="get" action="<c:url value="/core/user"/>">
                                ${user.name}
                                <input type="hidden" name="id" value="${user.id}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="flex-column center-items">
                <div style="display: flex; justify-content: space-between; width: 390px">
                    <button class="button" onclick="swapAddUserForm()">
                        Добавить пользователя
                    </button>
                    <div>
                        <a class="button" href="<c:url value="/core"/> ">На главную</a>
                    </div>
                <div/>
                <form id="add-user-form"
                      class="display-none flex-column center-items" style="border: 1px solid black; border-radius: 5px"
                      action="<c:url value="/core/user"/>" method="post">
                    <div>
                        <p>ФИО</p>
                        <input name="name" type="text">
                    </div>
                    <div>
                        <p>Логин</p>
                        <input name="login" type="text">
                    </div>
                    <button type="submit" class="margin-5-top">Сохранить</button>
                    <p>Пароль созданного пользователя будет идентичен логину</p>
                </form>
            </div>
        </div>
    </div>
    </body>
<script>
    function getUser(elem) {
        elem.querySelectorAll('form')[0].submit();
    }

    function swapAddUserForm() {
        const elem = document.querySelector('#add-user-form')
        if (elem.classList.contains('display-none')) {
            elem.classList.remove('display-none')
        }else {
            elem.classList.add('display-none')
        }
    }
</script>
</html>

<#import "../parts/common.ftlh" as c>

<@c.page>
    <table>
        <thead>
        <tr>
            <th width="150"><a href="/main">НА ГЛАВНУЮ</a></th>
            <th width="100"></th>
            <th width="100"><a href="/add/addStudents">Студенты</a></th>
            <th width="100"><a href="/add/addProfessors">Профессора</a></th>
        </tr>
        </thead>
    </table>
    <p></p>
    <div>
        <form method="post">
            <input type="text" name="title" placeholder="Название" autofocus required />
            <input type="number" name="number" placeholder="Номер" autofocus required />
            <input type="text" name="price" placeholder="Стоимость" autofocus required >
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список курсов:</div>
    <p></p>
    <table>
        <thead>
        <tr>
            <th align="left" width="250">Название</th>
            <th align="left" width="100">Номер</th>
            <th align="left" width="100">Цена</th>
        </tr>
        </thead>
        <p></p>
        <tbody>
        <#list courses as course>
            <tr>
                <td><b>${course.title}</b></td>
                <td><i>${course.number}</i></td>
                <td><i>${course.price}</i></td>
            </tr>
        <#else>
            Нет курсов
        </#list>
        </tbody>
    </table>

    <p></p>

    <p><a href="/list/listCourses">Редактировать</a></p>
</@c.page>
<#import "../parts/common.ftlh" as c>

<@c.page>
    <table>
        <thead>
        <tr>
            <th width="150"><a href="/main">НА ГЛАВНУЮ</a></th>
            <th width="100"></th>
            <th width="100"><a href="/add/addStudents">Студенты</a></th>
            <th width="100"><a href="/add/addCourses">Курсы</a></th>
            <th width="100"><a href="/add/addProfessors">Профессора</a></th>
        </tr>
        </thead>
    </table>
    <p></p>
    Список студентов
    <p></p>
    <table>
        <thead>
        <tr>
            <th>Имя студента</th>
            <th>Проходит курсы</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list students as student>
            <tr>
                <td>${student.name!}</td>
                <td><#if student.courses??>
                        <#list student.getCourses() as course>
                            ${course.toString()!}<#sep>,
                        </#list>
                    <#else>
                        -
                    </#if></td>
                <td><a href="/edit/studentEdit/${student.id}">Записаться</a> </td>
                <td><a href="/delete/studentDelete/${student.id}">Отписаться</a> </td>
                <td><a href="/studyProgress/${student.id}">Успеваемость</a> </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
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
    <div>
        <p><a href="/list/listStudents">К списку студентов</a></p>
    </div>
    Записаться на курс
    <p></p>
    <table>
        <thead>
        <tr>
            <th>Имя студента</th>
            <th>Курсы</th>
        </tr>
        </thead>
        <tbody>
        <td>${student.name}</td>
        <td><#if student.courses??>
                <#list student.getCourses() as course>
                    ${course.toString()!}<#sep>,
                </#list>
            <#else>
                -
            </#if></td>
        </tbody>
    </table>
    <p></p>
    <table>
        <thead>
        <tr>
            <th>Название</th>
            <th>Номер</th>
            <th>Цена</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list student.maybeSignUp(courses) as course>
            <form action="/edit/studentEdit/${student.id}" method="post">
                <tr>
                    <td>${course.title!}</td>
                    <td>${course.number!}</td>
                    <td>${course.price!}</td>
                    <input type="hidden" value="${course.id!}" name="courseId">
                    <td><button type="submit">Записаться</button></td>
                </tr>
            </form>
        </#list>
        </tbody>
    </table>
</@c.page>
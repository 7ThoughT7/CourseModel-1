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
        <p><a href="/list/listProfessors">К списку профессоров</a></p>
    </div>
    Курировать курс
    <p></p>
    <table>
        <thead>
        <tr>
            <th>Имя профессора</th>
            <th>Курсы</th>
        </tr>
        </thead>
        <tbody>
        <td>${professor.name}</td>
        <td><#if professor.courses??>
                <#list professor.getCourses() as course>
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
        </tr>
        </thead>
        <tbody>
        <#list professor.signUpCourse(courses) as course>
            <form action="/edit/professorEdit/${professor.id}" method="post">
                <tr>
                    <td>${course.title!}</td>
                    <td>${course.number!}</td>
                    <td>${course.price!}</td>
                    <input type="hidden" value="${course.id!}" name="courseId">
                    <td><button type="submit">Взять курс</button></td>
                </tr>
            </form>
        </#list>
        </tbody>
    </table>
</@c.page>
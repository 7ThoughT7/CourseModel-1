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
        <p><a href="/list/listCourses">К списку курсов</a></p>
    </div>
    Отчислить студента
    <p></p>
    <table>
        <thead>
        <tr>
            <th>Название курса</th>
            <th>Студенты</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${course.title}</td>
            <td><#if course.students??>
                    <#list course.getStudents() as student>
                        ${student.toString()!}<#sep>,
                    </#list>
                <#else>
                    -
                </#if></td>
        </tr>
        </tbody>
    </table>

    <p></p>

    <table>
        <thead>
        <tr>
            <th>Имя</th>
            <th>Адрес</th>
            <th>Телефон</th>
            <th>Email</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list course.getListOfListedStudents() as student>
            <form action="/delete/courseDeleteStudent/${course.id}" method="post">
                <tr>
                    <td>${student.name!}</td>
                    <td>${student.address!}</td>
                    <td>${student.telephone!}</td>
                    <td>${student.email!}</td>
                    <input type="hidden" value="${student.id!}" name="studentId">
                    <td><button type="submit">Отчислить</button></td>
                </tr>
            </form>
        </#list>
        </tbody>
    </table>
</@c.page>
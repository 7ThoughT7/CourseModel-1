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
    Список профессоров
    <p></p>
    <table>
        <thead>
        <tr>
            <th>Имя профессора</th>
            <th>Ведет курсы</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list professors as professor>
            <tr>
                <td>${professor.name!}</td>
                <td><#if professor.courses??>
                        <#list professor.getCourses() as course>
                            ${course.toString()!}<#sep>,
                        </#list>
                    <#else>
                        -
                    </#if></td>
                <td><a href="/edit/professorEdit/${professor.id}">Взять курс</a></td>
                <td><a href="/delete/professorDelete/${professor.id}">Уволиться с курса</a></td>
                <td><a href="/evaluateWork/${professor.id}">Проверить работы</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
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
    Нанять профессора на курс
    <p></p>
    <table>
        <thead>
        <tr>
            <th>Название курса</th>
            <th>Профессоры</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${course.title}</td>
            <td><#if course.professors??>
                    <#list course.getProfessors() as professor>
                        ${professor.toString()!}<#sep>,
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
            <th>Оплата</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list course.signUpProfessor(professors) as professor>
            <form action="/edit/courseEditProfessor/${course.id}" method="post">
                <tr>
                    <td>${professor.name!}</td>
                    <td>${professor.address!}</td>
                    <td>${professor.telephone!}</td>
                    <td>${professor.payment!}</td>
                    <input type="hidden" value="${professor.id!}" name="professorId">
                    <td><button type="submit">Нанять</button></td>
                </tr>
            </form>
        </#list>
        </tbody>
    </table>
</@c.page>
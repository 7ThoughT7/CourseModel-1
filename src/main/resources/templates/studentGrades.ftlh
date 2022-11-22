<#import "parts/common.ftlh" as c>

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
    <p><a href="/list/listProfessors">К списку профессоров</a></p>
    <p>${course.title}</p>
    <table>
        <thead>
        <tr>
            <th>Номер зачетки</th>
            <th>Студент</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list course.getStudents() as student>
            <tr>
                <td>${student.countingNum!}</td>
                <td>${student.name!}</td>
                <td>
                    <form action="/studentGrades/${course.id}" method="post">
                        <input type="number" name="grade" placeholder="Оценка" min="1" max="5" autofocus required />
                        <input type="hidden" value="${student.id!}" name="studentId">
                        <button type="submit">Добавить</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
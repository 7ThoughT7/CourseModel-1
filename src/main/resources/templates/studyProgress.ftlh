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
    <p><a href="/list/listStudents">К списку студентов</a></p>

    <p>${student.name!}</p>
    <table>
        <thead>
        <tr>
            <th>Название курса</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list student.getCourses() as course>
            <tr>
                <td>${course.title!}</td>
                <td>
                    <a href="/grades/${student.id}/${course.id}">Оценки</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>

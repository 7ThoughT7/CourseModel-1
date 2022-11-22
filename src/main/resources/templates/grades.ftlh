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

    <p>${course.title}</p>
    <table>
        <thead>
        <tr>
            <th>Оценки</th>
            <th>Средний балл</th>
            <th>Финальная отметка</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <#list passingCourse.getGrades() as g>
                    ${g.getGrade()}<#sep>,
                </#list>
            </td>
            <td>${passingCourse.getCurrentAverageScore()}</td>
            <td>${passingCourse.getFinalGrade()}</td>
        </tr>
        </tbody>
    </table>
</@c.page>




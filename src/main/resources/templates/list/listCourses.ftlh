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
    Список курсов
    <p></p>
    <table>
        <thead>
        <tr>
            <th>Название курса</th>
            <th>Профессор</th>
            <th>Студенты</th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list courses as course>
            <tr>
                <td>${course.title!}</td>
                <td><#if course.professors??>
                        <#list course.getProfessors() as professor>
                            ${professor.toString()!}<#sep>,
                        </#list>
                    <#else>
                        -
                    </#if></td>
                <td><#if course.students??>
                        <#list course.getStudents() as student>
                            ${student.toString()!}<#sep>,
                        </#list>
                    <#else>
                        -
                    </#if></td>
                <td width="50"><a href="/edit/courseEditStudent/${course.id}">Добавить студента</a> </td>
                <td width="50"><a href="/delete/courseDeleteStudent/${course.id}">Удалить студента</a> </td>
                <td width="50"><a href="/edit/courseEditProfessor/${course.id}">Добавить профессора</a> </td>
                <td width="50"><a href="/delete/courseDeleteProfessor/${course.id}">Удалить профессора</a> </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
<%@page contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Content</title>
</head>
<body>
<h1>
    New/Edit Contact
</h1>
<%--@elvariable id="contact" type="ir.seefa.tutorial.spring.entity.Contact"--%>
<form:form action="saveContact" method="POST" modelAttribute="contact">
    <table>
        <form:hidden path="contactId"/>
        <tr>
            <td>Name:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Family:</td>
            <td><form:input path="family"/></td>
        </tr>
        <tr>
            <td>Birthday:</td>
            <td>
                <fmt:formatDate value="${contact.birthday}"
                                type="date"
                                pattern="yyyy-MM-dd"
                                var="theFormattedDate"/>
                <form:input type="date" path="birthday" value="${theFormattedDate}"/>
            </td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><form:input path="phone"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Save"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
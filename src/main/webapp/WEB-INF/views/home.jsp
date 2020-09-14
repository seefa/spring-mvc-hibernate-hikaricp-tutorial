<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Content Manager Home</title>
</head>
<body>
<h1>
    Contacts
</h1>
<h3><a href="newContact">New Contact</a></h3>
<table border="1">
    <th>No</th>
    <th>Name</th>
    <th>Family</th>
    <th>Birthday</th>
    <th>Phone</th>
    <th>Action</th>

    <c:forEach var="contact" items="${contacts}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${contact.name}</td>
            <td>${contact.family}</td>
            <td>${contact.birthday}</td>
            <td>${contact.phone}</td>
            <td>
                <a href="editContact?id=${contact.contactId}">
                    Edit
                </a>
                <a href="deleteContact?id=${contact.contactId}">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
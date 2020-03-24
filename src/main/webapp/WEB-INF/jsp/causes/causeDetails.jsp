<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">

    <h2>Informacion de la causa</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${cause.name}"/></b></td>
        </tr>
        <tr>
            <th>Descripción</th>
            <td><c:out value="${cause.description}"/></td>
        </tr>
        <tr>
            <th>Objetivo</th>
            <td><c:out value="${cause.budget}"/></td>
        </tr>
        <tr>
            <th>Total recaudado</th>
            <td><c:out value=""/></td>
        </tr>
        <tr>
            <th>Organización</th>
            <td><c:out value="${cause.organization}"/></td>
        </tr>
    </table>

    <spring:url value="{causeId}/edit" var="editUrl">
        <spring:param name="causeId" value="${cause.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar causa</a>

</petclinic:layout>

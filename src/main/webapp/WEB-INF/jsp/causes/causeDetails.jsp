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
            <td><c:out value="${sumDonations}"/></td>
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
    
    <spring:url value="{causeId}/donations/new" var="addDonationUrl">
        <spring:param name="causeId" value="${cause.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addDonationUrl)}" class="btn btn-default">Realizar una donación</a>
    
    <h2>Donations</h2>

    <table id="donationsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 200px;">Money amount</th>
            <th>Date of donation</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cause.donations}" var="donations">
            <tr>
                <td>
                   <c:out value="${donations.name}"/>
                </td>
                <td>
                    <c:out value="${donations.moneyAmount}"/>
                </td>
                <td>
                    <c:out value="${donations.dateDonation}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>

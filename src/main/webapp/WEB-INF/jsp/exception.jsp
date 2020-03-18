<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/Excepcion.jpg" var="petsImage"/>
    <img src="${petsImage}" width=400 height=200/>

    <h2>Ha ocurrido algo...</h2>

    <p>${exception.message}</p>

</petclinic:layout>

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">

    <h2>Informacion del due�o</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${owner.firstName} ${owner.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Direcci�n</th>
            <td><c:out value="${owner.address}"/></td>
        </tr>
        <tr>
            <th>Ciudad</th>
            <td><c:out value="${owner.city}"/></td>
        </tr>
        <tr>
            <th>Telefono</th>
            <td><c:out value="${owner.telephone}"/></td>
        </tr>
    </table>

    <spring:url value="{ownerId}/edit" var="editUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar due�o</a>

    <spring:url value="{ownerId}/pets/new" var="addUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">A�adir nueva mascota</a>

    <br/>
    <br/>
        <spring:url value="/owners/{ownerId}/delete" var="deleteOwnerUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(deleteOwnerUrl)} "class="btn btn-default">Eliminar due�o</a>
    <br/>
    <br/>
    <h2>Mascotas, visitas y reservas</h2>

    <table class="table table-striped">
        <c:forEach var="pet" items="${owner.pets}">
			
            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Nombre</dt>
                        <dd><c:out value="${pet.name}"/></dd>
                        <dt>Fecha de nacimiento</dt>
                        <dd><petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Tipo</dt>
                        <dd><c:out value="${pet.type.name}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        
                        <tr><td><h2>Visitas</h2></td></tr>
                        
                        <tr>
                            <th>Fecha de la visita</th>
                            <th>Descripci�n</th>
                        </tr>
                        </thead>
                        <c:forEach var="visit" items="${pet.visits}">
                            <tr>
                                <td><petclinic:localDate date="${visit.date}" pattern="yyyy-MM-dd"/></td>
                                <td><c:out value="${visit.description}"/></td>
                                
                                <td>
                                	<spring:url value="/owners/{ownerId}/pets/{petId}/visits/{visitId}/delete" var="deleteVisitUrl">
	                                    <spring:param name="visitId" value="${visit.id}" />
	                                	<spring:param name="petId" value="${visit.pet.id}" />
	                                	<spring:param name="ownerId" value="${visit.pet.owner.id}" />
	                                </spring:url>
	                                <a href="${fn:escapeXml(deleteVisitUrl)}">Eliminar visita</a>
                                </td>
                            </tr>
                        </c:forEach>
                        
                        <tr>
                        
                        	<td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visits/new" var="visitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(visitUrl)}">A�adir visita</a>
                            </td>
                        
                        </tr>
                        
                        <tr><td><h2>Reservas</h2></td></tr>
                        
                        <thead>
                        
                        <tr>
                            <th>Fecha de inicio</th>
                            <th>Fecha de fin</th>
                            <th>Detalles</th>
                        </tr>
                        </thead>
                        
                        
                        <c:forEach var="booking" items="${pet.bookings}">
                            <tr>
                                <td><petclinic:localDate date="${booking.startDate}" pattern="yyyy-MM-dd"/></td>
                                <td><petclinic:localDate date="${booking.finishDate}" pattern="yyyy-MM-dd"/></td>
                                <td><c:out value="${booking.details}"/></td>
                                
                                <td>
	                                <spring:url value="/owners/{ownerId}/pets/{petId}/bookings/{bookingId}/delete" var="deleteBookingUrl">
	                                    <spring:param name="ownerId" value="${owner.id}"/>
	                                    <spring:param name="petId" value="${pet.id}"/>
	                                    <spring:param name="bookingId" value="${booking.id}"/>
	                                </spring:url>
	                                <a href="${fn:escapeXml(deleteBookingUrl)}">Eliminar reserva</a>
	                            </td>
                                
                            </tr>
                        </c:forEach>
                        
                        <tr>
                        	<td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/bookings/new" var="bookingUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(bookingUrl)}">A�adir reserva</a>
                            </td>
                        </tr>
                        
                        
                        <tr>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/edit" var="editPetUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(editPetUrl)}">Editar mascota</a>
                            </td>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/delete" var="deletePetUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(deletePetUrl)}">Eliminar mascota</a>
                            </td>
                        </tr>
                        
                    </table>
                </td>
                
            </tr>

        </c:forEach>
    </table>

</petclinic:layout>

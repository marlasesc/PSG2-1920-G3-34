<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<petclinic:layout pageName="causes">
	<h2>Causas</h2>

	<table id="vetsTable" class="table table-striped">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Descripción</th>
				<th>Objetivo</th>
				<th>Organización</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${causes}" var="cause">
				<tr>
					
					<td>
                    	<spring:url value="/causes/{causeId}" var="causeUrl">
                        	<spring:param name="causeId" value="${cause.id}"/>
                    	</spring:url>
                    	<a href="${fn:escapeXml(causeUrl)}"><c:out value="${cause.name}"/></a>
                	</td>
					<td><c:out value="${cause.description} " /></td>
					<td><c:out value="${cause.budget} " /></td>
					<td><c:out value="${cause.organization} " /></td>

					<td><spring:url value="/causes/{causeId}/delete" var="deleteCauseUrl">
							<spring:param name="causeId" value="${cause.id}" />
						</spring:url> <a href="${fn:escapeXml(deleteCauseUrl)}">Eliminar causa</a></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

	<table class="table-buttons">
		<tr>
			<td><a class="btn btn-default" href='<spring:url value="/causes/new" htmlEscape="true"/>'>Añadir causa</a></td>
		</tr>
	</table>
</petclinic:layout>

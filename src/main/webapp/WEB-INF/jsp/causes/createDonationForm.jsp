<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="donations">
	<h2>
		<c:if test="${donation['new']}">Nueva </c:if>
		Donación
	</h2>
	<form:form modelAttribute="donation" class="form-horizontal" id="add-cause-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="Nombre" name="name" />
			<petclinic:inputField label="Descripción" name="description" />
			<petclinic:inputField label="Cantidad a donar" name="moneyAmount" />
		</div>


		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${donation['new']}">
						<button class="btn btn-default" type="submit">Realizar donación</button>
					</c:when>
				</c:choose>
			</div>
		</div>
	</form:form>
</petclinic:layout>

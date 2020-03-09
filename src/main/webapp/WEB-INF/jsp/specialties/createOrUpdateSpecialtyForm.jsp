<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="specialties">
    <jsp:body>
        <h2>
            <c:if test="${true}">Nueva </c:if> Especialidad
        </h2><form:form modelAttribute="formSpecialty" class="form-horizontal">
        
            <div class="form-group has-feedback">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Veterinario</label>
                    <div class="col-sm-10">
                        <c:out value="${vet.firstName} ${vet.lastName}"/>
                    </div>
                </div>
                <div class="control-group">
                    <petclinic:selectField name="specialty" label="Specialty" names="${specialties}" size="5"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${true}">
                            <button class="btn btn-default" type="submit">Añadir especialidad</button>
                        </c:when>
                    </c:choose>
                </div>
            </div>  
        </form:form>
        
        <c:if test="${false}">
        </c:if>
    </jsp:body>
</petclinic:layout>

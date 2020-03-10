<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#startDate").datepicker({dateFormat: 'yy/mm/dd'});
                $("#finishDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${booking['new']}">Nueva </c:if>Reserva</h2>

        <b>Mascota</b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Fecha de nacimiento</th>
                <th>Tipo</th>
                <th>Due�o</th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${booking.pet.name}"/></td>
                <td><petclinic:localDate date="${booking.pet.birthDate}" pattern="yyyy/MM/dd"/></td>
                <td><c:out value="${booking.pet.type.name}"/></td>
                <td><c:out value="${booking.pet.owner.firstName} ${booking.pet.owner.lastName}"/></td>
            </tr>
        </table>

        <form:form modelAttribute="booking" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Fecha de inicio" name="startDate"/>
                <petclinic:inputField label="Fecha de fin" name="finishDate"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${booking.pet.id}"/>
                    <button class="btn btn-default" type="submit">A�adir Reserva</button>
                </div>
            </div>
            
         </form:form>   
         
        <br/>
        <b>Reservas previas</b>
        <table class="table table-striped">
            <tr>
                <th>Fecha de inicio</th>
                <th>Fecha de fin</th>
            </tr>
            <c:forEach var="booking" items="${booking.pet.bookings}">
                <c:if test="${!booking['new']}">
                    <tr>
                        <td><petclinic:localDate date="${booking.startDate}" pattern="yyyy/MM/dd"/></td>
                        <td><petclinic:localDate date="${booking.finishDate}" pattern="yyyy/MM/dd"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        
    </jsp:body>

</petclinic:layout>
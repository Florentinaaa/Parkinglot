<%-- 
    Document   : addCar
    Created on : Nov 21, 2020, 11:55:13 PM
    Author     : Socol Silvian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Add Car">
    <h1>Add Car</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddCar">
        <div class="row">  
        <div class="col-md-6 mb-3">
            <label for="licensePlate">License plate</label>
            <input type="text" class="form-control" id="licensePlate" name="license_plate" placeholder="" value="" required>
            <div class="invalid-feedback">
              License plate is required.
            </div>
          </div>
        </div>
       <div class="row"> 
          <div class="col-md-6 mb-3">
            <label for="parkingSpot">Parking spot</label>
            <input type="text" class="form-control" id="parkingSpot" name="parking_spot" placeholder="" value="" required>
            <div class="invalid-feedback">
              Parking spot is required.
            </div>
          </div>
       </div>
            <div class="row"> 
          <div class="col-md-6 mb-3">
            <label for="owner_id">Owner</label>
            <select class="custom-select d-block w-100" id="owner_id" name="owner_id" required>
              <option value="">Choose...</option>
              <c:forEach var="user" items="${users}" varStatus="status">
                  <option value="${user.id}">${user.username}</option>
              </c:forEach>
            </select>
            <div class="invalid-feedback">
              Please select an owner.
            </div>
          </div>
            </div>
                <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
        <script>(function () {
  'use strict';

  window.addEventListener('load', function () {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');

    // Loop over them and prevent submission
    Array.prototype.filter.call(forms, function (form) {
      form.addEventListener('submit', function (event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }

        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>
</t:pageTemplate>
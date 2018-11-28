<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Quidez-moi</title>

    <!---->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/cssd767.css?family=Roboto:300,400,500,700" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/dist/css/icone91f.css?family=Material+Icons" rel="stylesheet">
    <!---->
    <link href="${pageContext.request.contextPath}/resources/dist/css/bootstrap.min.css" rel="stylesheet">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/multiple-select.css" />

    <!---->
    <link href="${pageContext.request.contextPath}/resources/dist/css/bootstrap-material-design.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/dist/css/ripples.min.css" rel="stylesheet">
    <!---->
    <link href="${pageContext.request.contextPath}/resources/dist/css/snackbar.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/font-awesome.min.css">
    <!---->

    <link href="${pageContext.request.contextPath}/resources/dist/css/index.css" rel="stylesheet">
  </head>

  <body>
    <div id="map">
    </div>
    <div class="container cont1">
        <div class="well">
            <div class="row">
              <div class="col-md-2">
                <div class="form-group label-floating is-empty">
			              <label class="control-label" for="station_depart_latitude">Départ</label>
                    <input   class="form-control" type="text" name="name" value="">
			            	<span class="material-input"></span>
			          </div>
              </div>
              <div class="col-md-2">
              </div>
              <div class="col-md-2">
                <div class="form-group label-floating is-empty">
                    <label class="control-label" for="station_depart_latitude">Arrivée</label>
                    <input   class="form-control" type="text" name="name" value="">
                    <span class="material-input"></span>
                </div>
              </div>
              <div class="col-md-2">
              </div>
              <div class="col-md-4">
                <div class="btn-group btn-group-justified btn-group-raised bouton_cherche">
                  <a href="javascript:void(0)"   class="btn ">Chérche</a>
                </div>
              </div>
           </div>
        </div>
    </div>




  </body>
  <script src="${pageContext.request.contextPath}/resources/dist/js/jquery-1.10.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/dist/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/dist/js/multiple-select.js"></script>
  <script src="${pageContext.request.contextPath}/resources/dist/js/ripples.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/dist/js/material.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/dist/js/snackbar.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/dist/js/jquery.nouislider.min.js"></script>
  <script type="text/javascript">
  function getContextPath() {
	   return "${pageContext.request.contextPath}";
	}
  </script>
  <script src="${pageContext.request.contextPath}/resources/dist/js/index.js"></script>


  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDpgyc8XaNuPDDQPAWA2Tz_mri1tWP077k&signed_in=true&callback=initMap"></script>

</html>

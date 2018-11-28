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

    <link href="${pageContext.request.contextPath}/resources/dist/css/bus.css" rel="stylesheet">
  </head>

  <body>
    <div id="map">
    </div>
    <div class="container cont1">
        <div class="well">
            <div class="row">
              <div class="col-md-4">
                <div class="btn-group">
                  <a href="javascript:void(0)" class="btn btn-inverse btn-raised">
                    <span id="transport_type_text">Bus</span>
                    <div class="ripple-container"></div>
                  </a>
                  <a data-target="#" class="btn btn-inverse btn-raised dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)" class="bouton_b">Tramway</a></li>
                  </ul>
                </div>
              </div>
              <div class="col-md-8">
                <div class="btn-group btn-group-justified btn-group-raised">
                  <a href="javascript:void(0)" form-id="ajout_data_bus" click="true" class="btn bouton_a">Ajout</a>
                  <a href="javascript:void(0)" form-id="modifie_data_bus" click="false" class="btn bouton_a">Modification</a>
                  <a href="javascript:void(0)" form-id="supprime_data_bus" click="false" class="btn bouton_a">Supprésssion</a>
                </div>
              </div>
           </div>
        </div>
    </div>
    <div class="container cont2">
      <div class="well bs-component">
          <!-- interface ajouter bus data -->
          <form class="form-horizontal form_bus" id="ajout_data_bus">
            <fieldset>
              <legend>Ajouter donneés bus</legend>
              <div class="form-group is-empty">
                <div class="row">
                  <div class="col-md-7">
                    <button id="station_depart_popover"  data-exist="false" popover-html-id="station_depart_popover_html" type="button" class="btn btn-raised btn-inverse popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Station départ</button>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-7">
                    <button id="trajet_popover"  data-exist="false" popover-html-id="trajet_popover_html" type="button" class="btn btn-raised btn-inverse popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Trajet</button>
                  </div>
                  <div class="col-md-5">
                    <button form-id="form_ajout_data_bus" onclick="click_bouton(this)" type="button" name="button" class="btn btn-raised active popover_button">Ajoute <i class="fa fa-plus fa-1x"></i></button>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-7">
                    <button id="station_arrivee_popover"  data-exist="false" popover-html-id="station_arrivee_popover_html" type="button" class="btn btn-raised btn-inverse popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Station Arrivée</button>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <hr>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <button id="ajoute_bus_popover"  data-exist="false" popover-html-id="ajoute_bus_popover_html" type="button" class="btn btn-raised btn-inverse popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Ajoute un bus</button>
                  </div>
                </div>
              </div>
            </fieldset>
          </form>
          <!-- interface modifier bus data -->
          <form class="form-horizontal form_bus" id="modifie_data_bus" style="display: none">
            <fieldset>
              <legend>Modifier donneés bus</legend>
              <div class="form-group is-empty">
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
			          <div class="form-group label-floating is-empty">
			              <label class="control-label" for="station_depart_latitude">Selectionne un bus</label>
				            <select onchange="select_bus_modifier(this)" data-type="select-multiple" placeholder="selectionne le bus"  class="form-control " id="modifier_bus_id_bus" >
				                   <option value="-1"></option>
					               <c:forEach items="${buss}" var="bus">
									 <option value='${bus.getId()}'>
										  Bus : ${bus.getNumDeLigne()}
									  </option>
									</c:forEach>
				            </select> 		            
			            	<span class="material-input"></span>
			          </div>                 
		           </div>
                  <div class="col-md-1"></div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <button id="station_modifie_popover"  data-exist="false" popover-html-id="station_modifie_popover_html" type="button" class="btn btn-raised btn-inverse popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Modifier une station</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <button id="trajet_modifie_popover" data-exist="false" popover-html-id="trajet_modifie_popover_html" type="button" class="btn btn-raised btn-inverse popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Modifier un trajet</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <button id="bus_modifie_popover"  data-exist="false" popover-html-id="bus_modifie_popover_html" type="button" class="btn btn-raised btn-inverse popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Modifier le bus</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
              </div>
            </fieldset>
          </form>
          <!-- interface supprimer bus data -->
          <form class="form-horizontal form_bus" id="supprime_data_bus" style="display: none">
            <fieldset>
              <legend>Supprimer donneés bus</legend>
              <div class="form-group is-empty">
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
			          <div class="form-group label-floating is-empty">
			              <label class="control-label" for="station_depart_latitude">Selectionne un bus</label>
				            <select onchange="select_bus_supprimer(this)" data-type="select-multiple"    class="form-control " id="supprimer_bus_id_bus" >
				                   <option value="-1"></option>
				                   <c:forEach items="${buss}" var="bus">
									 <option value='${bus.getId()}'>
										  Bus : ${bus.getNumDeLigne()}
									  </option>
									</c:forEach>
				            </select> 		            
			            	<span class="material-input"></span>
			          </div>                 
		           </div>
                  <div class="col-md-1"></div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <button id="station_supprime_popover"  data-exist="false" popover-html-id="station_bus_supprime_popover_html" type="button" class="btn btn-raised btn-inverse popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Supprimer une station</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <button id="trajet_supprime_popover" data-exist="false" popover-html-id="trajet_bus_supprime_popover_html" type="button" class="btn btn-raised btn-inverse popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Supprimer un trajet</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <button id="bus_supprime_popover"  data-exist="false" popover-html-id="bus_bus_supprime_popover_html" type="button" class="btn btn-raised btn-inverse popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Supprimer le bus</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
              </div>
            </fieldset>
          </form>
      </div>
    </div>

    <!-- contenu des popover -->
    <!-- popover ajoute - station depart bus -->
    <div id="station_depart_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_depart_latitude">Latitude</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_bus" class="form-control input" data-id="station_depart_latitude" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_depart_longitude">Longitude</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_bus" class="form-control input" data-id="station_depart_longitude" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_depart_adresse_station">Adresse Station</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_bus" class="form-control input" data-id="station_depart_adresse_station" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_depart_nom_station">Nom Station</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_bus" class="form-control input" data-id="station_depart_nom_station" type="text">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
      <!-- popover ajoute - trajet bus -->
    <div id="trajet_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">Veuillez sélectionner les points du parcours.</label>
        </div>
        <div class="col-md-12">
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="trajet_duree">Durée</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_bus" class="form-control input" data-id="trajet_duree" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">

              <select data-type="select-multiple" placeholder="selectionne les bus" onchange="change_value(this)" form-id="form_ajout_data_bus"  class="form-control input" multiple="multiple" data-id="trajet_bus" >
                   <c:forEach items="${buss}" var="bus">
					 <option value='${bus.getId()}'>
						  Bus : ${bus.getNumDeLigne()}
					  </option>
					</c:forEach>
              </select>
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
      <!-- popover ajoute - station  arrivee bus -->
    <div id="station_arrivee_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_arrivee_latitude">Latitude</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_bus" class="form-control input" data-id="station_arrivee_latitude" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_arrivee_longitude">Longitude</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_bus" class="form-control input" data-id="station_arrivee_longitude" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_arrivee_adresse_station">Adresse Station</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_bus" class="form-control input" data-id="station_arrivee_adresse_station" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_arrivee_nom_station">Nom Station</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_bus" class="form-control input" data-id="station_arrivee_nom_station" type="text">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover ajoute -bus  -->
    <div id="ajoute_bus_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="ajoute_bus_numero">Numéro de bus</label>
              <input onchange="change_value(this)" form-id="form_ajoute_data_bus_bus" class="form-control input" data-id="ajoute_bus_numero" type="text">
            <span class="material-input"></span>
          </div>

          <div class="form-group label-floating is-empty">
              <label class="control-label" for="ajoute_bus_prix">Prix</label>
              <input onchange="change_value(this)" form-id="form_ajoute_data_bus_bus" class="form-control input" data-id="ajoute_bus_prix" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating">
                <input onclick="ajoute_bus()" form-id="form_ajoute_data_bus_bus" class="form-control input" data-id="ajoute_bus_bouton_ok" type="submit" value="Ajouter">
              <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover modifier - station bus -->
    <div id="station_modifie_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">Clique sur la station que vous voulez modifier.</label>
        </div>
        <div class="col-md-12">
        <!--
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_station_latitude">Latitude</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_bus_station" class="form-control input" data-id="modifier_station_latitude" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_station_longitude">Longitude</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_bus_station" class="form-control input" data-id="modifier_station_longitude" type="text">
            <span class="material-input"></span>
          </div>
          -->
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_station_adresse">Adresse Station</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_bus_station" class="form-control input" data-id="modifier_station_adresse" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_station_nom">Nom Station</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_bus_station" class="form-control input" data-id="modifier_station_nom" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating">
              <input onclick="click_bouton(this)" form-id="form_modifier_data_bus_station" class="form-control input" data-id="modifier_station_bouton_ok" type="submit" value="Modifier">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover modifier - trajet bus -->
    <div id="trajet_modifie_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">Clique sur le trajet que vous voulez modifier.</label>
        </div>
        <div class="col-md-12">
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_trajet_duree">Durée</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_bus_trajet" class="form-control input" data-id="modifier_trajet_duree" type="text">
            <span class="material-input"></span>
          </div>

          <div class="form-group label-floating">
              <input onclick="click_bouton(this)" form-id="form_modifier_data_bus_trajet" class="form-control input" data-id="modifier_trajet_bouton_ok" type="submit" value="Modifier">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover modifier -bus bus -->
    <div id="bus_modifie_popover_html" style="display: none">
      <div class="row">
     
        <div class="col-md-12">
  
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_bus_numero">Numéro de ligne</label>
              <input onchange="change_value(this)"  form-id="form_modifier_data_bus_bus" class="form-control input" data-id="modifier_bus_numero" type="text">
            <span class="material-input"></span>
          </div>
   
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_bus_prix">Prix</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_bus_bus" class="form-control input" data-id="modifier_bus_prix" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating">
                <input onclick="click_bouton(this)" form-id="form_modifier_data_bus_bus" class="form-control input" data-id="modifier_bus_bouton_ok" type="submit" value="Modifier">
              <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>

    <!-- popover supprimer - station bus -->
    <div id="station_bus_supprime_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">Clique sur la station que vous voulez supprimer.</label>
        </div>
        <div class="col-md-12">
        
          <div class="form-group label-floating is-empty">
              <label class="control-label" data-id="supprimer_bus_station_nom" >Station:</label>
             <span class="material-input"></span>
          </div>

          <div class="form-group label-floating">
              <input onclick="click_bouton(this)" form-id="form_supprimer_data_bus_station" class="form-control input" data-id="supprimer_station_bouton_ok" type="submit" value="Supprimer">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover supprimer - trajet bus -->
    <div id="trajet_bus_supprime_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">Clique sur le trajet que vous voulez supprimer.</label>
        </div>
        <div class="col-md-12">
         
          <div class="form-group label-floating is-empty">
              <label class="control-label" data-id="supprimer_bus_trajet_id_label" >Trajet:</label>
             <span class="material-input"></span>
          </div>

          <div class="form-group label-floating">
              <input onclick="click_bouton(this)" form-id="form_supprimer_data_bus_trajet" class="form-control input" data-id="supprimer_trajet_bouton_ok" type="submit" value="Supprimer">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover supprimer -bus bus -->
    <div id="bus_bus_supprime_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">voulez vous supprimer ce bus ?</label>
        </div>
        <div class="col-md-12">
          
          <div class="form-group label-floating">
              <input onclick="click_bouton(this)" form-id="form_supprimer_data_bus_bus" class="form-control input" data-id="supprimer_bus_bouton_ok" type="submit" value="Supprimer">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <div class="wait">
    	<i class="fa fa-spinner fa-pulse fa-5x"></i>
    </div>
    <!-- les formulaire -->
    <form id="form_ajout_data_bus" method="post" action="${pageContext.request.contextPath}\addBusData">
      <input type="hidden" name="station_depart_id" id="station_depart_id" value="-1">
      <input type="hidden" name="station_arrivee_id" id="station_arrivee_id" value="-1">
      <input type="hidden" name="nbr_posi" id="nbr_posi" value="0">

    </form>
    <form id="form_ajoute_data_bus_bus" method="post" action="${pageContext.request.contextPath}">
    </form>
    <form id="form_modifier_data_bus_station" method="post" action="${pageContext.request.contextPath}/updateStationBus">
     	<input type="hidden" name="modifier_station_id" id="modifier_station_id" value="-1">
    </form>
    <form id="form_modifier_data_bus_trajet" method="post" action="${pageContext.request.contextPath}/updateTrajetBus">
    	<input type="hidden" name="modifier_trajet_id" id="modifier_trajet_id"" value="-1">
    </form>
    <form id="form_modifier_data_bus_bus" method="post" action="${pageContext.request.contextPath}/updatebus">
    	<input type="hidden" name="modifier_bus_id" id="modifier_bus_id"" value="-1">
    </form>
    <form id="form_supprimer_data_bus_station" method="post" action="${pageContext.request.contextPath}/deleteStationBus">
    <input type="hidden" name="supprimer_station_id" id="supprimer_station_id" value="-1">
    	<input type="hidden" name="supprimer_station_bus_id" id="supprimer_station_bus_id" value="-1">
    </form>
    <form id="form_supprimer_data_bus_trajet" method="post" action="${pageContext.request.contextPath}/deleteTrajetBus">
    	<input type="hidden" name="supprimer_trajet_id" id="supprimer_trajet_id" value="-1">
    	<input type="hidden" name="supprimer_trajet_bus_id" id="supprimer_trajet_bus_id" value="-1">
    </form>
    <form id="form_supprimer_data_bus_bus" method="post" action="${pageContext.request.contextPath}/deleteBus">
    	<input type="hidden" name="supprimer_bus_id" id="supprimer_bus_id" value="-1">
    </form>

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
  <script src="${pageContext.request.contextPath}/resources/dist/js/bus.js"></script>


  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDpgyc8XaNuPDDQPAWA2Tz_mri1tWP077k&signed_in=true&callback=initMap"></script>

</html>

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

    <link href="${pageContext.request.contextPath}/resources/dist/css/tramway.css" rel="stylesheet">
  </head>

  <body>
    <div id="map">
    </div>
    <div class="container cont1">
        <div class="well">
            <div class="row">
              <div class="col-md-4">
                <div class="btn-group">
                  <a href="javascript:void(0)" class="btn btn-primary btn-raised">
                    <span id="transport_type_text">Tramway</span>
                    <div class="ripple-container"></div>
                  </a>
                  <a data-target="#" class="btn btn-primary btn-raised dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)" class="bouton_b">Bus</a></li>
                  </ul>
                </div>
              </div>
              <div class="col-md-8">
                <div class="btn-group btn-group-justified btn-group-raised">
                  <a href="javascript:void(0)" form-id="ajout_data_tramway" click="true" class="btn bouton_a">Ajout</a>
                  <a href="javascript:void(0)" form-id="modifie_data_tramway" click="false" class="btn bouton_a">Modification</a>
                  <a href="javascript:void(0)" form-id="supprime_data_tramway" click="false" class="btn bouton_a">Supprésssion</a>
                </div>
              </div>
           </div>
        </div>
    </div>
    <div class="container cont2">
      <div class="well bs-component">
          <!-- interface ajouter tramway data -->
          <form class="form-horizontal form_tramway" id="ajout_data_tramway">
            <fieldset>
              <legend>Ajouter donneés tramway</legend>
              <div class="form-group is-empty">
                <div class="row">
                  <div class="col-md-7">
                    <button id="station_depart_popover"  data-exist="false" popover-html-id="station_depart_popover_html" type="button" class="btn btn-raised btn-primary popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Station départ</button>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-7">
                    <button id="trajet_popover"  data-exist="false" popover-html-id="trajet_popover_html" type="button" class="btn btn-raised btn-primary popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Trajet</button>
                  </div>
                  <div class="col-md-5">
                    <button form-id="form_ajout_data_tramway" onclick="click_bouton(this)" type="button" name="button" class="btn btn-raised active popover_button">Ajoute <i class="fa fa-plus fa-1x"></i></button>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-7">
                    <button id="station_arrivee_popover"  data-exist="false" popover-html-id="station_arrivee_popover_html" type="button" class="btn btn-raised btn-primary popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Station Arrivée</button>
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
                    <button id="ajoute_tramway_popover"  data-exist="false" popover-html-id="ajoute_tramway_popover_html" type="button" class="btn btn-raised btn-primary popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Ajoute un Tramway</button>
                  </div>
                </div>
              </div>
            </fieldset>
          </form>
          <!-- interface modifier tramway data -->
          <form class="form-horizontal form_tramway" id="modifie_data_tramway" style="display: none">
            <fieldset>
              <legend>Modifier donneés tramway</legend>
              <div class="form-group is-empty">
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
			          <div class="form-group label-floating is-empty">
			              <label class="control-label" for="station_depart_latitude">Selectionne un Tramway</label>
				            <select onchange="select_tramway_modifier(this)" data-type="select-multiple" placeholder="selectionne le tramway"  class="form-control " id="modifier_tramway_id_tramway" >
				                   <option value="-1"></option>
				                   <c:forEach items="${trams}" var="tram">
									 <option value='${tram.getId()}'>
										  ${tram.getDepart()} ->  ${tram.getArrivee()}
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
                    <button id="station_modifie_popover"  data-exist="false" popover-html-id="station_modifie_popover_html" type="button" class="btn btn-raised btn-primary popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Modifier une station</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <button id="trajet_modifie_popover" data-exist="false" popover-html-id="trajet_modifie_popover_html" type="button" class="btn btn-raised btn-primary popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Modifier un trajet</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <button id="tramway_modifie_popover"  data-exist="false" popover-html-id="tramway_modifie_popover_html" type="button" class="btn btn-raised btn-primary popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Modifier le tramway</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
              </div>
            </fieldset>
          </form>
          <!-- interface supprimer tramway data -->
          <form class="form-horizontal form_tramway" id="supprime_data_tramway" style="display: none">
            <fieldset>
              <legend>Supprimer donneés tramway</legend>
              <div class="form-group is-empty">
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
			          <div class="form-group label-floating is-empty">
			              <label class="control-label" for="station_depart_latitude">Selectionne un Tramway</label>
				            <select onchange="select_tramway_supprimer(this)" data-type="select-multiple"    class="form-control " id="supprimer_tramway_id_tramway" >
				                   <option value="-1"></option>
				                   <c:forEach items="${trams}" var="tram">
									 <option value='${tram.getId()}'>
										  ${tram.getDepart()} ->  ${tram.getArrivee()}
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
                    <button id="station_supprime_popover"  data-exist="false" popover-html-id="station_tramway_supprime_popover_html" type="button" class="btn btn-raised btn-primary popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Supprimer une station</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <button id="trajet_supprime_popover" data-exist="false" popover-html-id="trajet_tramway_supprime_popover_html" type="button" class="btn btn-raised btn-primary popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Supprimer un trajet</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <button id="tramway_supprime_popover"  data-exist="false" popover-html-id="tramway_tramway_supprime_popover_html" type="button" class="btn btn-raised btn-primary popover_button" data-container="body" data-html="true" data-toggle="popover" data-placement="left" data-content="">Supprimer le tramway</button>
                  </div>
                  <div class="col-md-1"></div>
                </div>
              </div>
            </fieldset>
          </form>
      </div>
    </div>

    <!-- contenu des popover -->
    <!-- popover ajoute - station depart tramway -->
    <div id="station_depart_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_depart_latitude">Latitude</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_tramway" class="form-control input" data-id="station_depart_latitude" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_depart_longitude">Longitude</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_tramway" class="form-control input" data-id="station_depart_longitude" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_depart_adresse_station">Adresse Station</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_tramway" class="form-control input" data-id="station_depart_adresse_station" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_depart_nom_station">Nom Station</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_tramway" class="form-control input" data-id="station_depart_nom_station" type="text">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
      <!-- popover ajoute - trajet tramway -->
    <div id="trajet_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">Veuillez sélectionner les points du parcours.</label>
        </div>
        <div class="col-md-12">
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="trajet_duree">Durée</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_tramway" class="form-control input" data-id="trajet_duree" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">

              <select data-type="select-multiple" placeholder="selectionne les tramway" onchange="change_value(this)" form-id="form_ajout_data_tramway"  class="form-control input" multiple="multiple" data-id="trajet_tramway" >
                   <c:forEach items="${trams}" var="tram">
					 <option value='${tram.getId()}'>
						  ${tram.getDepart()} ->  ${tram.getArrivee()}
					  </option>
					</c:forEach>
              </select>
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
      <!-- popover ajoute - station  arrivee tramway -->
    <div id="station_arrivee_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_arrivee_latitude">Latitude</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_tramway" class="form-control input" data-id="station_arrivee_latitude" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_arrivee_longitude">Longitude</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_tramway" class="form-control input" data-id="station_arrivee_longitude" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_arrivee_adresse_station">Adresse Station</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_tramway" class="form-control input" data-id="station_arrivee_adresse_station" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="station_arrivee_nom_station">Nom Station</label>
              <input onchange="change_value(this)" form-id="form_ajout_data_tramway" class="form-control input" data-id="station_arrivee_nom_station" type="text">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover ajoute -tramway  -->
    <div id="ajoute_tramway_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="ajoute_tramway_depart">Départ</label>
              <input onchange="change_value(this)" form-id="form_ajoute_data_tramway_tramway" class="form-control input" data-id="ajoute_tramway_depart" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="ajoute_tramway_arrivee">Arrivée</label>
              <input onchange="change_value(this)" form-id="form_ajoute_data_tramway_tramway" class="form-control input" data-id="ajoute_tramway_arrivee" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="ajoute_tramway_prix">Prix</label>
              <input onchange="change_value(this)" form-id="form_ajoute_data_tramway_tramway" class="form-control input" data-id="ajoute_tramway_prix" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating">
                <input onclick="ajoute_tramway()" form-id="form_ajoute_data_tramway_tramway" class="form-control input" data-id="ajoute_tramway_bouton_ok" type="submit" value="Ajouter">
              <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover modifier - station tramway -->
    <div id="station_modifie_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">Clique sur la station que vous voulez modifier.</label>
        </div>
        <div class="col-md-12">
        <!--
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_station_latitude">Latitude</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_tramway_station" class="form-control input" data-id="modifier_station_latitude" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_station_longitude">Longitude</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_tramway_station" class="form-control input" data-id="modifier_station_longitude" type="text">
            <span class="material-input"></span>
          </div>
          -->
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_station_adresse">Adresse Station</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_tramway_station" class="form-control input" data-id="modifier_station_adresse" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_station_nom">Nom Station</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_tramway_station" class="form-control input" data-id="modifier_station_nom" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating">
              <input onclick="click_bouton(this)" form-id="form_modifier_data_tramway_station" class="form-control input" data-id="modifier_station_bouton_ok" type="submit" value="Modifier">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover modifier - trajet tramway -->
    <div id="trajet_modifie_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">Clique sur le trajet que vous voulez modifier.</label>
        </div>
        <div class="col-md-12">
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_trajet_duree">Durée</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_tramway_trajet" class="form-control input" data-id="modifier_trajet_duree" type="text">
            <span class="material-input"></span>
          </div>

          <div class="form-group label-floating">
              <input onclick="click_bouton(this)" form-id="form_modifier_data_tramway_trajet" class="form-control input" data-id="modifier_trajet_bouton_ok" type="submit" value="Modifier">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover modifier -tramway tramway -->
    <div id="tramway_modifie_popover_html" style="display: none">
      <div class="row">
     
        <div class="col-md-12">
  
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_tramway_depart">Départ</label>
              <input onchange="change_value(this)"  form-id="form_modifier_data_tramway_tramway" class="form-control input" data-id="modifier_tramway_depart" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_tramway_arrivee">Arrivée</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_tramway_tramway" class="form-control input" data-id="modifier_tramway_arrivee" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating is-empty">
              <label class="control-label" for="modifier_tramway_prix">Prix</label>
              <input onchange="change_value(this)" form-id="form_modifier_data_tramway_tramway" class="form-control input" data-id="modifier_tramway_prix" type="text">
            <span class="material-input"></span>
          </div>
          <div class="form-group label-floating">
                <input onclick="click_bouton(this)" form-id="form_modifier_data_tramway_tramway" class="form-control input" data-id="modifier_tramway_bouton_ok" type="submit" value="Modifier">
              <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>

    <!-- popover supprimer - station tramway -->
    <div id="station_tramway_supprime_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">Clique sur la station que vous voulez supprimer.</label>
        </div>
        <div class="col-md-12">
        
          <div class="form-group label-floating is-empty">
              <label class="control-label" data-id="supprimer_tramway_station_nom" >Station:</label>
             <span class="material-input"></span>
          </div>

          <div class="form-group label-floating">
              <input onclick="click_bouton(this)" form-id="form_supprimer_data_tramway_station" class="form-control input" data-id="supprimer_station_bouton_ok" type="submit" value="Supprimer">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover supprimer - trajet tramway -->
    <div id="trajet_tramway_supprime_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">Clique sur le trajet que vous voulez supprimer.</label>
        </div>
        <div class="col-md-12">
         
          <div class="form-group label-floating is-empty">
              <label class="control-label" data-id="supprimer_tramway_trajet_id_label" >Trajet:</label>
             <span class="material-input"></span>
          </div>

          <div class="form-group label-floating">
              <input onclick="click_bouton(this)" form-id="form_supprimer_data_tramway_trajet" class="form-control input" data-id="supprimer_trajet_bouton_ok" type="submit" value="Supprimer">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- popover supprimer -tramway tramway -->
    <div id="tramway_tramway_supprime_popover_html" style="display: none">
      <div class="row">
        <div class="col-md-12">
            <label class="control-label">voulez vous supprimer ce tramway ?</label>
        </div>
        <div class="col-md-12">
          
          <div class="form-group label-floating">
              <input onclick="click_bouton(this)" form-id="form_supprimer_data_tramway_tramway" class="form-control input" data-id="supprimer_tramway_bouton_ok" type="submit" value="Supprimer">
            <span class="material-input"></span>
          </div>
        </div>
      </div>
    </div>
    <div class="wait">
    	<i class="fa fa-spinner fa-pulse fa-5x"></i>
    </div>
    <!-- les formulaire -->
    <form id="form_ajout_data_tramway" method="post" action="${pageContext.request.contextPath}\addTramwayData">
      <input type="hidden" name="station_depart_id" id="station_depart_id" value="-1">
      <input type="hidden" name="station_arrivee_id" id="station_arrivee_id" value="-1">
      <input type="hidden" name="nbr_posi" id="nbr_posi" value="0">
      <input type="hidden" name="distance" id="distance" value="0">

    </form>
    <form id="form_ajoute_data_tramway_tramway" method="post" action="${pageContext.request.contextPath}">
    </form>
    <form id="form_modifier_data_tramway_station" method="post" action="${pageContext.request.contextPath}/updateStationTramway">
     	<input type="hidden" name="modifier_station_id" id="modifier_station_id" value="-1">
    </form>
    <form id="form_modifier_data_tramway_trajet" method="post" action="${pageContext.request.contextPath}/updateTrajetTramway">
    	<input type="hidden" name="modifier_trajet_id" id="modifier_trajet_id"" value="-1">
    </form>
    <form id="form_modifier_data_tramway_tramway" method="post" action="${pageContext.request.contextPath}/updateTramway">
    	<input type="hidden" name="modifier_tramway_id" id="modifier_tramway_id"" value="-1">
    </form>
    <form id="form_supprimer_data_tramway_station" method="post" action="${pageContext.request.contextPath}/deleteStationTramway">
    <input type="hidden" name="supprimer_station_id" id="supprimer_station_id" value="-1">
    	<input type="hidden" name="supprimer_station_tramway_id" id="supprimer_station_tramway_id" value="-1">
    </form>
    <form id="form_supprimer_data_tramway_trajet" method="post" action="${pageContext.request.contextPath}/deleteTrajetTramway">
    	<input type="hidden" name="supprimer_trajet_id" id="supprimer_trajet_id" value="-1">
    	<input type="hidden" name="supprimer_trajet_tramway_id" id="supprimer_trajet_tramway_id" value="-1">
    </form>
    <form id="form_supprimer_data_tramway_tramway" method="post" action="${pageContext.request.contextPath}/deleteTram">
    	<input type="hidden" name="supprimer_tramway_id" id="supprimer_tramway_id" value="-1">
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
  <script src="${pageContext.request.contextPath}/resources/dist/js/tramway.js"></script>


  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDpgyc8XaNuPDDQPAWA2Tz_mri1tWP077k&signed_in=true&callback=initMap"></script>

</html>

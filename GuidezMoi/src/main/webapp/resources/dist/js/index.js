var poly;
var map;
var lignes;
var StationDepart=null;
var StationArrivee=null;
var Stations_tab=[];
var Lignes_tab=[];
var directionsService;
var directionsDisplay;
 var service;
 var geocoder;
var tab_latlng=[];
var latlng_min={lat:1,lng:1};
var dist_min=-1;


var station_map = new Map();
var trajet_map = new Map();
var parcoure_map = new Map();

function initMap() {
  directionsService = new google.maps.DirectionsService;
   directionsDisplay = new google.maps.DirectionsRenderer;
   service = new google.maps.DistanceMatrixService;
    geocoder = new google.maps.Geocoder;

  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 14,
    center: {lat: 33.585808812391896, lng: -7.598161697387695}  // Center the map on Chicago, USA.
  });

  directionsDisplay.setMap(map);

  lignes = new google.maps.Polyline({
	    strokeColor: '#000000',
	    strokeOpacity: 1.0,
	    strokeWeight: 3
	  });
	  lignes.setMap(map);

    map.addListener('click', add);


}

function proche_station(origine,destination) {
  service.getDistanceMatrix({
    origins: [origine],
    destinations: destination,
    travelMode: google.maps.TravelMode.DRIVING,
    unitSystem: google.maps.UnitSystem.METRIC,
    avoidHighways: false,
    avoidTolls: false
  }, function(response, status) {
    if (status !== google.maps.DistanceMatrixStatus.OK) {
      alert('Error was: ' + status);
    } else {
      var originList = response.originAddresses;
      var destinationList = response.destinationAddresses;

      var showGeocodedAddressOnMap = function(asDestination) {
        return function(results, status) {
        };
      };
      var min=-1;
      var index_min=-1;
      for (var i = 0; i < originList.length; i++) {
        var results = response.rows[i].elements;
         for (var j = 0; j < results.length; j++) {
           if(min == -1 || min >= results[j].distance.value)
           {
             min= results[j].distance.value;
             index_min=j;
           }
        }
      }

      if(marker2)
        marker2.setMap(null);
      marker2 = new google.maps.Marker({
       position: tab_latlng[index_min],
       map: map,
     });
    }
  });
}

function toto() {
  alert(this.id);
}

function change_value(elem) {
    var id_input=elem.getAttribute("data-id");

  if(elem.getAttribute("data-type")=="select-multiple")
    {
      var  val_input=$("select[data-id='"+id_input+"'").multipleSelect("getSelects");
    }

  else
    var val_input=elem.value;
  $("#"+id_input).val(val_input);

}



function click_bouton(elem) {
    var id_form=elem.getAttribute("form-id");
    var form = document.getElementById(id_form);
    form.submit();
}

function getUrlVar(key){
	var result = new RegExp(key + "=([^&]*)", "i").exec(window.location.search);
	return result && unescape(result[1]) || "";
}

function PositionnerstationTramway(map,lat,lng,id,nom,adresse)
{
	var image = getContextPath()+"/resources/img/Tramway.png";
   var Station = new google.maps.Marker({
	    position: {lat: lat, lng: lng},
	    map: map,
	    icon: image,
	    id:id,
	    nom:nom,
	    adress:adresse
  });
  Stations_tab.push(Station);
  google.maps.event.addListener(Station, 'click', function() {
        if(Station.id != -1)
        	{  //console.log(Station.position.lat()+" "+Station.position.lng());
        		ajouteStation(map,Station.position.lat(),Station.position.lng(),Station.id,Station.nom,Station.adress);
        	}
  });
  return Station;
}


function PositionnerstationBus(map,lat,lng,id,nom,adresse)
{
	var image = getContextPath()+"/resources/img/Bus.png";
   var Station = new google.maps.Marker({
	    position: {lat: lat, lng: lng},
	    map: map,
	    icon: image,
	    id:id,
	    nom:nom,
	    adress:adresse
  });
  Stations_tab.push(Station);
  google.maps.event.addListener(Station, 'click', function() {
    //console.log(Station.position.lat()+" "+Station.position.lng());
        if(Station.id != -1)
        	{
        		ajouteStation(map,Station.position.lat(),Station.position.lng(),Station.id,Station.nom,Station.adress);
        	}
  });
  return Station;
}



function ajouteStation(map,lat,lng,id,nom,adresse)
{
	if($("#station_depart_popover").attr("aria-describedby")!=undefined)
	{
	    if(StationDepart!=null)
	    	StationDepart.setMap(null);
	    if(id == -1)
	    	StationDepart = PositionnerstationTramway(map,lat,lng,id,nom,adresse);
	    else
	    	StationDepart=1;

		  $(".popover input[data-id='station_depart_latitude']").val(lat);
      $(".popover input[data-id='station_depart_latitude']").trigger( "onchange" );
      $(".popover input[data-id='station_depart_latitude']").parent(".is-empty").removeClass("is-empty");

		  $(".popover input[data-id='station_depart_longitude']").val(lng);
      $(".popover input[data-id='station_depart_longitude']").parent(".is-empty").removeClass("is-empty");
      $(".popover input[data-id='station_depart_longitude']").trigger( "onchange" );

		  $(".popover input[data-id='station_depart_adresse_station']").val(adresse);
      $(".popover input[data-id='station_depart_adresse_station']").parent(".is-empty").removeClass("is-empty");
      $(".popover input[data-id='station_depart_adresse_station']").trigger( "onchange" );

		  $(".popover input[data-id='station_depart_nom_station']").val(nom);
      $(".popover input[data-id='station_depart_nom_station']").parent(".is-empty").removeClass("is-empty");
      $(".popover input[data-id='station_depart_nom_station']").trigger( "onchange" );

      $("#station_depart_id").val(id);
	} else if($("#station_arrivee_popover").attr("aria-describedby")!=undefined)
	{
	    if(StationArrivee!=null)
	    	StationArrivee.setMap(null);
	    if(id == -1)
	   		 StationArrivee = PositionnerstationTramway(map,lat,lng,id,nom,adresse);
	    else
	       StationDepart=1;

      $(".popover input[data-id='station_arrivee_latitude']").val(lat);
      $(".popover input[data-id='station_arrivee_latitude']").trigger( "onchange" );
      $(".popover input[data-id='station_arrivee_latitude']").parent(".is-empty").removeClass("is-empty");

		  $(".popover input[data-id='station_arrivee_longitude']").val(lng);
      $(".popover input[data-id='station_arrivee_longitude']").parent(".is-empty").removeClass("is-empty");
      $(".popover input[data-id='station_arrivee_longitude']").trigger( "onchange" );

		  $(".popover input[data-id='station_arrivee_adresse_station']").val(adresse);
      $(".popover input[data-id='station_arrivee_adresse_station']").parent(".is-empty").removeClass("is-empty");
      $(".popover input[data-id='station_arrivee_adresse_station']").trigger( "onchange" );

		  $(".popover input[data-id='station_arrivee_nom_station']").val(nom);
      $(".popover input[data-id='station_arrivee_nom_station']").parent(".is-empty").removeClass("is-empty");
      $(".popover input[data-id='station_arrivee_nom_station']").trigger( "onchange" );

      $("#station_arrivee_id").val(id);
	}
  else  if($("#station_modifie_popover").attr("aria-describedby")!=undefined || $(".bouton_a[form-id='modifie_data_tramway']").attr("click")=="true")
  {
  /*  $(".popover input[data-id='modifier_station_latitude']").val(lat);
    $(".popover input[data-id='modifier_station_latitude']").trigger( "onchange" );
    $(".popover input[data-id='modifier_station_latitude']").parent(".is-empty").removeClass("is-empty");

    $(".popover input[data-id='modifier_station_longitude']").val(lng);
    $(".popover input[data-id='modifier_station_longitude']").parent(".is-empty").removeClass("is-empty");
    $(".popover input[data-id='modifier_station_longitude']").trigger( "onchange" );*/
    if($("#station_modifie_popover").attr("aria-describedby")==undefined)
    {
        $("#station_modifie_popover").popover("show");
    }
    $(".popover input[data-id='modifier_station_adresse']").val(adresse);
    $(".popover input[data-id='modifier_station_adresse']").parent(".is-empty").removeClass("is-empty");
    $(".popover input[data-id='modifier_station_adresse']").trigger( "onchange" );

    $(".popover input[data-id='modifier_station_nom']").val(nom);
    $(".popover input[data-id='modifier_station_nom']").parent(".is-empty").removeClass("is-empty");
    $(".popover input[data-id='modifier_station_nom']").trigger( "onchange" );

    $("#modifier_station_id").val(id);
  }else  if($("#station_supprime_popover").attr("aria-describedby")!=undefined || $(".bouton_a[form-id='supprime_data_tramway']").attr("click")=="true")
  {
        if($("#station_supprime_popover").attr("aria-describedby")==undefined)
          $("#station_supprime_popover").popover("show");
        $("#supprimer_station_id").val(id);
        $(".popover label[data-id='supprimer_tramway_station_nom']").html("Station Nom :"+nom);
  }
}

  var marker1;
  var marker2;


function add(event) {
    var lat=event.latLng.lat();
    var lng=event.latLng.lng();
    dist_min=-1;

    for(var i of parcoure_map.keys())
        {
          var marker1 = new google.maps.Marker({
           position:station_map.get(i),
           map: map,
         });

         for(var j of parcoure_map.get(i))
         {
           var marker2 = new google.maps.Marker({
            position:station_map.get(j.s),
            map: map,
          });
          if(trajet_map.get(j.t).type=="B")
          {
            var flightPath = new google.maps.Polyline({
                path: [station_map.get(i),station_map.get(j.s)],
                geodesic: true,
                strokeColor: '#3F51B5',
                strokeOpacity: 1.0,
                strokeWeight: 2
              });
              flightPath.setMap(map);
          }
          else   if(trajet_map.get(j.t).type=="T") {
            var flightPath = new google.maps.Polyline({
                path: [station_map.get(i),station_map.get(j.s)],
                geodesic: true,
                strokeColor: '#3C763D',
                strokeOpacity: 1.0,
                strokeWeight: 2
              });
              flightPath.setMap(map);
          }
          else {
            var flightPath = new google.maps.Polyline({
                path: [station_map.get(i),station_map.get(j.s)],
                geodesic: true,
                strokeColor: '#FF5722',
                strokeOpacity: 1.0,
                strokeWeight: 2
              });
              flightPath.setMap(map);
          }
         }

        }


    /*tab_latlng=[];
    for( var elem of station_map.values())
      tab_latlng.push(elem);

    proche_station(event.latLng,tab_latlng);
  if(marker1)
    marker1.setMap(null);
  marker1 = new google.maps.Marker({
   position: {lat:lat,lng:lng},
   map: map,
 });*/


}

function  RouteDistance(origin, destination, service,responseFunction) {
    if(origin && destination){
        var request = {
            origin      : origin,
            destination : destination,
            travelMode  : google.maps.DirectionsTravelMode.WALKING
        }

        directionsService.route(request, function(response, status){
            if(status == google.maps.DirectionsStatus.OK){
                  responseFunction(Distance(response),destination);

            }
        });
    }

}

function Distance(result) {
  var total = 0;
  var myroute = result.routes[0];
  for (var i = 0; i < myroute.legs.length; i++) {
    total += myroute.legs[i].distance.value;
  }
  total = total / 1000;
  return  total + ' km';
}
$(document).ready(function(){

    $(".bouton_a").click(function(){
        $(".bouton_a").attr("click", false);
        $(this).attr("click",true);
        var form_id=  $(this).attr("form-id");
        clear_trajet();
       if(form_id=="ajout_data_tramway")
       {
           trace_trajet_tramway(-1);
       }
       else if(form_id=="modifie_data_tramway")
       {
         var valeur=$("#modifier_tramway_id_tramway").val();
         if(valeur!=-1)
          trace_trajet_tramway(valeur);
       }
       else if(form_id=="supprime_data_tramway")
       {
          var valeur=$("#supprimer_tramway_id_tramway").val();
          if(valeur!=-1)
           trace_trajet_tramway(valeur);
       }
       $(".form_tramway").hide();
       $(".popover").popover('hide');
       $("#"+form_id).show();
    });
    $(".bouton_b").click(function(){
        var text=$(this).html();
        $(this).html($("#transport_type_text").html());
        $("#transport_type_text").html(text);
          $(location).attr('href',getContextPath()+'/bus');
    });

    $('.popover_button').popover({
      html : true,
      content: function() {
        var html_popover=$(this).attr("popover-html-id");
         $("#"+html_popover).find(".ms-parent").remove();
        if($(this).attr("data-exist")=="true")
            return $("#"+html_popover).html();
        $(this).attr("data-exist","true");
        $("#"+html_popover).find(".label-floating").each(function() {
              var id=$( this ).find("input").attr("data-id");
              var form_id=$( this ).find("input").attr("form-id");
              var valeur=$( this ).find("input").val();
              if(id==undefined)
                 {
                   id=$( this ).find("select").attr("data-id");
                   form_id=$( this ).find("select").attr("form-id");
                   valeur="";
                 }
              if($( this ).find("input").attr("type")!="submit")
              {
                var html="<input id='"+id+"' value='"+valeur+"' name='"+id+"' type='hidden'>";
                 $("#"+form_id).html($("#"+form_id).html()+html);
              }
          });
            return $("#"+html_popover).html();
      }
    }).on('shown.bs.popover', function() {
      $("#"+$(this).attr("aria-describedby")).find(".label-floating").each(function() {
        var id_input=$( this ).find("input").attr("data-id");
        if($( this ).find("input").attr("type")=="submit") return ;
        var type="input";
        if(id_input==undefined)
          {
             id_input=$( this ).find("select").attr("data-id");
             type=$( this ).find("select").attr("data-type");
          }
        var val_input=$("#"+id_input).val();
        if(val_input!="")
          {
            $( this ).removeClass('is-empty');
            if(type!="select-multiple" && type!="select-single")
            $( this ).find("input").val(val_input);
          }
          if(type=="select-multiple" && id_input!=undefined)
          {
            $( this ).find("select").multipleSelect({
                       selectAll: false,
                       placeholder:"selecionner tramway"
                   });
            $( this ).find("select").multipleSelect("setSelects", val_input.split(","));
          }
          else if(type=="select-single" && id_input!=undefined)
          {
            $( this ).find("select").val(val_input);
          }
      });
  }).on('show.bs.popover', function() {
    $('.popover_button').not(this).popover('hide');
  }).on('hidden.bs.popover', function (e) {
    $(e.target).data("bs.popover").inState = { click: false, hover: false, focus: false }
});

  $.material.init();
  $(".shor").noUiSlider({
    start: 40,
    connect: "lower",
    range: {
      min: 0,
      max: 100
    }
  });

  $(".svert").noUiSlider({
    orientation: "vertical",
    start: 40,
    connect: "lower",
    range: {
      min: 0,
      max: 100
    }
  });

});
/*
$.ajax({
  type : "POST",
  contentType : "application/json",
   url: getContextPath()+"/getStationTramway/-1",
  data : JSON.stringify({}),
  dataType : 'json',
  timeout : 100000,
  success : function(data) {
    //console.log("SUCCESS: ", data);

    $.each(data, function(i, object) {

      var tab=[];

      var lat=object.Depart.Latitude;
      var lng=object.Depart.Longitude;
      var id=object.Depart.id;
      var nom=object.Depart.Nom;
      var adress=object.Depart.Adresse;
      tab_latlng.push({lat:lat,lng:lng});
      PositionnerstationTramway(map,lat,lng,id,nom,adress);
       tab.push({lat:lat,lng:lng});

       $.each(object.point, function(j, p) {
         tab.push({lat:p.Latitude ,lng:p.Longitude});
       });

      lat=object.Arrivee.Latitude;
       lng=object.Arrivee.Longitude;
       id=object.Arrivee.id;
       nom=object.Arrivee.Nom;
       adress=object.Arrivee.Adresse;
  tab_latlng.push({lat:lat,lng:lng});
      PositionnerstationTramway(map,lat,lng,id,nom,adress);
         tab.push({lat:lat,lng:lng});
      var ligne = new google.maps.Polyline({
          path: tab,
          id:object.id,
          duree:object.Duree,
          id_depart:object.Depart.id,
          id_arrivee:object.Arrivee.id,
          geodesic: true,
          strokeColor: '#009688',
          strokeOpacity: 1.0,
          strokeWeight: 3
        });

      ligne.setMap(map);
        Lignes_tab.push(ligne);
      ligne.addListener('click',click_trajet_modifier);
      });
      $(".wait").hide();
  },
  error : function(e) {
    console.log("ERROR: ", e);
  },
  done : function(e) {

  }
});

$.ajax({
  type : "POST",
  contentType : "application/json",
   url: getContextPath()+"/getStationBus/-1",
  data : JSON.stringify({}),
  dataType : 'json',
  timeout : 100000,
  success : function(data) {
    //console.log("SUCCESS: ", data);

    $.each(data, function(i, object) {

      var tab=[];

      var lat=object.Depart.Latitude;
      var lng=object.Depart.Longitude;
      var id=object.Depart.id;
      var nom=object.Depart.Nom;
      var adress=object.Depart.Adresse;
  tab_latlng.push({lat:lat,lng:lng});
      PositionnerstationBus(map,lat,lng,id,nom,adress);
       tab.push({lat:lat,lng:lng});

       $.each(object.point, function(j, p) {
         tab.push({lat:p.Latitude ,lng:p.Longitude});
       });

      lat=object.Arrivee.Latitude;
       lng=object.Arrivee.Longitude;
       id=object.Arrivee.id;
       nom=object.Arrivee.Nom;
       adress=object.Arrivee.Adresse;
  tab_latlng.push({lat:lat,lng:lng});
      PositionnerstationBus(map,lat,lng,id,nom,adress);
         tab.push({lat:lat,lng:lng});
      var ligne = new google.maps.Polyline({
          path: tab,
          id:object.id,
          duree:object.Duree,
          id_depart:object.Depart.id,
          id_arrivee:object.Arrivee.id,
          geodesic: true,
          strokeColor:'#3f51b5',
          strokeOpacity: 1.0,
          strokeWeight: 3
        });

      ligne.setMap(map);
        Lignes_tab.push(ligne);
      ligne.addListener('click',click_trajet_modifier);
      });
      $(".wait").hide();
  },
  error : function(e) {
    console.log("ERROR: ", e);
  },
  done : function(e) {

  }
});

*/
function clear_trajet(argument) {
    for (i in Stations_tab)
      Stations_tab[i].setMap(null);
   Stations_tab.length=0;

    for (i in   Lignes_tab)
        Lignes_tab[i].setMap(null);
     Lignes_tab.length=0;

     if(StationDepart)
      StationDepart.setMap(null);
    if(StationArrivee)
      StationArrivee.setMap(null);
    lignes.setPath([]);
}
function trace_trajet_tramway(id) {
    $(".wait").show();
  $.ajax({
    type : "POST",
    contentType : "application/json",
     url: getContextPath()+"/getStationTramway/"+id,
    data : JSON.stringify({}),
    dataType : 'json',
    timeout : 100000,
    success : function(data) {
      //console.log("SUCCESS: ", data);

      $.each(data, function(i, object) {

        var tab=[];
          var lat=object.Depart.Latitude;
         var lng=object.Depart.Longitude;
         var id=object.Depart.id;
         var nom=object.Depart.Nom;
         var adress=object.Depart.Adresse;
        PositionnerstationTramway(map,lat,lng,id,nom,adress);
         tab.push({lat:lat,lng:lng});

         $.each(object.point, function(j, p) {
           tab.push({lat:p.Latitude ,lng:p.Longitude});
         });

        lat=object.Arrivee.Latitude;
         lng=object.Arrivee.Longitude;
         id=object.Arrivee.id;
         nom=object.Arrivee.Nom;
         adress=object.Arrivee.Adresse;

        PositionnerstationTramway(map,lat,lng,id,nom,adress);
           tab.push({lat:lat,lng:lng});
        var ligne = new google.maps.Polyline({
            path: tab,
            id:object.id,
            duree:object.Duree,
            id_depart:object.Depart.id,
            id_arrivee:object.Arrivee.id,
            geodesic: true,
            strokeColor: '#009688',
            strokeOpacity: 1.0,
            strokeWeight: 3
          });

        ligne.setMap(map);
        Lignes_tab.push(ligne);

        ligne.addListener('click',click_trajet_modifier);
        });
        $(".wait").hide();
    },
    error : function(e) {
      console.log("ERROR: ", e);
    },
    done : function(e) {

    }
  });
}
function click_trajet_modifier()
{

  if($("#trajet_modifie_popover").attr("aria-describedby")!=undefined || $(".bouton_a[form-id='modifie_data_tramway']").attr("click")=="true")
  {
    if($("#trajet_modifie_popover").attr("aria-describedby")==undefined)
      $("#trajet_modifie_popover").popover("show");
    $(".popover input[data-id='modifier_trajet_duree']").val(this.duree);
    $(".popover input[data-id='modifier_trajet_duree']").trigger( "onchange" );
    $(".popover input[data-id='modifier_trajet_duree']").parent(".is-empty").removeClass("is-empty");
    $("#modifier_trajet_id").val(this.id);
    /*$.ajax({
           type: "POST",
           url: getContextPath()+"/getTramwayByStationDepartAndArrivee/"+this.id_depart+"/"+this.id_arrivee,
           data: {},
           success: function(response){
               $(".popover select[data-id='modifier_trajet_tamway']").multipleSelect("setSelects", response.split(","));
           },
           error: function(e){
              console.log("ERROR: ", e);
             }
           });*/
  }else if($("#trajet_supprime_popover").attr("aria-describedby")!=undefined || $(".bouton_a[form-id='supprime_data_tramway']").attr("click")=="true"){
    if($("#trajet_supprime_popover").attr("aria-describedby")==undefined)
      $("#trajet_supprime_popover").popover("show");
    $(".popover label[data-id='supprimer_tramway_trajet_id_label']").html("Trajet :"+this.id);
    $("#supprimer_trajet_id").val(this.id);
    $.ajax({
           type: "POST",
           url: getContextPath()+"/getTramwayByStationDepartAndArrivee/"+this.id_depart+"/"+this.id_arrivee,
           data: {},
           success: function(response){
               $(".popover select[data-id='supprimer_tramway_trajet_tamway']").multipleSelect("setSelects", response.split(","));
           },
           error: function(e){
              console.log("ERROR: ", e);
             }
           });
  }
  else if($("#trajet_popover").attr("aria-describedby")!=undefined)
  {
    var path_tab=this.getPath();
    for(var i=(path_tab.length-1);i>=0;i--)
    {
        var index=$("#nbr_posi").val();
        $("#nbr_posi").val(parseInt(index)+1);
        var html="";
        html+="<input  type='hidden' id='p_lat"+index+"' name='p_lat"+index+"' value='"+path_tab.j[i].lat()+"'>";
        html+="<input   type='hidden' id='p_lng"+index+"' name='p_lng"+index+"' value='"+path_tab.j[i].lng()+"'>";
        $("#form_ajout_data_tramway").html($("#form_ajout_data_tramway").html()+html);

          var path = lignes.getPath();
          path.push(new google.maps.LatLng(path_tab.j[i].lat(), path_tab.j[i].lng()));
          var point_trajet = new google.maps.Marker({
            position: new google.maps.LatLng(path_tab.j[i].lat(), path_tab.j[i].lng()),
            title: '',
            map: map
          });
          Stations_tab.push(point_trajet);
        }
      }


}

function ajoute_tramway() {
	 $.ajax({
	        type: "POST",
	        url: getContextPath()+"/addTramway",
	        data: $("#form_ajoute_data_tramway_tramway").serialize(),
	        success: function(response){
	         if(response!="")
	        	 {
                $(".popover").popover('hide');
                $("select[data-id='modifier_trajet_tamway']").html($("select[data-id='modifier_trajet_tamway']").html()+response);
                $("select[data-id='supprimer_tramway_tamway']").html($("select[data-id='supprimer_tramway_tamway']").html()+response);
                $("select[data-id='trajet_tramway']").html($("select[data-id='trajet_tramway']").html()+response);
	        	 }
	        },
	        error: function(e){
    	       console.log("ERROR: ", e);
	          }
	        });
}


function ajoute_tramway_trajet() {
	 $.ajax({
	        type: "POST",
	        url: getContextPath()+"/addTramwayData",
	        data: $("#form_ajout_data_tramway").serialize(),
	        success: function(response){
	         if(response!="")
	        	 {

	        	 }
	        },
	        error: function(e){
    	       console.log("ERROR: ", e);
	          }
	        });
}

function select_tramway_modifier(elem) {
     $(".popover").popover('hide');
  clear_trajet();
  var val=elem.value;
  $("#modifier_tramway_id").val(val);
  if(val!=-1)
  {
     trace_trajet_tramway(val);

     $.ajax({
       type : "POST",
       contentType : "application/json",
        url: "http://localhost:8080/GuidezMoi/getTramway/"+val,
       data : JSON.stringify({}),
       dataType : 'json',
       timeout : 100000,
       success : function(data) {

         if($("#tramway_modifie_popover").attr("data-exist")=="false")
         {
           $("input[data-id='modifier_tramway_depart']").val(data.Depart);
           $("input[data-id='modifier_tramway_arrivee']").val(data.Arrivee);
           $("input[data-id='modifier_tramway_prix']").val(data.Prix);
         }
         else {
           $("#modifier_tramway_depart").val(data.Depart);
           $("#modifier_tramway_arrivee").val(data.Arrivee);
           $("#modifier_tramway_prix").val(data.Prix);
         }
       },
       error : function(e) {
         console.log("ERROR: ", e);
       },
       done : function(e) {

       }
     });


  }
}

function select_tramway_supprimer(elem) {
     $(".popover").popover('hide');
  clear_trajet();
  var val=elem.value;
    $("#supprimer_station_tramway_id").val(val);
    $("#supprimer_trajet_tramway_id").val(val);
    $("#supprimer_tramway_id").val(val);

  if(val!=-1)
  {
    trace_trajet_tramway(val);
  }
}


$.ajax({
  type : "POST",
  contentType : "application/json",
   url: getContextPath()+"/getallbus",
  data : JSON.stringify({}),
  dataType : 'json',
  timeout : 100000,
  success : function(data1) {
    rassembler_bus(data1);
          $.ajax({
            type : "POST",
            contentType : "application/json",
             url: getContextPath()+"/getalltramway",
            data : JSON.stringify({}),
            dataType : 'json',
            timeout : 100000,
            success : function(data2) {
            rassembler_tramway(data2);

            for(var i of station_map.keys())
            {
              var tab=[];
              tab=successeur(tab,i);
              for(var j of station_map.keys())
                if(tab.indexOf(j) == -1)
                {
                  new_arc(station_map.get(i),station_map.get(j),i,j);
                }
            }

            console.log("END");
            },
            error : function(e) {
              console.log("ERROR2: ", e2);
            },
            done : function(e2) {
            }
          });
  },
  error : function(e) {
    console.log("ERROR1: ", e1);
  },
  done : function(e1) {

  }
});

function rassembler_bus(data) {
  $.each(data, function(i, object) {
   $.each(object.Itineraires, function(j, trj) {
        station_map.set(trj.Depart.id,{lat:trj.Depart.Latitude,lng:trj.Depart.Longitude});
        station_map.set(trj.Arrivee.id,{lat:trj.Arrivee.Latitude,lng:trj.Arrivee.Longitude});

        trajet_map.set(trj.id+"+",{type:'B',sens:'+',temp:trj.Duree,transport:object.id,cout:object.prix});
        trajet_map.set(trj.id+"-",{type:'B',sens:'-',temp:trj.Duree,transport:object.id,cout:object.prix});
        if(parcoure_map.get(trj.Depart.id)==undefined)
        {
          var tab=[];
          tab.push({s:trj.Arrivee.id,t:trj.id+"+"});
          parcoure_map.set(trj.Depart.id,tab);
        }
        else {
           var tab=parcoure_map.get(trj.Depart.id);
           tab.push({s:trj.Arrivee.id,t:trj.id+"+"});
           parcoure_map.set(trj.Depart.id,tab);
        }
        if(parcoure_map.get(trj.Arrivee.id)==undefined)
        {
          var tab=[];
          tab.push({s:trj.Depart.id,t:trj.id+"-"});
          parcoure_map.set(trj.Arrivee.id,tab);
        }
        else {
           var tab=parcoure_map.get(trj.Arrivee.id);
           tab.push({s:trj.Depart.id,t:trj.id+"-"});
           parcoure_map.set(trj.Arrivee.id,tab);
        }
    });
   });
}


function rassembler_tramway(data) {
  $.each(data, function(i, object) {
   $.each(object.Itineraires, function(j, trj) {
        station_map.set(trj.Depart.id,{lat:trj.Depart.Latitude,lng:trj.Depart.Longitude});
        station_map.set(trj.Arrivee.id,{lat:trj.Arrivee.Latitude,lng:trj.Arrivee.Longitude});
        trajet_map.set(trj.id+"+",{type:'T',sens:'+',temp:trj.Duree,transport:object.id,cout:object.prix});
        trajet_map.set(trj.id+"-",{type:'T',sens:'-',temp:trj.Duree,transport:object.id,cout:object.prix});
        if(parcoure_map.get(trj.Depart.id)==undefined)
        {
          var tab=[];
          tab.push({s:trj.Arrivee.id,t:trj.id+"+"});
          parcoure_map.set(trj.Depart.id,tab);
        }
        else {
           var tab=parcoure_map.get(trj.Depart.id);
           tab.push({s:trj.Arrivee.id,t:trj.id+"+"});
           parcoure_map.set(trj.Depart.id,tab);
        }
        if(parcoure_map.get(trj.Arrivee.id)==undefined)
        {
          var tab=[];
          tab.push({s:trj.Depart.id,t:trj.id+"-"});
          parcoure_map.set(trj.Arrivee.id,tab);
        }
        else {
           var tab=parcoure_map.get(trj.Arrivee.id);
           tab.push({s:trj.Depart.id,t:trj.id+"-"});
           parcoure_map.set(trj.Arrivee.id,tab);
        }
    });

   });
}



function successeur(successeur_map,id) {
      successeur_map.push(id);
      var tab=parcoure_map.get(id);
      for (var elem of tab)
       {
         if(successeur_map.indexOf(elem.s)==-1)
         {
           successeur_map=successeur(successeur_map,elem.s);
         }
       }
       return successeur_map;
}


function new_arc(origine,destination,i,j) {
  service.getDistanceMatrix({
      origins: [origine],
      destinations: [destination],
      travelMode: google.maps.TravelMode.DRIVING,
      unitSystem: google.maps.UnitSystem.METRIC,
      avoidHighways: false,
      avoidTolls: false
    }, function set_arc(response, status) {
      if (status == google.maps.DistanceMatrixStatus.OK)
        {
          var prix=1.70+((response.rows[0].elements[0].distance.value)*0.20);
          if(prix<7) prix=7;
          trajet_map.set(i+""+j+"+",{type:'TX',sens:'+',temp:response.rows[0].elements[0].duration.value/60,transport:-1,cout:prix});
          trajet_map.set(i+""+j+"-",{type:'TX',sens:'-',temp:response.rows[0].elements[0].duration.value/60,transport:-1,cout:prix});

          var tab1=parcoure_map.get(i);
          tab1.push({s:j,t:i+""+j+"+"});
          parcoure_map.set(i,tab1);

          var tab2=parcoure_map.get(j);
          tab2.push({s:i,t:i+""+j+"-"});
          parcoure_map.set(j,tab2);
        }
  });
}



/*var objet={courtchemin:new Map(),derniernoeud:new Map(),noeud_visite:[]};
objet.derniertraget.set(id,{id_station_d:,id_trajet:,id_station_a:,id_transport:,cout:});
object.courtchemin.set(id,{id_station:,id_trajet:});
var noeud_visite=[];
noeud_visite.push();
noeud_visite.push();
*/

function function_name(objet,noeudacteul,trajet,arrivee) {
  var bool=false;
  var tab=parcoure_map.get(noeudacteul);
  var o=objet.derniertraget.get(trajet);
  objet.derniernoeud.delete(trajet);
  for (var elem of tab)
  {
      if(elem.s==arrivee)
      {
        objet.courtchemin.set(noeudacteul,{id_station:elem.s,id_trajet:elem.t});
        objet.noeud_visite.push(elem.s);
        return objet;
      }
      if(object.noeud_visite.indexOf(elem.s)==-1)
      {
        var cout=o.cout;
        if(o.transport != trajet_map.get(elem.t).transport)
          cout+=trajet_map.get(elem.t).cout;
        objet.derniertraget.set(elem.t,{id_station_d:noeudacteul,id_trajet:elem.t,id_station_a:elem.s,cout:cout});
        bool=true;
      }
  }

  function deg2rad(){

  }
  function calcule_heuristique(object source, object destination)
  {
    var r=6371; // raduis
    var dlat=deg2rad(destination.getLat()-source.getLat());
    var dlong=deg2rad(destination.getLong()-source.getLong());
    var a= Math.sin(dlat/2)*Math.sin(dlat/2)+Math.cos(deg2rad(source.getLat()))*Math.cos(deg2rad(destination.getLat()))*Math.sin(dlong/2)*Math.sin(dlong/2);
    var c=2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
    var d=r*c;
    return d;
  }

}
function construction_path(){

}

/*   
    Obejct source: station qui contient var station= { id:,longitude:,latitude:}
*/
function findPath(object source, object destination)
{
  var open_list=[];
  var closed_list=[];
  var transport_possible=[];
  open_list.push(source);
  var successeur=[];
  successeur=parcoure_map.get(source.id);
  for(var e of successeur){
    var trajet_id=e.t;
    if(trajet_map.get(trajet_id).type !="T"){
      transport_possible.push({s:e.s,t:e.t,cout:trajet_map.get(trajet_id).cout,parent:e.t});// ici on ajoute les permiers possibilités 
    }

  }

  //SORT BY COST : TRI PAR SELECTION
  for(var i=0;i<transport_possible.length-1;i++)
  {
    var min = transport_possible[i];
    var min_i = i;
      for(var j=i+1;j<transport_possible.length;j++)
      {
        if(transport_possible[j].cout<min)
             { min = transport_possible[j];  
              min_i =j; }
      }
    transport_possible[min_i]=transport_possible[i];
    transport_possible[i]=min;
  }

  /// A*

  for(var tr of transport_possible)
  {
    var station_courant = tr;
    open_list.push(station_current);

    while(!open_list.is-empty())
    {
        var current = open_list.get(0);
        if(current.id==arrivee.id) return construction_path();
        open_list.remove(0);
        closed_list.push(current);
        var successeur =[];
        for(var e of parcoure_map.get(current.id)){

          successeur.push({s:e.s,t:e.t,cout:trajet_map.get(trajet_id).cout,heuristique:,G:,parent:current.t});
        }
        
        // parcourir les sueccsseurs

        for(var j of successeur){
          if(j.t==current.t) // le mm moyen de transport 
          {
            j.heuristique=calcule_heuristique(j,destination);
            j.G=j.cout+j.heuristique; // ici on ne peut changer 

          }
          else
          {
            tentative_g_score=current.cout+j.cout;
            if(open_list.indexOf(j)==-1 || j.cout>tentative_g_score)
            {
              j.cout=tentative_g_score;
              j.heuristique=calcule_heuristique(j,destination);
              j.parent=current.t;
            }

          }
          if(open_list.indexOf(j)==-1){
            open_list.push(j);
          }
        }

    }
  }
  findPath(j, destination);
}
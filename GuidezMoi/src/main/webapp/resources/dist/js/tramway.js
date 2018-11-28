var poly;
var map;
var lignes;
var StationDepart=null;
var StationArrivee=null;
var Stations_tab=[];
var Lignes_tab=[];
var directionsService
var directionsDisplay
function initMap() {
  directionsService = new google.maps.DirectionsService;
   directionsDisplay = new google.maps.DirectionsRenderer;

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

function add(event) {
     var lat=event.latLng.lat();
    var lng=event.latLng.lng();
    var adresse="";
    if($("#trajet_popover").attr("aria-describedby")!=undefined)
    {
      var index=$("#nbr_posi").val();
      $("#nbr_posi").val(parseInt(index)+1);
      var html="";
      html+="<input  type='hidden' id='p_lat"+index+"' name='p_lat"+index+"' value='"+lat+"'>";
      html+="<input   type='hidden' id='p_lng"+index+"' name='p_lng"+index+"' value='"+lng+"'>";
      $("#form_ajout_data_tramway").html($("#form_ajout_data_tramway").html()+html);

        var path = lignes.getPath();
        path.push(new google.maps.LatLng(lat, lng));
        var point_trajet = new google.maps.Marker({
          position: new google.maps.LatLng(lat, lng),
          title: '',
          map: map
        });
        Stations_tab.push(point_trajet);
    }
    else {
      var latlng = new google.maps.LatLng(lat, lng);
          var geocoder = new google.maps.Geocoder();
          geocoder.geocode({ 'latLng': latlng }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
            if (results[1]) {
                adresse=results[1].formatted_address;
                ajouteStation(map,lat,lng,-1,"",adresse);
                     }
            }
          });
    }


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

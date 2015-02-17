<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
	<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDQ0PseXCnnVGmSJU-c6xuefNZAiF4J0_k&sensor=false"></script>
	<script>
	function initialize() {
		var myCenter=new google.maps.LatLng(50.07513,19.99791);
		var mapProp = {
		  	center:myCenter,
		  	zoom:15,
		  	mapTypeId:google.maps.MapTypeId.ROADMAP
		  	};
			
			var map=new google.maps.Map(document.getElementById("googleMap")
					,mapProp);
				  
			var marker=new google.maps.Marker({
			  	position:myCenter,
			  	});
				
				marker.setMap(map);
			}
		
	google.maps.event.addDomListener(window, 'load', initialize);
	</script>
	
	
	<h1>Dane kontaktowe</h1>
			
	<div class="container-fluid">
		<div class="row-fluid">
				<div class="span2">
					<h2>Adres:</h2>
					<p>al. Jana Pawła II 37<p>
					<p>31-864 Kraków</p>
					<h2>Telefon/fax:</h2>
					<p>(070) 012-34-56 (070)-011-22-33<p>
				</div>
				
				<div class="span2 offset1">
					<h2>Mapa:</h2>
					<div align="right" id="googleMap" style="width:350px;height:280px; border: 1px solid #d4d4d4;"></div>	
				</div>
				<div class="span4 offset3">
					<img src="<c:url value="/static/img/wr_kon.png" />" />
				</div>
		</div>
	</div>
</div>
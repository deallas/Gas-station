<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h3 style="text-align: center"><spring:message code="label.emppanel.containerlevel" /></h3>
<div class="measurement_meter" style="width: 900px; margin: 0 auto">
	<c:forEach var="measurement" items="${pcmLevel}">
		<div id="measurement_meter_l_${measurement.petrolContainer.id}" style="width: 300px; height: 200px; float: left"></div>
		<script>
	   		$(document).ready(function(){
	 			s1 = [${measurement.value}];
	    		
	 			plot = $.jqplot('measurement_meter_l_${measurement.petrolContainer.id}',[s1],{
	    			seriesDefaults: {
	    				renderer: $.jqplot.MeterGaugeRenderer,
	    			    rendererOptions: {
	    			    	label: '${measurement.petrolContainer.type} [ ${measurement.petrolContainer.id} ] - ${measurement.value} mm',
	    			        labelPosition: 'bottom',
	    			        ticks: [0, 500, 1000, 1500, 2000, 2500, 3000, 3500],
	    			        intervals:[500, 1500, 3500 ],
	    			        intervalColors:['#cc6666', '#E7E658', '#66cc66']
	    			    }
	    			}
	    		 });
	    	});
		</script>
	</c:forEach>
</div>
<br style="clear: both" />

<h3 style="text-align: center"><spring:message code="label.emppanel.containerpressure" /></h3>
<div class="measurement_meter" style="width: 900px; margin: 0 auto">
	<c:forEach var="measurement" items="${pcmPressure}">
		<div id="measurement_meter_p_${measurement.petrolContainer.id}" style="width: 300px; height: 200px; float: left"></div>
		<script>
	   		$(document).ready(function(){
	 			s1 = [${measurement.value}];
	    		
	 			plot = $.jqplot('measurement_meter_p_${measurement.petrolContainer.id}',[s1],{
	    			seriesDefaults: {
	    				renderer: $.jqplot.MeterGaugeRenderer,
	    			    rendererOptions: {
	    			    	label: '${measurement.petrolContainer.type} [ ${measurement.petrolContainer.id} ] - ${measurement.value} Pa',
	    			        labelPosition: 'bottom',
	    			        ticks: [800, 1000, 1200, 1400],
	    			        intervals:[850, 1000, 1200, 1350, 1400],
	    			        intervalColors:['#cc6666', '#E7E658', '#66cc66', '#E7E658', '#cc6666']
	    			    }
	    			}
	    		 });
	    	});
		</script>
	</c:forEach>
</div>
<br style="clear: both" />
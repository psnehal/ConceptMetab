<!DOCTYPE html>
<html>
	<head>
		
		<g:set var="entityName" value="${message(code: 'concepts.label', default: 'Concepts')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'createChart.css')}" type="text/css">
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	</head>
 


<body> 


<body> 

<br/>

	
	<h3> Concept Informations</h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<hr>
			<table id = "result">
				<tr><td>Name</td>
					<td>	<g:set var="cnm" value="${conceptsInstance?.id}"/>	
					<span class="property-value" aria-labelledby="original_id-label">	<g:link controller="Enrichments" action="filterSlider" params="[id1:0,id2:300,q:cnm,fil:'pval' ]"><b>${conceptsInstance?.name.capitalize()}</b></g:link></span>
					</td></tr>
				<tr><td>Original-Id</td>
					<td><span class="property-value" aria-labelledby="original_id-label"><g:fieldValue bean="${conceptsInstance}" field="original_id"/></span></td></tr>
				<tr><td># of Compounds</td>
					<td><span class="property-value" aria-labelledby="num_compounds-label">	<g:link controller="Compounds_in_concepts" action="findComp" params="[q:cnm]"><g:fieldValue bean="${conceptsInstance}" field="num_compounds"/></g:link></span></td></tr>
				<tr><td>Concept Type</td>
					<td><g:each in="${conceptsInstance.concept_types}" var="c">
						<span class="property-value" aria-labelledby="ctypes-label"><g:link controller="concept_types" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
				  </td></tr>
			
			
			</table>
			
			
	

 </body>
 </html>



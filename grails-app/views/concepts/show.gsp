
<%@ page import="conceptmetab.Concepts" %>
<%@ page import="conceptmetab.Compounds" %>
<%@ page import="conceptmetab.Compounds_in_concepts" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'concepts.label', default: 'Concepts')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		
		
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	</head>
	<body>
		<div id="show-concepts" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list concepts">
			
				<g:if test="${conceptsInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="concepts.name.label" default="Name" /></span>
					<g:set var="cnm" value="${conceptsInstance?.id}"/>	
					
					<span class="property-value" aria-labelledby="original_id-label">	<g:link controller="Enrichments" action="filterSlider" params="[id1:1e-45,id2:0.01,q:cnm,fil:'qval' ]"><b>${conceptsInstance?.name.capitalize()}</b></g:link></span>
						
					
				</li>
				</g:if>		
			
				<g:if test="${conceptsInstance?.original_id}">
				<li class="fieldcontain">
					<span id="original_id-label" class="property-label"><g:message code="concepts.original_id.label" default="" />Concept-Id </span>
					
					<span class="property-value" aria-labelledby="original_id-label"><g:fieldValue bean="${conceptsInstance}" field="original_id"/></span>
					
				</li>
				
				<li class="fieldcontain">
					<span id="ctypes-label" class="property-label"><g:message code="concepts.ctypes.label" default="Concept Type" /></span>
					
						<g:each in="${conceptsInstance.concept_types}" var="c">
						<span class="property-value" aria-labelledby="ctypes-label"><g:link controller="concept_types" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
				
				
			
				<g:if test="${conceptsInstance?.num_compounds}">
				<li class="fieldcontain">
					<span id="num_compounds-label" class="property-label">Number of Compounds</span>
					<g:set var="cnm" value="${conceptsInstance?.id}"/>	
						<span class="property-value" aria-labelledby="num_compounds-label">	<g:link controller="Compounds_in_concepts" action="findComp" params="[q:cnm]"><g:fieldValue bean="${conceptsInstance}" field="num_compounds"/></g:link></span>
					
				</li>
				</g:if>
				
					
				
			
				
			
			</ol>
			
		
	</body>
</html>

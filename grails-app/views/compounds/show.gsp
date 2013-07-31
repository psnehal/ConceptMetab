
<%@ page import="conceptmetab.Compounds" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'compounds.label', default: 'Compounds')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-compounds" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-compounds" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list compounds">
			
				<g:if test="${compoundsInstance?.display_id}">
				<li class="fieldcontain">
					<span id="display_id-label" class="property-label"><g:message code="compounds.display_id.label" default="Displayid" /></span>
					
						<span class="property-value" aria-labelledby="display_id-label"><g:fieldValue bean="${compoundsInstance}" field="display_id"/></span>
					
				</li>
				</g:if>
			
				
			
				<g:if test="${compoundsInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="compounds.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${compoundsInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${compoundsInstance?.compSrc}">
				<li class="fieldcontain">
					<span id="compSrc-label" class="property-label"><g:message code="compounds.compSrc.label" default="Comp Src" /></span>
					
						<g:each in="${compoundsInstance.compSrc}" var="c">
						<span class="property-value" aria-labelledby="compSrc-label"><g:link controller="compound_sources" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${compoundsInstance?.id}" />
					<g:link class="edit" action="edit" id="${compoundsInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

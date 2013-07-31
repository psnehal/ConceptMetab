<%@ page import="conceptmetab.Compound_sources" %>



<div class="fieldcontain ${hasErrors(bean: compound_sourcesInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="compound_sources.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${compound_sourcesInstance?.name}"/>
</div>


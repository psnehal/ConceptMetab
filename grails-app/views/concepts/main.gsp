
<%@ page import="conceptmetab.Concepts" %>
<%@ page import="conceptmetab.Concept_types" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'concepts.label', default: 'Concepts')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
		 <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
        <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script> 
        
        <g:javascript>
            $(document).ready(function() {
               $('#city').autocomplete({
                      source: '<g:createLink controller="Concepts" action="ajaxFindCity"/>'
                  
                });
              
            });       
        </g:javascript>
	</head>
	<body>
	
		<a href="#list-concepts" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
					<li><a class="intro"  href="${createLink(uri:'/concepts/intro')}">About</a></li>
			</ul>
		</div>
		
		<div class ="searchm" align="center">
		 <g:form action="search" method="get">
	            <g:textField name="q" id="city" value="${params.q}"/>
	            <g:submitButton name="Search concepts"  style="background-color:#C2E0FF"/>
	        </g:form>		
		</div>
		
		<div id="list-concept_types" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<table>
				<thead>
					<tr>
					<g:sortableColumn property="name" title="Concept Type" />					
					
						<g:sortableColumn property="info" title="Information" />
							<g:sortableColumn property="size" title="Concept_size" />
					
						
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${map}" status="i" var="ci_inst">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
						 <g:set var="cnm" value="${ci_inst.getKey()}"/>			
						<g:link controller="Concepts" action="dbspecific" params="[name:cnm]">${cnm}</g:link>
						</td>
									
						<td> <a href="#"><img src="${createLinkTo(dir: 'images/skin', file: 'information.png')}"  alt="Grails"/></a></td>
						<td>${ci_inst.getValue() }</td>
					
					</tr>
				</g:each>
				
				</tbody>
			</table>
			
		
		</div>
	</body>
</html>

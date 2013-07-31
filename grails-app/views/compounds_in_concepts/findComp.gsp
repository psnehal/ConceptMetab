<%@ page import="conceptmetab.Concepts" %>
<%@ page import="conceptmetab.Concept_types" %>
<!DOCTYPE html>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>ConceptMetab</title>
  </head>
  <body>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    
    <div>
     
					<table>
					<thead>
					<tr>
					<th>Compound id</th>
					<th>Compound Name</th>					
					</thead>
					<tbody>
                    <g:each in="${ searchResults}" >
                    <tr>
                    
                        <td>${ it.id}</td>
                        <td>${it.compounds.name }</td>
                        <tr>
                    </g:each>
                   </tbody> 
                   
	 </table>
	</div> 

    <div class="body">
      <div class="list">
        <g:each in="${searchResults}" status="i" var="compounds">

        <div class="compounds">
          <h3>
            <g:link action="show"
                    id="${compounds_id}">${compounds_id}</g:link>
          </h3>
         
          </br>
          <br>
          
          
          
		
          
          
          
        </div>

        </g:each>
      </div>
    </div>
    
  </body>
</html>
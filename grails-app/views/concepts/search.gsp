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

    <div class="body">
      <div class="list">
        <g:each in="${searchResults}" status="i" var="concepts">

        <div class="concepts">
          <h3>
            <g:link action="show"
                    id="${concepts.id}">${concepts.name}</g:link>
          </h3>
          
          
         
		<p>${Concept_types.get(name)} ${concepts.original_id}</p>
          
          
          
        </div>

        </g:each>
      </div>
    </div>
    <div class="paginateButtons">
      <g:paginate total="${resultCount}" params="${flash}"/>
    </div>
  </body>
</html>

<html lang="en">
<head>
<meta charset="utf-8" />


<title>jQuery UI Slider - Range slider</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script>
$(function() {

	$( "#tabs" ).tabs();

	var first = getUrlVars()["id1"];
    var second = getUrlVars()["id2"];
    var q = getUrlVars()["q"];
   console.log("q ="+ q);

   $("#slider-verticalbackwards").slider({
	    orientation: "vertical",
	    range: "min",
	    min: 0.01,
	    max: 400.01,
	    value: 200.009,
	    
	    slide: function(event, ui) {

	    	var id2_int = 400.01- ui.value.toFixed(3) ;
	    	var id2 = Math.pow(10, -id2_int);
	    	console.log("id2_int"+ id2_int + "  id2 = " + id2)
	        $("#amount-backwards").val(id2);
	        
	        
	        
	    },


		change:function(event, ui)
		{
			var id2_int = 400.01- ui.value.toFixed(3) ;
	    	var id2 = Math.pow(10, -id2_int);
	    	var id = getUrlVars()["q"];
	        var id1 = 1.45e-323;

			console.log("changed id1 = "+ id1 +"  id2  = " + id2 );
			window.location.href ='http://localhost:8080/conceptmetab/enrichments/filterSlider?id1='+id1+'&id2='+id2+'&q='+id+'&fil=pval';
			
		}
	});
   var idc = Math.pow(10, -$("#slider-verticalbackwards").slider("value"));
   console.log("idc is "+ idc);
	$("#amount-backwards").val(idc);
	//$("#amount-backwards").val(100 - $("#slider-verticalbackwards").slider("value"));
//****************************************************************qval Slider*****************************************************************
  $("#slider-qval-back").slider({
	    orientation: "vertical",
	    range: "min",
	    min: 0,
	    max: 500,
	    value: 100,
	    slide: function(event, ui) {

	    	var id2_int = 500- ui.value;
	    	var id2 = Math.pow(10, -id2_int);
	    	console.log("id2_int"+ id2_int + "  id2 = " + id2)
	    	
	        $("#amount-slider-qval-back").val(id2);
	        
	        
	        
	    },


		change:function(event, ui)
		{
			var id2_int = 500- ui.value;
	    	var id2 = Math.pow(10, -id2_int);
	    	var id = getUrlVars()["q"];
	        var id1 = 1.45e-323;

			console.log("changed id1 = "+ id1 +"  id2  = " + id2 );
			window.location.href ='http://localhost:8080/conceptmetab/enrichments/filterSlider?id1='+id1+'&id2='+id2+'&q='+id+'&fil=qval';
			
		}
	});
   var idq = Math.pow(10, -$("#slider-verticalbackwards").slider("value"));
   console.log("idc is "+ idq);
	$("#amount-slider-qval-back").val(idq);
	//$("#amount-backwards").val(100 - $("#slider-verticalbackwards").slider("value"));
	//***************************************************************************************************************************************
	
	
	//itializes slider
			$( "#slider-range" ).slider({
				
					range: true,
					min:  0,
					max: 400,
					values: [ 0, 373 ],
					
		
					
					slide: function( event, ui ) {
								console.log("inside slide");
							//getter	
							$( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
							//window.alert(ui.values[ 0 ] + " - $" + ui.values[ 1 ]);
							console.log("value 1 ==  "+ ui.values[ 0 ]+ "   value 2 == " + ui.values[ 1 ]);
							//var id2 = Math.pow(10, -ui.values[ 0 ]);
							//var id1 = Math.pow(10, -ui.values[ 1 ]);
							console.log("3");
							var id2 = ui.values[ 1 ];
							var id1 = ui.values[ 0 ];
							console.log("id1 == "+ ui.values[ 0 ]);
							console.log("id2 == "+ ui.values[ 1 ]);
							var uiv =  Math.pow(10, - ui.values[ 1 ]);
							$("#para").text(uiv);
							//var url = window.location.href;
						//window.open(link ,'Popup','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no, width=420,height=400,left=430,top=23');
					},
					change:function(event, ui)
					{
						console.log("inside change");
						var id1_int = ui.values[ 1 ];
						var id2_int = ui.values[ 0 ];
						
						//$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +" - $" + $( "#slider-range" ).slider( "values", 1 ))
						var id = getUrlVars()["q"];
						console.log("id is "+ id);
						console.log("id is before id1 =  "+ id1 + " id2 = "+ id2);
						
						var id1 = Math.pow(10, -id1_int)
						var id2 = Math.pow(10, -id2_int);
						console.log("changed id1 = "+ id1 +"  id2  = " + id2 );
						window.location.href ='http://localhost:8080/conceptmetab/enrichments/filterSlider?id1='+id1+'&id2='+id2+'&q='+id+'&fil=pval';  
					}
				
			}); //Slider

			$( "#qval-range" ).slider({
				
				range: true,
				min:  0,
				max: 400,
				values: [ 0, 373 ],
	
				
				slide: function( event, ui ) {
							console.log("inside slide");
						//getter	
						$( "#qval" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
						//window.alert(ui.values[ 0 ] + " - $" + ui.values[ 1 ]);
						console.log("value 1 ==  "+ ui.values[ 0 ]+ "   value 2 == " + ui.values[ 1 ]);
						//var id2 = Math.pow(10, -ui.values[ 0 ]);
						//var id1 = Math.pow(10, -ui.values[ 1 ]);
						console.log("3");
						var id2 = ui.values[ 1 ];
						var id1 = ui.values[ 0 ];
						console.log("id1 == "+ ui.values[ 0 ]);
						console.log("id2 == "+ ui.values[ 1 ]);
						var uiv =  Math.pow(10, - ui.values[ 1 ]);
						$("#qval").text(uiv);
						//var url = window.location.href;
					//window.open(link ,'Popup','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no, width=420,height=400,left=430,top=23');
				},
				change:function(event, ui)
				{
					console.log("inside change");
					var id1_int = ui.values[ 1 ];
					var id2_int = ui.values[ 0 ];
					
					//$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +" - $" + $( "#slider-range" ).slider( "values", 1 ))
					var id = getUrlVars()["q"];
					console.log("id is "+ id);
					console.log("id is before id1 =  "+ id1 + " id2 = "+ id2);
					
					var id1 = Math.pow(10, -id1_int)
					var id2 = Math.pow(10, -id2_int);
					console.log("changed id1 = "+ id1 +"  id2  = " + id2 );
					window.location.href ='http://localhost:8080/conceptmetab/enrichments/filterSlider?id1='+id1+'&id2='+id2+'&q='+id+'&fil=qval';  
				}
			
		}); //Slider
		}); //Function


function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }
</script>


<!-- JSON support for IE (needed to use JS API) -->
 
 <g:javascript src="cytoscape/min/json2.min.js" />
        
 <!-- Flash embedding utility (needed to embed Cytoscape Web) -->

 <g:javascript src="cytoscape/min/AC_OETags.min.js" />
        
<!-- Cytoscape Web JS API (needed to reference org.cytoscapeweb.Visualization) -->
<g:javascript src="cytoscape/min/cytoscapeweb.min.js" />
     
         
<title>Enrichments</title>
  <style>
            /* The Cytoscape Web container must have its dimensions set. */
            html, body { height: 100%; width: 100%; padding: 0; margin: 0; }
            #cytoscapeweb { width: 100%; height: 100%; }
        </style>
  
  <script type="text/javascript">
            window.onload=function() {
                // id of Cytoscape Web container div
                var div_id = "cytoscapeweb";
                
                // you could also use other formats (e.g. GraphML) or grab the network data via AJAX
               
				var t = ${check};
				console.log(t)
               // var networ_json = { 
				// data :  ${check}
                  //     };

				 var networ_json = {
                    // you need to specify a data schema for custom attributes!
                    dataSchema: {
                        nodes: [ { name: "label", type: "string" },
                                 { name: "conTypes", type: "string" },
                                 { name: "comNo", type: "interger" }
                            ],
                        edges: [ { name: "label", type: "string" },
                                 { name: "thick", type: "integer" },
                                 { name: "db_id", type: "string" }
                        ]
                    },
                    // NOTE the custom attributes on nodes and edges
                    data:  ${check}
            };
				 var visual_style = {
		                    
		                    nodes: {
		                        shape: "ELLIPSE",
		                        borderWidth: 3,
		                        borderColor: "#ffffff",
		                        size: {
		                            defaultValue: 25,
		                            continuousMapper: { attrName: "comNo", minValue: 25, maxValue: 75 }
		                        },
		                        color: {
		                            discreteMapper: {
		                            	attrName: "conTypes",
				                        entries: [
				                            { attrValue: "GOBP", value: "#FF0000"},
				                            { attrValue: "GOCC", value: "#FFFF00" },
				                            { attrValue: "GOMF", value: "#64FE2E" },
				                            { attrValue: "Enzyme", value: "#0000FF" },
				                            { attrValue: "KEGG", value: "#CC2EFA"},
				                            { attrValue: "MeSH", value: "#CCCCFF" },
				                            { attrValue: "Cluster", value: "#000000" }
				                        ]
		                            }
		                        },
		                        labelHorizontalAnchor: "center"
		                    },
		                    edges: {
		                    	width: {
		                            defaultValue: 1,
		                            continuousMapper: { attrName: "thick", minValue: 1, maxValue: 10 }
		                        },
		                        color: "#0B94B1"
		                        	
		                    }
		                };

				
                         
                
                // initialization options
                var options = {
                        // where you have the Cytoscape Web SWF
                        swfPath: "/conceptmetab/swf/CytoscapeWeb",
                        // where you have the Flash installer SWF
                        flashInstallerPath: "/conceptmetab/swf/playerProductInstall"

                        
                    };
                // init and draw
                var vis = new org.cytoscapeweb.Visualization(div_id, options);

                vis.ready(function() {
                    
                    // add a listener for when nodes and edges are clicked
                    vis.addListener("click", "nodes", function(event) {
                        handle_click(event);
                    })
                    .addListener("click", "edges", function(event) {
                        handle_click(event);
                    });
                    
                  
                    
                    function clear() {
                        document.getElementById("note").innerHTML = "";
                    }
                
                    function print(msg) {
                        document.getElementById("note").innerHTML += "<p>" + msg + "</p>";
                    }
                });
 

                vis.ready(function() {
                    
                    // add a listener for when nodes and edges are clicked
                    vis.addListener("click", "nodes", function(event) {
                        handle_click(event);
                    })
                    .addListener("click", "edges", function(event) {
                    	handle_edge_click(event);
                    });
                    
                    function handle_click(event) {
                         var target = event.target;
                         
                         clear();
                         
                         var test = "event.group = " + event.group ;
                        
                             var test2 = "0"+ test;
                         for (var i in target.data) {
                            var variable_name = i;
                            var variable_value = target.data[i];
                            //print( "    " + variable_name + " = " + variable_value );
                            var variable_info = "    " + variable_name + " = " + variable_value + "\n";
                            test =test+variable_info;
                            console.log("i = "+ i + " value = "+ target.data[i]);
                            if(i == "id")
		                           {
		                           console.log("found pval"+ target.data[i]);
		                           var id = target.data[i];
		                           }
                          
                             }
                        
                         window.open('displayMsg?q='+id,'Popup','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no, width=620,height=500,left=430,top=23'); 
                         
                    }

                    function handle_edge_click(event) {
                        var target = event.target;
                        
                        clear();
                        
                        var test = "event.group = " + event.group ;
                        var test2 = "0"+ test;
                        var map = new Object();
						console.log("target data"+target.id)
                        
                        for (var i in target.data) {
                           var variable_name = i;
                           var variable_value = target.data[i];
                           //print( "    " + variable_name + " = " + variable_value );
                          console.log("i = "+ i + " value = "+ target.data[i]);
                          var variable_info = "    " + variable_name + " = " + variable_value + ";";
                            test =test+variable_info;
			                   if(i == "db_id")
			                           {
			                           console.log("found db_id == "+ target.data[i]);
			                           var id = target.data[i];
			                           }
			                 
                            }
                        console.log("test"+test)
                        window.open('displayEdge?q='+id,'Popup','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no, width=620,height=500,left=430,top=23'); 
                        
                   }
                    
                    function clear() {
                        document.getElementById("note").innerHTML = "";
                    }
                
                    function print(msg) {
                        document.getElementById("note").innerHTML += "<p>" + msg + "</p>";
                       // alert(msg);
                       

                      window.open('displayMsg?q='+msg,'Popup','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no, width=420,height=400,left=430,top=23'); 
                            
                     //   window.open( <g:remoteFunction action="show" id="1" />,'Popup','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no, width=420,height=400,left=430,top=23'); 
                        
                         
                        
                    }
                });
              
                var draw_options = {
                    // your data goes here
                    network:  networ_json,
                    
                    // show edge labels too
                   // edgeLabelsVisible: true,
                    
                    // let's try another layout
                    layout: "ForceDirected",
                    
                    // set the style at initialisation
                    visualStyle: visual_style,
                    nodeTooltipsEnabled : true,
                    
                    // hide pan zoom
                    //panZoomControlVisible: false 
                };
                vis.draw(draw_options);
            };
        </script>
        
        
        
        
</head>
<body>



<div style ="width:100% color:#000000">
<p><g:link class="header-main" controller="Concepts">Conceptmetab</g:link></p>
  <p class="header-sub">Metabolic Network</p>    
</div>

<div >
 	<div id="cytoscapeweb" style="width: 70%; float:left ;  border:1px solid">
		            Cytoscape Web will replace the contents of this div with your graph.
	 </div>
    
    
    <div style="width: 29%; float:right ;  border:1px solid ;">
    
    
    
    <div id="tabs">
  <ul>
    <li><a href="#tabs-1">Input pval </a></li>
    <li><a href="#tabs-2">p-q vertical</a></li>
    <li><a href="#tabs-3">Database</a></li>
     <li><a href="#tabs-4">p-q horizontal</a></li>
  </ul>
  <div id="tabs-1">
  
  	
      	 <div id="note">
		 	<p>Click nodes or edges.</p>
		 </div>
		 
		 
		   <div class ="searchm" align="left">
				    <p> enter pval to see result</p>      
				         <g:form action="filterSlider" method="get">
				                <g:textField name="id2" id="id2" value=""/>
				                 <g:hiddenField name="id1"  value="1.45e-323"/>
				                  <g:hiddenField name="fil"  value="pval"/>
				               <g:hiddenField name="q" value="${params.q}" />
				                <g:submitButton name="pval"/>
				            </g:form> 
				            </div>
				            
				            
				            
		 
		 
  </div>
  <div id="tabs-2">
       <label for="amount-backwards">Pvalue range:	</label>		
		<input type="text" id="amount-backwards" style="border:0; color:#f6931f; font-weight:bold;" />
		</p>
		<div id="slider-verticalbackwards" style="height:200px;"></div>


		<label for="amount-backwards">Qvalue range:	</label>		
		<input type="text" id="amount-slider-qval-back" style="border:0; color:#f6931f; font-weight:bold;" />
		</p>
		<div id="slider-qval-back" style="height:200px;"></div>
  </div>
		  <div id="tabs-3">
		 			<div>
				<table  align="center">
				<tr>
					<td>
					<b>Database</b>
					</td>
					<td >
					<b>Color</b>
					</td>		
				</tr>
				<tr>
					<td>
					"GOBP"
					</td>
					<td style="background-color:#FF0000">
					</td>		
				</tr>
				<tr>
					<td> "GOCC"
					</td>
						<td style="background-color:#FFFF00">
				
					</td>		
				</tr>
				<tr>
					<td> "GOMF"
					</td>
					<td style="background-color: #64FE2E">
					</td>		
				</tr>
				<tr>
					<td>"Enzyme"
					</td>
					<td style="background-color:#0000FF">
					</td>		
				</tr>
				<tr>
					<td>"KEGG"
					</td>
					<td style="background-color: #CC2EFA">
					</td>		
				</tr>
				<tr>
					<td>  "MeSH"
					</td>
					<td style="background-color:#CCCCFF" >
					</td>		
				</tr>
				<tr>
					<td>   "Cluster"
					</td>
					<td style="background-color:#000000">
					</td>		
				</tr>
				
				
				
				</table>
		
			<p> is id ${ params.q}</p>
		
		  	<g:include action=  "createChart" id = "${ params.q}"/>
		  
		 	 ${dbc}
		
				</div> 
				</div>
				
		<div id="tabs-4">
		 <div div style="border:1px ">
	         <p id="para" />
	         <label for="amount">Pvalue range:(1 : 1e-323) (0-323)</label>
	         <input type="text" id="amount" style="border: 0; color: #f6931f; font-weight: bold;" />
         </div>

		<div id="slider-range"></div>
		
		<div id = "dialog" title="Basic dialog">
		  <p></p>
		</div>
		
		<div style="border:1px ">
		  <p id="qval" />
         <label for="qval">Qvalue range:</label>
         <input type="text" id="qval" style="border: 0; color: #f6931f; font-weight: bold;" />
		 <div id="qval-range"></div>
		</div>
		
		<p>   
				  
      </div>		
  
   </div>
</div>





		
		



		
	</div>
	
	
	

		
		
		
    
    
</body>
</html>
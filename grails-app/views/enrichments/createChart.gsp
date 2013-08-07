<html>
  <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
          //['GOMF':4899, 'GOCC':287, 'Enzyme':142, 'KEGG':68, 'Cluster':46]
          //[['database':'GOBP', 'no':340], ['database':'KEGG', 'no':4], ['database':'GOMF', 'no':57], ['database':'Cluster', 'no':1], ['database':'Enzyme', 'no':2]]
        var jsonData = ${dbc}
        console.log(jsonData);
        for (var i = 0, len = jsonData.length; i < len; ++i) {
            var db = objJSON[i];
            console.log("testing"+db);
        }
        var data = new google.visualization.DataTable(jsonData);

        console.log("rraylist is "+ ${clst})
        
        var options = {
          title: 'database distribution',
         // slices: {0: {color: '#FF0000'}, 1:{color: '#00FF08'}, 2:{color: 'blue'}, 3: {color: 'red'}, 4:{color: 'grey'}}
          colors:${clst}
        };

        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);

        google.visualization.events.addListener(chart, 'select', selectHandler);

        function selectHandler() {
            var selection = chart.getSelection();
            var message = '';
            for (var i = 0; i < selection.length; i++) {
              var item = selection[i];
              if (item.row != null && item.column != null) {
                  console.log('1');
                var str = data.getFormattedValue(item.row, item.column);
                message += '{row:' + item.row + ',column:' + item.column + '} = ' + str + '\n';
              } else if (item.row != null) {
                  console.log('2');
                var str = data.getFormattedValue(item.row, 0);
                message += str+ '\n';
              } else if (item.column != null) {
                  console.log('3');
                var str = data.getFormattedValue(0, item.column);
                message += '{row:none, column:' + item.column + '}; value (row 0) = ' + str + '\n';
              }
            }
            if (message == '') {
              message = 'nothing';
            }
           // alert('You selected ' + message);
            var first = getUrlVars()["id1"];
            var second = getUrlVars()["id2"];
            var q = getUrlVars()["q"];
           console.log("q ="+ q);
            window.open('createDb?id='+q+'&db='+message,'Popup','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no, width=620,height=500,left=430,top=23');
          }
     

        
      }

      function getUrlVars() {
          var vars = {};
          var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
              vars[key] = value;
          });
          return vars;
      }
    </script>
  </head>
  <body>
    <div id="chart_div" style="width: 900px; height: 500px;"></div>
    
    <div>${allR }</div>
  </body>
</html>
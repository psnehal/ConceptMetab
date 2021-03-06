<html>
  <head>
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {});
      google.load("prototype", "1.6");
    </script>
    
    <script type="text/javascript" src="http://systemsbiology-visualizations.googlecode.com/svn/trunk/src/main/js/load.js"></script>
    <script type="text/javascript">
        systemsbiology.load("visualization", "1.0", {packages:["bioheatmap"]});
    </script>

    <script type="text/javascript">
      google.setOnLoadCallback(drawHeatMap);
      function drawHeatMap() {
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Gene Name');
          data.addColumn('number', 'chip_XXX_XXX_600');
          data.addColumn('number', 'chip2');
          data.addColumn('number', 'chip3');
          data.addColumn('number', 'chip4');
          data.addColumn('number', 'chip5');
          data.addColumn('number', 'chip6');
          data.addRows(4);
          data.setCell(0, 0, 'ATF3');
          data.setCell(0, 1, 0);
          data.setCell(0, 2, 0.5);
          data.setCell(0, 3, 1);
          data.setCell(0, 4, 1.5);
          data.setCell(0, 5, 2);
          data.setCell(0, 6, 2.5);
          data.setCell(1, 0, 'INS');
          data.setCell(1, 1, 3);
          data.setCell(1, 2, 3.5);
          data.setCell(1, 3, 4);
          data.setCell(1, 4, 4.5);
          data.setCell(1, 5, 5);
          data.setCell(1, 6, 5.5);
          data.setCell(2, 0, 'TAP1');
          data.setCell(2, 1, 0);
          data.setCell(2, 2, null);
          data.setCell(2, 3, -1);
          data.setCell(2, 4, -1.5);
          data.setCell(2, 5, -2);
          data.setCell(2, 6, -2.5);
          data.setCell(3, 0, 'IL6');
          data.setCell(3, 1, -3);
          data.setCell(3, 2, -3.5);
          data.setCell(3, 3, -4);
          data.setCell(3, 4, -4.5);
          data.setCell(3, 5, -5);
          data.setCell(3, 6, -5.5);

          console.log(data);
          heatmap = new org.systemsbiology.visualization.BioHeatMap(document.getElementById('heatmapContainer'));
          heatmap.draw(data, {});
      }
    </script>
  </head>

  <body>
    <div id="heatmapContainer"></div>
    
  <div>
   <embed src="${resource(dir: 'images', file: 'hist.pdf')}" type="application/pdf" height="800" width="1000"/>
</div>
  </body>
</html>
<?php
include('adodb/adodb5/adodb.inc.php');

$server = "your-host-ip";
$user = "your-user";
$pwd = "your-passwd";
$db = "your-db";

?>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  
<title>J-RPI-Therm Report</title>

<script type="text/javascript" src="jquery/jquery-1.8.0.min.js"></script>
<script language="javascript" type="text/javascript" src="dist/jquery.jqplot.js"></script>
<script language="javascript" type="text/javascript" src="dist/plugins/jqplot.dateAxisRenderer.js"></script>
<script language="javascript" type="text/javascript" src="dist/plugins/jqplot.canvasAxisTickRenderer.js"></script>
<script language="javascript" type="text/javascript" src="dist/plugins/jqplot.canvasTextRenderer.js"></script>
<link rel="stylesheet" type="text/css" href="dist/jquery.jqplot.min.css" />
  
  <style type='text/css'>
    #chart{
    width: 100%;
    height: 200;
}
</style>

<?php
$data1 = "";
$data2 = "";
$data3 = "";
$data4 = "";

$DB = NewADOConnection('postgres');
$DB->Connect($server, $user, $pwd, $db);
$query = "SELECT * FROM measurelog order by measure_timestamp DESC limit 1440";
$result = $DB->Execute($query) or die("Error in query: $query. " . $DB->ErrorMsg());
$i = 0;
while (!$result->EOF)
{
   $data_timestamp =strtotime($result->fields[3] );
   if($i==59) $min1 = $data_timestamp;
   if($i==180) $min2 = $data_timestamp;
   if($i==720) $min3 = $data_timestamp;
   if($i==1439) $min4 = $data_timestamp;

   if($i < 60) $data1 =  "[".$data_timestamp."000,".$result->fields[1]."]" . "," . $data1;
   if($i >= 0 && $i <= 180) $data2 =  "[".$data_timestamp."000,".$result->fields[1]."]" . "," . $data2;
   if($i >= 0 && $i <= 720) $data3 =  "[".$data_timestamp."000,".$result->fields[1]."]" . "," . $data3;
   if($i >= 0 && $i <= 1440) $data4 =  "[".$data_timestamp."000,".$result->fields[1]."]" . "," . $data4;   
   $result->MoveNext();
   $i++;
}
$DB->Close();
$data1 = "[".substr($data1, 0,  strlen($data1)-1)."]";
$data2 = "[".substr($data2, 0,  strlen($data2)-1)."]";
$data3 = "[".substr($data3, 0,  strlen($data3)-1)."]";
$data4 = "[".substr($data4, 0,  strlen($data4)-1)."]";
?>  


<script class="code" type="text/javascript">  
$(window).load(function(){
$(document).ready(function() {
var lines = <?php echo($data1); ?>;
$.jqplot('chart', [lines], {
        title: "Temp Variation (Last 60 min)",
        axes: {
            xaxis: {
                renderer:$.jqplot.DateAxisRenderer, 
              tickOptions:{formatString:'%H:%M'},
		min: <?php echo($min1."000");?>
            },
            yaxis: { }
        },
//	series:[{lineWidth:4, markerOptions:{style:'square'}}]
    });
});
});
</script>

<script class="code" type="text/javascript">
$(window).load(function(){
$(document).ready(function() {
var lines = <?php echo($data2); ?>;
$.jqplot('chart2', [lines], {
        title: "Temp Variation (Last 3 hours)",
        axes: {
            xaxis: {
                renderer:$.jqplot.DateAxisRenderer,
              tickOptions:{formatString:'%H:%M'},
                min: <?php echo($min2."000");?>
            },
            yaxis: { }
        },
	seriesDefaults: {
            showMarker:false
        }
    });
});
});
</script>

<script class="code" type="text/javascript">
$(window).load(function(){
$(document).ready(function() {
var lines = <?php echo($data3); ?>;
$.jqplot('chart3', [lines], {
        title: "Temp Variation (Last 12 hours)",
        axes: {
            xaxis: {
                renderer:$.jqplot.DateAxisRenderer,
              tickOptions:{formatString:'%H:%M'},
                min: <?php echo($min3."000");?>
            },
            yaxis: { }
        },
	seriesDefaults: {
            showMarker:false
        }
    });
});
});
</script>

	



<script class="code" type="text/javascript">
$(window).load(function(){
$(document).ready(function() {
var lines = <?php echo($data4); ?>;
$.jqplot('chart4', [lines], {
        title: "Temp Variation (Last 24 hours)",
        axes: {
            xaxis: {
                renderer:$.jqplot.DateAxisRenderer,
              tickOptions:{formatString:'%H:%M'},
                min: <?php echo($min4."000");?>
            },
            yaxis: { }
        },
	seriesDefaults: {
            showMarker:false
        }
    });
});
});
</script>


</head>
<body>
<table style="width:auto;"><tr><td><a href="https://picasaweb.google.com/lh/photo/MaVfzXV_uPIjXgAV263kdtMTjNZETYmyPJy0liipFm0?feat=embedwebsite"><img src="https://lh5.googleusercontent.com/-hNCGM_dJsmo/UE-7NOaBI8I/AAAAAAAAALg/Sqj66_3Njoo/s144/DSCN0444.JPG" height="94" width="144" /></a></td></tr><tr><td style="font-family:arial,sans-serif; font-size:11px; text-align:right">From <a href="https://picasaweb.google.com/113557199789179991340/Raspberry_pi?authuser=0&feat=embedwebsite">raspberry_pi</a></td></tr></table>
<h2>J-RPI-THERM-D</h2><a href="http://github.com/damico/j-rpi-therm-d">http://github.com/damico/j-rpi-therm-d</a><hr><br>
<div id="chart"></div><br><br>
<div id="chart2"></div><br><br> 
<div id="chart3"></div><br><br>
<div id="chart4"></div>
<br>
<hr>
<a href="http://github.com/damico/j-rpi-therm-d">http://github.com/damico/j-rpi-therm-d</a>
</body>


</html>


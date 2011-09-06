<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Timer</title>
<script type="text/javascript">
	var timer;
	var sek;
	var startSek;
	var running;
	function startTimer() {
		if (running) {
			clearTimeout(timer);
			document.getElementById('start').value = "Start timer";
			document.getElementById('number').value = startSek;
			running = false;
			return;
		}
		document.body.styl.backgroundColor = "white";
		startSek = document.getElementById('number').value;
		sek = startSek;
		setTimeout(secTic, 1000);
		document.getElementById('start').value = "Stop timer";
		running = true;
		}
	function secTic() {
		if (sek <= 1) {
			timerEnd();
		} else {
			sek = sek -1;
			document.getElementById('number').value = sek;
			if (running) {
				timer = setTimeout(secTic, 1000);
			}
		}
	}
	function timerEnd() {
		document.body.styl.backgroundColor = "red";
		
		document.getElementById('start').value = "Start timer";
	}
</script>
</head>
<body>
<span>Hvor lenge til drikke kaffe</span><input type="text" id="number" value="45"/><span>sekunder</span>
<input id="start" type="button" onclick="startTimer();" value="Start timer"/>
</body>
</html>
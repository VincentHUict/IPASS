window.onload = function() {
	var a = false;
	
	document.getElementById('collapsed').onclick = function() {
		if(a == false) {
			document.getElementById('incollapse').style.maxHeight = '10px';
			a = false;
		} else {
			document.getElementById('incollapse').style.maxHeight = '0px';
			a = false;
		}
	}
}
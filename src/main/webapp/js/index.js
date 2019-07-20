$(document).ready(function() {
	$('.navbar-light .dmenu').hover(function() {
		$(this).find('.sm-menu').first().stop(true, true).slideDown(150);
	}, function() {
		$(this).find('.sm-menu').first().stop(true, true).slideUp(105)
	});
});

function store() {
	var formData = new FormData(document.querySelector("#save_form"));
	var encData = new URLSearchParams(formData.entries());
	document.querySelector("#error").innerHTML = "";
	
	let fetchoptions = {method: 'POST', body: encData, headers: {
			'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
		}
	}
	
	fetch('/restservices/problemen/', fetchoptions)
	.then((response) => {
		console.log(response.status);
		if (response.status == 403) {
			document.querySelector("#error").innerHTML = "U dient eerst in te loggen!";
		} else if (response.status == 402) {
			document.querySelector("#error").innerHTML = "Iets is foutgegaan!";
			console.log("error2");
			document.querySelector("#error").innerHTML = "Iets is foutgegaan!";
		} else {
			initPage();
		}
	});
	
	return false;
}

//function updateProbleem(clickedButton) {	
//}

function initPage() {
	let fetchoptions = { headers: {
				'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
			}
	}
	
	document.querySelector("#error").innerHTML = "";
	fetch('/restservices/problemen/', fetchoptions)
		.then((response) => {
			if (response.status == 403) {
				document.querySelector('#error').innerHTML = "U dien eerst in te loggen!";
			} else {
				return response.json();
			}
		})
		.then((myJson) => {
			let table = document.querySelector('tbody');
			table.innerHTML = "";
			
			var deleteButtons = document.getElementsByClassName('delete');
			for (i = 0; i < deleteButtons.length; i++) {
				deleteButtons[i].addEventListener('click', (event) => {
					let deletebutton = event.target.closest(".delete");
					deleteLand(deletebutton.getAttribute("data-code"));
				}, false);
			}
			
			var updateButtons = document.getElementsByClassName('update');
			for (i = 0; i < updateButtons.length; i++) {
				updateButtons[i].addEventListener('click', (event) => {
					let updateButton = event.target.closest(".update");
					updateProbleem(updateButton);
				}, false);
			}
		})
}

initPage();
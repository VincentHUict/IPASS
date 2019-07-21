$(document).ready(function() {
	$('.navbar-light .dmenu').hover(function() {
		$(this).find('.sm-menu').first().stop(true, true).slideDown(150);
	}, function() {
		$(this).find('.sm-menu').first().stop(true, true).slideUp(105)
	});
});

let vandaag = new Date();
let dv_maand = vandaag.getMonth();
let dv_jaar = vandaag.getFullYear();

function createProbleem(event) {
	
}

function deleteProbleem(event) {
	document.querySelector("#error").innerHTML = "";
	var fetchoptions = { method: 'DELETE', headers : {
				'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
			}
	}

	fetch("restservices/problemen" + event, fetchoptions)
		.then(function(response) {
			if (response.ok) {
				console.log("Probleem verwijderd!");
			} else {
				initPage();
				console.log("Kon het probleem niet verwijderen!");
			}
		})
		.catch(error => console.log(error));
}
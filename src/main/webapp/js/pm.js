function deleteProbleem(event) {
	document.querySelector("#error").innerHTML = "";
	let fetchoptions = { method: 'DELETE', headers : {
				'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
			}
	}

	fetch('restservices/problemen/' + event, fetchoptions)
		.then((response) => {
			if (response.status == 403) {
				document.querySelector("#error").innerHTML = "U dient eerst in te loggen!";
			} else {
				initPage();
				console.log("Verwijderd!");
			}
		})
		.catch(error => console.log(error));
}

$(document).ready(function() {
	$('.navbar-light .dmenu').hover(function() {
		$(this).find('.sm-menu').first().stop(true, true).slideDown(150);
	}, function() {
		$(this).find('.sm-menu').first().stop(true, true).slideUp(105)
	});
});
let saveButton = document.querySelector('#loginbutton');
saveButton.addEventListener('click', (event) => {	
	var formData = new FormData(document.querySelector("#loginform"));
	var encData = new URLSearchParams(formData.entries());
	document.querySelector("#error").innerHTML = "";
	
	fetch('restservices/authentication', { method: 'POST', body: encData})
		.then(function(response) {
			if (response.ok) return response.json();
			else throw "Gebruikersnaam en/of wachtwoord is incorrect!";
		})
		.then(myJson => window.sessionStorage.setItem("myJWT", myJson.JWT))
		.catch(error => console.log(error));
})

document.querySelector('#login').addEventListener("click", login);
document.querySelector('#register').addEventListener("click", event => window.location.href = "reg.html")

$(document).ready(function() {
	$('.navbar-light .dmenu').hover(function() {
		$(this).find('.sm-menu').first().stop(true, true).slideDown(150);
	}, function() {
		$(this).find('.sm-menu').first().stop(true, true).slideUp(105)
	});
});

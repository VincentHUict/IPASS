let saveButton = document.querySelector('#login_button');
saveButton.addEventListener('click', function() {
	var formData = new FormData(document.querySelector("#login_form"));
	var encData = new URLSearchParams(formData.entries());
	
	fetch('/restservices/authentication', { method: 'POST', body: encData})
		.then(function(response) {
			if (response.ok) {
				console.log("YES")
				return response.json();
			} else {
				window.alert("Gebruikersnaam en/of wachtwoord is incorrect!");
			}
		})
		.then((myJson) => {
			window.sessionStorage.setItem("sessionToken", myJson.JWT);
			window.location.href = "/login.html";
		})
		.catch(error => console.log(error));
})


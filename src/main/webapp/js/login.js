function login(event) {
	var formData = new FormData(document.querySelector("#loginform"));
	var encData = new URLSearchParams(formData.entries());
	
	fetch("restservices/authentication", { method: 'POST', body: encData })
		.then(function(response) {
			if (response.ok) {
				console.log("YES")
				window.location.href = "index.html";
				return response.json();
			}
			else {
				window.alert("Gebruikersnaam en/of wachtwoord is incorrect!");
			}
		})
		.then(myJson => window.sessionStorage.setItem("myJWT", myJson.JWT))
		.catch(error => console.log(error));
}
document.querySelector("#login").addEventListener("click", login);

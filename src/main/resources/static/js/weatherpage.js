function getWeatherJson() {
	var city = $("#inputCity").val();
	$.ajax({
		url : "weatherConditions/" + city,
		success : updateWeatherConditions
	});
	console.log("elooo");
	// $.getJSON(/*[+ [[@{/weatherConditions/}]] + city +]*/,
	// updateWeatherConditions);
}

function updateWeatherConditions(data) {
	$("#date").text(dateConverter(data.dt));
	$("#city").text(data.name);
	$("#country").text(data.sys.country);
	$("#temp").text(data.main.temp + "°C");
	$("#description").text(data.weather[0].description)
	$("#sunrise").text(timeConverter(data.sys.sunrise));
	$("#sunset").text(timeConverter(data.sys.sunset));
	
	var weatherImageSrc = "http://openweathermap.org/img/w/" + data.weather[0].icon + ".png";
	$("#weatherImage").attr("src", weatherImageSrc);
}

function dateConverter(UNIX_timestamp) {
	var a = new Date(UNIX_timestamp * 1000);
	var year = a.getFullYear();
	var month =  ('0' + (a.getMonth()+1) ).slice( -2 );
	var date = ('0' + (a.getDate()) ).slice( -2 );
	var result = date + '.' + month + '.' + year;
	return result;
}

function timeConverter(UNIX_timestamp) {
	var date = new Date(UNIX_timestamp*1000);
	var hours = date.getHours();
	var minutes = "0" + date.getMinutes();
	var seconds = "0" + date.getSeconds();

	// Will display time in 10:30:23 format
	var formattedTime = hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
	return formattedTime;
}
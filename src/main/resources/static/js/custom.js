//dynamiczne zmiany selected
$(document).on(
		"change",
		"select",
		function() {
			$("option[value=" + this.value + "]", this).attr("selected", true)
					.siblings().removeAttr("selected")
		});

function submitBmrForm() {
	var bmrForm = document.getElementById("bmrForm");

	var age = document.getElementById("inputAge").cloneNode(true);
	var height = document.getElementById("inputHeight").cloneNode(true);
	var weight = document.getElementById("inputWeight").cloneNode(true);
	var sex = document.getElementById("inputSex").cloneNode(true);
	var bodyType = document.getElementById("inputBodyType").cloneNode(true);
	var frequencyOfActivity = document.getElementById("inputFoa").cloneNode(
			true);

	bmrForm.appendChild(age);
	bmrForm.appendChild(height);
	bmrForm.appendChild(weight);
	bmrForm.appendChild(sex);
	bmrForm.appendChild(bodyType);
	bmrForm.appendChild(frequencyOfActivity);

	bmrForm.submit();
}

function submitBmiForm() {
	var bmiForm = document.getElementById("bmiForm");

	var age = document.getElementById("inputAge").cloneNode(true);
	var height = document.getElementById("inputHeight").cloneNode(true);
	var weight = document.getElementById("inputWeight").cloneNode(true);
	var sex = document.getElementById("inputSex").cloneNode(true);
	var bodyType = document.getElementById("inputBodyType").cloneNode(true);
	var frequencyOfActivity = document.getElementById("inputFoa").cloneNode(
			true);

	bmiForm.appendChild(age);
	bmiForm.appendChild(height);
	bmiForm.appendChild(weight);
	bmiForm.appendChild(sex);
	bmiForm.appendChild(bodyType);
	bmiForm.appendChild(frequencyOfActivity);

	bmiForm.submit();
}

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
	$("#date").text(timeConverter(data.dt));
	$("#city").text(data.name);
	$("#country").text(data.country);
	$("#temp").text(data.main.temp);
}

function timeConverter(UNIX_timestamp) {
	var a = new Date(UNIX_timestamp * 1000);
	var year = a.getFullYear();
	var month =  ('0' + (a.getMonth()+1) ).slice( -2 );
	var date = ('0' + (a.getDate()) ).slice( -2 );
	var time = date + '.' + month + '.' + year;
	return time;
}

var catalogInput = document.getElementById('catalog');
var subcatalogInput = document.getElementById('subcatalog');
var titleInput = document.getElementById('title');
var subtitleInput = document.getElementById('subtitle');
var contentInput = document.getElementById('content');
var publishTimeInput = document.getElementById('publishTime');
var expirationTimeInput = document.getElementById('expirationTime');
var creatorInput = document.getElementById('creator');
var editorInput = document.getElementById('editor');
var importanceInput = document.getElementById('importance');
var audienceLevelInput = document.getElementById('audienceLevel');
var submitButton = document.getElementById("submitButton");

function validateAllInputs() {
    var inputs = [
        catalogInput,
        subcatalogInput,
        titleInput,
        subtitleInput,
        contentInput,
        publishTimeInput,
        expirationTimeInput,
        creatorInput,
        editorInput,
        importanceInput,
        audienceLevelInput
    ];

    var isValid = true;
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].value.trim() === '') {
            inputs[i].classList.add('is-invalid');
            isValid = false;
        } else {
            inputs[i].classList.remove('is-invalid');
        }
    }
    return isValid;
}

document.getElementById("catalog").addEventListener("change", function() {
	var requestBody = {
		parent: this.value
	};
	fetch('/find_catalog', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(requestBody)
	}).then(response => response.json())
		.then(result => {
			var subcatalogSelect = document.getElementById("subcatalog");
			subcatalogSelect.innerHTML = '';
			result.catalogList.forEach(option => {
				var optionElement = document.createElement("option");
				optionElement.value = option.name;
				optionElement.text = option.name;
				subcatalogSelect.appendChild(optionElement);
			});
		})
		.catch(error => console.error("发生错误：", error));
});

contentInput.addEventListener("input", function() {
	document.getElementById("charCount").textContent = "字符数：" + this.value.length + "/ 1000";
});

function confirmAndSubmit() {
	validateAllInputs() && confirm("以下の操作を実行してもよろしいでしょうか？") && document.getElementById("Excute").submit();
}

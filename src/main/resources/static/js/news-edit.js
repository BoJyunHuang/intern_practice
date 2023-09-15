var titleInput = document.getElementById('title');
var subtitleInput = document.getElementById('subtitle');
var contentInput = document.getElementById('content');
var creatorInput = document.getElementById('creator');
var editorInput = document.getElementById('editor');
var submitButton = document.getElementById("submitButton");

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
	confirm("以下の操作を実行してもよろしいでしょうか？") && document.getElementById("Excute").submit();
}

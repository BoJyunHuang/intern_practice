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

function goBack() {
	window.history.back();
}

function save() {
	// 禁用save按钮
    document.getElementById('saveButton').disabled = true;
	var requestBody = {
		catalog: $('#catalog').val(),
		subcatalog: $('#subcatalog').val(),
		title: $('#title').val(),
		subtitle: $('#subtitle').val(),
		tags: $('#tags').val(),
		content: $('#content').val(),
		publishTime: $('#publishTime').val(),
		expirationTime: $('#expirationTime').val(),
		creator: $('#creator').val(),
		importance: $('input[name="stack1"]:checked').val(),
		audienceLevel: $('input[name="stack2"]:checked').val(),
	};
	console.log(requestBody);
	fetch('/preview_news', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(requestBody)
	}).then(response => response.json())
		.then(data => {
			document.getElementById('previewButton').removeAttribute('disabled');
			alert(data.message);
		})
		.catch(error => {
			console.error('预览数据时出错：', error);
		});
}
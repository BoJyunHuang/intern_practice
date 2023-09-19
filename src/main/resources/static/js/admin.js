window.addEventListener('DOMContentLoaded', event => {
	const datatablesSimple = document.getElementById('datatablesSimple');
	if (datatablesSimple) {
		new simpleDatatables.DataTable(datatablesSimple);
	}
});

window.addEventListener('DOMContentLoaded', event => {
	const sidebarToggle = document.body.querySelector('#sidebarToggle');
	if (sidebarToggle) {
		sidebarToggle.addEventListener('click', event => {
			event.preventDefault();
			document.body.classList.toggle('sb-sidenav-toggled');
			localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
		});
	}
});

var contentElement = document.getElementById('content');
var catalogElement = document.getElementById("catalog");

if (contentElement) {
	contentElement.addEventListener("input", function() {
		document.getElementById("charCount").textContent = "字符数：" + this.value.length + "/ 1000";
	});
}
if (catalogElement) {
	catalogElement.addEventListener("change", function() {
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
			.catch(error => console.error("エラーが発生しました：", error));
	});
}

function confirmDelete() {
	confirm("以下の操作を実行してもよろしいでしょうか？") && deleteSelected();
}

function deleteSelected() {
	var selectedIds = [];
	document.querySelectorAll('.catalog-checkbox:checked').forEach(checkbox => selectedIds.push(parseInt(checkbox.value)));
	if (selectedIds.length > 0) {
		var requestBody = {
			idList: selectedIds
		};
		fetch('/delete_catalog', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(requestBody)
		}).then(response => response.json())
			.then(result => confirm(result.message) && window.location.reload())
			.catch(error => console.error("エラーが発生しました：", error));
	} else {
		alert("項目を選択してください");
	}
}

function toggleParentInput() {
	var catalogTypeRadio = document.querySelector('input[name="type"]:checked');
	var parentSelect = document.getElementById("parent");
	if (catalogTypeRadio.value === "catalog") {
		parentSelect.querySelector('option[value="none"]').disabled = false;
		parentSelect.value = "none";
		parentSelect.disabled = true;
	} else {
		parentSelect.querySelector('option[value="none"]').disabled = true;
		parentSelect.disabled = false;
	}
}

(function() {
	'use strict'
	var forms = document.querySelectorAll('.needs-validation')
	// Loop over them and prevent submission
	Array.prototype.slice.call(forms)
		.forEach(function(form) {
			form.addEventListener('submit', function(event) {
				if (!form.checkValidity()) {
					event.preventDefault()
					event.stopPropagation()
				}
				form.classList.add('was-validated')
			}, false)
		})
})()

function save() {
	var requestBody = {
		catalog: $('#catalog').val(),
		subcatalog: $('#subcatalog').val(),
		title: $('#title').val(),
		subtitle: $('#subtitle').val(),
		tags: $('#tags').val(),
		content: $('#content').val(),
		publishTime: $('#publishTime').val(),
		creator: $('#creator').val(),
	};
	fetch('/preview_news', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(requestBody)
	}).then(response => response.json())
		.then(data => alert(data.message))
}

function goBack() {
	window.history.back();
}
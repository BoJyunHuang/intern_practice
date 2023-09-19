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

function confirmDelete() {
	confirm("确定要删除所选项目吗？" + "以下の操作を実行してもよろしいでしょうか？") && deleteSelected();
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
			.catch(error => console.error("发生错误：", error));
	} else {
		alert("请选择要删除的项目");
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

function goBack() {
	window.history.back();
}
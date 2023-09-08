function confirmAndSubmit() { confirm("以下の操作を実行してもよろしいでしょうか？") && document.getElementById("Excute").submit() }

function confirmDelete() { confirm("确定要删除所选项目吗？") && deleteSelected(); }

function goBack() {
	window.history.back();
}

var nameInput = document.getElementById('name');
var nameError = document.querySelector('.invalid-feedback');

nameInput.addEventListener('blur', function() {
	if (nameInput.value.trim() === '') {
		nameInput.classList.add('is-invalid');
		nameError.style.display = 'block';
	} else {
		nameInput.classList.remove('is-invalid');
		nameError.style.display = 'none';
	}
});

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


function changeNewsStatus(serialNumber, reveal) {
	const formData = new FormData();
	formData.append('serialNumber', serialNumber);
	formData.append('reveal', reveal);
	fetch('/change_news_status', {
		method: 'POST',
		body: formData
	}).then(response => {
		if (response.ok) {
			location.reload();
		} else {
			window.alert('Failed to execute');
		}
	}).catch(error => {
		console.error('An error occurred:', error);
	});
}
function confirmDelete() {
	confirm("确定要删除所选项目吗？") && deleteSelected();
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

function goBack() {
	window.history.back();
}
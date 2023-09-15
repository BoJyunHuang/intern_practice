function confirmAndSubmit() {
	confirm("以下の操作を実行してもよろしいでしょうか？") && document.getElementById("Excute").submit()
}

function toggleParentInput() {
	var catalogTypeRadio = document.querySelector('input[name="type"]:checked');
	var parentSelect = document.getElementById("parent");
	if (catalogTypeRadio.value === "catalog") {
		parentSelect.value = "none";
		parentSelect.disabled = true;
	} else {
		parentSelect.disabled = false;
	}
}


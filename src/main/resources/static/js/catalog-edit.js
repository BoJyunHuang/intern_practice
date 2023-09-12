var nameInput = document.getElementById('name');
var submitButton = document.getElementById("submitButton");

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

nameInput.addEventListener('blur', function() {
	if (nameInput.value.trim() === '') {
		nameInput.classList.add('is-invalid');
		submitButton.disabled = true;
	} else {
		nameInput.classList.remove('is-invalid');
		submitButton.disabled = false;
	}
});


var errorMessage = '';

if (errorMessage && errorMessage.trim() !== '') {
    window.alert(errorMessage);
}

function confirmAndSubmit() {
    var confirmed = window.confirm("以下の操作を実行してもよろしいでしょうか？");
    if (confirmed) {
        document.getElementById("addForm").submit();
    }
}

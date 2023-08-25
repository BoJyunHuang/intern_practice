var errorMessage = '';

if (errorMessage && errorMessage.trim() !== '') {
    window.alert(errorMessage);
}

function confirmAndSubmit() {
    var confirmed = window.confirm("確定要新增嗎？");
    if (confirmed) {
        document.getElementById("addNewsForm").submit();
    }
}
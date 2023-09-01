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

function deletePersonInfo(employeeNumber) {
    const formData = new FormData();
    formData.append('employeeNumber', employeeNumber);
    fetch('/delete_person_info', {
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
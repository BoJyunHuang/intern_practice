var errorMessage = '';

if (errorMessage && errorMessage.trim() !== '') {
    window.alert(errorMessage);
}

function confirmAndSubmit() {
    var confirmed = window.confirm("確定要執行嗎？");
    if (confirmed) {
        document.getElementById("addNewsForm").submit();
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
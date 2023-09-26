$(document).ready(function() {
	$('#sidebarToggle').click(function(event) {
		event.preventDefault();
		$('body').toggleClass('sb-sidenav-toggled');
		localStorage.setItem('sb|sidebar-toggle', $('body').hasClass('sb-sidenav-toggled'));
	});

	if ($('#catalogTable').length > 0) {
		$('#catalogTable').DataTable({
			language: {
				url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/ja.json',
			},
			"order": [[2, "asc"], [0, "asc"]]
		});
	};

	if ($('#newsTable').length > 0) {
		$('#newsTable').DataTable({
			"scrollCollapse": true,
			language: {
				url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/ja.json',
			},
			"order": [[6, "desc"]]
		});
	};

	$('#content').on('input', function() {
		$('#charCount').text("字符数：" + this.value.length + "/ 1000");
	});

	$('#catalog-main').change(function() {
		var requestBody = {
			parent: $(this).find('option:selected').text()
		};
		fetch('/find_catalog', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(requestBody)
		}).then(response => response.json())
			.then(result => {
				var subcatalogSelect = $('#catalog');
				subcatalogSelect.empty();
				$.each(result.catalogList, function(option) {
					subcatalogSelect.append($('<option>', {
						value: option.catalogId,
						text: option.name
					}));
				});
			})
			.catch(error => console.error("エラーが発生しました：", error));
	});

	$('.needs-validation').on('submit', function(event) {
		if (!this.checkValidity()) {
			event.preventDefault();
			event.stopPropagation();
		}
		$(this).addClass('was-validated');
	});

});

function confirmDelete(id, type) {
	confirm("以下の操作を実行してもよろしいでしょうか？") && deleteSelectedItems(id, type);
}

function deleteSelectedItems(id, type) {
	var requestBody = {};
	if (id !== '') {
		requestBody[type + 'Id'] = id;
	} else {
		var selectedIds = [];
		$('.' + type + '-checkbox:checked').each(function() {
			selectedIds.push(parseInt($(this).val()));
		});
		if (selectedIds.length > 0) {
			requestBody.idList = selectedIds;
		} else {
			alert("項目を選択してください");
			return;
		}
	}
	fetch(type === 'news' ? '/delete_news' : '/delete_catalog', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(requestBody)
	}).then(response => response.json())
		.then(result => {
			switch (result.msg) {
				case 'SUCCESS':
					alert('操作成功');
					window.location.reload();
					break;
				default:
					alert('操作失敗');
			};
		})
		.catch(error => alert("エラーが発生しました：", error));
}

function toggleParentInput() {
	var catalogTypeRadio = $('input[name="type"]:checked');
	var parentSelect = $('#parent');
	if (catalogTypeRadio.val() === "catalog") {
		parentSelect.find('option[value="カタログ"]').prop('disabled', false);
		parentSelect.val("カタログ").prop('disabled', true);
	} else {
		parentSelect.find('option[value="カタログ"]').prop('disabled', true);
		parentSelect.prop('disabled', false);
	}
}

function save() {
	var requestBody = {
		catalog: $('#catalog-main option:selected').text(),
		subcatalog: $('#catalog').find('option:selected').text(),
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
		.then(data => {
			switch (data.msg) {
				case 'SUCCESS':
					alert('操作成功');
					$('#previewButton').removeClass('d-none');
					break;
				default:
					alert('操作失敗');
			}
		});
}

function goBack() {
	window.history.back();
}
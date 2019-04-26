$(document).ready(function() {
	$("#btnExecute").click(function() {	
		saveCode();
		executeCode($("input[name=snippetId]").val());
	});
	
	$("#btnSave").click(function(){
		saveCode();
	})
	
	$(window).bind('keydown', function(event) {
	    if (event.ctrlKey || event.metaKey) {
	        switch (String.fromCharCode(event.which).toLowerCase()) {
	        case 's':
	            event.preventDefault();
	            saveCode();
	            break;
	        }
	    }
	});
	
	function executeCode(snippetId) {
		$.ajax({
			url : window.location.origin+'/snippets/' + snippetId + '/execute',
			type : 'GET',
			dataType : 'JSON',
			success : function(data) {
				$('#executionOutput').html(data['output'] + data['error'])
			}
		});
	}
	
	function saveCode()
	{
		snippetId = $("input[name=snippetId]").val();
		name = $("#snippetName").val();
		language = $("#snippetLanguageSelect").val()
		accessibility = $("#snippetAccessiblity").val();
		ownerId = $("input[name=ownerId]").val();
		content = editor1.getValue();
		
		$.ajax({
			url : window.location.origin+'/snippets/save',
			type : 'POST',
			async: false,
			dataType : 'html',
			data : "snippet_id=" +snippetId + '&snippet_name='+name + "&snippet_content="+content + "&snippet_user="+ownerId + "&snippet_language="+language + "&snippet_accessibility="+accessibility,
			success : function(data) {
				console.log("Snippet saved.");
			}
		});
	}
});

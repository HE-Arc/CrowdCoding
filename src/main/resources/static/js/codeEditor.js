$(document).ready(function() {
	$("#btnExecute").click(function() {	
		executeCode($("input[name=snippetId]").val());
	});
	
	$("#btnSave").click(function(){
		saveCode();
	})
	
	function executeCode(snippetId) {
		saveCode();
		$.ajax({
			url : window.location.origin+'/snippets/' + snippetId + '/execute',
			type : 'GET',
			dataType : 'JSON',
			success : function(data) {
				$('#executionOutput').html(data['output'])
			}
		});
	}
	
	function saveCode()
	{
		snippetId = $("input[name=snippetId]").val();
		name = $("#snippetName").val();
		language = $("#snippetLanguage").val();
		accessibility = $("#snippetAccessiblity").val();
		ownerId = $("input[name=ownerId]").val();
		content = editor1.getValue();
		
		
		console.log(content);
		console.log(snippetId);
		console.log(language);
		console.log(accessibility);
		console.log(ownerId);
		console.log(name);
		
		$.ajax({
			url : window.location.origin+'/snippets/save',
			type : 'POST',
			dataType : 'html',
			data : "snippet_id=" +snippetId + '&snippet_name='+name + "&snippet_content="+content + "&snippet_user="+ownerId + "&snippet_language="+language + "&snippet_accessibility="+accessibility,
			success : function(data) {
				console.log("Snippet saved.");
			}
		});
	}
});

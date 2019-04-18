$(document).ready(function() {
	$("#btnExecute").click(function() {	
		executeCode($("input[name=snippetId]").val());
	});
	
	$("#btnSave").click(function(){
		saveCode();
	})
	
	function executeCode(snippetId) {
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
		accessibility = $("#snippetAccessibility").val();
		ownerId = $("input[name=owner]").val();
		content = $("#editor1").text();
		content = content.substring(0, content.indexOf('ה'));
		
		console.log(content);
		console.log(snippetId);
		
		/*$.ajax({
			url : window.location.origin+'/snippets/save',
			type : 'POST',
			dataType : 'html',
			data : "snippet_id=" +snippetId + '&snippet_name='+name + "&snippet_content="+content + "&snippet_user="+ownerId + "&snippet_language="+language + "&snippet_accessibility="+accessibility,
			success : function(data) {
				console.log("Snippet saved.");
			}
		});*/
	}
});

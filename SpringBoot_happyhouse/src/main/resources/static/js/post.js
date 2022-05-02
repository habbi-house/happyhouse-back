$(document).ready(function() {
	$("#deleteBtn").on("click", deleteHandler);
});

function deleteHandler() {
	const code = $("#code").val();
	
	$("form").attr("action", "/board/" + code + "/delete").submit();
}
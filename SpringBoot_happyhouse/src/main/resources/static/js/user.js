$(document).ready(function() {
	$("#signInBtn").on("click", signInHandler);
});

function signInHandler() {
	const id = $("#id").val();
	const pwd = $("#password").val();
	const name = $("#name").val();
	const email = $("#email").val();
	const phone = $("#phone").val();

	if (!id || !pwd || !name || !email || !phone) {
	  showAlert(false, "빈 칸을 채워주세요.");
	  return;
	}
	  
	$("form").submit();
}
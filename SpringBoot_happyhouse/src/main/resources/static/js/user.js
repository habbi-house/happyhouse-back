$(document).ready(function() {
	$("#signInBtn").on("click", signInHandler);
	$("#loginBtn").on("click", loginHandler);
	
	$("#switchBtn").on("click", switchHandler);
	$("#cancelBtn").on("click", cancelHandler);
	$("#updateUserBtn").on("click", updateUserHandler);
	$("#deleteUserBtn").on("click", deleteUserHandler);
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

function loginHandler() {
	const id = $("#id").val();
	const pwd = $("#password").val();
	
	if (!id || !pwd) {
	  showAlert(false, "빈 칸을 채워주세요.");
	  return;
	}
	  
	$("form").submit();
}

function switchHandler() {
	// 수정 버튼 클릭 시, input의 readonly 속성 모두 제거
	$("form > span > input").removeAttr("readonly");
	$("form > span > input").removeClass("border-white");
	
	$("#switchBtn").hide();
	$("#cancelBtn").show();
	$("#updateUserBtn").show();
}

function cancelHandler() {
  // 취소 버튼 클릭 시, 원래의 상태로 돌아간다
  // input의 readonly 다 붙고, 수정 버튼으로 전환
  $("form > span > input").attr("readonly", true);
  $("form > span > input").addClass("border-white");

  $("#switchBtn").show();
  $("#cancelBtn").hide();
  $("#updateUserBtn").hide();
}

function updateUserHandler() {
  const no = $("#no").val();
  const id = $("#id").val();
  const pwd = $("#password").val();
  const name = $("#name").val();
  const email = $("#email").val();
  const phone = $("#phone").val();

  if (!id || !pwd || !name || !email || !phone) {
    showAlert(false, "빈 칸을 채워주세요.");
    return;
  }
  
  $.post("/user/" + no + "/update", { no, id, password: pwd, name, email, phone }, function(data) {
	  const { ok, msg } = JSON.parse(data);
	  
	  if(ok) {		  
		  $("#id").val(id);
		  $("#password").val(pwd);
		  $("#name").val(name);
		  $("#email").val(email);
		  $("#phone").val(phone);
		  
		  cancelHandler();
	  }
	  showAlert(ok, msg);
  });
}

function deleteUserHandler() {
	const no = $("#no").val();
	
	location.href = "/user/" + no + "/delete"; 
}

$(document).ready(function () {
  $("#alertBar").hide();
});

// 상단 Alert Bar 띄우기
function showAlert(ok, msg) {
  const msgElement = $(`<span>&nbsp;&nbsp;${msg}</span>`);
  const successIcon = $('<i class="fa-solid fa-circle-check"></i>');
  const failIcon = $('<i class="fa-solid fa-triangle-exclamation"></i>');

  $("#alertBar").empty();

  if (ok) {
    $("#alertBar").removeClass("alert-danger");
    $("#alertBar").addClass("alert-success");
    $("#alertBar").append(successIcon);
    $("#alertBar").append(msgElement);
  } else {
    $("#alertBar").removeClass("alert-success");
    $("#alertBar").addClass("alert-danger");
    $("#alertBar").append(failIcon);
    $("#alertBar").append(msgElement);
  }

  $("#alertBar")
    .fadeTo(2000, 500)
    .slideUp(function () {
      $("#alertBar").slideUp(500);
    });
}
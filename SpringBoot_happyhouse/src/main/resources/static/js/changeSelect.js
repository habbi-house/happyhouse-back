let locNum1 = "";
let locNum2 = "";
let locNum3 = "";

$(document).ready(function(){
  // 도/광역시, 시/군/구, 동 정보 불러오기
  $("#category1").change(function () {
    locNum1 = $(this).val();
    $("#sigu").val($("select[name=category1] option:selected").text());
    changeSelect1();
  });
  $("#category2").change(function () {
	locNum2 = $(this).val();
	$("#gugun").val($("select[name=category2] option:selected").text());
    changeSelect2();
  });
  $("#category3").change(function () {
	locNum3 = $(this).val();
	$("#dong").val($("select[name=category3] option:selected").text());
  });
});

function changeSelect1() {
  let code = locNum1;

  /* 메인화면에 이식할때는 윗 줄을 지우고 이 코드를 이용해주세요 
   const e = document.getElementById("category2");
   let code = e.option[e.selectedIndex].value;
  */

  $.get(
    "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=" +
      code +
      "*000000",
    function (data) {
      let sigu = "<option value=''>선택</option>";
      $.each(data.regcodes, function (index, item) {
        let tempLocNames = item.name.split(" ");
        let currentLocName = tempLocNames[tempLocNames.length - 1];
        if (index != 0) {
          sigu +=
            `<option value=${item.code.slice(0, 4)}>` +
            currentLocName +
            "</option>";
        }
      });
      $("#category2").html(sigu);
    }
  );
}

function changeSelect2() {
  let code = locNum2;

  /* 메인화면에 이식할때는 윗줄을 지우고 이 코드를 이용해주세요 
   const e = document.getElementById("category2");
   let code = e.option[e.selectedIndex].value;
  */

  $.get(
    `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=${code}*`,
    function (data) {
      let dong = "<option value=''>선택</option>";
      $.each(data.regcodes, function (index, item) {
        if (index != 0) {
          let tempLocNames = item.name.split(" ");
          let currentLocName = tempLocNames[tempLocNames.length - 1];
          dong +=
            `<option value=${item.code.slice(0, 5)}>` +
            currentLocName +
            "</option>";
        }
      });
      $("#category3").html(dong);
    }
  );
}

	  
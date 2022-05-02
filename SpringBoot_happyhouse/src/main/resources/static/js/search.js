let locNum1 = "";
let locNum2 = "";
let locNum3 = "";

let locName1 = "";
let locName2 = "";
let locName3 = "";

let map;

$(document).ready(function () {
  // 지도 불러오기
  loadMap();
  $("#zoomIn").click(zoomIn);
  $("#zoomOut").click(zoomOut);

  showCenterLocation("서울특별시 강남구 대치동");

  // 도/광역시, 시/군/구, 동 정보 불러오기
  $("#category1").change(function () {
    locNum1 = $(this).val();
    locName1 = $("select[name=category1] option:selected").text();
    changeSelect1();
  });
  $("#category2").change(function () {
    locNum2 = $(this).val();
    locName2 = $("select[name=category2] option:selected").text();
    changeSelect2();
  });
  $("#category3").change(function () {
    locNum3 = $(this).val();
    locName3 = $("select[name=category3] option:selected").text();
  });
});

function loadData(type, items) {
  const listGroupElement = $(".list-group");

  let idx = 0;
  for (const item of items) {
    const listGroupItemElement = createListGroupItem(type, idx++);
    addItemInfoToListGroupElement(item, listGroupItemElement);
    listGroupElement.append(listGroupItemElement);
  }
}

function createListGroupItem(type, index) {
  const listGroupItemElement = $(
    `<div class='list-group-item list-group-item-action list-group-item-light p-3' data-bs-toggle='modal' data-bs-target='#detailedInfo' style="cursor: pointer;" onclick='loadDetailedInfo(${index})'></div>`
  );

  return listGroupItemElement;
}

function addItemInfoToListGroupElement(item, listGroupItemElement) {
  const name = item.name;
  const price = item.price;
  const area = item.area;
  const date = item.date;

  const nameElement = $(`<div style='font-weight: bold'>${name}</div>`);

  const blankLine = $("<br>");

  const priceElement = $(`<div>거래금액: ${price}</div>`);

  const areaElement = $(`<div>전용면적: ${area}</div>`);
  const dateElement = $(
    `<div><i class='fa fa-calendar' aria-hidden='true'></i><span> ${date}</span></div>`
  );

  listGroupItemElement.append(nameElement);
  listGroupItemElement.append(blankLine);
  listGroupItemElement.append(priceElement);
  listGroupItemElement.append(areaElement);
  listGroupItemElement.append(dateElement);
}

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

function search() {
  if (!locNum1 || !locNum2 || !locNum3) {
    alert("값을 선택해주세요");
    return;
  }

  let dong = locNum3;
  
  requestAPIandLoad(dong);
}

function requestAPIandLoad(code) {

	  const items = [];

	  const e3 = document.getElementById("category3");
	  let dong = e3.options[e3.selectedIndex].text;
	  
	  $.post('search', {dong}, function(houseDealList) {
		  console.log(houseDealList[0]);
		  for(const deal of houseDealList) {
			const { aptName, dealAmount, area, dealYear,
				dealMonth, dealDay, builtYear, dong, areaNumber, floor } = deal;
			let item = {};
            item.name = aptName;
            item.price = dealAmount + "만원";
            item.area = area + "제곱미터";
            item.date = dealYear + ". " + dealMonth + ". " + dealDay;
            item.constructedYear = builtYear;
            item.dong = dong;
            item.areaNumber = areaNumber;
            item.floor = floor;
            items.push(item);
		  }
		  $(".list-group").html("");
		  localStorage.setItem("items", JSON.stringify(items));
		  loadData("total", items);
	  });
	  
	  // 지도 정보 가져오기
	  const address = locName1 + " " + locName2 + " " + locName3;
	  showCenterLocation(address);
	  showAllLocations(address);
}

function loadDetailedInfo(index) {
  const e1 = document.getElementById("category1");
  let city = e1.options[e1.selectedIndex].text;

  const e2 = document.getElementById("category2");
  let gu = e2.options[e2.selectedIndex].text;

  let items = localStorage.getItem("items");
  items = JSON.parse(items);

  let item = items[index];
  const modalBodyElement = $(".modal-body").html("");
  modalBodyElement.append(
    `<div style='font-weight: bold'>${item.name}</div><br>`
  );

  modalBodyElement.append(`<div>건물정보</div> <br>`);
  modalBodyElement.append(
    `<div>주소: ${city} ${gu} ${item.dong} ${item.areaNumber}</div>`
  );
  modalBodyElement.append(`<div>층: ${item.floor}층</div>`);
  modalBodyElement.append(`<div>전용면적: ${item.area}</div>`);
  modalBodyElement.append(`<div>건축년도: ${item.constructedYear}</div><br>`);

  modalBodyElement.append(`<div>거래정보</div><br>`);
  modalBodyElement.append(`<div>거래금액: ${item.price}</div>`);
  modalBodyElement.append(`<div>거래일: ${item.date}</div>`);
}

function loadMap() {
  const container = document.getElementById("map");
  let options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };

  map = new kakao.maps.Map(container, options);
}

function zoomIn() {
  map.setLevel(map.getLevel() - 1);
}

function zoomOut() {
  map.setLevel(map.getLevel() + 1);
}

function showCenterLocation(address) {
  const geocoder = new kakao.maps.services.Geocoder();

  geocoder.addressSearch(address, function (result, status) {
    if (status === kakao.maps.services.Status.OK) {
      const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

      // 결과값으로 받은 위치를 마커로 표시
      const marker = new kakao.maps.Marker({
        map: map,
        position: coords,
      });

      // 인포 윈도우로 장소에 대한 설명을 표시
      var infowindow = new kakao.maps.InfoWindow({
        content: `<div style="width:150px;text-align:center;padding:6px 0;border-radius:5px;">${address}</div>`,
      });
      infowindow.open(map, marker);

      // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
      map.panTo(coords);
    }
  });
}

function showAllLocations(address) {
  const items = JSON.parse(localStorage.getItem("items"));
  const geocoder = new kakao.maps.services.Geocoder();
  const imageSrc =
    "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
  const imageSize = new kakao.maps.Size(24, 35);

  for (const item of items) {
    geocoder.addressSearch(
      address + " " + item.areaNumber,
      function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
          const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          // 결과값으로 받은 위치를 마커로 표시
          const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
          console.log(coords);
          const marker = new kakao.maps.Marker({
            map: map,
            position: coords,
            title: item.name,
            image: markerImage,
          });
        }
      }
    );
  }
}

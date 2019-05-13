<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>jBlog</title>

  <!-- Bootstrap core CSS -->
  <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <link href="/css/template/clean-blog.min.css" rel="stylesheet">
 <style>
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:500px;}
#menu_wrap {height:50%;position:absolute;top:0;left:0;bottom:0;width:250px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}
</style>
</head>

<body>
<jsp:include page="../include/nav.jsp"/>
  <!-- Main Content -->
  <div class="container mt-5 pt-5">
    <div class="row">
      <div class="col mx-auto">
      	<div style="margin-bottom:5px;">
      		<div class="input-group input-group-sm mb-1">
			  <div class="input-group-prepend">
			    <button class="btn btn-outline-secondary dropdown-toggle" id="category_btn" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="min-width:100px">
			    	<c:choose>
				  		<c:when test="${not empty post_dto.category }">
				  			<c:forEach items="${categoryList }"  var="list">
				  				<c:if test="${list.idx== post_dto.category }">
				  					${list.category }
				  				</c:if>
				        	</c:forEach>
				  		</c:when>
				  		<c:otherwise>
				  			선택하세요.
				  		</c:otherwise>
				  	</c:choose>
			    </button>
			    <div class="dropdown-menu">
			    	<c:forEach items="${categoryList }"  var="list">
			    		<a class="dropdown-item" href="javascript:;" data-value="${list.idx }" onclick="click_category(this)">${list.category }</a>
		        	</c:forEach>
			    </div>
			  </div>
			  <input type="hidden" id="category" value="${post_dto.category}">
			  <input type="text" id="title" class="form-control" aria-label="제목" value="${post_dto.title }">
			</div>
			<div class="input-group input-group-sm mb-1">
			  	<div class="input-group-prepend">
			    	<span class="input-group-text text-center" id="inputGroup-sizing-sm" style="min-width:100px">부제목</span>
			  	</div>
			  		<input type="text" id="sub_title" class="form-control" aria-label="부제" aria-describedby="inputGroup-sizing-sm" value="${post_dto.sub_title }">
			</div>
			<div class="input-group input-group-sm mb-1">
			  <div class="input-group-prepend">
			    <span class="input-group-text text-center" style="min-width:100px">헤더 이미지</span>
			  </div>
			  <div class="custom-file" style="height:37.99px">
			    <input type="file" class="custom-file-input" id="inputGroupFile01" style="height:unset;" onchange="file_change(this);">
			    <label class="custom-file-label" for="inputGroupFile01" style="font-size:medium;">
			   		 <c:choose>
				  		<c:when test="${not empty post_dto.header_image }">
				  			${post_dto.header_image }
				  		</c:when>
				  		<c:otherwise>
				  			 파일을 선택하세요
				  		</c:otherwise>
				  	</c:choose>
			    </label>
			  </div>
			</div>
				<img src="/getImg?imgName=${post_dto.header_image }" id="thumbnail" class="img-thumbnail" width="20%" onerror="this.src='/img/thumbnail_error.jpg'" onclick="imgclick();">
	    </div><!-- /.col-lg-6 -->
  		<textarea name="ir1" id="ir1" rows="10" cols="100">${post_dto.content }</textarea>
  		<div class="btn-group btn-group-sm float-right" role="group" aria-label="submit_or_cancel">
  			<c:choose>
  				<c:when test="${not empty post_dto }">
  					<button type="button" class="btn btn-outline-primary" onclick="jBlog.update();">수정</button>
  				</c:when>
  				<c:otherwise>
  					<button type="button" class="btn btn-outline-primary" onclick="jBlog.save();">저장</button>
  				</c:otherwise>
  			</c:choose>
		  <button type="button" class="btn btn-outline-danger" onclick="jBlog.cancel();">취소</button>
		</div>
  		<input type="hidden"	name="${_csrf.parameterName}"	value="${_csrf.token}"/>
  		<input type="hidden"	id="idx" name="idx"	value="${post_dto.idx}"/>
  		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#mapModal" style="display:none" id="map_modal">
		  map modal
		</button>
		
		<!-- Modal -->
		<div class="modal fade" id="mapModal" tabindex="-1" role="dialog" aria-labelledby="mapModalTitle" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="mapModalTitle">지도</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <div class="map_wrap">
				    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
				
				    <div id="menu_wrap" class="bg_white">
				        <div class="option">
				            <div>
				                <form onsubmit="searchPlaces(); return false;">
				                    키워드 : <input type="text" value="" id="keyword" size="15"> 
				                    <button type="submit">검색하기</button> 
				                </form>
				            </div>
				        </div>
				        <hr>
				        <ul id="placesList"></ul>
				        <div id="pagination"></div>
				    </div>
				</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" onclick="choose_map();">선택</button>
		      </div>
		    </div>
		  </div>
		</div>
      </div>
    </div>
  </div>

  <hr>
<jsp:include page="../include/footer.jsp"/>
<div id="staticMap" style="width:600px;height:350px;display:none;"></div>    
  <!-- Bootstrap core JavaScript -->
  <script src="/vendor/jquery/jquery.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
	<script type="text/javascript" src="/js/smart_editor.js"></script>
	<script type="text/javascript" src="/smarteditor2/js/service/HuskyEZCreator.js"></script>  
	<script type="text/javascript" src="/js/jBlog.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=876463df23372cf60bb0e5366302345e&libraries=services"></script>
<script>
$('#mainNav').addClass('is-visible is-fixed');
var Editor_Obj_ID = "";

$(document).ready(function () {
	Editor_Obj_ID = Global_Create_Smart_Editor("ir1");
});
function imgclick(){
	$("input[type=file]").trigger("click");
	
}
var mapContainer;
var markers=[];
var map,ps,infowindow;
$('#mapModal').on('shown.bs.modal', function (e) {
	if(!mapContainer){
		// 마커를 담을 배열입니다
		 markers = [];

		mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new daum.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  

		// 지도를 생성합니다    
		 map = new daum.maps.Map(mapContainer, mapOption); 

		// 장소 검색 객체를 생성합니다
		 ps = new daum.maps.services.Places();  

		// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
		 infowindow = new daum.maps.InfoWindow({zIndex:1});

	}
});
//키워드 검색을 요청하는 함수입니다
function searchPlaces() {

    var keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( keyword, placesSearchCB); 
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === daum.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === daum.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === daum.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

    var listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new daum.maps.LatLngBounds(), 
    listStr = '';
    
    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    for ( var i=0; i<places.length; i++ ) {

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new daum.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i), 
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function(marker, title) {
            daum.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, title);
            });

            daum.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();
            });

        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info">' +
                '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>'; 
    }
                 
      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';           

    el.innerHTML = itemStr;
    el.className = 'item';
    el.onclick=function(){
    	bounds = new daum.maps.LatLngBounds();
    	removeMarker();
    	var placePosition = new daum.maps.LatLng(places.y, places.x);
    	var marker= addMarker(placePosition,0,places.place_name);
    	displayInfowindow(marker, places.place_name);
    	map.setLevel(2);
    	map.setCenter(placePosition);
    	 bounds.extend(placePosition);
    	 map.setBounds(bounds);
    }
    return el;
}
// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new daum.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new daum.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new daum.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new daum.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new daum.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });
	marker.setTitle(title);
    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}
 function choose_map(){
	 $("#staticMap").html('');
	 $("#staticMap").show();
	 var markers_static;
	 if(markers.length>0){
		 markers_static  = [
			    {
			        position: markers[0].getPosition(), 
			        text: markers[0].getTitle() // text 옵션을 설정하면 마커 위에 텍스트를 함께 표시할 수 있습니다     
			    }
		];
	 }
	
		var staticMapContainer  = document.getElementById('staticMap'), // 이미지 지도를 표시할 div  
		    staticMapOption = { 
		        center: map.getCenter(), // 이미지 지도의 중심좌표
		        level: map.getLevel(), // 이미지 지도의 확대 레벨
		        marker: markers_static // 이미지 지도에 표시할 마커 
		    };    

		// 이미지 지도를 생성합니다
		var staticMap = new daum.maps.StaticMap(staticMapContainer, staticMapOption);
		var Set_HTML = $("#staticMap").children()[0].outerHTML;
		GSE_Editors_Obj.getById["ir1"].exec("PASTE_HTML", [Set_HTML]);
		$('#mapModal').modal('hide')
		 $("#staticMap").hide();
 }
</script>
</body>

</html>

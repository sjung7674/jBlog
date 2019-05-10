
	/*
		* 필수 수정 사항 *
			1. smart_editor.js
				Global_Smart_Editor_Base_URI = 스마트 에디터의 절대 경로, 경로 앞뒤로 "/" 붙이세요.
				Global_Smart_Editor_Image_Upload_File_Name = 업로드 처리 파일 파일명과 확장자만 붙이세요.

			2. "스마트 에디터 설치 경로"/photo_upload/photo_upload_proc.asp
				업로드 처리 파일, 각 WAS별 업로드 개발 후 업로드 상태에 따라 아래의 함수를 호출하세요.
					업로드 성공시 : parent.File_Upload_Complete_Func("FileURL");
										File_Upload_Complete_Func함수의 인자 값은 업로드 된 파일 절대 경로 입니다.
										예) /user_file/editor/test1234.png
					업로드 실패시 : parent.File_Upload_Error_Func("Error_Code");
										File_Upload_Error_Func함수의 인자 값은 업로드 실패의 코드입니다.
										본 함수는 "스마트 에디터 설치 경로"/photo_upload/js/photo_upload.js 에 있습니다.

			3. "스마트 에디터 설치 경로"/photo_upload/js/photo_upload.js
				File_Upload_Error_Func()함수의 내용을 수정.
				업로드 오류에 대한 처리를 이곳에서 하세요.

			4. 설정 방법을 숙지 후 해당 주석의 내용은 삭제 하세요.

		* 사용 방법 *
			Global_Create_Smart_Editor("", "", "", "")함수를 호출 하여 에디터를 생성하세요.

			Global_Create_Smart_Editor()함수의 인자 값은 아래와 같습니다.
				1. "Textarea의 ID값(필수)"
				2. "사용 언어(생략 가능, 기본 : K)"
					※ 설정 가능한 언어 = K(한국), E(미국), J(일본), C(중국), T(타이완)
				3. "Load후 실행될 콜백 함수(생략 가능, 기본 : 없음)"
				4. "Unload후 실행될 콜백 함수(생략 가능, 기본 : 없음)"
				※ 인자값 생략은 역순만(4->3->2) 가능 합니다.

			Global_Create_Smart_Editor()함수의 반환 값은 에디터가 생성될 대상의 Textarea의 ID 값입니다.

			Global_Create_Smart_Editor()함수에서 사용하는 에디터 설정 변수는 아래와 같습니다.
				1. Global_Smart_Editor_UseToolbar_Flag = 툴바 사용 여부 (true:사용 / false:사용하지 않음)
				2. Global_Smart_Editor_UseVerticalResizer_Flag = 입력창 크기 조절바 사용 여부 (true:사용 / false:사용하지 않음)
				3. Global_Smart_Editor_UseModeChanger_Flag = 모드 탭 (Editor | HTML | TEXT) 사용 여부 (true:사용 / false:사용하지 않음)
				4. Global_Smart_Editor_SkipXssFilter_Flag = client-side xss filter 무시 여부[ style 소스 사용 여부 ] (true:사용하지 않음 / 그외:사용)

			Global_Create_Smart_Editor()함수 호출 직전 에디터의 속성을 설정하여 개별로 에디터를 설정 할 수 있습니다.
				예) 처음 에디터는 툴바 미사용, 두번째 에디터는 툴바 사용으로 생성
					Global_Smart_Editor_UseToolbar_Flag = false;
					Editor_Obj_ID = Global_Create_Smart_Editor("ir1");
					
					Global_Smart_Editor_UseToolbar_Flag = true;
					Editor_Obj_ID_2 = Global_Create_Smart_Editor("ir2");

			Global_Smart_Editor_Set_Content_To_Textarea("")함수를 이용하여 Editor의 값을 Textarea에 설정
				※ Submit또는 Editor의 값을 이용할때는 꼭 본 함수를 호출 후 이용

			Global_Smart_Editor_Set_Content_To_Textarea()함수의 인자 값은 아래와 같습니다.
				1. "Textarea의 ID값(필수, 직접 입력 또는 Global_Create_Smart_Editor()함수에서 반환된 값을 저장한 변수를 대입해도 됨)"

			Global_Smart_Editor_Get_Content_For_Textarea("")함수를 이용하여 Textarea의 값을 추출
				※ 빈값 확인 용 또는 기타 용도로 이용, 대상 Editor와 내용이 다를 수 있습니다.
					Global_Smart_Editor_Set_Content_To_Textarea()함수를 호출하여 동기화 가능

			Global_Smart_Editor_Get_Content_For_Textarea()함수의 인자 값은 아래와 같습니다.
				1. "Textarea의 ID값(필수, 직접 입력 또는 Global_Create_Smart_Editor()함수에서 반환된 값을 저장한 변수를 대입해도 됨)"

			Global_Smart_Editor_Get_Content_For_Editor("")함수를 이용하여 Editor의 값을 추출
				※ 빈값 확인 용 또는 기타 용도로 이용, 대상 Textarea와 내용이 다를 수 있습니다.
					Global_Smart_Editor_Set_Content_To_Textarea()함수를 호출하여 동기화 가능

			Global_Smart_Editor_Get_Content_For_Editor()함수의 인자 값은 아래와 같습니다.
				1. "Textarea의 ID값(필수, 직접 입력 또는 Global_Create_Smart_Editor()함수에서 반환된 값을 저장한 변수를 대입해도 됨)"
	*/

	var Global_Smart_Editor_Base_URI = "/admin/smarteditor2/";
	var Global_Smart_Editor_Image_Upload_File_Name = "photo_upload_proc.asp";
	var GSE_Editors_Obj = [];

	// 툴바 사용 여부 (true:사용 / false:사용하지 않음)
	var Global_Smart_Editor_UseToolbar_Flag = true;

	// 입력창 크기 조절바 사용 여부 (true:사용 / false:사용하지 않음)
	var Global_Smart_Editor_UseVerticalResizer_Flag = true;

	// 모드 탭 (Editor | HTML | TEXT) 사용 여부 (true:사용 / false:사용하지 않음)
	var Global_Smart_Editor_UseModeChanger_Flag = true;

	// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
	var Global_Smart_Editor_SkipXssFilter_Flag = true;

	function Global_Create_Smart_Editor(Target_Textarea_Object_ID){
		return Global_Create_Smart_Editor(Target_Textarea_Object_ID, "K", "", "");
	}

	function Global_Create_Smart_Editor(Target_Textarea_Object_ID, Langage_Type){
		return Global_Create_Smart_Editor(Target_Textarea_Object_ID, Langage_Type, "", "");
	}

	function Global_Create_Smart_Editor(Target_Textarea_Object_ID, Langage_Type, Load_Callback_Function){
		return Global_Create_Smart_Editor(Target_Textarea_Object_ID, Langage_Type, Load_Callback_Function, "");
	}

	function Global_Create_Smart_Editor(Target_Textarea_Object_ID, Langage_Type, Load_Callback_Function, Unload_Callback_Function){
		var Set_Lang_Flag = "ko_KR";
		var Set_Skin_Type = "SmartEditor2Skin.html";

		if (Langage_Type == "K"){
			Set_Lang_Flag = "ko_KR";
			Set_Skin_Type = "SmartEditor2Skin.html";
		}else if (Langage_Type == "E"){
			Set_Lang_Flag = "en_US";
			Set_Skin_Type = "SmartEditor2Skin_en_US.html";
		}else if (Langage_Type == "J"){
			Set_Lang_Flag = "ja_JP";
			Set_Skin_Type = "SmartEditor2Skin_ja_JP.html";
		}else if (Langage_Type == "C"){
			Set_Lang_Flag = "zh_CN";
			Set_Skin_Type = "SmartEditor2Skin_zh_CN.html";
		}else if (Langage_Type == "T"){
			Set_Lang_Flag = "zh_TW";
			Set_Skin_Type = "SmartEditor2Skin_zh_TW.html";
		}

		Set_Skin_Type = Global_Smart_Editor_Base_URI + Set_Skin_Type + "#" + Target_Textarea_Object_ID;

		var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"]];

		nhn.husky.EZCreator.createInIFrame({
			oAppRef : GSE_Editors_Obj,
			elPlaceHolder : Target_Textarea_Object_ID,
			sSkinURI : Set_Skin_Type,
			htParams : {
				bUseToolbar : Global_Smart_Editor_UseToolbar_Flag,
				bUseVerticalResizer : Global_Smart_Editor_UseVerticalResizer_Flag,
				bUseModeChanger : Global_Smart_Editor_UseModeChanger_Flag,
				aAdditionalFontList : aAdditionalFontSet,
				bSkipXssFilter : Global_Smart_Editor_SkipXssFilter_Flag,
				fOnBeforeUnload : function(){
					if (Unload_Callback_Function != ""){
						eval(Unload_Callback_Function);
					}
				},
				I18N_LOCALE : Set_Lang_Flag
			},
			fOnAppLoad : function(){
				if (Load_Callback_Function != ""){
					eval(Load_Callback_Function);
				}
			},
			fCreator : "createSEditor2_" + Target_Textarea_Object_ID
		});
		return Target_Textarea_Object_ID;
	}

	function Global_Smart_Editor_Image_Upload(Get_Hash_Code){
		var Get_Editor_Lang_Type = Global_Smart_Editor_Get_Langage_For_Editor(Get_Hash_Code);
		window.open(Global_Smart_Editor_Base_URI + "photo_upload/photo_upload_" + Get_Editor_Lang_Type.toLowerCase() + ".html#" + Get_Hash_Code, "", "width=383,height=270,menubar=no,toolbar=no,location=no,status=no,resizable=yes,scrollbars=0");
	}

	function Global_Smart_Editor_Set_Content_To_Textarea(Target_Textarea_Object_ID) {
		GSE_Editors_Obj.getById[Target_Textarea_Object_ID].exec("UPDATE_CONTENTS_FIELD", []);
	}

	function Global_Smart_Editor_Get_Content_For_Textarea(Target_Textarea_Object_ID) {
		return document.getElementById(Target_Textarea_Object_ID).value;
	}

	function Global_Smart_Editor_Get_Content_For_Editor(Target_Textarea_Object_ID) {
		return GSE_Editors_Obj.getById[Target_Textarea_Object_ID].getIR();
	}

	function Global_Smart_Editor_Get_Langage_For_Editor(Target_Textarea_Object_ID) {
		var Get_Editor_Lang_Type = GSE_Editors_Obj.getById[Target_Textarea_Object_ID].htOptions.I18N_LOCALE;
		if (Get_Editor_Lang_Type == "ko_KR"){
			return "K";
		}else if (Get_Editor_Lang_Type == "en_US"){
			return "E";
		}else if (Get_Editor_Lang_Type == "ja_JP"){
			return "J";
		}else if (Get_Editor_Lang_Type == "zh_CN"){
			return "C";
		}else if (Get_Editor_Lang_Type == "zh_TW"){
			return "T";
		}else{
			return "K";
		}
	}


	function File_Upload_Func(){
		
		var Target_Editor_Object_ID = window.location.hash.slice(1);
		document.getElementById("editor_id").value = Target_Editor_Object_ID;
		var Form_Obj = document.getElementById("upload_file_form");
		Form_Obj.target = "global_file_upload_iframe";
		Form_Obj.enctype = "multipart/form-data";
		Form_Obj.action = "/dext5_upload_handler.do";
		document.getElementById("Upload_Process_Background_Div").style.display = "block";
		Form_Obj.submit();
	}

	function File_Upload_Complete_Func(returnUrl){
		var Set_HTML = "<img src=\"" + returnUrl + "\" />";
		var Target_Editor_Object_ID = window.location.hash.slice(1);
		opener.GSE_Editors_Obj.getById[Target_Editor_Object_ID].exec("PASTE_HTML", [Set_HTML]);
		self.close();
	}

	function File_Upload_Error_Func(Error_Code){
		document.getElementById("Upload_Process_Background_Div").style.display = "none";
		var Get_Editor_Lang_Type = opener.Global_Smart_Editor_Get_Langage_For_Editor(window.location.hash.slice(1));
		/*
			오류에 관한 처리를 아래부터 작성하세요.
			Error_Code = 오류 코드
			Get_Editor_Lang_Type = 에디터 언어
		*/

		if (Get_Editor_Lang_Type == "E"){
			
		}else if (Get_Editor_Lang_Type == "J"){
			
		}else if (Get_Editor_Lang_Type == "C"){
			
		}else if (Get_Editor_Lang_Type == "T"){
			
		}else{
			
		}
	}

	function File_Upload_Cancel_Func(){
		self.close();
	}

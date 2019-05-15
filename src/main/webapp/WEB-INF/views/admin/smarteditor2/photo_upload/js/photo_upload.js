
	function File_Upload_Func(){
		var formData = new FormData(); 
		formData.append("file",document.getElementById("upload_file").files[0]);
		var headers = {};
		headers["X-CSRF-TOKEN"] = opener.document.getElementsByName("_csrf")[0].value;
		$.ajax({
			url:"/admin/file_upload_handler",
			type:"post",
			data:formData,
			processData : false, //true : data의 파일형태가 query String으로 전송. false : non-processed data 
			contentType : false, // false : multipart/form-data 형태로 전송되기 위한 옵션값
			headers : headers,
			success : function(data){
				if(data=='error' || data=='over_size' || data=='not_JGPBImage'){
					File_Upload_Error_Func(data);
				}else{
					File_Upload_Complete_Func(data);
				}
			},
			error:function(request,status,error){
				console.log(request);
				console.log(status);
				console.log(error);
			}
		})
		/*var Target_Editor_Object_ID = window.location.hash.slice(1);
		document.getElementById("editor_id").value = Target_Editor_Object_ID;
		var Form_Obj = document.getElementById("upload_file_form");
		Form_Obj.target = "global_file_upload_iframe";
		Form_Obj.enctype = "multipart/form-data";
		Form_Obj.action = "/file_upload_handler";
		document.getElementById("Upload_Process_Background_Div").style.display = "block";
		Form_Obj.submit();*/
	}

	function File_Upload_Complete_Func(returnUrl){
		var Set_HTML = "<img class=\"img-fluid\" src=\"/getImg?imgName=" + returnUrl + "\" />";
		var Target_Editor_Object_ID = window.location.hash.slice(1);
		opener.GSE_Editors_Obj.getById[Target_Editor_Object_ID].exec("PASTE_HTML", [Set_HTML]);
		self.close();
	}

	function File_Upload_Error_Func(Error_Code){
		document.getElementById("Upload_Process_Background_Div").style.display = "none";
		var Get_Editor_Lang_Type = opener.Global_Smart_Editor_Get_Langage_For_Editor(window.location.hash.slice(1));

		if (Get_Editor_Lang_Type == "E"){
			
		}else if (Get_Editor_Lang_Type == "J"){
			
		}else if (Get_Editor_Lang_Type == "C"){
			
		}else if (Get_Editor_Lang_Type == "T"){
			
		}else{
			if(Error_Code=='error'){
				alert("에러가 발생하였습니다.");
			}else if (Error_Code=='over_size'){
				alert("이미지 파일 용량이 10MB 초과 하였습니다.");
			}else if(Error_Code=='not_JGPBImage'){
				alert("이미지는 (JPG, GIF, PNG, BMP)만 가능합니다.");
			}
		}
	}

	function File_Upload_Cancel_Func(){
		self.close();
	}

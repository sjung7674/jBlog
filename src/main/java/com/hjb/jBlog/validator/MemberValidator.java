package com.hjb.jBlog.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hjb.jBlog.dto.MemberDTO;
import com.hjb.jBlog.dto.PostDTO;

public class MemberValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberDTO memberDTO = (MemberDTO) target;
		ValidationUtils.rejectIfEmpty(errors, "userid", "field.required","에러가 발생하였습니다.");
		ValidationUtils.rejectIfEmpty(errors, "nick_name", "field.required","별명을 입력해 주세요.");
	}

}

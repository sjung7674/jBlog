package com.hjb.jBlog.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hjb.jBlog.dto.PostDTO;

public class PostValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PostDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PostDTO postDTO = (PostDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "field.required","카테고리를 선택해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required","제목을 입력해 주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "field.required","본문을 입력해주세요.");
	}

}

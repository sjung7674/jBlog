package com.hjb.jBlog.admin.dao;

import java.util.HashMap;
import java.util.Map;

public interface AdminHeaderDAO {
	public int insertMainHeader(HashMap<String,String> map);
	public Map<String,String> selectMainHeader();
}

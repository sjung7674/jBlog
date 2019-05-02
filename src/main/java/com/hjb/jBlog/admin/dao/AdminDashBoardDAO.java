package com.hjb.jBlog.admin.dao;

import java.util.List;
import java.util.Map;

public interface AdminDashBoardDAO {
	public List<Map<String,String>> selectAccessCnt();
	public List<Map<String,String>> selectDeviceCnt();
}

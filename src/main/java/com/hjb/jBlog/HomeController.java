package com.hjb.jBlog;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hjb.jBlog.service.WriteService;
import com.hjb.jBlog.service.impl.ViewServiceImpl;
import com.hjb.jBlog.service.impl.WriteServiceImpl;
import com.hjb.jBlog.util.PagingBean;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	ViewServiceImpl viewService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home_redirect(HttpServletRequest request ) {
		return "redirect:/1";
	}
	@RequestMapping(value = "/{idx}", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request,@PathVariable String idx ) {
		ModelAndView view = new ModelAndView();
		try{
			int currentPageNum = Integer.parseInt(idx);
			String category_no = request.getParameter("category_no");
			PagingBean purchaserPage = new PagingBean(viewService.selectCountPostListNewest(category_no),currentPageNum);
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("currentPageNum",  ((currentPageNum-1)*purchaserPage.getItemsPerPage()));
			m.put("currentListRowCount", purchaserPage.getItemsPerPage());
			m.put("category_no", category_no);
			List list =  viewService.postList(m);
			System.out.println(list);
			if(list.size()==0){
				view.setViewName("redirect:/1");
			}else{
				view.addObject("page",purchaserPage);
				view.addObject("currentPageNum",currentPageNum);
				view.addObject("post_list",list);
				view.setViewName("index");
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
			view.setViewName("redirect:/1");
		}
		return view;
	}
}

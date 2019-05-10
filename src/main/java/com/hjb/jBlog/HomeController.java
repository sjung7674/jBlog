package com.hjb.jBlog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hjb.jBlog.service.ViewService;
import com.hjb.jBlog.service.impl.HeaderServiceImpl;
import com.hjb.jBlog.util.PagingBean;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private ViewService viewService;
	@Autowired
	private HeaderServiceImpl headerService;
	
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
			view.addObject("page",purchaserPage);
			view.addObject("currentPageNum",currentPageNum);
			view.addObject("post_list",list);
			view.addObject("main_header", headerService.selectMainHeader());
			view.setViewName("index");
		}catch(NumberFormatException e){
			e.printStackTrace();
			view.setViewName("redirect:/1");
		}
		return view;
	}
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(HttpServletRequest request ) {
		return "errors/500";
	}
}

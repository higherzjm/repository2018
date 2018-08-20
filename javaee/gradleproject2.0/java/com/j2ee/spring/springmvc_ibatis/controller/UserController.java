package com.j2ee.spring.springmvc_ibatis.controller;

import com.j2ee.spring.springmvc_ibatis.model.User;
import com.j2ee.spring.springmvc_ibatis.service.UserService;
import com.j2ee.spring.springmvc_ibatis.util.Pages;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;

	private final String LIST="redirect:/user/list.do?pageNo=1";

	@RequestMapping(value="/index")
	public ModelAndView index(){
		return new ModelAndView("ibatis/index");
	}
	/**
	 * 登录
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(User user, BindingResult result, HttpSession session){
		if(!result.hasErrors()){
		User loginUser=userService.login(user);
		if(loginUser!=null){
			session.setAttribute("USER", loginUser);
			return new ModelAndView("redirect:/user/list.do?pageNo=1");
		}else{
			return new ModelAndView("redirect:/");
		}
	  }else{
		  ModelAndView view=new ModelAndView();
		  view.setViewName("redirect:/");
		  view.addObject("error", result.getAllErrors());
		  return view;
	  }
	}
	/**
	 * 跳转至添加页
	 * @return
	 */
	@RequestMapping(value="/new",method= RequestMethod.GET)
	public ModelAndView toAdd(){
		return new ModelAndView("ibatis/user/add");
	}
	/**
	 * 保存
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/new",method= RequestMethod.POST)
	public ModelAndView add(User user, BindingResult result){
		if(result.hasErrors()){
			return new ModelAndView("ibatis/user/add","error", result.getAllErrors());
		}else{
			user.setId(Integer.parseInt(new SimpleDateFormat("HHmmss").format(new Date())));
			userService.addUser(user);
			return new ModelAndView(LIST);
		}
	}
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/del")
	public ModelAndView delete(int id){
		userService.deleteUser(id);
		return new ModelAndView(LIST);
	}
	/**
	 * 跳转至编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(int id){
		User user=userService.getUserById(id);
		return new ModelAndView("ibatis/user/edit","user",user);
	}
	/**
	 * 编辑
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/edit",method= RequestMethod.POST)
	public ModelAndView update(User user, BindingResult result){
		ModelAndView view=new ModelAndView();
		if(result.hasErrors()){
			view.addObject("error", result.getAllErrors());
			view.setViewName("ibatis/user/edit");
			return view;
		}else{
		userService.updateUser(user);
		return new ModelAndView(LIST);
		}
	}
	/**
	 * 分页
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(int pageNo){
		Pages<User> pages=userService.getUserByPageNo(pageNo);
		return new ModelAndView("ibatis/user/main","pages",pages);
	}
}

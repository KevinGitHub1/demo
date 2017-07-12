package com.zmk.cms.mobile.common.controller;

//                                        _oo0oo_
//                                       o8888888o
//                                       88" . "88
//                                       (| -_- |)
//                                       0\  =  /0
//                                     ___/`---'\___
//                                   .` \\|     |// '.
//                                  / \\|||  :  |||// \
//                                 / _||||| -:- |||||- \
//                                |   | \\\  -  /// |   |
//                                | \_|  ''\---/''  |_/ |
//                                \  .-\__  '-'  ___/-. /
//                              ___'. .'  /--.--\  `. .'___
//                           ."" '<  `.___\_<|>_/___.' >' "".
//                          | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//                          \  \ `_.   \_ __\ /__ _/   ._` /  /
//                      =====`-.____`.___ \_____/___.-`___.-'=====
//                                        `=---='
//
//                 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//                               佛祖保佑                         永无BUG
//

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmk.cms.mobile.common.bean.MobileBean;
import com.zmk.cms.mobile.common.service.IndexService;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private IndexService indexService;
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
 	public MobileBean login(String username, String password,HttpServletRequest request) {
		return indexService.login(username, password);
	}
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
 	public MobileBean logout(String token, String username) {
		return indexService.logout(token, username);
	}
	@ResponseBody
	@RequestMapping(value = "/getUserConfig", method = RequestMethod.POST)
 	public MobileBean getUserConfig(String token) {
		return indexService.getUserConfig(token);
	}
}

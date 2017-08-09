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
//                    佛曰:  
//                    写字楼里写字间，写字间里程序员；  
//                    程序人员写程序，又拿程序换酒钱。  
//                    酒醒只在网上坐，酒醉还来网下眠；  
//                    酒醉酒醒日复日，网上网下年复年。  
//                    但愿老死电脑间，不愿鞠躬老板前；  
//                    奔驰宝马贵者趣，公交自行程序员。  
//                    别人笑我忒疯癫，我笑自己命太贱；  
//                    不见满街漂亮妹，哪个归得程序员？ 
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

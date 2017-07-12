package com.zmk.cms.login.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zmk.cms.common.bean.ResultBean;
import com.zmk.cms.login.bean.User;
import com.zmk.cms.login.service.ILoginServices;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ILoginServices loginServices;
    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    public ResultBean checkUser(User user, HttpServletRequest request)
            throws Exception {
        HttpSession session = request.getSession();
        ResultBean resultBean = new ResultBean();
        Map<String, Object> result = loginServices.CheckUser(user);
        if (result != null) {
            resultBean.setFlag("success");
            resultBean.setContent(result);
            for (Entry<String, Object> entry : result.entrySet()) {
                session.setAttribute(entry.getKey(), entry.getValue());
            }
            session.setAttribute("cmsSession", session);
        } else {
            resultBean.setFlag("error");
            resultBean.setErrorInfo("用户名不存在或密码错误！");
        }

        return resultBean;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getRyxxSession", method = RequestMethod.GET)
    public ResultBean getRyxxSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ResultBean resultBean = new ResultBean();
        HttpSession obj = (HttpSession) session.getAttribute("cmsSession");
        Map<String, Object> result = new HashMap<String, Object>();
        if (obj != null) {
            result.put("ID", (String) obj.getAttribute("ID"));
            result.put("NAME", (String) obj.getAttribute("NAME"));
            result.put("USERNAME", (String) obj.getAttribute("USERNAME"));
            result.put("PASSWORD", (String) obj.getAttribute("PASSWORD"));
            result.put("POWERS", (String) obj.getAttribute("POWERS"));
            result.put("GH", (String) obj.getAttribute("GH"));
            result.put("DEPID", (String) obj.getAttribute("DEPID"));
            result.put("DWMC", (String) obj.getAttribute("DWMC"));
            result.put("TYPE", (String) obj.getAttribute("TYPE"));
            result.put("DWJB", (String) obj.getAttribute("DWJB"));
            result.put("level1MenuId", (String) obj
                    .getAttribute("level1MenuId"));
            result.put("level2MenuId", (String) obj
                    .getAttribute("level2MenuId"));
            result.put("level3MenuId", (String) obj
                    .getAttribute("level3MenuId"));
            String fileNameConfigType = "";
            result.put("fileNameConfigType", fileNameConfigType);
            result.put("allMenus", (List<Map<String, Object>>) obj
                    .getAttribute("allMenus"));
        }
        if (obj != null) {
            resultBean.setFlag("success");
            resultBean.setContent(result);
        }
        return resultBean;
    }

    @RequestMapping(value = "/removeSession", method = RequestMethod.GET)
    public void removeSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}

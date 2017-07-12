package com.zmk.cms.mobile.common.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zmk.cms.mobile.common.service.TokenService;
import com.zmk.cms.mobile.common.bean.MobileBean;
import com.zmk.cms.mobile.common.util.ResultStatus;

public class CommonInterceptor implements HandlerInterceptor {

    @Resource
    private TokenService tokenService;

    public CommonInterceptor() {

    }

    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */

    @SuppressWarnings("unchecked")
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String requestUrl = request.getRequestURI().replace(
                request.getContextPath(), "");
        if ("/mobile/login".equals(requestUrl)) {// 如果是登录请求，放行
            return true;
        }
        Map map = request.getParameterMap();
        if (map.get("token") == null) {// 如果请求没有token，拦截返回错误信息
            PrintWriter wirter = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            MobileBean bean = new MobileBean(ResultStatus.FAIL);
            bean.setResult("请求错误");
            wirter.write(mapper.writeValueAsString(bean));
            wirter.flush();
            return false;
        }
        String tokenid = ((String[]) (map.get("token")))[0];
        if (tokenService.validateToken(tokenid)) {// 验证token有效，放行
            return true;
        } else {// token无效
            PrintWriter wirter = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            MobileBean bean = new MobileBean(ResultStatus.FAIL);
            bean.setResult("token无效");
            wirter.write(mapper.writeValueAsString(bean));
            wirter.flush();
        }
        return false;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * */
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
        // log.info("==============执行顺序: 2、postHandle================");
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用
     * 
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // log.info("==============执行顺序: 3、afterCompletion================");
    }

}

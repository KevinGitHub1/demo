package com.zmk.cms.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.zmk.cms.common.util.CommUtil;



/**
 * 仅做是否登录会话存在的判断。
 * 
 */
public class SafeFilters implements Filter {
    private static final Logger LOG = Logger.getLogger(SafeFilters.class);

    public static String EXCEPT_REQ = "web/";

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        LOG.debug("Safefilter : dofilter() start....................");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String url = httpRequest.getRequestURI();// 请求资源地址
        // System.out.println(url);
        LOG.debug("url：" + url);
        // 欢迎页
//        if ("/layuiCMS/index.html".equalsIgnoreCase(url)) {
//            chain.doFilter(request, response);
//            //return;
//        }
        String[] array = EXCEPT_REQ.split("#");
        for (int i = 0; i < array.length; i++) {
            if (url.contains(array[i])) {
                chain.doFilter(request, response);
                return;
            }
        }
        
        String useID = (String) session.getAttribute("id");
        if (useID != null) {
            chain.doFilter(request, response);
        } else {
            CommUtil.writeUtf8Text(httpResponse, "{\"result\":\"notLogin\"}");
        }
        LOG.debug("Safefilter : dofilter() end....................");
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}

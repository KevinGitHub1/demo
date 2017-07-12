package com.zmk.cms.common.servlet;

import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
//import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class InitServlet extends HttpServlet {

    private static final long serialVersionUID = 6081590748058994110L;

    public InitServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    //private static final Logger LOG = Logger.getLogger(InitServlet.class);

    public void init() throws ServletException {
        // 初始化项目根目录
        try {
            // 读取Log4j配置文件
            initLog4j(getServletContext().getRealPath("/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLog4j(String strPrefix) {
        System.setProperty("cms.root", strPrefix);
        String strFile = getInitParameter("log4j");
        if (strFile != null)
            PropertyConfigurator
                    .configure(strPrefix + File.separator + strFile);
        else {
            System.exit(-1);
        }
    }
}
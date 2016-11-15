package org.sel.rms.interceptor;

import org.sel.rms.exception.PaperException;
import org.sel.rms.exception.TeacherException;
import org.sel.rms.status.PaperStatus;
import org.sel.rms.status.TeacherStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * Created by xubowei on 08/11/2016.
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Value("${config.admin.key")
    String adminKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(true);
        String servletPath = request.getServletPath();
        boolean adminLogin = (session.getAttribute(adminKey) != null);

        // 分解请求地址
        String[] flag = servletPath.split("/");

        System.out.println("admininter is begin!");
        System.out.println(servletPath);
        System.out.println(Arrays.toString(flag));

        if (adminLogin) {
            if (flag[2].startsWith("login")) {
                if (request.getMethod().equals("GET")) {
                    response.sendRedirect(request.getContextPath() + "/admin/index.html");
                } else {
                    throw new TeacherException("logined!", TeacherStatus.LOGINED);
                }
                return false;
            } else {
                return true;
            }
        } else {
            if (flag[2].startsWith("login")) {
                return true;
            } else {
                if (request.getMethod().equals("GET")) {
                    response.sendRedirect(request.getContextPath() + "/admin/login.html");
                } else {
                    throw new PaperException("permission deny!", PaperStatus.PERMISSIOM_DENY);
                }
                return false;
            }
        }

    }
}

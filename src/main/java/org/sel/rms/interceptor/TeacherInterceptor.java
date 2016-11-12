package org.sel.rms.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * Created by xubowei on 08/11/2016.
 */
public class TeacherInterceptor extends HandlerInterceptorAdapter {
    @Value("${config.teacher.key")
    String teacherKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(true);
        String servletPath = request.getServletPath();
        boolean teacherLogin = (session.getAttribute(teacherKey) != null);

        // 分解请求地址
        String[] flag = servletPath.split("/");


        System.out.println("teacherinter is begin!");
        System.out.println(servletPath);
        System.out.println(Arrays.toString(flag));
        if (teacherLogin) {
            if (flag[2].startsWith("login")) {
                response.sendRedirect(request.getContextPath() + "/teacher/index.html");
                return false;
            } else {
                return true;
            }
        } else {
            if (flag[2].startsWith("login")) {
                return true;
            } else {
                response.sendRedirect(request.getContextPath() + "/teacher/login.html");
                return false;
            }
        }

    }
}

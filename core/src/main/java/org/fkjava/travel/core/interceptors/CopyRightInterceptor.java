package org.fkjava.travel.core.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CopyRightInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.application.name:疯狂软件}")
    private String applicationName;
    @Value("${spring.application.company:广州为学教育科技有限公司}")
    private String companyName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.getServletContext().setAttribute("applicationName", applicationName);
        request.getServletContext().setAttribute("companyName", companyName);
        return super.preHandle(request, response, handler);
    }
}

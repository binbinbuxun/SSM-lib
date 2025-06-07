package com.library.interceptor;

import com.library.annotation.RequireAdmin;
import com.library.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            RequireAdmin requireAdmin = handlerMethod.getMethodAnnotation(RequireAdmin.class);

            // 如果方法上没有注解，检查类上是否有注解
            if (requireAdmin == null) {
                requireAdmin = handlerMethod.getBeanType().getAnnotation(RequireAdmin.class);
            }

            // 如果需要管理员权限
            if (requireAdmin != null) {
                HttpSession session = request.getSession(false);
                if (session == null) {
                    response.setStatus(401);
                    return false;
                }

                User user = (User) session.getAttribute("user");
                if (user == null || !"ADMIN".equals(user.getRole())) {
                    response.setStatus(403);
                    return false;
                }
            }
        }
        return true;
    }
}

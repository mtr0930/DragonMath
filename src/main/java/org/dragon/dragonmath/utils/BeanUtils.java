package org.dragon.dragonmath.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Objects;

public class BeanUtils {
    public static <T> T getBean(Class<T> type){
        HttpServletRequest request =
                ((ServletRequestAttributes)
        Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        WebApplicationContext context =
                WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        return (T) Objects.requireNonNull(context).getBean(type);
    }
}

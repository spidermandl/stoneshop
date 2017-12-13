package org.goshop.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.goshop.shiro.Constants;
import org.goshop.users.i.UserService;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>User: Desmond
 * 获取用户信息
 */
public class CustomUserFilter extends PathMatchingFilter {

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, user);
        return true;
    }

}

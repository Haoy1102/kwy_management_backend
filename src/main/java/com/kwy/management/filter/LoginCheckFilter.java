package com.kwy.management.filter;

import com.alibaba.fastjson.JSON;
import com.kwy.management.comon.BaseContext;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.Employee;
import com.kwy.management.service.EmployeeService;
import com.kwy.management.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haoy
 * @description
 * @date 2022/11/30 15:50
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Autowired
    private EmployeeService employeeService;

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    private static final TokenUtil tokenUtil = new TokenUtil();

    /*
      1、荻取本次情求的URI
      2、判断本次情状是否需要处理
      3、如果不秀要处理，则直接成行
      4、判断登灵状态，如果己登录，则直接成行
      5、如果夫登灵则返回夫登陆结果，结合前端，返回一个输出流
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        1、获取本次情求的URI
        String requestURI = request.getRequestURI();

        log.info("拦截的请求：{}", requestURI);

//        定义不需要处理的URI
        String[] urls = {
                "/api/users/login",
                "/api/users/logout",
                "/api/employees/login",
                "/api/employees/login",
                "/hello",
//                "/api/**"
        };

//        2、判断本次情状是否需要处理
//        3、如果不需要处理，则直接放行
        if (check(urls, requestURI)) {
            log.info("本次{}请求不需要处理", requestURI);
            chain.doFilter(request, response);
            return;
        }

//        4.检查是否存在登录状态的标识（例如token或session）
        if (null!=request.getSession().getAttribute("employeeId")){
//        if (false) {
            // 已登录，放行请求
            Long employeeId = (Long) request.getSession().getAttribute("employeeId");
            String employeeName = (String) request.getSession().getAttribute("employeeName");
            log.info("用户{}已登陆", employeeName);
            BaseContext.setCurrentId(employeeId);
            BaseContext.setCurrentUserName(employeeName);
            chain.doFilter(request, response);
        } else {
            //如果cookie中有这两个数据 设置Session
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("employeeId")) {
                        Long employeeId = Long.parseLong(cookie.getValue());
                        Employee employee = employeeService.getById(employeeId);
                        String employeeName = employee.getName();
                        request.getSession().setAttribute("employeeId", employeeId);
                        request.getSession().setAttribute("employeeName", employeeName);
                        log.info("用户{}已登录", employeeName);
                        BaseContext.setCurrentId(employeeId);
                        BaseContext.setCurrentUserName(employeeName);
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
            // 未登录
            log.info("用户未登陆");
            response.getWriter().write(JSON.toJSONString(R.error("身份认证超时，请重新登录", Code.LOGIN_FAILED)));
        }

    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param urls
     * @param requestURI
     * @return
     */
    private boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) return true;
        }
        return false;
    }

    /**
     * @param request
     * @return
     */
    private boolean isLoggedIn(HttpServletRequest request) {
        // 在这里进行判断后台登录状态的逻辑
        // 可以根据具体的实现方式，例如检查token是否有效，或者检查session中是否存在登录信息
        // 返回true表示已登录，返回false表示未登录
        // 示例中使用了一个名为"token"的cookie来存储token，你可以根据实际情况进行修改
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    // 在这里根据token进行登录状态的验证
                    // 如果验证通过，返回true
                    // 否则返回false
                    // 示例中使用了一个名为"your_valid_token"的字符串来表示有效的token，你可以根据实际情况进行修改
                    return tokenUtil.validateToken(token);
                }
            }
        }
        return false;
    }

}

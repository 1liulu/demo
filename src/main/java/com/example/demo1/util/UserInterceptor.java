package com.example.demo1.util;

import com.alibaba.fastjson.JSON;
import com.example.demo1.bean.Account;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Logger;

public class UserInterceptor implements HandlerInterceptor {
    final Logger logger = Logger.getLogger("org.jediael.crawl.MyCrawler");
    private static final String urlMatcher = "\\S*(login)S*$";
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
   //     logger.info("Begin Crawling, Good Luck!");
//        System.out.println(httpServletRequest.getRequestURL());
//        System.out.println(httpServletRequest.getQueryString());
        //跨域访问设置
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET,OPTIONS");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Authentication,Origin, X-Requested-With, Content-Type, Accept,token");
        httpServletResponse.setCharacterEncoding("utf-8");
        String requestUrl = httpServletRequest.getServletPath();
        if (requestUrl.matches(urlMatcher) || requestUrl.indexOf("swagger") != -1 || requestUrl.contains("v2")) {
            return true;
        }else{
            httpServletResponse.setContentType("application/json; charset=utf8");
            String token = httpServletRequest.getParameter("token");
            if (StringUtils.isBlank(token)) {
                Map resultMap = MsgBuilder.buildReturnErrorMessage("缺少token，请重试！");
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write(JSON.toJSONString(resultMap));
                writer.flush();
                writer.close();
            }else {
                String tokenCheckResult = Token.verifyToken(token);
                if (Constant.GENERATE_TOKEN_ERROR.equals(tokenCheckResult)) {
                    Map resultMap = MsgBuilder.buildReturnErrorMessage("token信息有误，请重试！");
                    PrintWriter writer = httpServletResponse.getWriter();
                    writer.write(JSON.toJSONString(resultMap));
                    writer.flush();
                    writer.close();
                } else {
                    Account account = Token.getDataByTokenSubject(tokenCheckResult);
                    httpServletRequest.setAttribute("account", account);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

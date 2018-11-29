/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-15 下午3:43
 * LastModified: 18-11-15 下午3:43
 */

package cn.com.benny.learn.interceptors;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/11/15
 * @Time: 15:43
 */
@ComponentScan
public class LoginInterceptor extends HandlerInterceptorAdapter {


    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ServletInputStream inputStream = request.getInputStream();


        return true;
    }

}

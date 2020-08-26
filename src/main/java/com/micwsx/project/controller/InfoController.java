package com.micwsx.project.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Michael
 * @create 8/26/2020 11:26 AM
 * 打印请求数据(请求url,method,header,parameters)
 */
@RestController
@RequestMapping("/info")
public class InfoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/content")
    public HashMap<String, Object> content(HttpServletRequest request) {

        HashMap<String, Object> hashMap = new HashMap<>();
        String uri = request.getRequestURI();
        StringBuffer url = request.getRequestURL();
        String method = request.getMethod();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String contentType = request.getContentType();
        hashMap.put("RequestURI", uri);
        hashMap.put("RequestURL", url.toString());
        hashMap.put("Method", method);
        hashMap.put("ContextPath", contextPath);
        hashMap.put("ServletPath", servletPath);
        hashMap.put("ContentType", contentType);
        // 打印请求method,请求头数据
        HashMap<String, Object> headHash = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement();
            String value = request.getHeader(s);
            String head = s + ":" + value;
            headHash.put(s, value);
        }
        hashMap.put("header", headHash);
        // xml
        if (!StringUtils.isEmpty(contentType)&&contentType.contains("xml")) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream(), "utf-8")) {
                StringBuffer requestXml = new StringBuffer();
                char[] buffer = new char[1024];
                int read = 0;
                while ((read=inputStreamReader.read(buffer, 0, buffer.length)) > 0) {
                    requestXml.append(buffer,0,read);
                }
                hashMap.put("parameters", requestXml.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            // 打印请求参数
            HashMap<String, Object> paramHash = new HashMap<>();
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paraName = parameterNames.nextElement();
                String[] parameterValues = request.getParameterValues(paraName);
                paramHash.put(paraName, String.join(",", parameterValues));
            }
            hashMap.put("parameters", paramHash);
        }
        logger.info(JSONObject.toJSON(hashMap).toString());
        return hashMap;
    }


}

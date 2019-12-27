package com.sky.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 请求日志记录
 */
@Slf4j
public class RequestLogRecordFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //可缓存流中的数据
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        requestLog(requestWrapper);
        responseLog(responseWrapper);
        //流中的body只能使用一次，使用后需重新写入一份回去
        responseWrapper.copyBodyToResponse();

    }


    //请求日志打印
    private void requestLog(ContentCachingRequestWrapper requestWrapper) {
        try {
            String requestBody = IOUtils.toString(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //响应日志打印
    private void responseLog(ContentCachingResponseWrapper responseWrapper) {
        try {
            String responseBody = IOUtils.toString(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

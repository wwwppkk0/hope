package com.sky.common.filter;

import lombok.Data;
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
 *
 * 请求日志记录
 * */
@Data
@Slf4j
public class RequestLogRecordFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);

        filterChain.doFilter(requestWrapper,responseWrapper);


    }


    private void requestLog(ContentCachingRequestWrapper requestWrapper) {
        try {
            String requestBody = IOUtils.toString(requestWrapper.getContentAsByteArray(),requestWrapper.getCharacterEncoding());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void responseLog(ContentCachingResponseWrapper responseWrapper){

    }

}

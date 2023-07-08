//package com.exam.Filter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JWTFilter extends GenericFilterBean {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest httpReq = (HttpServletRequest) request;
//        HttpServletResponse httpRes = (HttpServletResponse) response;
//
//        String authHeader = httpReq.getHeader("authorization");
//
//        if(authHeader==null || !authHeader.startsWith("Bearer"))
//        {
//            throw new ServletException("Missing or invalid authentication header");
//        }
//
//        String jwtToken = authHeader.substring(7);
//        Claims claims = Jwts.parser().setSigningKey("secret key").parseClaimsJws(jwtToken).getBody();
//        httpReq.setAttribute("userName", claims);
//        chain.doFilter(request,response);
//    }
//}

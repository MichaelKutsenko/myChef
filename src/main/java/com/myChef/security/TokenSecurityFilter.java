/*
 * 
 * 
 */
package com.myChef.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 * @author al
 */
public class TokenSecurityFilter extends  OncePerRequestFilter{
    public final String AUTH_HTTP_HEADER ="X-Authorization";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    TokenProvider tokenPtovider; 
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader(AUTH_HTTP_HEADER);
        //if(SecurityContextHolder.getContext().getAuthentication() == null)
        AuthUser u = tokenPtovider.get(authToken);
        if(u!=null){
           logger.debug("Token found, it belongs to: "+u.getUsername());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("authenticated user " + u.getUsername() + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
         
        }else{
                logger.info("authentication failed, may be token is expired");
        }
        //we must call all filters in the chain
        filterChain.doFilter(request, response);        
    }

    
}

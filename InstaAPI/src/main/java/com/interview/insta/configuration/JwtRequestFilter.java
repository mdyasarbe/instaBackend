package com.interview.insta.configuration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.insta.entity.ErrorResponse;
import com.interview.insta.exception.ExceptionController;
import com.interview.insta.service.JwtService;
import com.interview.insta.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtRequestFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExceptionController resolver;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // Manually get an instance of MyExceptionController
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());

        // MyExceptionHanlder is now accessible because I loaded it manually
        this.resolver = ctx.getBean(ExceptionController.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        final String requestTokenHeader = req.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtil.getUsernameFromToken(jwtToken);
            } catch (SignatureException e) {
                log.error("Spring Security Filter Chain Exception ", e.getLocalizedMessage());
                ErrorResponse error = resolver.BadRequest(request, e);
                res.setStatus(error.getStatuscode().value());
                res.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                PrintWriter out = res.getWriter();
                out.print(mapper.writeValueAsString(error));
                out.flush();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("JWT token start with Bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = jwtService.loadUserByUsername(username);

            try {
                if (jwtUtil.validateToken(jwtToken, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);

    }

}

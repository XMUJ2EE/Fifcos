package xmu.crms.security;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import xmu.crms.util.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author mads
 * @date 2017/12/21 21:46
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    private String tokenHeader = "Authorization";

    private String tokenHead = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            System.out.println("auth header"+authHeader);
            final String authToken = authHeader.substring(tokenHead.length());
            try {
                String username = jwtTokenUtil.getUsernameFromToken(authToken);

                logger.info("checking authentication " + username);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

                    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                        String type;
                        if (userDetails.getType() == 0) {
                            type = "student";
                        } else {
                            type = "teacher";
                        }
                        List<SimpleGrantedAuthority> simpleGrantedAuthority = (List<SimpleGrantedAuthority>) userDetails.getAuthorities();
                        if(simpleGrantedAuthority == null){
                            logger.info("no role ");
                        }
                        FifcosAuthenticationToken authentication = new FifcosAuthenticationToken(userDetails.getNumber(),
                                userDetails.getPhone(),
                                userDetails.getPassword(),
                                type,
                                userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                                httpServletRequest));
                        logger.info("authenticated user " + username + ", setting security context");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (Exception e) {
                logger.error("UserNotFound,", e);
            }
        }

        doFilter(httpServletRequest, httpServletResponse, filterChain);
    }
}

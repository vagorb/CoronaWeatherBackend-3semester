package ee.taltech.iti02032020.backend.security;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER_ = "Bearer ";

    @Resource
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwtToken = getToken(request);
        if (jwtToken == null) {
            filterChain.doFilter(request, response);
            return;
        }
        String username = getUsername(jwtToken);
        if (username == null) {
            filterChain.doFilter(request, response);
            return;
        }
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
//            if (jwtTokenProvider.validateToken(jwtToken, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
        }
        filterChain.doFilter(request, response);
    }


    private String getUsername(String jwtToken) {
        try {
            return jwtTokenProvider.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            log.error("Unable to get JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.error("JWT Token has expired", e);
        }
        return null;
    }


    private String getToken(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader(AUTHORIZATION);
        if (requestTokenHeader == null || !requestTokenHeader.startsWith(BEARER_)) {
            return null;
        }
        return requestTokenHeader.substring(7);
    }
}

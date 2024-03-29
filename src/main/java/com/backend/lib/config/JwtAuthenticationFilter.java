package com.backend.lib.config;

import com.backend.lib.Service.JWTService;
import com.backend.lib.Service.UserAuthService;
import com.backend.lib.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserAuthService userAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //here we will validate jwt token for every api call
        //api req, and res we are going to return and filterchain is not used here
        final String authHeader = request.getHeader("Authorization");
        //from the api request we will get Header = Authorization

        final String jwt;
        final String email;
        //here these String not going to change and we are adding value after validation

        if (StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")) {
            //our authheader is null and dont start with "Bearer sfiwiadic..."
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        email = jwtService.extractUserName(jwt);

        if (StringUtils.isNotEmpty(email) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userAuthService.userDetailsService().loadUserByUsername(email);
            //here we are passing a email found from loadUsername() in userService which uses userRepo to get the email
            if (jwtService.isTokenValid(jwt, userDetails)) {
                //need an empty context to store the token with all details of request
                //and this will hold the authentication information of the user
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                //this token is created using userDetails(email), and null password as null as token dont want password and user authorities are also stored in the token
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //The details of the authentication token, such as the remote address and session ID, are set using WebAuthenticationDetailsSource().buildDetails(request).
                //This provides information about the current request
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //This associates the authenticated user with the current security context.
                securityContext.setAuthentication(token);

                //SecurityContextHolder, which manages the security context for the current thread. This ensures that subsequent security-related operations within the thread
                // have access to the authentication information
                SecurityContextHolder.setContext(securityContext);


            }
        }
        //This allows other filters in the chain to process the request, or for the request to reach the servlet
        // if there are no more filters to process.
        filterChain.doFilter(request, response);

    }

}

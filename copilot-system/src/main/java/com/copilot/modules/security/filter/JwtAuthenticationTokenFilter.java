package com.copilot.modules.security.filter;

import cn.hutool.core.util.ObjectUtil;
import com.copilot.modules.security.bean.SecurityUser;
import com.copilot.modules.security.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @program: copilot-server
 * @description: TODO
 * @author: hfli8
 * @create: 2023/4/19 10:38
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        SecurityUser securityUser = jwtUtil.getUserFromToken(request);
        if (ObjectUtil.isNotNull(securityUser)) {
//            Set<String> permissions = userService.findPermsByUserId(securityUser.getUserId());
            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUser, null, authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }
}

package com.copilot.modules.security.handle;

import com.copilot.modules.security.bean.UsernamePasswordAuthentication;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @program: copilot-server
 * @description: TODO
 * @author: hfli8
 * @create: 2023/4/14 17:04
 */
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UsernamePasswordAuthentication passwordAuthentication = (UsernamePasswordAuthentication) authentication;
        if (passwordAuthentication.getCredentials() == null || passwordAuthentication.getCurrentTimestamp() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            // 校验登录时间是否过期
            if (System.currentTimeMillis() - passwordAuthentication.getCurrentTimestamp() > 60000) {
                throw new BadCredentialsException("登录时间已过期");
            }
            String presentedPassword = passwordAuthentication.getCredentials().toString();
            if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword() + passwordAuthentication.getCurrentTimestamp())) {
                this.logger.debug("Failed to authenticate since password does not match stored value");
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
        }
    }
}

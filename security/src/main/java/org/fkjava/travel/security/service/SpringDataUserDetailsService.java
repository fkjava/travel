package org.fkjava.travel.security.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.fkjava.travel.identity.domain.Role;
import org.fkjava.travel.identity.domain.User;
import org.fkjava.travel.identity.service.IdentityService;
import org.fkjava.travel.security.domain.SimpleUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    private IdentityService identityService;

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.identityService.getUserByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException(this.messages.getMessage("JdbcDaoImpl.notFound",
                    new Object[] { username }, "Username {0} not found"));
        }

        List<Role> roles = this.identityService.getRolesByUserId(user.getId());

        Collection<GrantedAuthority> authorities = new HashSet<>();

        roles.forEach(r -> {
            SimpleGrantedAuthority a = new SimpleGrantedAuthority(r.getRoleKey());
            authorities.add(a);
        });
        // 无论如何，都加入一个ROLE_USER的角色，这样所有用户在登录成功以后，都具有最基本的USER权限了。
        SimpleGrantedAuthority a = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(a);

        SimpleUserDetails details = new SimpleUserDetails(user, authorities);

        return details;
    }
}

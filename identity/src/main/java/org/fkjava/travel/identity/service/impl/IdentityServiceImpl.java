package org.fkjava.travel.identity.service.impl;

import java.util.List;
import java.util.Optional;

import org.fkjava.travel.identity.dao.UserDao;
import org.fkjava.travel.identity.domain.Role;
import org.fkjava.travel.identity.domain.User;
import org.fkjava.travel.identity.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IdentityServiceImpl implements IdentityService {

    @Autowired
    private UserDao userDao;
//    @Autowired
//    private RoleDao roleDao;

    @Override
    @Transactional(readOnly = true)
    public User getUserByLoginName(String loginName) {
        Optional<User> o = this.userDao.findByLoginName(loginName);
        User user = o.orElse(null);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getRolesByUserId(String userId) {
        User user = this.userDao.getOne(userId);
        List<Role> roles = user.getRoles();
        return roles;
    }

}

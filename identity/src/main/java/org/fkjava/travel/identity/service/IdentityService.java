package org.fkjava.travel.identity.service;

import java.util.List;

import org.fkjava.travel.identity.domain.Role;
import org.fkjava.travel.identity.domain.User;

public interface IdentityService {

    User getUserByLoginName(String loginName);

    List<Role> getRolesByUserId(String userId);
}

package org.fkjava.travel.identity.dao;

import java.util.Optional;

import org.fkjava.travel.identity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String> {

    Optional<User> findByLoginName(String loginName);

}

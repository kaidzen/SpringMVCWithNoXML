package org.hardcrystal.springmvc.dao;

import org.hardcrystal.springmvc.model.User;

import java.util.List;

/**
 * Created by Administrator on 21.01.2016.
 */
public interface UserDao {
    User findById(int id);
    User findBySSO(String sso);
    void save(User user);
    void deleteBySSO(String sso);
    List<User> findAllUsers();
}

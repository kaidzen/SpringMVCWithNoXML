package org.hardcrystal.springmvc.service;

import org.hardcrystal.springmvc.model.User;

import java.util.List;

/**
 * Created by Administrator on 21.01.2016.
 */
public interface UserService {

    User findById(int id);
    User findBySSO(String sso);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserBySSO(String sso);
    List<User> findAllUsers();
    boolean isUserSSOUnique(Integer id, String sso);
}

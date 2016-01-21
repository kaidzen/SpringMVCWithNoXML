package org.hardcrystal.springmvc.service;

import org.hardcrystal.springmvc.dao.UserDao;
import org.hardcrystal.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 21.01.2016.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public User findById(int id) {
        return dao.findById(id);
    }

    public User findBySSO(String sso) {
        return dao.findBySSO(sso);
    }

    public void saveUser(User user) {
        dao.save(user);
    }

    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if (entity != null){
            entity.setSsoId(user.getSsoId());
            entity.setEmail(user.getEmail());
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setPassword(user.getPassword());
            entity.setUserProfiles(user.getUserProfiles());
        }
    }

    public void deleteUserBySSO(String sso) {
        dao.deleteBySSO(sso);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySSO(sso);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }
}

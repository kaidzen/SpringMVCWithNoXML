package org.hardcrystal.springmvc.dao;

import org.hardcrystal.springmvc.model.User;
import org.hardcrystal.springmvc.model.UserProfile;

import java.util.List;

/**
 * Created by Administrator on 21.01.2016.
 */
public interface UserProfileDao {
    List<UserProfile> findAll();
    UserProfile findByType(String type);
    UserProfile findById(int id);
}

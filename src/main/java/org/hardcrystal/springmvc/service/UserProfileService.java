package org.hardcrystal.springmvc.service;

import org.hardcrystal.springmvc.model.UserProfile;

import java.util.List;

/**
 * Created by Administrator on 21.01.2016.
 */
public interface UserProfileService {

    UserProfile findById(int id);
    UserProfile findByType(String type);
    List<UserProfile> findAll();
}

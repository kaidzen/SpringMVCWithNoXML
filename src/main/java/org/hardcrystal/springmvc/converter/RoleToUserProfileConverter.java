package org.hardcrystal.springmvc.converter;

import org.hardcrystal.springmvc.model.UserProfile;
import org.hardcrystal.springmvc.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 21.01.2016.
 * Class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile>{

    @Autowired
    UserProfileService userProfileService;

    /**
     * Gets UserProfile by  Id
     * @param element
     * @return
     */
    public UserProfile convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        UserProfile profile = userProfileService.findById(id);
        System.out.println("Profile : " + profile );
        return profile;
    }
}

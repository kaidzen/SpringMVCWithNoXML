package org.hardcrystal.springmvc.controller;

import com.sun.javafx.sg.prism.NGShape;
import org.hardcrystal.springmvc.model.User;
import org.hardcrystal.springmvc.model.UserProfile;
import org.hardcrystal.springmvc.service.UserProfileService;
import org.hardcrystal.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 21.01.2016.
 */
@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    //List all existing users
    @RequestMapping(value = { "/", "/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model){
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    //Provide the medium to add a new user
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    //Will be called on form submission, handling POST for saving in db. Also validates user input
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, ModelMap model){
        if (result.hasErrors()){
            return "registration";
        }

        /*
        * To achive uniqueness of field [sso] should be implementing custom @Unique annotation
        * and applying it on field [sso] on Model class [User]
        *
        * Followed code allow fill custom errors outside the validation framework as well while
        * still using internationalized messages.
        */
        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("not.unique.ssoId",
                    new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }
        userService.saveUser(user);

        model.addAttribute("success", "User" + user.getFirstName() + " " + user.getLastName() + " registered successfully");

        return "registrationsuccess";
    }
    /**
     * Method will provide the medium to update an existing user
     */
    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model){
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

    /**
     * Method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId){
        if (result.hasErrors()){
            return "registration";
        }

        /*//Uncomment if WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
            if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new
                                String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }*/
        userService.updateUser(user);

        model.addAttribute("success", "User" + user.getFirstName() + " " + user.getLastName() + " updated successfully");

        return "registrationsuccess";
    }

    /**
    * Method delete an user by ssoId.
    */
    @RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId){
        userService.deleteUserBySSO(ssoId);
        return "redirect:/list";
    }

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles(){
        return userProfileService.findAll();
    }
}

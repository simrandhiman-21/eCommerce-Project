package com.ecomm.application.service;

import com.ecomm.application.UserRole;
import com.ecomm.application.beans.UserModelMapper;
import com.ecomm.application.beans.UserRequestBean;
import com.ecomm.application.beans.UserResponseBean;
import com.ecomm.application.entity.User;
import com.ecomm.application.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  UserModelMapper userModelMapper;

    /*
    //Crud using List<User>
    ArrayList<User> userlist=new ArrayList<User>();

    public void saveUser(User user){
        userlist.add(user);
        userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userlist;
    }

    public Optional<User> findByUserId(Long id){
        /* for(User user:userlist){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
        return userlist.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public boolean updateuser(Long id, User updateduser){
        for(User existinguser:userlist){
            if(existinguser.getId().equals(id)){
                existinguser.setFirstname(updateduser.getFirstname());
                existinguser.setLastname(updateduser.getLastname());
                return true;
            }
        }
        return false;
    }


    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
    */

    public void createUser(UserRequestBean user){
        User newuser=userModelMapper.userMapper(user);
        if (newuser.getRole() == null) {
            newuser.setRole(UserRole.CUSTOMER);
        }
        userRepository.save(newuser);
    }

    public List<UserResponseBean> getAllUsers(){
        List<User> userlist=userRepository.findAll();
        List<UserResponseBean> userResponseBeanList=new ArrayList<>();
        for(User user:userlist) {
            userResponseBeanList.add(userModelMapper.userResponseMapper(user));
        }
        return userResponseBeanList;
    }

    public UserResponseBean getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userModelMapper.userResponseMapper(user);

    }

    public boolean updateUser(Long id, User updateduser){
        //findById() returns an Optional<User>, not a User.
        Optional<User> existinguser=userRepository.findById(id);
        if(existinguser.isPresent()) {
            User user=existinguser.get();

            user.setFirstname(updateduser.getFirstname());
            user.setLastname(updateduser.getLastname());
            user.setEmail(updateduser.getEmail());
            user.setPhone(updateduser.getPhone());
            userRepository.save(user);

            return true;
        }
        return false;
    }


    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

}

package com.ecomm.application.service;

import com.ecomm.application.entity.User;
import com.ecomm.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public void createUser(User user){
        System.out.println(user.getRole());
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
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

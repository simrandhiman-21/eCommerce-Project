package com.ecomm.application;

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
        return null; */
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

}

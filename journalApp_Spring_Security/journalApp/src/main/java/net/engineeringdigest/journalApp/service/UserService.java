package net.engineeringdigest.journalApp.service;


import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;

import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //spring security
    private static final PasswordEncoder passwordencoder=new BCryptPasswordEncoder();


    //for logger
//    private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    public boolean  saveNewEntry (User user)
    {
try{
    user.setPassword(passwordencoder.encode(user.getPassword()));
    user.setRoles(Arrays.asList("USER"));

    userRepository.save(user);
    return true;
}
catch(Exception e){
//for logger

//    logger.error("Error occured for {}",user.getUserName(),e);
//    logger.warn("hiii");
//    logger.info("hiii");
//    logger.debug("hiii");
//    logger.trace("hiii");

    log.error("Error occured for {}",user.getUserName(),e);
//    logger.warn("hiii");

    return false;
}
        //spring security


    }

    //spring security
    public void saveUser(User user){
        userRepository.save(user);
//        logger.info("hiii");
    }





    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){

        return userRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
      return userRepository.findByUserName(userName);
    }
}

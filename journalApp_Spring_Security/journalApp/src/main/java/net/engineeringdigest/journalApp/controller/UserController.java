package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private WeatherService weatherService;


    //for spring security commented
//@GetMapping
//public List<User> getAllUsers(){
//   return  userService.getAll();
//}

    //for spring security commented
//@PostMapping
//public void createUser(@RequestBody User user){
// userService.saveEntry(user);
//}

//@PutMapping("/{userName}")
//public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName) {
//    User userInDb = userService.findByUserName(userName);
//if(userInDb!=null){
//
//    userInDb.setUserName(user.getUserName());
//    userInDb.setPassword(user.getPassword());
//    userService.saveEntry(userInDb);
//
//
//}
//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//}

    //for spring security
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    for API Integration in Spring Boot
@GetMapping
public ResponseEntity<?> greeting() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    WeatherResponse weatherResponse = weatherService.getweather("Mumbai");
String greeting="";;
    if(weatherResponse !=null){
        greeting=",weather feels like"+weatherResponse.getCurrent();
    }
    return new ResponseEntity<>("Hiiii " +authentication.getName()+greeting,HttpStatus.OK);
}


}




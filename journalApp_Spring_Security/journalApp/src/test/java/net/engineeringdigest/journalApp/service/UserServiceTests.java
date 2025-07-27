//package net.engineeringdigest.journalApp.service;
//
//import net.engineeringdigest.journalApp.entity.User;
//import net.engineeringdigest.journalApp.repository.UserRepository;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//public class UserServiceTests {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//
////    @Disabled
////    @Test
////    public void testfindByUserName(){
//////       assertEquals(4,2+2);
//////        assertNotNull(userRepository.findByUserName("vipul"));
////        User user = userRepository.findByUserName("vipul");
////        assertTrue(!user.getJournalEntries().isEmpty());
//////        assertTrue(5>3);
////    }
//
//
////    @Disabled
////    @ParameterizedTest
////    @CsvSource({
////         //"a,b,expected"
////            "1,1,2",
////            "5,5,10",
////            "3,3,9"
////    })
////    public void test(int a,int b,int expected){
////        assertEquals(expected,a+b);
////    }
//
////    @Disabled
////    @ParameterizedTest
////    @CsvSource({
////            "ram",
////            "preeti",
////            "vipul"
////    })
////    public void testfindByUserNameNew(String name){
////       assertNotNull(userRepository.findByUserName(name),"failed for :" +name) ;
////    }
//
//
////    @ParameterizedTest
////    @ArgumentsSource(UserArgumentsProvider.class)
////    public void testsaveNewUser(User user) {
////      assertTrue(userService.saveNewEntry(user));
////    }
//}

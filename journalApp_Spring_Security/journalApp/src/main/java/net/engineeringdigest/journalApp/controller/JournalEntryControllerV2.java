package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
   private UserService userService;

    //for Journal
//    @GetMapping
//    public List<JournalEntry> getAll() {
//
//        return journalEntryService.getAll();
//    }

//    @PostMapping
//    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
//        myEntry.setDate(LocalDateTime.now());
//        journalEntryService.saveEntry(myEntry);
//        return myEntry;
//    }

//    @GetMapping("id/{myId}")
//    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
//
//       return  journalEntryService.findById(myId).orElse(null);
//    }

//    @DeleteMapping("id/{myId}")
//    public boolean deleteJournalEntryById(@PathVariable ObjectId myId) {
//
//        journalEntryService.deleteById(myId);
//        return true;
//    }

//    @PutMapping("/id/{id}")
//    public JournalEntry putJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
//
//
//        JournalEntry old =journalEntryService.findById(id).orElse(null);
//
//    if(old !=null){
//     old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle(): old.getTitle());
//     old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
//    }
//
//
//        journalEntryService.saveEntry(old);
//        return old;
//    }


//for Relationship in spring Boot


//    @Autowired
//    private UserService userService;
//
//
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {

        //for spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();



        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            //User user = userService.findByUserName(userName);

            //for spring security
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();


            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {


        //for spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userService.findByUserName(userName);

        //for spring security
      List<JournalEntry> collect=  user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
if(!collect.isEmpty()){
    Optional<JournalEntry> journalEntry=journalEntryService.findById(myId);
    if(journalEntry.isPresent()){
        return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
    }
}

return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       // return journalEntryService.findById(myId).orElse(null);
    }
//
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {

        //for spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

      boolean removed=  journalEntryService.deleteById(myId,username);
      if(removed){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
        else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> putJournalEntryById(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntry newEntry) {

//for spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userService.findByUserName(userName);

        //for spring security
        List<JournalEntry> collect=  user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry=journalEntryService.findById(myId);
            if(journalEntry.isPresent()){
                JournalEntry old = journalEntry.get();
                // return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
                    old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle(): old.getTitle());
                    old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
                    journalEntryService.saveEntry(old);
                    return new ResponseEntity<>(old,HttpStatus.OK);

            }
        }


       // JournalEntry old =journalEntryService.findById(myId).orElse(null);


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//
//}
}
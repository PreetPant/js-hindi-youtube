package net.engineeringdigest.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection="users")
@Data
@Builder
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique=true)
    @NonNull
    private String userName;
    @NonNull
    private String password;


//    for email send
    private String email;
    private boolean sentimentAnalysis;

    //for db link and user journal communication
    @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();

    //for spring security  //user ke pas kya kya role hai
    private List<String> roles;

}
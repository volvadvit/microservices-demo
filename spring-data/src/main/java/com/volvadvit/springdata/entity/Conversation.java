package com.volvadvit.springdata.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "conversations", targetEntity = User.class)
    private Set<User> users;

    @DateTimeFormat
    Timestamp createdAt = Timestamp.from(Instant.now());

    @OneToMany(mappedBy = "conversation", targetEntity = Message.class, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Message> messages;

    private String name;
}

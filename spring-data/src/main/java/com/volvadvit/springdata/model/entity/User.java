package com.volvadvit.springdata.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Message.class)
    private List<Message> sentMessages;

    @ManyToMany
    @JoinTable(
            name = "user_conversations",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "conversation_id") })
    private Set<Conversation> conversations;
}


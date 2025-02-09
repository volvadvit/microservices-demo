package com.volvadvit.springdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String body;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    private Conversation conversation;

    @JsonIgnore
    public User getSender() {
        return sender;
    }
}

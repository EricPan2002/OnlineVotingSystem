package com.example.OnlineVotingSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "VOTE_ITEM")
@Data

public class VoteItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
}



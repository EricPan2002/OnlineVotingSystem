package com.example.OnlineVotingSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "VOTE_RECORD")
@Data

public class VoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "VOTER_NAME", nullable = false)
    private String voterName;

    @Column(name = "ITEM_ID", nullable = false)
    private Integer itemId;

}

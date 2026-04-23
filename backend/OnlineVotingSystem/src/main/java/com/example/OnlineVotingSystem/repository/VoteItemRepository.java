package com.example.OnlineVotingSystem.repository;

import com.example.OnlineVotingSystem.entity.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteItemRepository extends JpaRepository<VoteItem, Integer> {

} 

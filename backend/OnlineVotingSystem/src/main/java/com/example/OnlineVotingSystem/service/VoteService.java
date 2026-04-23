package com.example.OnlineVotingSystem.service;

import com.example.OnlineVotingSystem.entity.VoteItem;
import com.example.OnlineVotingSystem.repository.VoteItemRepository;
import com.example.OnlineVotingSystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteService {
 
    @Autowired // @Autowired will automatically inject the repository written for the Service to use
    private VoteItemRepository itemRepository;

    @Autowired
    private VoteRepository voteRepository;

    //get all vote items
    public List<VoteItem> getAllItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public void doVote(String voterName, List<Integer> itemIds) {
        // for each select item, call stored procedure once
        for (Integer itemId : itemIds) {
            voteRepository.callVoteProcedure(voterName, itemId);
        }
    }

}

package com.example.OnlineVotingSystem.service;

import com.example.OnlineVotingSystem.entity.VoteItem;
import com.example.OnlineVotingSystem.entity.VoteRecord;
import com.example.OnlineVotingSystem.repository.VoteItemRepository;
import com.example.OnlineVotingSystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    public VoteItem saveItem(VoteItem item) {
        return itemRepository.save(item);
    }

    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }

    public List<VoteItem> getCount() {
        List<VoteItem> items = itemRepository.findAll();
        List<VoteRecord> allRecords = voteRepository.findAll();

        for (VoteItem item : items) {
            long count = allRecords.stream()
                .filter(record -> record.getItemId() == item.getId())
                .count();
        
            item.setVoteCount((int) count);

        }    
        
        return items;
    }
}

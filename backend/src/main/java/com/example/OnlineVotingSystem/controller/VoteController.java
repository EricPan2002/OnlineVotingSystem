package com.example.OnlineVotingSystem.controller;

import com.example.OnlineVotingSystem.entity.VoteItem;
import com.example.OnlineVotingSystem.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController   //convert Java objects to JSON format
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class VoteController {

    @Autowired
    private VoteService voteService;

    /**
     * get all vote items
     * GET http://localhost:8080/api/items
     */
    @GetMapping("/items")
    public List<VoteItem> getVoteItems() {
        return voteService.getCount();
    }

    /**
     * execute voting
     * POST http://localhost:8080/api/vote
     * form：{"voterName": "小明", "itemIds": [1, 2]}
     */
    @PostMapping("/vote")
    public String doVote(@RequestBody Map<String, Object> payload) {
        
        String voterName = (String) payload.get("voterName");
        List<Integer> itemIds = (List<Integer>)payload.get("itemIds");

        // call service
        voteService.doVote(voterName, itemIds);

        return "投票成功！";
        
    }
    @PostMapping("/items")
    public VoteItem addItem(@RequestBody VoteItem item) {
        return voteService.saveItem(item);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable int id) {
        voteService.deleteItem(id);
    }

}

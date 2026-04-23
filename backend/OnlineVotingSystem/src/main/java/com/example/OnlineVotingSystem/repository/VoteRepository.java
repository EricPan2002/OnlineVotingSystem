package com.example.OnlineVotingSystem.repository;

import com.example.OnlineVotingSystem.entity.VoteRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface VoteRepository extends JpaRepository<VoteRecord, Integer> {

    /**
     * call stored procedure to execute voting
     * @param voterName 
     * @param itemId 
     */

    @Procedure(procedureName = "voting_procedure")
    void callVoteProcedure(@Param("voterName") String voterName, @Param("itemId") int itemId);
}

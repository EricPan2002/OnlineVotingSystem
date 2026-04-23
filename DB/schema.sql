-- voting item table
CREATE TABLE VOTE_ITEM (
    ID INT PRIMARY KEY AUTO_INCREMENT, 
    NAME VARCHAR(100) NOT NULL
);

-- voting record table
CREATE TABLE VOTE_RECORD (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    VOTER_NAME VARCHAR(50) NOT NULL,
    ITEM_ID INT,
    FOREIGN KEY (ITEM_ID) REFERENCES VOTE_ITEM(ID)
);

--stored procedure

CREATE ALIAS IF NOT EXISTS voting_procedure AS $$
void vote(Connection conn, String voterName, int itemId) throws SQLException {
    PreparedStatement ps = conn.prepareStatement("INSERT INTO VOTE_RECORD (VOTER_NAME, ITEM_ID) VALUES (?, ?)");
    ps.setString(1, voterName);
    ps.setInt(2, itemId);
    ps.executeUpdate();
}
$$;;

CREATE ALIAS IF NOT EXISTS query_votes AS $$
ResultSet getItems(Connection conn) throws SQLException {
    return conn.createStatement().executeQuery(
        "SELECT i.ID, i.NAME, COUNT(r.ITEM_ID) AS TOTAL_VOTES " +
        "FROM VOTE_ITEM i " +
        "LEFT JOIN VOTE_RECORD r ON i.ID = r.ITEM_ID " +
        "GROUP BY i.ID, i.NAME"
    );
}
$$;;



-- voting procedure
-- CREATE PROCEDURE voting_procedure(IN p_voter VARCHAR(50), IN p_item_id INT)
-- BEGIN
--     INSERT INTO VOTE_RECORD(VOTER_NAME, ITEM_ID)
--     VALUES (p_voter, p_item_id);
-- END ;

-- -- query votes
-- CREATE PROCEDURE query_votes()
-- BEGIN
--     SELECT i.ID, i.NAME, COUNT(r.ITEM_ID) AS TOTAL_VOTES
--     FROM VOTE_ITEM i
--     LEFT JOIN VOTE_RECORD r ON i.ID = r.ITEM_ID
--     GROUP BY i.ID, i.NAME;
-- END ;

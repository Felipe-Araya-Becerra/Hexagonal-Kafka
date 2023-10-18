package com.acl.hexagonal.infraestructure.out.adapters;

import com.acl.hexagonal.domain.Card;
import com.acl.hexagonal.infraestructure.out.ports.CardRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CardRepositoryImpl implements CardRepository {
    private final JdbcTemplate jdbcTemplate;

    public CardRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Card save(Card card) {
        String insertSql = "INSERT INTO cards (card_number, owner_name, balance) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertSql, card.getCardNumber(), card.getOwnerName(), card.getBalance());
        return card;
    }

    @Override
    public Card findByCardNumber(String cardNumber) {
        String selectSql = "SELECT card_number, owner_name, balance FROM cards WHERE card_number = ?";
        return jdbcTemplate.queryForObject(selectSql, new Object[]{cardNumber}, (rs, rowNum) -> {
            Card card = new Card(cardNumber, rs.getString("owner_name"), rs.getDouble("balance"));
            return card;
        });
    }

    @Override
    public List<Card> findAll() {
        String selectSql = "SELECT card_number, owner_name, balance FROM cards";
        return jdbcTemplate.query(selectSql, (rs, rowNum) -> {
            String cardNumber = rs.getString("card_number");
            String ownerName = rs.getString("owner_name");
            double balance = rs.getDouble("balance");
            return new Card(cardNumber, ownerName, balance);
        });
    }

    @Override
    public void update( String cardNumber, Card card) {
        String updateSql = "UPDATE cards SET owner_name = ?, balance = ? WHERE card_number = ?";
        jdbcTemplate.update(updateSql, card.getOwnerName(), card.getBalance(), cardNumber);
    }

    @Override
    public void delete(String cardNumber) {
        String deleteSql = "DELETE FROM cards WHERE card_number = ?";
        jdbcTemplate.update(deleteSql, cardNumber);
    }
}

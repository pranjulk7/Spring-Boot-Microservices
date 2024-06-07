package com.eazybytes.card.repository;

import com.eazybytes.card.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Card, Long>{
    Optional<Card> findByMobileNumber(String mobilenumber);



}

package com.ccservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccservice.entity.CreditCard;

/**
 * The repository class used to access db entities
 * @author avpra
 *
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, String>{

}

package com.ccservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ccservice.entity.CreditCard;
import com.ccservice.exception.InvalidCardException;
import com.ccservice.repository.CreditCardRepository;
import com.ccservice.vo.CreditCardVO;

@RunWith(SpringJUnit4ClassRunner.class)
public class CreditCardServiceTest {
	@Mock
	CreditCardRepository creditCardRepo;

	@InjectMocks
	CreditCardService creditCardService;


	@Test
	public void testGetAllCards() {
		ArrayList<CreditCard> arrayList = new ArrayList<CreditCard>();
		CreditCard card = new CreditCard();
		card.setCcNumber("123");
		card.setName("test card");
		card.setCreditBalance(123.00);
		card.setCreditLimit(200.00);
		arrayList.add(card);
		Mockito.when(creditCardRepo.findAll(Mockito.any(Sort.class))).thenReturn(arrayList);
		List<CreditCardVO> allCards = creditCardService.getAllCards();
		Assert.assertNotNull(allCards);
		Assert.assertTrue(arrayList.size() == allCards.size());

	}

	@Test
	public void testAddCard() {
		CreditCardVO card = new CreditCardVO();
		card.setCcNumber("123");
		card.setName("test card");
		card.setCreditLimit(0.00);
		card.setCreditBalance(0.00);
		Mockito.when(creditCardRepo.findById("123")).thenReturn(Optional.empty());
		Mockito.when(creditCardRepo.save(Mockito.any(CreditCard.class))).thenReturn(card.toCreditCard());
		try {
			CreditCardVO addCard = creditCardService.addCard(card);
			Assert.assertNotNull(addCard);
			Assert.assertTrue(addCard.getCcNumber().equals(card.getCcNumber()));
			Assert.assertTrue(addCard.getName().equals(card.getName()));
			Assert.assertTrue(addCard.getCreditLimit() == card.getCreditLimit());
		} catch (InvalidCardException e) {
			Assert.fail();
		}
	}

	@Test
	public void testDuplicateAddCardException() {
		CreditCardVO card = new CreditCardVO();
		card.setCcNumber("123");
		card.setName("test card");
		card.setCreditLimit(0.00);
		card.setCreditBalance(0.00);
		Mockito.when(creditCardRepo.findById("123")).thenReturn(Optional.of(card.toCreditCard()));
		Mockito.when(creditCardRepo.save(Mockito.any(CreditCard.class))).thenReturn(card.toCreditCard());
		try {
			creditCardService.addCard(card);
			Assert.fail();
		} catch (InvalidCardException e) {
			Assert.assertNotNull(e);
		}
	}

}

package com.ccservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccservice.common.CreditCardConstants;
import com.ccservice.entity.CreditCard;
import com.ccservice.exception.InvalidCardException;
import com.ccservice.repository.CreditCardRepository;
import com.ccservice.vo.CreditCardVO;

/**
 * The service class provides different services to the controller
 * @author avpra
 *
 */
@Service
public class CreditCardService {
	private Logger logger = LoggerFactory.getLogger(CreditCardService.class);
	@Autowired
	private CreditCardRepository creditCardRepo;

	/**
	 * The method returns the cards from DB
	 * @return List<CreditCardVO>
	 */
	@Transactional(readOnly = true)
	public List<CreditCardVO> getAllCards() {
		List<CreditCard> creditCards = creditCardRepo.findAll(new Sort(Sort.Direction.DESC, "createdTime"));
		List<CreditCardVO> creditCardVOs = new ArrayList<CreditCardVO>();
		for (CreditCard creditCard : creditCards) {
			creditCardVOs.add(creditCard.toCreditCardVO());
		}
		return creditCardVOs;

	}

	/**
	 * The method to add card to DB
	 * 
	 * @param creditCardVO
	 * @return CreditCardVO
	 * @throws InvalidCardException
	 */
	public CreditCardVO addCard(CreditCardVO creditCardVO) throws InvalidCardException{
		try {
			if (creditCardRepo.findById(creditCardVO.getCcNumber()).isPresent()) {
				throw new InvalidCardException(CreditCardConstants.DUPLICATE_CARD);
			}
			creditCardVO.setCreatedTime(new Date());
			return creditCardRepo.save(creditCardVO.toCreditCard()).toCreditCardVO();
		} catch (InvalidCardException e) {
			logger.error(e.getMessage(), e);
			throw new InvalidCardException(e.getMessage(), e);
		}
	}
}

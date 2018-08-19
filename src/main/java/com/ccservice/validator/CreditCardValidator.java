package com.ccservice.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ccservice.common.CreditCardConstants;
import com.ccservice.common.CreditCardUtil;
import com.ccservice.vo.CreditCardVO;

/**
 * The validator used to validate the input and rejects 
 * the request with appropriate error
 * @author avpra
 *
 */
@Component
public class CreditCardValidator implements Validator {
	private Logger logger = LoggerFactory.getLogger(CreditCardValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return CreditCardVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CreditCardVO creditCardVO = (CreditCardVO) target;
		// length check
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "invalid.name");
		if (!(creditCardVO.getCcNumber().length() > 15 && creditCardVO.getCcNumber().length() < 20)) {
			logger.error("Invalid Log Length");
			errors.reject(CreditCardConstants.INVALID_CARD_LENGTH);
		}
		if (creditCardVO.getCreditBalance() > 0) {
			logger.error("Invalid Card Balance");
			errors.reject(CreditCardConstants.INVALID_CARD_BALANCE);
		}
		if (!CreditCardUtil.isValidCCNumber(creditCardVO.getCcNumber())) {
			logger.error("Invalid Card Number");
			errors.reject(CreditCardConstants.INVALID_CARD_NUMBER);
		}
	}
}

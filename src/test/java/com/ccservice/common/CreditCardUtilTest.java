package com.ccservice.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CreditCardUtilTest {

	@Test
	public void testValidCreditCard() {
		Assert.assertTrue(CreditCardUtil.isValidCCNumber("4586890862091103632"));
		Assert.assertTrue(CreditCardUtil.isValidCCNumber("4586890862091103"));
	}
	@Test
	public void tstInvalidCard() {
		Assert.assertFalse(CreditCardUtil.isValidCCNumber("234123434"));
		Assert.assertFalse(CreditCardUtil.isValidCCNumber("4586890862091106"));
		
	}

}

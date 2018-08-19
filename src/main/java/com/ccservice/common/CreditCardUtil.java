package com.ccservice.common;

/**
 * @author avpra
 * Util class for the app
 *
 */
public interface CreditCardUtil {

	/**
	 * The method to validate the card number using Luhn 10
	 * 
	 * @param creditCardNumber
	 * @return boolean
	 */
	public static boolean isValidCCNumber(String creditCardNumber) {
		int length = creditCardNumber.length();
		int evenSum = 0;
		int oddSum = 0;
		for (int i = length - 1; i >= 0; i--) {
			int value = Character.getNumericValue(creditCardNumber.charAt(i));
			if (i % 2 == 0) {
				value *= 2;
				evenSum += value > 9 ? value - 9 : value;
			}else {
				oddSum += value;
			}
		}
		return ((oddSum + evenSum) % 10 == 0);
	}

}

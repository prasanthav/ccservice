package com.ccservice;

import com.ccservice.vo.CreditCardVO;
import com.google.gson.Gson;

public class Validator {
	public static void main(String[] args) {

		String input = "4586890862091103";
		// String input = "4586890862091103401";
		// String input = "4586890862091103712";
		// String input = "4586890862091103632";
		// String input = "4586890862091103590";
		int sum = 0;
		int length = input.length();

		for (int i = 0; i < length; i++) {
			int value = Character.getNumericValue(input.charAt(length - i - 1));
			System.out.println(value);
			if (i % 2 == 1) {
				value *= 2;
			}
			System.out.println(value);
			sum += value > 9 ? value - 9 : value;
		}
		System.out.println("sum is " + sum);
		sum = 0;

		for (int i = length - 1; i >= 0; i--) {
			int value = Character.getNumericValue(input.charAt(i));
			System.out.println(value);
			if (i % 2 == 0) {
				value *= 2;
			}
			System.out.println(value);
			sum += value > 9 ? value - 9 : value;
		}
		System.out.println("sum is " + sum);

		CreditCardVO card = new CreditCardVO();
		System.out.println(new Gson().toJson(card));
	}

}

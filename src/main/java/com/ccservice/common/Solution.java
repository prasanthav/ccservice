package com.ccservice.common;

public class Solution {

	
	
	public static long subString(String s) {
		long count = 0l;
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < i; j++) {
				String subStr = s.substring(j, i);
				if (subStr.length() > 1) {
					System.out.println(subStr);
					count++;
				}
			}
		}
		return count;
	}
	public static void main(String[] args) {
		String temp = "kincenvizh";
		long count = 0l;
		for (int i = 0; i < temp.length(); i++) {
			for (int j = 0; j < i; j++) {
				String subStr = temp.substring(j, i);
				if (subStr.length() > 1) {
					System.out.println(subStr);
					count++;
				}
			}
		}

		System.out.println(count);

	}
}

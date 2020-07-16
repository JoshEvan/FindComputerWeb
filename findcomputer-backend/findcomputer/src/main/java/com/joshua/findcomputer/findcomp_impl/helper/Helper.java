package com.joshua.findcomputer.findcomp_impl.helper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Helper {
	public static final String
		ITEM = "Item ",
		USER = "User ",
		SUCCESS = " SUCCESSFULLY ",
		FAIL = " FAILED TO BE",
		INSERTED = " INSERTED ",
		UPDATED = " UPDATED ",
		DELETED = " DELETED ",
		BUY = " BOUGHT ",
		REGISTERED = " REGISTERED ",
		NOTFOUND = " DOESN'T EXIST";

	public static String formatCurrency(BigDecimal amount){
		if(amount == null) amount = BigDecimal.ZERO;
		DecimalFormat kursIDR = (DecimalFormat) DecimalFormat.getCurrencyInstance();
		DecimalFormatSymbols rp = new DecimalFormatSymbols();
		rp.setCurrencySymbol("IDR ");
		rp.setMonetaryDecimalSeparator(',');
		rp.setGroupingSeparator('.');
		kursIDR.setDecimalFormatSymbols(rp);
		return kursIDR.format(amount);
	}

}

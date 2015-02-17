package pl.noname.stacjabenzynowa.util;

import java.math.BigDecimal;

public class BigDecimalGenerator {
	
	public static BigDecimal generate() {
		
		BigDecimal minRange = new BigDecimal(0.00);
		BigDecimal maxRange = new BigDecimal(500.00);
		BigDecimal range = maxRange.subtract(minRange);
		BigDecimal result = minRange.add(range.multiply(new BigDecimal(Math.random())));
		return result;
	}
	
	public static BigDecimal generate(BigDecimal maxRange) {
		
		BigDecimal minRange = new BigDecimal(0.00);
		BigDecimal range = maxRange.subtract(minRange);
		BigDecimal result = minRange.add(range.multiply(new BigDecimal(Math.random())));
		
		return result;
	}
	
	public static BigDecimal generate(BigDecimal maxRange, BigDecimal minRange) {
		
		BigDecimal range = maxRange.subtract(minRange);
		BigDecimal result = minRange.add(range.multiply(new BigDecimal(Math.random())));
		
		return result;
	}
}

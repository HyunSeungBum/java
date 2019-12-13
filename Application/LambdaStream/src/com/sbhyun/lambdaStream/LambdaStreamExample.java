package com.sbhyun.lambdaStream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/*
 * 
 */
public class LambdaStreamExample {
	
	private static final List<BigDecimal> prices = Arrays.asList(
			new BigDecimal("10"), new BigDecimal("30"), new BigDecimal("17"),
			new BigDecimal("20"), new BigDecimal("15"), new BigDecimal("18"),
			new BigDecimal("45"), new BigDecimal("12")
	);
	
	public static void main(String[] args) {
		/*
		 * 각 price 가 $20보다 높으면 10% 할인하고, 할인된 가격은 모두 더한다.
		 * 전통적인 코딩 방식
		 */
		{
			BigDecimal totalOfDiscountedPrices = BigDecimal.ZERO;

			for(BigDecimal price: prices) {
				// 각 price 값을 $20 보다 높은지 체크
				if (price.compareTo(BigDecimal.valueOf(20)) > 0) {
					// BigDecimal 에 multiply 연산자를 이용해 할인율을 적용.
					totalOfDiscountedPrices = totalOfDiscountedPrices.add(price.multiply(BigDecimal.valueOf(0.9)));
				}
			}
			
			System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
			
			/*
			 * 가변변수(mutable variable) 사용은 되도록이면 하지 말아야 한다.
			 * 여기서 가변변수는 totalOfDiscountedPrices 이다.
			 * 
			 * 코드 자체가 너무 자세한 내용까지 코딩되어 있어서 로우 레벨 형태의 코드다. 
			 * 이러한 코드는 개발자가 원하는 내용을 무조건 코드로 작성해야 한다.
			 */
		}
		
		/*
		 * Lambda 표현식
		 */
		{
			
			final BigDecimal totalOfDiscountedPrices2 = prices.stream()
					.filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0) // predicate
					.map(price -> price.multiply(BigDecimal.valueOf(0.9))) // Function<T, R>
					//.reduce(BigDecimal.ZERO, (t, u) -> t.add(u));
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			
			System.out.println("Total of discounted prices: " + totalOfDiscountedPrices2);
		}
	}

}

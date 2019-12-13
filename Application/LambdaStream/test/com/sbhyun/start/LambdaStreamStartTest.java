package com.sbhyun.start;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.sbhyun.lambdaStream.myConsumer;

@RunWith(JUnit4.class)
class LambdaStreamStartTest {

	private static List<Integer> myList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
	private Consumer<Integer> c2;
	private Consumer<Integer> c3;
	
	@Test
	public void testList() {	
		// traversing using for
		for(Integer i: myList) {
			System.out.println(i);
		}
		
		// traversing using Iterator
		Iterator<Integer> it = myList.iterator();
		while(it.hasNext()) {
			System.out.println("Iterator Value:" + it.next().toString());
		}
		
		assertEquals(myList.size(), 10);
		assertTrue(myList.contains(2));
	}
	
	@Test
	public void testForEachConsumer() {
		
		// Implement Consumer
		Consumer<Integer> c1 = new myConsumer();
		
		myList.forEach(c1);
		
		// anonymous
		c2 = new Consumer<Integer>() {

			@Override
			public void accept(Integer t) {
				// TODO Auto-generated method stub
				System.out.println("C2 Consumer anonymous class value::" + t);
			}
			
		};
		
		myList.forEach(c2);
		
		// FunctionInteface, anonymous
		myList.forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.println("forEach anonymous class value::" + t);
			}
		});
		
		// traversing with another Consumer inteface implementation
		c3 = (t) -> System.out.println("C3 Consumer accept::" + t);
		myList.forEach(c3);
		// traversing with lambda expression
		myList.forEach(t -> System.out.println("C3 Consumer accept::" + t));
		
		// traversing with method reference
		myList.forEach(System.out::println);
	}
 
}

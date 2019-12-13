package com.sbhyun.lambdaStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class LambdaStreamForeach {
	
	private static List<Integer> myList;
	private static Consumer<Integer> c2;
	private static Consumer<? super Integer> c3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		myList = new ArrayList<Integer>();	
		for (int i=0; i< 10; i++) {
			myList.add(i);
		}
		
		// 1
		// traversing using for
		{
			
			for(Integer i: myList) {
				System.out.println("for Value::" + i);
			}
			
		}
		
		// 2
		// traversing using Iterator
		{
			Iterator<Integer> it = myList.iterator();
			while(it.hasNext()) {
				Integer i = it.next();
				System.out.println("Iterator Value::" + i);
			}
		}
		
		// 3
		// traversing through Consumer interface implementation
		{
			// Implement Consumer
			Consumer<Integer> c1 = new myConsumer();
			myList.forEach(c1);
		}
		
		// 4
		// traversing through forEach method of Iterable with anonymous class
		{
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
			
		}
		
		// 5
		// traversing with another Consumer inteface implementation
		{ 
			c3 = (t) -> System.out.println("C3 Consumer accept::" + t);
			myList.forEach(c3);
		}
		
		// 6
		// traversing with lambda expression
		{
			myList.forEach(t -> System.out.println("C3 Consumer accept::" + t));
		}
		
		// 7
		// traversing with method reference
		{	
			myList.forEach(System.out::println);
		}
		
		// 8
		// declare stream
		{
			Stream<List<Integer>> st = Stream.of(myList);
			st.forEach(System.out::println);
			
			Stream<Integer> myst = myList.stream();
			myst.forEach(System.out::println);
				
		}
	}

}

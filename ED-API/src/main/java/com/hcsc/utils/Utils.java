package com.hcsc.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Utils {

	public static void main(String[] args) {
		
		List<Integer> asList = Arrays.asList(1,2,3,5);
		
		Stream<Integer> stream = asList.stream();
		
		Optional<Integer> findAny = stream.filter(i->i>0).findAny();
		
		long count = stream.count();
		
		System.err.println(findAny);
	}
}

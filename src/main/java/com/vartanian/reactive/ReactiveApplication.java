package com.vartanian.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.OptionalInt;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;


@SpringBootApplication
public class ReactiveApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(ReactiveApplication.class, args);
//	}

	public static void main(String[] args) {

//		int[] a = {1,5,7,7};
//		int[] b = {0,1,2,3};
//
//		int length = a.length;
//		int[] tmp = new int[length * 2];
//		int[] tmpCount = new int[50];
//
//		System.arraycopy(a, 0, tmp, 0, length);
//		System.arraycopy(b, 0, tmp, length, length);
//
//		Arrays.sort(tmp);
//		for (int value : tmp) {
//			tmpCount[value] = ++tmpCount[value];
//		}
//		int[] result = new int[length * 2];
//		int lastPosition = 0;
//		for (int index = 1; index <= tmpCount.length; index++) {
//			int i = tmpCount[index-1];
//			if (i == 0){
//				continue;
//			}
//			for (int countValue = 1; countValue <= i; countValue++) {
//				result[lastPosition++] = index-1;
//			}
//		}
//
//		System.out.println(Arrays.toString(result));



//		int[] array = {5,4,4,2,2,8};
////		int[] array = {1,2,3,4,3,3,2,1};
//
//		int[] ints = cutSticks(array);
//
//		System.out.println(Arrays.toString(ints));

		int cost_per_cut = 100;
		int metal_price = 10;
		int[] lengths = {26, 103, 59};

		int max = lengths[0];
		int maxProfit = 0;
		for(int i = 0 ; i < lengths.length; i++) {
			if(max < lengths[i]){
				max = lengths[i];
			}
		}
		for(int size = 1 ; size <= max; size++) {
			int profit = 0;
			for(int i = 0 ; i < lengths.length; i++) {
				if(size > lengths[i])
					continue;
				int currPrice = (lengths[i] / size) * metal_price * size; // current Rod price after cutting it.
				int cuts = lengths[i] % size == 0 ? (lengths[i] / size) - 1 : (lengths[i] / size); // Number of cuts depend on the length of rod.
				int currProfit = currPrice - cost_per_cut * cuts;
				if(currProfit > 0)
					profit += currProfit;
			}
			if(profit > maxProfit)
				maxProfit = profit;
		}
		System.out.println(maxProfit);
	}

	static int[] cutSticks(int[] lengths) {


		int arraySize = lengths.length;
		int[] tmp = new int[arraySize];
		int[] result = new int[10000];
		System.arraycopy(lengths, 0, tmp, 0, arraySize);

		int iteration = 0;
		int cutCount = 0;
		do {

			cutCount = 0;
			OptionalInt optionalInt = Arrays.stream(tmp).filter(value -> value != 0).min();
			if (!optionalInt.isPresent()) {
				break;
			}
			int min = optionalInt.getAsInt();
			for (int index = 0; index < arraySize; index++) {
				int value = tmp[index];
				if (value < 1){
					continue;
				}
				tmp[index] = value - min;
				cutCount++;
			}

			if (cutCount != 0){
				result[iteration] = cutCount;
				iteration++;
			}

		} while (cutCount != 0);

		int[] finalResult = new int[iteration];
		System.arraycopy(result, 0, finalResult, 0, iteration);
		return finalResult;
	}

}

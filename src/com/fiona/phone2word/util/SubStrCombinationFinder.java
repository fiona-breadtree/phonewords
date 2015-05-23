package com.fiona.phone2word.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubStrCombinationFinder {
	private final int BEGIN_POS = 0;

	/**
	 * Find all combinations of substring for an input string.
	 * For a given String s with length n, traverse from the end:
	 * (1) for the last one s[n-1], put it into result sets as a list.
	 * (2) for others s[m] (m is the current position) :
	 *     for every list in result: if the list is ["1","2"] and s[m]="3"
	 *     (a) create a new list and change the first element with:
	 *     			s[m] + element;  in this case, that is ["31", "2"] 
	 *     (b) and add s[m] to the first element of the list, 
	 *     			in this case, that is:["3", "1", "2"]
	 * @param input
	 * @return
	 */
	public List<List<String>> findAllCombinationNoRecursion(String input) {
		List<List<String>> combinations = new ArrayList<List<String>>();

		for (int index = input.length() - 1; index >= 0; index--) {
			String tmp = Character.toString(input.charAt(index));
			List<List<String>> newCombinations = new ArrayList<List<String>>();
			if (combinations.size() != 0) {
				for (List<String> combination : combinations) {
					List<String> newCombination = new ArrayList<String>(combination);
					String oldData = newCombination.remove(0);
					newCombination.add(BEGIN_POS, tmp + oldData);
					newCombinations.add(newCombination);
					combination.add(BEGIN_POS, tmp);
				}
				combinations.addAll(newCombinations);
			} else {
				combinations.add(new ArrayList<String>(Arrays.asList(tmp)));
			}
		}
	    
		return combinations;
	}
	
	
}

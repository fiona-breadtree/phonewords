package com.fiona.phone2word.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubStrCombinationFinder {
	
	private FoundCombinationCallback callback = null;
	private final int BEGIN_POS = 0;

	
	public SubStrCombinationFinder(FoundCombinationCallback callback) {
		super();
		this.callback = callback;
	}


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

	
	public void findCombinationsOneByOne(String input) {
		List<String> subStrings = splitString(input, 10);
		
		List<List<List<String>>> allSubCombinations = new ArrayList<List<List<String>>>();
		for (String subStr : subStrings) {
			List<List<String>> subCombinations = this.findAllCombinationNoRecursion(subStr);
			allSubCombinations.add(subCombinations);
		}

		int subStrNumber = subStrings.size();
		int[] cursors = new int[subStrNumber];
		for (int i = 0; i < subStrNumber; i++) {
			cursors[i] = 0;
		}
		int lastIndex = (subStrNumber - 1);
		
		boolean finished = false;
		while (!finished) {
			List<List<String>> strings = new ArrayList<List<String>>();
			for (int index = 0; index < allSubCombinations.size(); index++) {
				strings.add(allSubCombinations.get(index).get(cursors[index]));
			}
			
			for (int j = lastIndex; j >= 0; j--) {
				cursors[j]++;
				if (cursors[j] >= allSubCombinations.get(j).size()) {
					if (j == 0) {
						finished = true;
						break;
					}
					cursors[j] = 0;
				} else {
					break;
				}
			}

			List<List<String>> foundSubCombination = combineSubStrings(strings);
			for (List<String> foundResult : foundSubCombination) {
				if (callback != null) {
					callback.handle(foundResult);
				}
 			}
		}
	}


	private List<String> splitString(String input, int pace) {
		List<String> subStrings = new ArrayList<String>();

		if (input.length() > pace) {
			for (int i = 0; i < input.length(); i += pace) {
				int endPos = ((i+ pace) > input.length() ? input.length() : i + pace);
				subStrings.add(input.substring(i, endPos));
			}
		} else {
			subStrings.add(input);
		}
		return subStrings;
	}
	
	private List<List<String>> combineSubStrings(List<List<String>> strings) {
		if (strings == null || strings.size() == 0 || strings.size() == 1) {
			return strings;
		}

		List<List<String>> newCombinations = new ArrayList<List<String>>(Arrays.asList(strings.get(0)));
		for (int i = 1; i < strings.size(); i++) {
			List<List<String>> tmpCombinations = new ArrayList<List<String>>();

			for (List<String> newCombination : newCombinations) {
				tmpCombinations.add(combineTwo(newCombination, strings.get(i), true));
				tmpCombinations.add(combineTwo(newCombination, strings.get(i), false));
			}
			newCombinations = tmpCombinations;
		}
		return newCombinations;
	}
	
	private List<String> combineTwo(List<String> stringOne, List<String> string2, boolean isSeperated) {
		ArrayList<String> mergedList = new ArrayList<String>(stringOne);
		if (isSeperated) {
			mergedList.addAll(string2);
		} else {
			ArrayList<String> copiedString2 = new ArrayList<String>(string2);
			String lastElementOfStringOne = mergedList.remove(mergedList.size() - 1);
			String firstElementOfStringTwo = copiedString2.remove(0);
		
			mergedList.add(lastElementOfStringOne + firstElementOfStringTwo);
			mergedList.addAll(copiedString2);
		}
		return mergedList;
	}
}

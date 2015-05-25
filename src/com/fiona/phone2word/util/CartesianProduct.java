package com.fiona.phone2word.util;

import java.util.ArrayList;
import java.util.List;

/**
 * a Cartesian product is a mathematical operation which returns a set (or product set or simply product) 
 * from multiple sets.
 * 
 * @author fiona
 *
 */
public class CartesianProduct {
	
	public List<List<String>> process(List<List<String>> strsList, List<String> strings) {
		List<List<String>> results = new ArrayList<List<String>>();
		
		for (List<String> wordList : strsList ) {
			results.addAll(processTwoList(strings, wordList));
		}
		return results;
	}

	public List<List<String>> processTwoList(List<String> strings, List<String> wordList) {
		List<List<String>> results = new ArrayList<List<String>>();
		for (String string : strings) {
			List<String> newList = new ArrayList<String>(wordList);
			newList.add(string);
			results.add(newList);
		}
		return results;
	}

}

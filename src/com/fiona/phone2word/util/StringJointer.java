package com.fiona.phone2word.util;

import java.util.List;

public class StringJointer {
	public String join(List<String> convertedWords, String boundary) {
		StringBuilder sb = new StringBuilder();
		for (String word : convertedWords) {
			sb.append(word).append(boundary);
		}
		return sb.substring(0, sb.length() - boundary.length());
	}
}

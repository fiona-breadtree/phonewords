package com.fiona.phone2word.util;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FoundCombinationCallbackTestImpl implements
		FoundCombinationCallback {
	private StringJointer jointer = new StringJointer();
	private AtomicLong number = new AtomicLong(0);
	
	@Override
	public void handle(List<String> combination) {
		//System.out.println("Callback-----founded combination is: " + jointer.join(combination, "-"));
		number.addAndGet(1);
	}

	@Override
	public long getHandledNumber() {
		return number.get();
	}

	@Override
	public void excutionFinished() {
		System.out.println("Excution finished.");
	}

}

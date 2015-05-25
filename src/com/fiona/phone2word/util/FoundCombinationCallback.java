package com.fiona.phone2word.util;

import java.util.List;

public interface FoundCombinationCallback {
	public void handle(List<String> combination);
	public long getHandledNumber();
	public void excutionFinished();
}
package com.fiona.phone2word.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class FileReader {
	private static Logger log = Logger.getLogger(FileReader.class);
	private LineReaderCallBack callBack = null;

	public FileReader(LineReaderCallBack callBack) {
		super();
		this.callBack = callBack;
	}

	public void readFileByLine(String fileName) throws Exception {
		if (fileName == null || fileName.isEmpty()) {
			return;
		}
		
		FileInputStream fstream = null;
		BufferedReader br = null;
		try {
			fstream = new FileInputStream(fileName);
			br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			while ((strLine = br.readLine()) != null) {
				callBack.processLine(strLine);
			}
		} finally {
			try {
				if (fstream != null) {
					fstream.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}
}

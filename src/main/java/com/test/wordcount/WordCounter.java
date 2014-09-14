package com.test.wordcount;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

public class WordCounter {
	private Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
	private static String CONFIG_FILE = "wordcount.properties";
	private static String BASE_DIR;

	{
		try {
			Properties prop = new Properties();
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);
			prop.load(inputStream);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '" + CONFIG_FILE + "' not found in the classpath");
			}
			prop.load(inputStream);
			BASE_DIR = prop.getProperty("base.file.dir");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private int getCount(String word) {
		if (wordCountMap.containsKey(word)) {
			return wordCountMap.get(word);
		} else {
			return 0;
		}
	}

	public int getWordCount(String inputString) throws Exception {
		int wordCounts = 0;
		Collection<File> listOfFiles = FileUtils.listFiles(new File(BASE_DIR), new IOFileFilter() {

			public boolean accept(File file, String s) {
				return file.isFile();
			}

			public boolean accept(File file) {
				return file.isFile();
			}

		}, null);
		// call readWordFile for every file
		for (File file : listOfFiles) {
			readWordFile(file.getAbsolutePath());
		}
		if (wordCountMap.containsKey(inputString.toLowerCase())) {
			wordCounts = wordCountMap.get(inputString.toLowerCase());
		}

		return wordCounts;
	}

	public void readWordFile(String fileName) throws Exception {
		Scanner wordFile;
		String word;
		int count;

		try {
			File file = new File(fileName);
			wordFile = new Scanner(new FileInputStream(file));
		} catch (FileNotFoundException e) {

			throw new Exception("File does not exist" + fileName);
		}
		while (wordFile.hasNext()) {
			word = wordFile.next();
			count = getCount(word.toLowerCase()) + 1;
			wordCountMap.put(word.toLowerCase(), count);
		}
	}
}

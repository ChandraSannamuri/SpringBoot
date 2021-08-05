package com.j2c.restservice.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.opendevl.JFlat;

public class JsonToCsvUtil {

	public static void convertJsonToCsv(String jsonPath, String csvPath) throws IOException {
		String str = new String(Files.readAllBytes(Paths.get(jsonPath)));
		System.out.print("JSON File Content"+str);
		System.out.print("csvPath"+csvPath);
		
		JFlat flatMe = new JFlat(str);
		flatMe.json2Sheet().write2csv(csvPath);
	}

}
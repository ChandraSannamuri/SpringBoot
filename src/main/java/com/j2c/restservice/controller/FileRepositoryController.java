package com.j2c.restservice.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileRepositoryController {
	@Value("${fileStore.Path}")
	private String fileStorePathValue;

	public String exportJson(String fileName, String category, String subcategory) {
		System.out.println("Start of CatalogueRepositoryController.exportJson() method.");
		System.out.println("fileName: " + fileName + " ; category: " + category + " ; subcategory: " + subcategory);

		Long catgry_id = (long) 25;
		System.out.println("catgry_id : " + catgry_id);
		System.out.println("fileStorePathValue : " + fileStorePathValue
				+ " ; saveFileData.getExtension().toUpperCase(): " + "json".toUpperCase());
		System.out.println("File.separator : " + File.separator + " ; saveFileData.getSubCategory(): " + subcategory);
		String pathTillDevComp = fileStorePathValue + "json".toUpperCase() + File.separator + subcategory
				+ File.separator;

		String oldVersionInString = "3.0";

		String filePath = pathTillDevComp + fileName + "/" + oldVersionInString + "/" + fileName + ".json";
		System.out.println("FileRepositoryController.exportJson() method. reading resource - {}" + filePath);

		return filePath;
	}

	public String buildCsvFilePath(String fileName, String category, String subcategory) {
		System.out.println("fileName: " + fileName + " ; category: " + category + " ; subcategory: " + subcategory);

		Long catgry_id = (long) 25;
		System.out.println("catgry_id : " + catgry_id);
		System.out.println("fileStorePathValue : " + fileStorePathValue
				+ " ; saveFileData.getExtension().toUpperCase(): " + "json".toUpperCase());
		System.out.println("File.separator : " + File.separator + " ; saveFileData.getSubCategory(): " + subcategory);
		String pathTillDevComp = fileStorePathValue + "json".toUpperCase() + File.separator + subcategory
				+ File.separator;

		String oldVersionInString = "3.0";

		String filePath = pathTillDevComp + fileName + "/" + oldVersionInString + "/" + fileName + ".csv";
		System.out.println("FileRepositoryController.buildCsvFilePath() method. reading resource - {}" + filePath);

		return filePath;
	}

}

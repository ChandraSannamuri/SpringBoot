package com.j2c.restservice.controller;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.j2c.restservice.util.JsonToCsvUtil;
import com.j2c.restservice.vo.Greeting;

@RestController
@RequestMapping("/networkService")
@CrossOrigin
public class FileController {
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@Autowired
	FileRepositoryController fileRepositoryController;

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping(value = "/process/exportJson/{filename}/{category}/{subcategory}", produces = "application/zip")
	public void downloadFile(HttpServletResponse response, @PathVariable String filename, @PathVariable String category,
			@PathVariable String subcategory) throws Exception {
		ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
		try {
			String jsonFilePath = fileRepositoryController.exportJson(filename, category, subcategory);
			String csvFilePath = fileRepositoryController.buildCsvFilePath(filename, category, subcategory);
			JsonToCsvUtil.convertJsonToCsv(jsonFilePath, csvFilePath);
			FileSystemResource resource = new FileSystemResource(csvFilePath);
			ZipEntry zipEntry = new ZipEntry(resource.getFilename());
			zipEntry.setSize(resource.contentLength());
			zipOut.putNextEntry(zipEntry);
			StreamUtils.copy(resource.getInputStream(), zipOut);
			zipOut.closeEntry();
			zipOut.finish();
			zipOut.close();
			System.out.println("filePath : " + jsonFilePath);
			response.setStatus(HttpServletResponse.SC_OK);
			response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
		} catch (IOException e) {
			System.out.println("IOException in downloadReport" + e);
		}
	}



}

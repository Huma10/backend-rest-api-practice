package com.example.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// fetch file name
		String filename = file.getOriginalFilename();

		// random name generate file
		String randomId = UUID.randomUUID().toString();
		String newFilename = randomId.concat(filename.substring(filename.lastIndexOf(".")));

		// full path
		String fullPath = path + File.separator + newFilename;

		// create folder if not created
		File file2 = new File(path);
		if (!file2.exists()) {
			file2.mkdir();
		}

		// copy file
		Files.copy(file.getInputStream(), Paths.get(fullPath));
		return newFilename;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// full path
		String fullPath = path + File.separator + fileName;
		InputStream in = new FileInputStream(fullPath);
		return in;
	}

}

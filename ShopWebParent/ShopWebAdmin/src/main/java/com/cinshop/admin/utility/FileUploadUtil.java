package com.cinshop.admin.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static String saveFile(String productId, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get("../product-images");
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		String randomCode = RandomStringUtils.randomAlphanumeric(5);
		String uploadedFileName = productId + "_" + randomCode + "-" + fileName;
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(uploadedFileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save file: " + fileName, ioe);
		}

		return uploadedFileName;
	}

	public static boolean deleteImage(String imageName) {
		String absolutePathName = ".." + imageName;
		Path imagePath = Paths.get(absolutePathName).toAbsolutePath();

		try {
			Files.deleteIfExists(imagePath);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static List<String> saveExtraImage(Integer detailId, MultipartFile[] extraImages) throws IOException {
		Path uploadPath = Paths.get("../product-images");
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		List<String> uploadedFileName = new ArrayList<>();
		for (MultipartFile multipartFile : extraImages) {

			if (multipartFile.getSize() <= 0)
				continue;

			String randomCode = RandomStringUtils.randomAlphanumeric(8);
			String fileName = detailId + "_" + randomCode;
			try (InputStream inputStream = multipartFile.getInputStream()) {
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException ioe) {
				throw new IOException("Could not save file: " + fileName, ioe);
			}

			uploadedFileName.add(fileName);
		}

		return uploadedFileName;
	}

	public static String saveMainImage(MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get("../product-images");
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		String randomCode = RandomStringUtils.randomAlphanumeric(8);
		String uploadedFileName = "_" + randomCode;
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(uploadedFileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save file: " + uploadedFileName, ioe);
		}

		return uploadedFileName;
	}
}

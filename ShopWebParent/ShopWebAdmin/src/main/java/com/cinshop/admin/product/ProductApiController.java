package com.cinshop.admin.product;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cinshop.admin.utility.FileUploadUtil;
import com.cinshop.admin.utility.ProductImageService;

@RestController
@RequestMapping("/api/p")
public class ProductApiController {

	private static final String IMAGE_SOURCE_PATH = "/product-images";
	@Autowired
	private ProductImageService imageService;

	@PostMapping("/img/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("productId") String id)
			throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String imagePathName = "/product-images/";
		String uploadedFileName = null;
		try {
			uploadedFileName = FileUploadUtil.saveFile(id, fileName, multipartFile);
			imagePathName += uploadedFileName;
			imageService.saveImage(Integer.parseInt(id), imagePathName);
		} catch (Exception e) {
			return null;
		}

		return imagePathName;
	}

	@GetMapping("/img/delete")
	public Integer deleteImage(@RequestParam("imgName") String imageName) throws IOException {
		try {
			if (imageName.contains(IMAGE_SOURCE_PATH)) {
				FileUploadUtil.deleteImage(imageName);
			}
			imageService.delete(imageName);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
}

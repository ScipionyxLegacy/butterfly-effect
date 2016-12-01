package com.scipionyx.butterflyeffect.api.checkfraud.services.checkimage;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.scipionyx.butterflyeffect.api.checkfraud.model.CheckImage;
import com.scipionyx.butterflyeffect.api.checkfraud.services.ServiceConstants;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Component
public class CheckImageRESTService extends AbstractCheckFraudRESTService<CheckImage> implements ICheckImageService {

	/**
	 * 
	 */
	@Override
	public CheckImage analyze(File file) {

		FileSystemResource fileSystemResource = new FileSystemResource(file);

		return analyzeDo(fileSystemResource);
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Override
	public CheckImage analyze(String fileName, byte[] bs) throws IOException {

		File tmpFile = null;

		try {

			tmpFile = File.createTempFile(FilenameUtils.getBaseName(fileName), FilenameUtils.getExtension(fileName));
			FileUtils.writeByteArrayToFile(tmpFile, bs);

			FileSystemResource fileSystemResource = new FileSystemResource(tmpFile);

			return analyzeDo(fileSystemResource);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			FileUtils.forceDelete(tmpFile);

		}

		return null;
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	private CheckImage analyzeDo(AbstractResource resource) {

		final String uri = baseUrl + ServiceConstants.REST_MAPPING_IMAGE_ANALYZE;

		// MultipartRequest
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("image", resource);

		//
		// CheckImage postForObject = restTemplate.postForObject(uri, parts,
		// CheckImage.class);
		ResponseEntity<CheckImage> postForEntity = restTemplate.postForEntity(uri, parts, CheckImage.class);

		return postForEntity.getBody();

	}

	/**
	 * 
	 */
	@Override
	public String ping() {
		final String uri = baseUrl + ServiceConstants.REST_MAPPING_IMAGE_PING;
		return restTemplate.getForObject(uri, String.class);
	}

}

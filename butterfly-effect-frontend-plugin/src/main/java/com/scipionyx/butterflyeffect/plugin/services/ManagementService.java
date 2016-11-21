package com.scipionyx.butterflyeffect.plugin.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scipionyx.butterflyeffect.frontend.services.AbstractConfigurationService;
import com.scipionyx.butterflyeffect.plugin.model.Plugin;

/**
 * 
 * @author Renato Mendes
 *
 */
@Service("PluginManagementService")
public class ManagementService extends AbstractConfigurationService<Plugin> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @throws IOException
	 * 
	 */
	@PostConstruct
	public void init() throws IOException {
		super.init();
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Override
	public void readConfigurations() throws IOException {

		List<InputStream> resources = loadResources("plugin.info", null);

		for (InputStream inputStream : resources) {
			Plugin plugin = getObjectMapper().readValue(inputStream, Plugin.class);
			getConfigurations().add(plugin);
		}

	}

	/**
	 * 
	 * @param out
	 * @param plugin
	 * 
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void writePlugin(OutputStream out, Plugin plugin)
			throws JsonGenerationException, JsonMappingException, IOException {
		// Define Output Stream
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(out, plugin);
	}

}

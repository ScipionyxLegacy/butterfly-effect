package com.scipionyx.butterflyeffect.frontend.model;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 * 
 *
 */
public class TitleDeserializer {
	// extends JsonDeserializer<Title> {

	// /**
	// *
	// */
	// @Override
	// public Title deserialize(JsonParser jp, DeserializationContext ctxt)
	// throws IOException, JsonProcessingException {
	//
	// Title title = new Title();
	//
	// JsonNode node = jp.getCodec().readTree(jp);
	//
	// if (node.has("title"))
	// title.setTitle(node.get("title").asText());
	//
	// if (node.has("subTitle"))
	// title.setSubTitle(node.get("subTitle").asText());
	//
	// if (node.has("icon")) {
	// String iconString = node.get("icon").asText();
	// try {
	// String className = iconString.split("-")[0];
	// String iconName = iconString.split("-")[1];
	// Class<?> clazz = Class.forName(className);
	// Object[] enumConstants = clazz.getEnumConstants();
	// for (int i = 0; i < enumConstants.length; i++) {
	// if (enumConstants[i].toString().equals(iconName)){
	// title.setIcon((Resource) enumConstants[i]);
	// break;
	// }
	// }
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// return title;
	//
	// }

}

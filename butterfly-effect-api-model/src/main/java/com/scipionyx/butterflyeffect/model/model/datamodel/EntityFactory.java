package com.scipionyx.butterflyeffect.model.model.datamodel;

/**
 * 
 * @author Renato Mendes
 *
 */
public class EntityFactory {

	// private ClassPool classPool;

	/**
	 * 
	 */
	public EntityFactory() {
		// classPool = new ClassPool(true);
	}

	/**
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Object instance(Entity entity) throws InstantiationException, IllegalAccessException {
		return entity.getClazz().newInstance();
	}

	// /**
	// *
	// * @param entity
	// * @return
	// * @throws CannotCompileException
	// * @throws NotFoundException
	// */
	// public void buid(Model model) throws CannotCompileException,
	// NotFoundException {
	//
	// Entity[] entities = new Entity[model.getEntities().size()];
	//
	// model.getEntities().toArray(entities);
	//
	// for (Entity entity : entities) {
	//
	// CtClass clazz = classPool.makeClass(entity.getName());
	//
	// for (Field field : entity.getFields()) {
	// buildField(field, clazz);
	// }
	//
	// entity.setClazz(clazz.toClass());
	//
	// }
	//
	// }

//	/**
//	 * @param field
//	 * @param clazz
//	 * @throws NotFoundException
//	 * @throws CannotCompileException
//	 * 
//	 */
//	private void buildField(Field field, CtClass clazz) throws NotFoundException, CannotCompileException {
//
//		// Prepare Field Name using Java Standards
//		// String fieldName = field.getName().replace(" ", "_");
//		// fieldName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,
//		// fieldName);
//		//
//		// // Creating Method Name following Java standards
//		// String methodName =
//		// CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, fieldName);
//		//
//		// //
//		// CtClass type =
//		// classPool.getCtClass(field.getType().getClazz().getName());
//		// CtField ctField = new CtField(type, fieldName, clazz);
//		//
//		// // creating getters and setters
//		// CtMethod getterMethod = CtNewMethod.getter("get" + methodName,
//		// ctField);
//		// CtMethod setterMethod = CtNewMethod.setter("set" + methodName,
//		// ctField);
//		//
//		// // adding fields and methods to the class.
//		// clazz.addField(ctField);
//		// clazz.addMethod(setterMethod);
//		// clazz.addMethod(getterMethod);
//	}

}

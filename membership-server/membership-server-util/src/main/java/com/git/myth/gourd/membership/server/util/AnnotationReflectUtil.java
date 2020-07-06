package com.git.myth.gourd.membership.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AnnotationReflectUtil {

	private static final Logger LOG = LoggerFactory.getLogger(AnnotationReflectUtil.class);
	
	@SuppressWarnings("unchecked")
	public static <T> T findDeclaredPropertyDefault(Class<?> innotationClass, String propertyName)
	{
		try {
			Object obj = innotationClass.getDeclaredMethod(propertyName).getDefaultValue();
			if (obj != null){
				return (T)obj;
			}
		} catch (NoSuchMethodException e) {
			LOG.error("Can not found innotation property " + innotationClass.getName() + "-" + propertyName , e);
		} catch (SecurityException e) {
			LOG.error("SecurityException reflect " + innotationClass.getName() + "-" + propertyName , e);
		}
		return null;
	}
}

package br.ufsc.bridge.res.util;

import java.beans.Expression;
import java.beans.Statement;
import java.net.BindException;

public class ReflectionHelper {

	public static Object executeGetter(String property, Class<?> formClass, Object target) throws BindException {
		String methodName = "get" + StringHelper.capitalize(property);
		try {
			Expression expression = new Expression(target, methodName, new Object[0]);
			expression.execute();
			return expression.getValue();
		} catch (Exception e) {
			throw new BindException(formClass.getName() + " does not contain a " + methodName + "() method.");
		}
	}

	public static <T> void executeSetter(String property, T param, Class<?> formClass, Object target) throws BindException {
		String methodName = "set" + StringHelper.capitalize(property);
		String paramType = param == null ? "" : param.getClass().getName();
		try {
			new Statement(target, methodName, new Object[] { param }).execute();
		} catch (Exception e) {
			throw new BindException(formClass.getName() + " does not contain a " + methodName + "(" + paramType + ") method.");
		}
	}

	public static <T> T newInstance(Class<T> type) throws BindException {
		try {
			return type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BindException("Not found a valid constructor for type " + type.getName());
		}
	}

}

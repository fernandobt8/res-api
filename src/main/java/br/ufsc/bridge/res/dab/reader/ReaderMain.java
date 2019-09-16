package br.ufsc.bridge.res.dab.reader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.res.dab.JsonPathProperty;
import br.ufsc.bridge.res.dab.domain.JsonPathValueConverter;
import br.ufsc.bridge.res.dab.dto.ResABResumoConsulta;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class ReaderMain {

	public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		System.out.println("foi");

		ReaderMain rm = new ReaderMain();
		DocumentContext document = JsonPath.parse(rm.getClass().getClassLoader().getResourceAsStream("json.parties" + File.separator + "exemplo-clinico.json"));
		ResABResumoConsulta resumo = rm.readFrom(document, ResABResumoConsulta.class);
		System.out.println(resumo);

	}

	public <T> T readFrom(DocumentContext document, Class<T> type) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		T returnOject = type.newInstance();
		for (Field field: type.getDeclaredFields()) {
			if (field.isAnnotationPresent(JsonPathProperty.class)) {
				JsonPathProperty annotation = field.getAnnotation(JsonPathProperty.class);
				String jsonKey = annotation.group().getPath() + annotation.value();
				Method setMethod = type.getMethod("set" + StringUtils.capitalize(field.getName()), field.getType());
				Object value;
				if (field.getType() == List.class) {
					JSONArray items = document.read(jsonKey, JSONArray.class);
					ArrayList values = new ArrayList();
					for (Object item : items) {
						ParameterizedType lType = (ParameterizedType) field.getGenericType();
						Class<?> lClazz = (Class<?>) lType.getActualTypeArguments()[0];

						DocumentContext context = JsonPath.parse(((JSONArray) item).toJSONString());
						values.add(readFrom(context, lClazz));
					}
					value = values;
				} else {
					JSONArray arrayValue = document.read(jsonKey, JSONArray.class);

					JsonPathValueConverter valueConverter = annotation.converter().newInstance();
					if (!arrayValue.isEmpty()) {
						value = valueConverter.convert((String) arrayValue.get(0));
					} else {
						System.out.println(String.format("Sem valor encontrato para: %s", jsonKey));
						value = null;
					}
				}
				if (value != null) {
					setMethod.invoke(returnOject, value);
				}
			}
		}

		return returnOject;
	}
}

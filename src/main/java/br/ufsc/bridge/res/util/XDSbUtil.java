package br.ufsc.bridge.res.util;

import org.apache.commons.lang3.StringUtils;

public class XDSbUtil {

	public static String[] nameToXDSbName(String nome) {
		String[] split = nome.split(" ");

		String name = split[0];
		String secondName = split.length > 2 ? split[1] : "";
		String lastName = split[split.length - 1];
		return new String[] { name, secondName, lastName };
	}

	public static String xdsbNameToName(String nomeProfissional) {
		if (StringUtils.isNotBlank(nomeProfissional) && nomeProfissional.contains("^")) {
			String[] values = nomeProfissional.split("\\^");

			String ultimoNome = values.length >= 2 ? values[1] : "";
			String nome = values.length >= 3 ? values[2] : "";
			String segundoNome = values.length >= 4 ? values[3] : "";
			nomeProfissional = nome + " " + segundoNome + " " + ultimoNome;
			return StringUtils.isNotBlank(nomeProfissional) ? nomeProfissional.trim() : null;
		}
		return null;
	}
}

package br.ufsc.bridge.res.util;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryError;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryErrorList;

@Slf4j
public class ResLogError {

	public void printLogError(RegistryErrorList error) {
		for (RegistryError registryError : error.getRegistryError()) {
			log.error("------- Registry Error -------");
			this.print("codeContext", registryError.getCodeContext());
			this.print("errorCode", registryError.getErrorCode());
			this.print("location", registryError.getLocation());
			this.print("severity", registryError.getSeverity());
			this.print("value", registryError.getValue());
		}
	}

	private void print(String tag, String value) {
		if (StringUtils.isNotBlank(value)) {
			log.error(tag + " = " + value);
		}
	}
}

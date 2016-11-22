package br.ufsc.bridge.res.dab.exception;

import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryError;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryErrorList;
import br.ufsc.bridge.res.service.repository.RepositoryService;

public class ResABXMLWriterException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String CODIGO_ERRO_INTERNO_INSTABILIDADE_RES_NACIONAL = "BEA-380002";
	private static final String MSG_ERRO_INTERNO_INESPERADO_RES_NACIONAL = "Ocorreu um erro inesperado ao enviar este registro para o RES-nacional.";

	public ResABXMLWriterException(String message) {
		super(message);
	}

	public ResABXMLWriterException(RegistryErrorList registryErrorList) {
		super(getMessageError(registryErrorList));
	}

	private static String getMessageError(RegistryErrorList error) {
		for (RegistryError registryError : error.getRegistryError()) {
			if (registryError.getCodeContext().contains(CODIGO_ERRO_INTERNO_INSTABILIDADE_RES_NACIONAL)) {
				return RepositoryService.SERVICO_RES_INDISPONIVEL;
			}
		}
		return MSG_ERRO_INTERNO_INESPERADO_RES_NACIONAL;
	}
}

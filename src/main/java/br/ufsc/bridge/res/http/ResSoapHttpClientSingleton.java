package br.ufsc.bridge.res.http;

import static br.ufsc.bridge.res.http.ResSoapHttpHeaders.PURPOSE_OF_USE;

import br.ufsc.bridge.soap.http.SoapHttpClient;

public class ResSoapHttpClientSingleton {

	private static SoapHttpClient httpClient;

	public static SoapHttpClient resHttpClient() {
		if (httpClient == null) {
			httpClient = new SoapHttpClient();
			httpClient.putHeader(PURPOSE_OF_USE, "Atendimento");
		}
		return httpClient;
	}
}

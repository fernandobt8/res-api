package br.ufsc.bridge.res.http;

import br.ufsc.bridge.soap.http.SoapHttpClient;

public class ResSoapHttpClientSingleton {

	private static SoapHttpClient httpClient;

	public static SoapHttpClient resHttpClient() {
		if (httpClient == null) {
			httpClient = new SoapHttpClient();
		}
		return httpClient;
	}
}

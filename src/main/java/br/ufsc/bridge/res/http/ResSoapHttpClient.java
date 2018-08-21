package br.ufsc.bridge.res.http;

import static br.ufsc.bridge.res.http.ResSoapHttpHeaders.PURPOSE_OF_USE;

import br.ufsc.bridge.soap.SoapClient;
import br.ufsc.bridge.soap.http.SoapHttpRequest;
import br.ufsc.bridge.soap.http.SoapHttpResponse;
import br.ufsc.bridge.soap.http.exception.SoapHttpConnectionException;
import br.ufsc.bridge.soap.http.exception.SoapHttpResponseException;

public class ResSoapHttpClient {

	private ResSoapHttpClient() {
		// utility class
	}

	public static SoapHttpResponse request(SoapHttpRequest request) throws SoapHttpResponseException, SoapHttpConnectionException {
		request.addHeader(PURPOSE_OF_USE, "Atendimento");
		return SoapClient.request(request);
	}
}

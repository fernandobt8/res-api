package br.ufsc.bridge.res.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import lombok.Cleanup;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.res.http.exception.ResHttpRequestResponseException;

public class MtomProcessor {

	public static InputStream process(String value) throws ResHttpRequestResponseException {
		try {
			if (StringUtils.isBlank(value)) {
				return IOUtils.toInputStream(value);
			}

			@Cleanup
			BufferedReader bufferedReader = new BufferedReader(new StringReader(value));
			String firstLine = bufferedReader.readLine();
			if (!firstLine.startsWith("--")) {
				return IOUtils.toInputStream(value);
			}

			firstLine = bufferedReader.readLine();
			while (firstLine != null && !firstLine.startsWith("<?xml")) {
				firstLine = bufferedReader.readLine();
			}

			String secondLine = bufferedReader.readLine();
			while (secondLine != null && !secondLine.startsWith("<?xml")) {
				secondLine = bufferedReader.readLine();
			}
			if (StringUtils.isNotBlank(secondLine)) {
				firstLine = firstLine.replaceAll("(<xop:)((.)*)(\\/>)", Base64.encodeBase64String(secondLine.getBytes("UTF-8")));
			}

			return IOUtils.toInputStream(firstLine);
		} catch (IOException e) {
			throw new ResHttpRequestResponseException("Couldn't create buffer", e);
		}
	}
}

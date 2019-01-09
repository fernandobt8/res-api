package br.ufsc.bridge.res;

import java.io.FileInputStream;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;

import br.ufsc.bridge.res.dab.dto.ResABResumoConsulta;

@Slf4j
public class ResABResumoXmlTest {

	public static void main(String args[]) {
		try {
			InputStream resourceAsStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/CN4-CIT_doc-crianca.xml");

			try {
				ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
				System.out.println(resumoConsulta.getXml());
			} catch (Exception e) {
				log.error("", e);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
}

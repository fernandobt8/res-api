package br.ufsc.bridge.res;

import java.io.FileInputStream;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResABResumoXmlTest {

	public static void main(String args[]) {
		try {
			InputStream resourceAsStream = new FileInputStream("/home/fernandobt8/Documents/res/rnds-2019/exemplo_reg_atendimento_clinico.json");

			try {
				//				ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream), true);
				//				System.out.println(resumoConsulta.getXml());
			} catch (Exception e) {
				log.error("", e);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
}

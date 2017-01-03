package br.ufsc.bridge.res.repository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.dto.ResABIdentificacaoProfissional;
import br.ufsc.bridge.res.dab.dto.ResABResumoConsulta;

public class TestConvertResABResumoConsultaToXML {

	String PATH_TEST_RESOURCE = System.getProperty("user.dir") + "/src/test/resources/repository/";

	@Test
	public void CDT001() throws Exception {
		// Dados minimos de um atendimento

		String pathFile = this.PATH_TEST_RESOURCE + "NewFile.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);

		ResABResumoConsulta resABResumoConsulta = new ResABResumoConsulta();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = "2016-07-09";
		resABResumoConsulta.setDataAtendimento(formatter.parse(dateInString));

		resABResumoConsulta.setTipoAtendimento(ResABTipoAtendimentoEnum.DEMANDA_ESPONTANEA_CONSULTA_NO_DIA);

		ResABIdentificacaoProfissional profissional = new ResABIdentificacaoProfissional();
		List<ResABIdentificacaoProfissional> profissionais = new ArrayList<>();
		profissional.setCns("123456");
		profissional.setCbo("654321");
		profissional.setResponsavel(true);
		profissionais.add(profissional);
		resABResumoConsulta.setProfissionais(profissionais);

		Assert.assertEquals(resABResumoConsulta.getXml(), IOUtils.toString(resourceAsStream));

		ResABResumoConsulta resABResumoConsultaRecuperado = new ResABResumoConsulta(resABResumoConsulta.getXml());

		Assert.assertEquals(resABResumoConsulta, resABResumoConsultaRecuperado);

	}
}
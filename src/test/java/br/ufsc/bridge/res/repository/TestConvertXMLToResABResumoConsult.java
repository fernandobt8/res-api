package br.ufsc.bridge.res.repository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.domain.ResABTipoProblemaDiagnostico;
import br.ufsc.bridge.res.dab.domain.ResABTurnoEnum;
import br.ufsc.bridge.res.dab.dto.ResABResumoConsulta;

public class TestConvertXMLToResABResumoConsult {

	String PATH_TEST_RESOURCE = System.getProperty("user.dir") + "/src/test/resources/repository/";

	@Test
	public void CDT001() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "docCompleto.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		try {
			ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
			Assert.assertEquals(ResABTipoAtendimentoEnum.CONSULTA_AGENDADA, resumoConsulta.getTipoAtendimento());
			Assert.assertEquals("4254160", resumoConsulta.getCnes());
			Assert.assertEquals("703006821796679", resumoConsulta.getProfissionais().get(0).getCns());
			Assert.assertEquals("2235-65", resumoConsulta.getProfissionais().get(0).getCbo());
			Assert.assertEquals("703006821796679", resumoConsulta.getProfissionais().get(0).getCns());
			Assert.assertEquals("2235-65", resumoConsulta.getProfissionais().get(0).getCbo());
			Assert.assertEquals("707000801749036", resumoConsulta.getProfissionais().get(1).getCns());
			Assert.assertEquals("2236-05", resumoConsulta.getProfissionais().get(1).getCbo());
			Assert.assertEquals("898001153249911", resumoConsulta.getProfissionais().get(2).getCns());
			Assert.assertEquals("2235-64", resumoConsulta.getProfissionais().get(2).getCbo());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			String dateInString = "2016-05-23T15:00:00.000-03:00";
			Assert.assertEquals(formatter.parse(dateInString), resumoConsulta.getDataAtendimento());
			Assert.assertEquals(ResABTurnoEnum.NOITE, resumoConsulta.getTurno());
			Assert.assertEquals("994.250", resumoConsulta.getPeso());
			Assert.assertEquals("987.55", resumoConsulta.getAltura());
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			dateInString = "2016-03-16";
			Assert.assertEquals(formatter.parse(dateInString), resumoConsulta.getDum());
			Assert.assertEquals("P9W5D", resumoConsulta.getIdadeGestacional());
			Assert.assertEquals("2", resumoConsulta.getGestasPrevias());
			Assert.assertEquals("1", resumoConsulta.getPartos());
			Assert.assertEquals("A920", resumoConsulta.getProblemasDiagnosticos().get(0).getCodigo());
			Assert.assertEquals("Febre de Chikungunya", resumoConsulta.getProblemasDiagnosticos().get(0).getDescricao());
			Assert.assertEquals(ResABTipoProblemaDiagnostico.CID10, resumoConsulta.getProblemasDiagnosticos().get(0).getTipo());
			Assert.assertEquals("T28", resumoConsulta.getProblemasDiagnosticos().get(1).getCodigo());
			Assert.assertEquals("LIMITAÇÃO FUNCIONAL/INCAPACIDADE", resumoConsulta.getProblemasDiagnosticos().get(1).getDescricao());
			Assert.assertEquals(ResABTipoProblemaDiagnostico.CIAP, resumoConsulta.getProblemasDiagnosticos().get(1).getTipo());
			Assert.assertEquals("", resumoConsulta.getProblemasDiagnosticos().get(2).getCodigo());
			Assert.assertEquals("ABORTO ESPONTÂNEO - COMPLETO OU NÃO ESPEC., COM OUTRAS COMPLICAÇÕES OU COM COMPLICAÇÕES NÃO ESPECIF.",
					resumoConsulta.getProblemasDiagnosticos().get(2).getDescricao());
			Assert.assertEquals(null, resumoConsulta.getProblemasDiagnosticos().get(2).getTipo());

			resumoConsulta.getAlergias();
			resumoConsulta.getCondutas();
			resumoConsulta.getEncaminhamentos();
			resumoConsulta.getIne();
			resumoConsulta.getMedicamentos();

			resumoConsulta.getProcedimentos();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void CDT002() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "docMinimoInterno.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		try {
			ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
			Assert.assertNull("Turno", resumoConsulta.getTurno());
			Assert.assertNull("CNES", resumoConsulta.getCnes());
			Assert.assertNull("CNS", resumoConsulta.getProfissionais().get(0).getCns());
			Assert.assertNull("CBO", resumoConsulta.getProfissionais().get(0).getCbo());
			Assert.assertNull("Data de Atendimento", resumoConsulta.getDataAtendimento());
			Assert.assertNull("Alergias", resumoConsulta.getAlergias());
			Assert.assertNull("Altura", resumoConsulta.getAltura());
			Assert.assertNull("Condutas", resumoConsulta.getCondutas());
			Assert.assertNull("DUM", resumoConsulta.getDum());
			Assert.assertNull("Atendimento", resumoConsulta.getTipoAtendimento());
			Assert.assertNull("Encaminhamentos", resumoConsulta.getEncaminhamentos());
			Assert.assertNull("Gestas Previas", resumoConsulta.getGestasPrevias());
			Assert.assertNull("Idade Gestacional", resumoConsulta.getIdadeGestacional());
			Assert.assertNull("INE", resumoConsulta.getIne());
			Assert.assertNull("Partos", resumoConsulta.getPartos());
			Assert.assertNull("Medicamentos", resumoConsulta.getMedicamentos());
			Assert.assertNull("Peso", resumoConsulta.getPeso());
			Assert.assertNull("Problemas", resumoConsulta.getProblemasDiagnosticos());
			Assert.assertNull("Procedimentos", resumoConsulta.getProcedimentos());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void CDT003() throws Exception {
		// XML apenas com as TAGs obrigatorias
		String pathFile = this.PATH_TEST_RESOURCE + "docCDT003.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		try {
			ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void CDT004() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "docValoresMiniExt.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		try {
			ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

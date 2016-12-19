package br.ufsc.bridge.res.repository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.res.dab.domain.ResABGravidadeEnum;
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

			Assert.assertEquals("1282844269", resumoConsulta.getIne());

			Assert.assertEquals("703006821796679", resumoConsulta.getProfissionais().get(0).getCns());
			Assert.assertEquals("2235-65", resumoConsulta.getProfissionais().get(0).getCbo());
			Assert.assertEquals(true, resumoConsulta.getProfissionais().get(0).isResponsavel());

			Assert.assertEquals("707000801749036", resumoConsulta.getProfissionais().get(1).getCns());
			Assert.assertEquals("2236-05", resumoConsulta.getProfissionais().get(1).getCbo());
			Assert.assertEquals(false, resumoConsulta.getProfissionais().get(1).isResponsavel());

			Assert.assertEquals("898001153249911", resumoConsulta.getProfissionais().get(2).getCns());
			Assert.assertEquals("2235-64", resumoConsulta.getProfissionais().get(2).getCbo());
			Assert.assertEquals(true, resumoConsulta.getProfissionais().get(2).isResponsavel());

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

			Assert.assertEquals(ResABGravidadeEnum.BAIXO, resumoConsulta.getAlergias().get(0).getGravidade());
			Assert.assertEquals("Alimento", resumoConsulta.getAlergias().get(0).getCategoria());
			Assert.assertEquals("leite", resumoConsulta.getAlergias().get(0).getAgente());

			formatter = new SimpleDateFormat("yyyy-MM");
			dateInString = "2014-05";
			Assert.assertEquals(formatter.parse(dateInString), resumoConsulta.getAlergias().get(0).getEventoReacao().get(0).getDataInstalacao());
			Assert.assertEquals("sob controle", resumoConsulta.getAlergias().get(0).getEventoReacao().get(0).getEvolucaoAlergia());
			Assert.assertEquals("início há 2 anos, percebeu mau estar após ingestão de leite e derivados",
					resumoConsulta.getAlergias().get(0).getEventoReacao().get(0).getManifestacao());

			formatter = new SimpleDateFormat("yyyy-MM-dd");
			dateInString = "2016-07-09";
			Assert.assertEquals(formatter.parse(dateInString), resumoConsulta.getAlergias().get(0).getEventoReacao().get(1).getDataInstalacao());
			Assert.assertEquals("sob cautela", resumoConsulta.getAlergias().get(0).getEventoReacao().get(1).getEvolucaoAlergia());
			Assert.assertEquals("início mês passado, percebeu piora após ingestão de leite e derivados",
					resumoConsulta.getAlergias().get(0).getEventoReacao().get(1).getManifestacao());

			Assert.assertEquals(ResABGravidadeEnum.ALTO, resumoConsulta.getAlergias().get(1).getGravidade());
			Assert.assertEquals("Remedio", resumoConsulta.getAlergias().get(1).getCategoria());
			Assert.assertEquals("metamizol", resumoConsulta.getAlergias().get(1).getAgente());

			formatter = new SimpleDateFormat("yyyy-MM");
			dateInString = "2013-09";
			Assert.assertEquals(formatter.parse(dateInString), resumoConsulta.getAlergias().get(1).getEventoReacao().get(0).getDataInstalacao());
			Assert.assertEquals("situação de alto risco", resumoConsulta.getAlergias().get(1).getEventoReacao().get(0).getEvolucaoAlergia());
			Assert.assertEquals("início há 3 anos, percebeu mau estar após utilização do componente",
					resumoConsulta.getAlergias().get(1).getEventoReacao().get(0).getManifestacao());

			Assert.assertNull(resumoConsulta.getAlergias().get(1).getEventoReacao().get(1).getDataInstalacao());
			Assert.assertEquals("sob atenção", resumoConsulta.getAlergias().get(1).getEventoReacao().get(1).getEvolucaoAlergia());
			Assert.assertEquals("piora nos meses seguintes", resumoConsulta.getAlergias().get(1).getEventoReacao().get(1).getManifestacao());

			Assert.assertEquals("18512000", resumoConsulta.getProcedimentos().get(0).getCodigo());
			Assert.assertEquals("TERAPIA INDIVIDUAL", resumoConsulta.getProcedimentos().get(0).getNome());

			Assert.assertEquals("167376008", resumoConsulta.getProcedimentos().get(1).getCodigo());
			Assert.assertEquals("TESTE RÁPIDO DE GRAVIDEZ", resumoConsulta.getProcedimentos().get(1).getNome());

			Assert.assertEquals("122869004", resumoConsulta.getProcedimentos().get(2).getCodigo());
			Assert.assertEquals("AVALIAÇÃO ANTROPOMÉTRICA", resumoConsulta.getProcedimentos().get(2).getNome());

			Assert.assertEquals("433465004", resumoConsulta.getProcedimentos().get(3).getCodigo());
			Assert.assertEquals("COLETA DE MATERIAL P/ EXAME LABORATORIAL", resumoConsulta.getProcedimentos().get(3).getNome());

			Assert.assertEquals("ALBENDAZOL 200 mg comprimido", resumoConsulta.getMedicamentos().get(0).getNomeMedicamento());
			Assert.assertEquals("BR0269628", resumoConsulta.getMedicamentos().get(0).getCodigoMedicamentoCatmat());
			Assert.assertEquals("ALBENDAZOL 200 mg cmp FF", resumoConsulta.getMedicamentos().get(0).getDescricaoFormaFarmaceutica());
			Assert.assertEquals("BR0269629", resumoConsulta.getMedicamentos().get(0).getCodigoFormaFarmaceutica());
			Assert.assertEquals("Oral", resumoConsulta.getMedicamentos().get(0).getDescricaoViaAdministracao());
			Assert.assertEquals("25", resumoConsulta.getMedicamentos().get(0).getCodigoViaAdministracao());
			Assert.assertEquals("1 comp 30 min antes do almoço", resumoConsulta.getMedicamentos().get(0).getDescricaoDose());
			Assert.assertEquals("P30D", resumoConsulta.getMedicamentos().get(0).getCodigoDoseEstruturada());

			Assert.assertEquals("ACITRETINA 10 mg cápsula", resumoConsulta.getMedicamentos().get(1).getNomeMedicamento());
			Assert.assertEquals("BR038719", resumoConsulta.getMedicamentos().get(1).getCodigoMedicamentoCatmat());
			Assert.assertEquals("ACITRETINA", resumoConsulta.getMedicamentos().get(1).getDescricaoFormaFarmaceutica());
			Assert.assertEquals("BR038720", resumoConsulta.getMedicamentos().get(1).getCodigoFormaFarmaceutica());
			Assert.assertEquals("NASAL", resumoConsulta.getMedicamentos().get(1).getDescricaoViaAdministracao());
			Assert.assertEquals("29", resumoConsulta.getMedicamentos().get(1).getCodigoViaAdministracao());
			Assert.assertEquals("1 comp intervalo 3", resumoConsulta.getMedicamentos().get(1).getDescricaoDose());
			Assert.assertEquals("P5W", resumoConsulta.getMedicamentos().get(1).getCodigoDoseEstruturada());

			Assert.assertEquals("Retorno para cuidado continuado/programado", resumoConsulta.getCondutas().get(0).getDescricao());
			Assert.assertEquals("Alta do episódio", resumoConsulta.getCondutas().get(1).getDescricao());

			Assert.assertEquals("Interno no dia", resumoConsulta.getEncaminhamentos().get(0));
			Assert.assertEquals("Externo no dia", resumoConsulta.getEncaminhamentos().get(1));
			Assert.assertEquals("Agendamento", resumoConsulta.getEncaminhamentos().get(2));
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

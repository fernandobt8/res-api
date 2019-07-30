package br.ufsc.bridge.res.repository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.res.dab.domain.ResABAleitamentoMaternoEnum;
import br.ufsc.bridge.res.dab.domain.ResABEstadoMedicamentoEnum;
import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.dto.ResABAlergiaReacoes;
import br.ufsc.bridge.res.dab.dto.ResABEventoReacao;
import br.ufsc.bridge.res.dab.dto.ResABIdentificacaoProfissional;
import br.ufsc.bridge.res.dab.dto.ResABMedicamento;
import br.ufsc.bridge.res.dab.dto.ResABProblemaDiagnostico;
import br.ufsc.bridge.res.dab.dto.ResABProcedimento;
import br.ufsc.bridge.res.dab.dto.ResABResumoConsulta;
import br.ufsc.bridge.res.domain.ResCriticidadeEnum;
import br.ufsc.bridge.res.domain.ResTipoProblemaDiagnostico;

public class TestConvertXMLToResABResumoConsulta {

	String PATH_TEST_RESOURCE = System.getProperty("user.dir") + "/src/test/resources/repository/";

	@Test
	public void CDT001() throws Exception {
		// XML atendimento completo
		String pathFile = this.PATH_TEST_RESOURCE + "atendimentoCompleto.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
		Assert.assertEquals(ResABTipoAtendimentoEnum.CONSULTA_AGENDADA, resumoConsulta.getTipoAtendimento());
		Assert.assertEquals("4254160", resumoConsulta.getCnes());
		Assert.assertEquals("1282844269", resumoConsulta.getIne());

		Assert.assertEquals(3, resumoConsulta.getProfissionais().size());

		ResABIdentificacaoProfissional resABProfissional1 = resumoConsulta.getProfissionais().get(0);
		Assert.assertEquals("703006821796679", resABProfissional1.getCns());
		Assert.assertEquals("2235-65", resABProfissional1.getCbo());
		Assert.assertEquals("Pedro Henrique", resABProfissional1.getNome());
		Assert.assertEquals(true, resABProfissional1.isResponsavel());

		ResABIdentificacaoProfissional resABProfissional2 = resumoConsulta.getProfissionais().get(1);
		Assert.assertEquals("707000801749036", resABProfissional2.getCns());
		Assert.assertEquals("2236-05", resABProfissional2.getCbo());
		Assert.assertNull(resABProfissional2.getNome());
		Assert.assertEquals(false, resABProfissional2.isResponsavel());

		ResABIdentificacaoProfissional resABProfissional3 = resumoConsulta.getProfissionais().get(2);
		Assert.assertEquals("898001153249911", resABProfissional3.getCns());
		Assert.assertEquals("2235-64", resABProfissional3.getCbo());
		Assert.assertEquals("João da Silva", resABProfissional3.getNome());
		Assert.assertEquals(true, resABProfissional3.isResponsavel());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String dateInString = "2016-05-23T15:00:00.000-03:00";
		Assert.assertEquals(formatter.parse(dateInString), resumoConsulta.getDataAtendimento());

		// Assert.assertEquals(ResABTurnoEnum.NOITE, resumoConsulta.getTurno());

		Assert.assertEquals("994.250", resumoConsulta.getPeso());
		Assert.assertEquals("987.55", resumoConsulta.getAltura());
		Assert.assertEquals("43", resumoConsulta.getPerimetroCefalico());
		Assert.assertEquals(ResABAleitamentoMaternoEnum.EXCLUSIVO, resumoConsulta.getAleitamentoMaterno());

		formatter = new SimpleDateFormat("yyyy-MM-dd");
		dateInString = "2016-03-16";
		Assert.assertEquals(formatter.parse(dateInString), resumoConsulta.getDum());

		Assert.assertEquals("P9W5D", resumoConsulta.getIdadeGestacional());
		Assert.assertEquals("2", resumoConsulta.getGestasPrevias());
		Assert.assertEquals("1", resumoConsulta.getPartos());

		Assert.assertEquals(3, resumoConsulta.getProblemasDiagnosticos().size());

		ResABProblemaDiagnostico resABProblemaDiagnostico1 = resumoConsulta.getProblemasDiagnosticos().get(0);
		Assert.assertEquals("A920", resABProblemaDiagnostico1.getCodigo());
		Assert.assertEquals("Febre de Chikungunya", resABProblemaDiagnostico1.getDescricao());
		Assert.assertEquals(ResTipoProblemaDiagnostico.CID10, resABProblemaDiagnostico1.getTipo());

		ResABProblemaDiagnostico resABProblemaDiagnostico2 = resumoConsulta.getProblemasDiagnosticos().get(1);
		Assert.assertEquals("T28", resABProblemaDiagnostico2.getCodigo());
		Assert.assertEquals("LIMITAÇÃO FUNCIONAL/INCAPACIDADE", resABProblemaDiagnostico2.getDescricao());
		Assert.assertEquals(ResTipoProblemaDiagnostico.CIAP, resABProblemaDiagnostico2.getTipo());

		ResABProblemaDiagnostico resABProblemaDiagnostico3 = resumoConsulta.getProblemasDiagnosticos().get(2);
		Assert.assertEquals("O038", resABProblemaDiagnostico3.getCodigo());
		Assert.assertEquals("ABORTO ESPONTÂNEO - COMPLETO OU NÃO ESPEC., COM OUTRAS COMPLICAÇÕES OU COM COMPLICAÇÕES NÃO ESPECIF.", resABProblemaDiagnostico3.getDescricao());
		Assert.assertEquals(ResTipoProblemaDiagnostico.CID10, resABProblemaDiagnostico3.getTipo());

		Assert.assertEquals(2, resumoConsulta.getAlergias().size());

		ResABAlergiaReacoes resABAlergiaReacoes1 = resumoConsulta.getAlergias().get(0);
		Assert.assertEquals(ResCriticidadeEnum.BAIXO, resABAlergiaReacoes1.getCriticidade());
		Assert.assertEquals("Alimento", resABAlergiaReacoes1.getCategoria());
		Assert.assertEquals("leite", resABAlergiaReacoes1.getAgente());

		formatter = new SimpleDateFormat("yyyy-MM-dd");
		dateInString = "2014-05-01";
		ResABEventoReacao resABAlergia1EventoReacao1 = resABAlergiaReacoes1.getEventoReacao().get(0);
		Assert.assertEquals(formatter.parse(dateInString), resABAlergia1EventoReacao1.getDataInstalacao());
		Assert.assertEquals("sob controle", resABAlergia1EventoReacao1.getEvolucaoAlergia());
		Assert.assertEquals("início há 2 anos, percebeu mau estar após ingestão de leite e derivados", resABAlergia1EventoReacao1.getManifestacao());

		formatter = new SimpleDateFormat("yyyy-MM-dd");
		dateInString = "2016-07-09";
		ResABEventoReacao resABAlergia1EventoReacao2 = resABAlergiaReacoes1.getEventoReacao().get(1);
		Assert.assertEquals(formatter.parse(dateInString), resABAlergia1EventoReacao2.getDataInstalacao());
		Assert.assertEquals("sob cautela", resABAlergia1EventoReacao2.getEvolucaoAlergia());
		Assert.assertEquals("início mês passado, percebeu piora após ingestão de leite e derivados", resABAlergia1EventoReacao2.getManifestacao());

		ResABAlergiaReacoes resABAlergiaReacoes2 = resumoConsulta.getAlergias().get(1);
		Assert.assertEquals(ResCriticidadeEnum.ALTO, resABAlergiaReacoes2.getCriticidade());
		Assert.assertEquals("Remedio", resABAlergiaReacoes2.getCategoria());
		Assert.assertEquals("metamizol", resABAlergiaReacoes2.getAgente());

		formatter = new SimpleDateFormat("yyyy-MM-dd");
		dateInString = "2013-09-01";
		ResABEventoReacao resABAlergia2EventoReacao1 = resABAlergiaReacoes2.getEventoReacao().get(0);
		Assert.assertEquals(formatter.parse(dateInString), resABAlergia2EventoReacao1.getDataInstalacao());
		Assert.assertEquals("situação de alto risco", resABAlergia2EventoReacao1.getEvolucaoAlergia());
		Assert.assertEquals("início há 3 anos, percebeu mau estar após utilização do componente", resABAlergia2EventoReacao1.getManifestacao());

		ResABEventoReacao resABAlergia2EventoReacao2 = resABAlergiaReacoes2.getEventoReacao().get(1);
		Assert.assertNull(resABAlergia2EventoReacao2.getDataInstalacao());
		Assert.assertEquals("sob atenção", resABAlergia2EventoReacao2.getEvolucaoAlergia());
		Assert.assertEquals("piora nos meses seguintes", resABAlergia2EventoReacao2.getManifestacao());

		Assert.assertEquals(4, resumoConsulta.getProcedimentos().size());

		ResABProcedimento resABProcedimento1 = resumoConsulta.getProcedimentos().get(0);
		Assert.assertEquals("18512000", resABProcedimento1.getCodigo());
		Assert.assertEquals("TERAPIA INDIVIDUAL", resABProcedimento1.getNome());

		ResABProcedimento resABProcedimento2 = resumoConsulta.getProcedimentos().get(1);
		Assert.assertEquals("167376008", resABProcedimento2.getCodigo());
		Assert.assertEquals("TESTE RÁPIDO DE GRAVIDEZ", resABProcedimento2.getNome());

		ResABProcedimento resABProcedimento3 = resumoConsulta.getProcedimentos().get(2);
		Assert.assertEquals("122869004", resABProcedimento3.getCodigo());
		Assert.assertEquals("AVALIAÇÃO ANTROPOMÉTRICA", resABProcedimento3.getNome());

		ResABProcedimento resABProcedimento4 = resumoConsulta.getProcedimentos().get(3);
		Assert.assertEquals("433465004", resABProcedimento4.getCodigo());
		Assert.assertEquals("COLETA DE MATERIAL P/ EXAME LABORATORIAL", resABProcedimento4.getNome());

		Assert.assertEquals(2, resumoConsulta.getMedicamentos().size());

		ResABMedicamento resABMedicamento1 = resumoConsulta.getMedicamentos().get(0);
		Assert.assertEquals("ALBENDAZOL 200 mg comprimido", resABMedicamento1.getNomeMedicamento());
		Assert.assertEquals("BR0269628", resABMedicamento1.getCodigoMedicamentoCatmat());
		Assert.assertEquals("ALBENDAZOL 200 mg cmp FF", resABMedicamento1.getDescricaoFormaFarmaceutica());
		Assert.assertEquals("BR0269629", resABMedicamento1.getCodigoFormaFarmaceutica());
		Assert.assertEquals("Oral", resABMedicamento1.getDescricaoViaAdministracao());
		Assert.assertEquals("25", resABMedicamento1.getCodigoViaAdministracao());
		Assert.assertEquals("1 comp 30 min antes do almoço", resABMedicamento1.getDescricaoDose());
		Assert.assertEquals("P30D", resABMedicamento1.getDuracaoTratamento());
		Assert.assertEquals(ResABEstadoMedicamentoEnum.ATIVA, resABMedicamento1.getEstadoMedicamento());

		ResABMedicamento resABMedicamento2 = resumoConsulta.getMedicamentos().get(1);
		Assert.assertEquals("ACITRETINA 10 mg cápsula", resABMedicamento2.getNomeMedicamento());
		Assert.assertEquals("BR038719", resABMedicamento2.getCodigoMedicamentoCatmat());
		Assert.assertEquals("ACITRETINA", resABMedicamento2.getDescricaoFormaFarmaceutica());
		Assert.assertEquals("BR038720", resABMedicamento2.getCodigoFormaFarmaceutica());
		Assert.assertEquals("NASAL", resABMedicamento2.getDescricaoViaAdministracao());
		Assert.assertEquals("29", resABMedicamento2.getCodigoViaAdministracao());
		Assert.assertEquals("1 comp intervalo 3", resABMedicamento2.getDescricaoDose());
		Assert.assertEquals("P5W", resABMedicamento2.getDuracaoTratamento());
		Assert.assertEquals(ResABEstadoMedicamentoEnum.TRATAMENTO_COMPLETO, resABMedicamento2.getEstadoMedicamento());

		Assert.assertEquals(2, resumoConsulta.getCondutas().size());

		// ResABCondutaEnum resABConduta1 = resumoConsulta.getCondutas().get(0);
		// Assert.assertEquals("Retorno para cuidado continuado/programado", resABConduta1.getDescricao());
		// ResABCondutaEnum resABCondutaEnum2 = resumoConsulta.getCondutas().get(1);
		// Assert.assertEquals("Alta do episódio", resABCondutaEnum2.getDescricao());

		Assert.assertEquals(3, resumoConsulta.getEncaminhamentos().size());

		Assert.assertEquals("Interno no dia", resumoConsulta.getEncaminhamentos().get(0));
		Assert.assertEquals("Externo no dia", resumoConsulta.getEncaminhamentos().get(1));
		Assert.assertEquals("Agendamento", resumoConsulta.getEncaminhamentos().get(2));
	}

	@Test
	public void CDT002() throws Exception {
		// XML sem as tags de value
		String pathFile = this.PATH_TEST_RESOURCE + "/TestConvertXMLToResABResumoConsulta/CDT002.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
		// Assert.assertNull(resumoConsulta.getTurno());
		Assert.assertNull(resumoConsulta.getCnes());

		Assert.assertEquals(1, resumoConsulta.getProfissionais().size());
		ResABIdentificacaoProfissional resABProfissional1 = resumoConsulta.getProfissionais().get(0);
		Assert.assertNull(resABProfissional1.getCns());
		Assert.assertNull(resABProfissional1.getCbo());
		Assert.assertNull(resABProfissional1.getNome());
		Assert.assertEquals(true, resABProfissional1.isResponsavel());

		Assert.assertNull(resumoConsulta.getDataAtendimento());

		Assert.assertEquals(1, resumoConsulta.getAlergias().size());

		ResABAlergiaReacoes resABAlergiaReacoes1 = resumoConsulta.getAlergias().get(0);
		Assert.assertNull(resABAlergiaReacoes1.getCriticidade());
		Assert.assertNull(resABAlergiaReacoes1.getCategoria());
		Assert.assertNull(resABAlergiaReacoes1.getAgente());
		Assert.assertEquals(1, resABAlergiaReacoes1.getEventoReacao().size());
		ResABEventoReacao resABAlergia1EventoReacao1 = resABAlergiaReacoes1.getEventoReacao().get(0);
		Assert.assertNull(resABAlergia1EventoReacao1.getDataInstalacao());
		Assert.assertNull(resABAlergia1EventoReacao1.getEvolucaoAlergia());
		Assert.assertNull(resABAlergia1EventoReacao1.getDataInstalacao());

		Assert.assertNull(resumoConsulta.getAltura());
		Assert.assertNull(resumoConsulta.getPerimetroCefalico());
		Assert.assertNull(resumoConsulta.getAleitamentoMaterno());
		Assert.assertEquals(1, resumoConsulta.getCondutas().size());
		Assert.assertNull(resumoConsulta.getCondutas().get(0));

		Assert.assertNull(resumoConsulta.getDum());

		Assert.assertEquals(1, resumoConsulta.getEncaminhamentos().size());
		Assert.assertNull(resumoConsulta.getEncaminhamentos().get(0));

		Assert.assertNull(resumoConsulta.getGestasPrevias());
		Assert.assertNull(resumoConsulta.getIdadeGestacional());
		Assert.assertNull(resumoConsulta.getIne());
		Assert.assertNull(resumoConsulta.getPartos());

		Assert.assertNull(resumoConsulta.getPeso());

		Assert.assertEquals(2, resumoConsulta.getProblemasDiagnosticos().size());
		ResABProblemaDiagnostico resABProblemaDiagnostico1 = resumoConsulta.getProblemasDiagnosticos().get(0);
		Assert.assertNull(resABProblemaDiagnostico1.getCodigo());
		Assert.assertNull(resABProblemaDiagnostico1.getDescricao());
		Assert.assertNull(resABProblemaDiagnostico1.getTipo());

		ResABProblemaDiagnostico resABProblemaDiagnostico2 = resumoConsulta.getProblemasDiagnosticos().get(1);
		Assert.assertNull(resABProblemaDiagnostico2.getCodigo());
		Assert.assertNull(resABProblemaDiagnostico2.getDescricao());
		Assert.assertNull(resABProblemaDiagnostico2.getTipo());

		Assert.assertEquals(1, resumoConsulta.getProcedimentos().size());
		ResABProcedimento resABProcedimento1 = resumoConsulta.getProcedimentos().get(0);
		Assert.assertNull(resABProcedimento1.getCodigo());
		Assert.assertNull(resABProcedimento1.getNome());
		// Assert.assertEquals(0, resABProcedimento1.getResultadoObservacoes().size());

		Assert.assertEquals(3, resumoConsulta.getMedicamentos().size());
		ResABMedicamento resABMedicamento1 = resumoConsulta.getMedicamentos().get(0);
		Assert.assertNull(resABMedicamento1.getNomeMedicamento());
		Assert.assertNull(resABMedicamento1.getCodigoMedicamentoCatmat());
		Assert.assertNull(resABMedicamento1.getDescricaoFormaFarmaceutica());
		Assert.assertNull(resABMedicamento1.getCodigoFormaFarmaceutica());
		Assert.assertNull(resABMedicamento1.getDescricaoViaAdministracao());
		Assert.assertNull(resABMedicamento1.getCodigoViaAdministracao());
		Assert.assertNull(resABMedicamento1.getDescricaoDose());
		Assert.assertNull(resABMedicamento1.getDuracaoTratamento());
		Assert.assertNull(resABMedicamento1.getEstadoMedicamento());

		ResABMedicamento resABMedicamento2 = resumoConsulta.getMedicamentos().get(1);
		Assert.assertNull(resABMedicamento2.getNomeMedicamento());
		Assert.assertNull(resABMedicamento2.getCodigoMedicamentoCatmat());
		Assert.assertNull(resABMedicamento2.getDescricaoFormaFarmaceutica());
		Assert.assertNull(resABMedicamento2.getCodigoFormaFarmaceutica());
		Assert.assertNull(resABMedicamento2.getDescricaoViaAdministracao());
		Assert.assertNull(resABMedicamento2.getCodigoViaAdministracao());
		Assert.assertNull(resABMedicamento2.getDescricaoDose());
		Assert.assertNull(resABMedicamento2.getDuracaoTratamento());
		Assert.assertNull(resABMedicamento2.getEstadoMedicamento());

		ResABMedicamento resABMedicamento3 = resumoConsulta.getMedicamentos().get(2);
		Assert.assertEquals("ALBENDAZOL 200 mg comprimido", resABMedicamento3.getNomeMedicamento());
		Assert.assertEquals("BR0269628", resABMedicamento3.getCodigoMedicamentoCatmat());
		Assert.assertEquals("ALBENDAZOL 200 mg comprimido", resABMedicamento3.getDescricaoFormaFarmaceutica());
		Assert.assertEquals("BR0269628", resABMedicamento3.getCodigoFormaFarmaceutica());
		Assert.assertEquals("Oral", resABMedicamento3.getDescricaoViaAdministracao());
		Assert.assertEquals("25", resABMedicamento3.getCodigoViaAdministracao());
		Assert.assertEquals("1 comp 30 min antes do almoço", resABMedicamento3.getDescricaoDose());
		Assert.assertNull(resABMedicamento3.getDuracaoTratamento());
		Assert.assertNull(resABMedicamento3.getEstadoMedicamento());

		Assert.assertEquals(ResABTipoAtendimentoEnum.CONSULTA_AGENDADA_PROGRAMADA_CUIDADO_CONTINUADO, resumoConsulta.getTipoAtendimento());

	}

	@Test
	public void CDT003() throws Exception {
		// XML apenas com as TAGs obrigatorias
		String pathFile = this.PATH_TEST_RESOURCE + "/TestConvertXMLToResABResumoConsulta/CDT003.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
		// Assert.assertNull(resumoConsulta.getTurno());
		Assert.assertNull(resumoConsulta.getCnes());
		Assert.assertNull(resumoConsulta.getDataAtendimento());
		Assert.assertNull(resumoConsulta.getAltura());
		Assert.assertNull(resumoConsulta.getPerimetroCefalico());
		Assert.assertNull(resumoConsulta.getDum());
		Assert.assertNull(resumoConsulta.getTipoAtendimento());
		Assert.assertNull(resumoConsulta.getPeso());
		Assert.assertNull(resumoConsulta.getAleitamentoMaterno());
		Assert.assertNull(resumoConsulta.getGestasPrevias());
		Assert.assertNull(resumoConsulta.getIdadeGestacional());
		Assert.assertNull(resumoConsulta.getIne());
		Assert.assertNull(resumoConsulta.getPartos());
		Assert.assertEquals(0, resumoConsulta.getEncaminhamentos().size());
		Assert.assertEquals(0, resumoConsulta.getAlergias().size());
		Assert.assertEquals(0, resumoConsulta.getCondutas().size());
		Assert.assertEquals(0, resumoConsulta.getMedicamentos().size());
		Assert.assertEquals(0, resumoConsulta.getProfissionais().size());
		Assert.assertEquals(0, resumoConsulta.getProblemasDiagnosticos().size());
		Assert.assertEquals(0, resumoConsulta.getProcedimentos().size());
	}

	@Test
	public void CDT004() throws Exception {
		// Arquivo XML sem algumas tags
		String pathFile = this.PATH_TEST_RESOURCE + "/TestConvertXMLToResABResumoConsulta/CDT004.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));

		Assert.assertEquals(ResABTipoAtendimentoEnum.DEMANDA_ESPONTANEA_CONSULTA_NO_DIA, resumoConsulta.getTipoAtendimento());
		// Assert.assertNull(resumoConsulta.getTurno());
		Assert.assertNull(resumoConsulta.getCnes());

		Assert.assertEquals(1, resumoConsulta.getProfissionais().size());
		ResABIdentificacaoProfissional resABIdentificacaoProfissional1 = resumoConsulta.getProfissionais().get(0);
		Assert.assertEquals("703006821798000", resABIdentificacaoProfissional1.getCns());
		Assert.assertEquals("1235-65", resABIdentificacaoProfissional1.getCbo());
		Assert.assertNull(resABIdentificacaoProfissional1.getNome());
		Assert.assertEquals(false, resABIdentificacaoProfissional1.isResponsavel());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String dateInString = "2016-05-20T15:00:00.000-03:00";
		Assert.assertEquals(formatter.parse(dateInString), resumoConsulta.getDataAtendimento());

		Assert.assertEquals(2, resumoConsulta.getAlergias().size());

		ResABAlergiaReacoes resABAlergiaReacoes1 = resumoConsulta.getAlergias().get(0);
		Assert.assertNull(resABAlergiaReacoes1.getCriticidade());
		Assert.assertEquals("Alimento", resABAlergiaReacoes1.getCategoria());
		Assert.assertEquals("leite", resABAlergiaReacoes1.getAgente());
		Assert.assertEquals(0, resABAlergiaReacoes1.getEventoReacao().size());

		ResABAlergiaReacoes resABAlergiaReacoes2 = resumoConsulta.getAlergias().get(1);
		Assert.assertNull(resABAlergiaReacoes2.getCriticidade());
		Assert.assertEquals("Remédio", resABAlergiaReacoes2.getCategoria());
		Assert.assertEquals("engove", resABAlergiaReacoes2.getAgente());
		Assert.assertEquals(1, resABAlergiaReacoes2.getEventoReacao().size());

		ResABEventoReacao resABAlergia2EventoReacao1 = resABAlergiaReacoes2.getEventoReacao().get(0);
		Assert.assertNull(resABAlergia2EventoReacao1.getEvolucaoAlergia());
		Assert.assertNull(resABAlergia2EventoReacao1.getDataInstalacao());
		Assert.assertNull(resABAlergia2EventoReacao1.getManifestacao());

		Assert.assertNull(resumoConsulta.getAltura());
		Assert.assertNull(resumoConsulta.getAleitamentoMaterno());
		Assert.assertNull(resumoConsulta.getPerimetroCefalico());
		Assert.assertEquals(0, resumoConsulta.getCondutas().size());
		Assert.assertNull(resumoConsulta.getDum());

		Assert.assertEquals(0, resumoConsulta.getEncaminhamentos().size());

		Assert.assertNull(resumoConsulta.getGestasPrevias());
		Assert.assertNull(resumoConsulta.getIdadeGestacional());
		Assert.assertNull(resumoConsulta.getIne());
		Assert.assertNull(resumoConsulta.getPartos());

		Assert.assertEquals(0, resumoConsulta.getMedicamentos().size());
		Assert.assertNull(resumoConsulta.getPeso());

		Assert.assertEquals(2, resumoConsulta.getProblemasDiagnosticos().size());

		ResABProblemaDiagnostico resABProblemaDiagnostico1 = resumoConsulta.getProblemasDiagnosticos().get(0);
		Assert.assertNull(resABProblemaDiagnostico1.getCodigo());
		Assert.assertNull(resABProblemaDiagnostico1.getDescricao());
		Assert.assertNull(resABProblemaDiagnostico1.getTipo());

		ResABProblemaDiagnostico resABProblemaDiagnostico2 = resumoConsulta.getProblemasDiagnosticos().get(1);
		Assert.assertEquals("A920", resABProblemaDiagnostico2.getCodigo());
		Assert.assertEquals("Febre de Chikungunya", resABProblemaDiagnostico2.getDescricao());
		Assert.assertEquals(ResTipoProblemaDiagnostico.CID10, resABProblemaDiagnostico2.getTipo());

		Assert.assertEquals(0, resumoConsulta.getProcedimentos().size());
	}
}

package br.ufsc.bridge.res.repository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.res.dab.domain.ResABAleitamentoMaternoEnum;
import br.ufsc.bridge.res.dab.domain.ResABCondutaEnum;
import br.ufsc.bridge.res.dab.domain.ResABEstadoMedicamentoEnum;
import br.ufsc.bridge.res.dab.domain.ResABGravidadeEnum;
import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.domain.ResABTipoProblemaDiagnostico;
import br.ufsc.bridge.res.dab.domain.ResABTurnoEnum;
import br.ufsc.bridge.res.dab.dto.ResABAlergiaReacoes;
import br.ufsc.bridge.res.dab.dto.ResABEventoReacao;
import br.ufsc.bridge.res.dab.dto.ResABIdentificacaoProfissional;
import br.ufsc.bridge.res.dab.dto.ResABMedicamento;
import br.ufsc.bridge.res.dab.dto.ResABProblemaDiagnostico;
import br.ufsc.bridge.res.dab.dto.ResABProcedimento;
import br.ufsc.bridge.res.dab.dto.ResABResumoConsulta;

public class TestConvertResABResumoConsultaToXML {

	String PATH_TEST_RESOURCE = System.getProperty("user.dir") + "/src/test/resources/repository/";

	@Test
	public void CDT001() throws Exception {
		// Dados minimos de um atendimento

		String pathFile = this.PATH_TEST_RESOURCE + "/TestConvertXMLToResABResumoConsulta/CDT001.xml";
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

		Assert.assertEquals(IOUtils.toString(resourceAsStream).replace("\n", "").replace("\r", "").replace("\t", ""), resABResumoConsulta.getXml());

		ResABResumoConsulta resABResumoConsultaRecuperado = new ResABResumoConsulta(resABResumoConsulta.getXml());

		Assert.assertEquals(resABResumoConsulta, resABResumoConsultaRecuperado);
	}

	@Test
	public void CDT002() throws Exception {
		// Dados completos de um atendimento
		String pathFile = this.PATH_TEST_RESOURCE + "atendimentoCompleto.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);

		ResABResumoConsulta resumoConsulta = new ResABResumoConsulta();
		resumoConsulta.setTipoAtendimento(ResABTipoAtendimentoEnum.CONSULTA_AGENDADA);
		resumoConsulta.setCnes("4254160");
		resumoConsulta.setIne("1282844269");

		resumoConsulta.setProfissionais(this.getProfissionaisCDT002());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String dateInString = "2016-05-23T15:00:00.000-03:00";
		resumoConsulta.setDataAtendimento(formatter.parse(dateInString));

		resumoConsulta.setTurno(ResABTurnoEnum.NOITE);
		resumoConsulta.setPeso("994.250");
		resumoConsulta.setAltura("987.55");
		resumoConsulta.setPerimetroCefalico("43");
		resumoConsulta.setAleitamentoMaterno(ResABAleitamentoMaternoEnum.EXCLUSIVO);

		formatter = new SimpleDateFormat("yyyy-MM-dd");
		dateInString = "2016-03-16";
		resumoConsulta.setDum(formatter.parse(dateInString));

		resumoConsulta.setIdadeGestacional("P9W5D");
		resumoConsulta.setGestasPrevias("2");
		resumoConsulta.setPartos("1");

		resumoConsulta.setProblemasDiagnosticos(this.getProblemasCDT002());
		resumoConsulta.setAlergias(this.getAlergiasCDT002());
		resumoConsulta.setProcedimentos(this.getProcedimentosCDT002());
		resumoConsulta.setMedicamentos(this.getMedicamentosCDT002());

		List<ResABCondutaEnum> condutas = new ArrayList<>();
		condutas.add(ResABCondutaEnum.RETORNO_PARA_CUIDADO_CONTINUADO_PROGRAMADO);
		condutas.add(ResABCondutaEnum.ALTA_DO_EPISODIO);
		resumoConsulta.setCondutas(condutas);

		List<String> encaminhamentos = new ArrayList<>();
		encaminhamentos.add("Interno no dia");
		encaminhamentos.add("Externo no dia");
		encaminhamentos.add("Agendamento");
		resumoConsulta.setEncaminhamentos(encaminhamentos);

		Assert.assertEquals(IOUtils.toString(resourceAsStream).replace("\n", "").replace("\r", "").replace("\t", ""), resumoConsulta.getXml());

		ResABResumoConsulta resABResumoConsultaRecuperado = new ResABResumoConsulta(resumoConsulta.getXml());

		Assert.assertEquals(resumoConsulta, resABResumoConsultaRecuperado);
	}

	private List<ResABMedicamento> getMedicamentosCDT002() {
		List<ResABMedicamento> medicamentos = new ArrayList<>();
		ResABMedicamento medicamento = new ResABMedicamento();
		medicamento.setNomeMedicamento("ALBENDAZOL 200 mg comprimido");
		medicamento.setCodigoMedicamentoCatmat("BR0269628");
		medicamento.setDescricaoFormaFarmaceutica("ALBENDAZOL 200 mg cmp FF");
		medicamento.setCodigoFormaFarmaceutica("BR0269629");
		medicamento.setDescricaoViaAdministracao("Oral");
		medicamento.setCodigoViaAdministracao("25");
		medicamento.setDescricaoDose("1 comp 30 min antes do almoço");
		medicamento.setDuracaoTratamento("P30D");
		medicamento.setEstadoMedicamento(ResABEstadoMedicamentoEnum.ATIVA);
		medicamentos.add(medicamento);

		medicamento = new ResABMedicamento();
		medicamento.setNomeMedicamento("ACITRETINA 10 mg cápsula");
		medicamento.setCodigoMedicamentoCatmat("BR038719");
		medicamento.setDescricaoFormaFarmaceutica("ACITRETINA");
		medicamento.setCodigoFormaFarmaceutica("BR038720");
		medicamento.setDescricaoViaAdministracao("NASAL");
		medicamento.setCodigoViaAdministracao("29");
		medicamento.setDescricaoDose("1 comp intervalo 3");
		medicamento.setDuracaoTratamento("P5W");
		medicamento.setEstadoMedicamento(ResABEstadoMedicamentoEnum.TRATAMENTO_COMPLETO);
		medicamentos.add(medicamento);
		return medicamentos;
	}

	private List<ResABProcedimento> getProcedimentosCDT002() {
		List<ResABProcedimento> procedimentos = new ArrayList<>();
		ResABProcedimento procedimento = new ResABProcedimento();

		procedimento.setCodigo("18512000");
		procedimento.setNome("TERAPIA INDIVIDUAL");
		procedimentos.add(procedimento);

		procedimento = new ResABProcedimento();
		procedimento.setCodigo("167376008");
		procedimento.setNome("TESTE RÁPIDO DE GRAVIDEZ");
		procedimentos.add(procedimento);

		procedimento = new ResABProcedimento();
		procedimento.setCodigo("122869004");
		procedimento.setNome("AVALIAÇÃO ANTROPOMÉTRICA");
		procedimentos.add(procedimento);

		procedimento = new ResABProcedimento();
		procedimento.setCodigo("433465004");
		procedimento.setNome("COLETA DE MATERIAL P/ EXAME LABORATORIAL");
		procedimentos.add(procedimento);
		return procedimentos;
	}

	private List<ResABAlergiaReacoes> getAlergiasCDT002() throws ParseException {
		SimpleDateFormat formatter;
		String dateInString;
		List<ResABAlergiaReacoes> alergias = new ArrayList<>();

		ResABAlergiaReacoes alergia1 = new ResABAlergiaReacoes();
		alergia1.setAgente("leite");
		alergia1.setCategoria("Alimento");
		alergia1.setGravidade(ResABGravidadeEnum.BAIXO);

		List<ResABEventoReacao> reacoesAlergia1 = new ArrayList<>();
		ResABEventoReacao reacao1Alergia1 = new ResABEventoReacao();
		formatter = new SimpleDateFormat("yyyy-MM");
		dateInString = "2014-05";
		reacao1Alergia1.setDataInstalacao(formatter.parse(dateInString));
		reacao1Alergia1.setManifestacao("início há 2 anos, percebeu mau estar após ingestão de leite e derivados");
		reacao1Alergia1.setEvolucaoAlergia("sob controle");
		reacoesAlergia1.add(reacao1Alergia1);

		formatter = new SimpleDateFormat("yyyy-MM-dd");
		dateInString = "2016-07-09";
		ResABEventoReacao reacao2Alergia1 = new ResABEventoReacao();
		reacao2Alergia1.setDataInstalacao(formatter.parse(dateInString));
		reacao2Alergia1.setManifestacao("início mês passado, percebeu piora após ingestão de leite e derivados");
		reacao2Alergia1.setEvolucaoAlergia("sob cautela");
		reacoesAlergia1.add(reacao2Alergia1);

		alergia1.setEventoReacao(reacoesAlergia1);
		alergias.add(alergia1);

		ResABAlergiaReacoes alergia2 = new ResABAlergiaReacoes();
		alergia2.setAgente("metamizol");
		alergia2.setCategoria("Remedio");
		alergia2.setGravidade(ResABGravidadeEnum.ALTO);

		List<ResABEventoReacao> reacoesAlergia2 = new ArrayList<>();
		ResABEventoReacao reacao1Alergia2 = new ResABEventoReacao();
		formatter = new SimpleDateFormat("yyyy-MM");
		dateInString = "2013-09";
		reacao1Alergia2.setDataInstalacao(formatter.parse(dateInString));
		reacao1Alergia2.setManifestacao("início há 3 anos, percebeu mau estar após utilização do componente");
		reacao1Alergia2.setEvolucaoAlergia("situação de alto risco");
		reacoesAlergia2.add(reacao1Alergia2);

		ResABEventoReacao reacao2Alergia2 = new ResABEventoReacao();
		reacao2Alergia2.setManifestacao("piora nos meses seguintes");
		reacao2Alergia2.setEvolucaoAlergia("sob atenção");
		reacoesAlergia2.add(reacao2Alergia2);

		alergia2.setEventoReacao(reacoesAlergia2);
		alergias.add(alergia2);
		return alergias;
	}

	private List<ResABProblemaDiagnostico> getProblemasCDT002() {
		List<ResABProblemaDiagnostico> problemas = new ArrayList<>();
		ResABProblemaDiagnostico problema = new ResABProblemaDiagnostico();

		problema.setCodigo("A920");
		problema.setDescricao("Febre de Chikungunya");
		problema.setTipo(ResABTipoProblemaDiagnostico.CID10);
		problemas.add(problema);

		problema = new ResABProblemaDiagnostico();
		problema.setCodigo("T28");
		problema.setDescricao("LIMITAÇÃO FUNCIONAL/INCAPACIDADE");
		problema.setTipo(ResABTipoProblemaDiagnostico.CIAP);
		problemas.add(problema);

		problema = new ResABProblemaDiagnostico();
		problema.setDescricao("ABORTO ESPONTÂNEO - COMPLETO OU NÃO ESPEC., COM OUTRAS COMPLICAÇÕES OU COM COMPLICAÇÕES NÃO ESPECIF.");
		problema.setTipo(ResABTipoProblemaDiagnostico.CID10);
		problema.setCodigo("O038");
		problemas.add(problema);
		return problemas;
	}

	private List<ResABIdentificacaoProfissional> getProfissionaisCDT002() {
		ResABIdentificacaoProfissional profissional = new ResABIdentificacaoProfissional();
		List<ResABIdentificacaoProfissional> profissionais = new ArrayList<>();
		profissional.setCns("703006821796679");
		profissional.setCbo("2235-65");
		profissional.setResponsavel(true);
		profissionais.add(profissional);

		profissional = new ResABIdentificacaoProfissional();
		profissional.setCns("707000801749036");
		profissional.setCbo("2236-05");
		profissional.setResponsavel(false);
		profissionais.add(profissional);

		profissional = new ResABIdentificacaoProfissional();
		profissional.setCns("898001153249911");
		profissional.setCbo("2235-64");
		profissional.setResponsavel(true);
		profissionais.add(profissional);
		return profissionais;
	}
}
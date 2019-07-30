package br.ufsc.bridge.res.write;

import java.util.Date;

import org.junit.Test;

import br.ufsc.bridge.res.dab.domain.ResABCriticidadeEnum;
import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.write.builder.ResumoConsultaABBuilder;
import br.ufsc.bridge.res.dab.write.builder.caracterizacaoconsulta.CaracterizacaoConsultaABBuilder;

public class ResumoConsultaABTest {

	@Test
	public void test2() {
		ResumoConsultaABBuilder abBuilder = new ResumoConsultaABBuilder().data(new Date());
		//@formatter:off
		CaracterizacaoConsultaABBuilder<ResumoConsultaABBuilder> caracterizacaoConsulta = abBuilder.caracterizacaoConsulta();
		caracterizacaoConsulta
				.tipoAtendimento(ResABTipoAtendimentoEnum.CONSULTA_AGENDADA_PROGRAMADA_CUIDADO_CONTINUADO)
				.localizacaoAtribuidaPaciente("aaaa", "ine")
				.identificacaoProfissional()
					.cns("123456789")
					.cbo("000000")
					.responsavel(true)
				.close()
				.dataHoraAdmissao(new Date())
//				.turnoAtendimento(ResABTurnoEnum.MANHA)
			.close()
			.medicoesObservacoes()
				.avaliacaoAntropometrica()
					.pesoCorporal(new Date(), "53")
					.altura(new Date(), "33")
				.close()
//				.gestante()
//					.cicloMenstrual(new Date(), new Date())
//					.gestacao(new Date(), "22")
//					.sumarioObstetrico("asdf", "fdas")
//				.close()
			.close()
			.problemaDiagnostico()
				.problema()
					.descricao("fdp")
					.tipo("wtf")
					.codigo("madafaka")
				.close()
				.problema()
					.descricao("fdp2")
					.tipo("wtf2")
					.codigo("madafaka2")
				.close()
			.close()
			.alergiaReacaoAdversa()
				.alergia()
					.agente("porcaria")
					.categoria("filha da putagem")
					.gravidade(ResABCriticidadeEnum.ALTO)
					.eventoReacao()
						.dataInstalacao(new Date())
//						.evolucaoAlergia("evoluiu para um alien")
						.manifestacao("ser saindo do embigo")
					.close()
				.close()
			.close()
			.procedimentosPequenasCirurgias()
				.procedimento()
					.nome("CONSULTA MEDICA EM ATENÇAO BASICA")
					.data(new Date())
					.codigo("0301010064")
//					.resultadoObservacoes(Arrays.asList("nada consta"))
				.close()
				.procedimento()
					.nome("AVALIAÇÃO ANTROPOMÉTRICA")
					.data(new Date())
					.codigo("122869004")
//					.resultadoObservacoes(Arrays.asList("nada consta1","nada consta2","nada consta3"))
				.close()
			.close()
			.listaMedicamentos()
				.itemMedicacao()
					.medicamento("LAMIVUDINA 10 mg solução oral", "BR0328810")
					.formaFarmaceutica("LAMIVUDINA 10 mg solução oral", "BR0328810")
					.viaAdministracao("Oral", "25")
					.dose("1 comp 30 min antes do almoço")
					.doseEstruturada("P30D")
				.close()
				.itemMedicacao()
					.medicamento("LERCANIDIPINO 10 mg comprimido", "BR0272229")
					.formaFarmaceutica("LERCANIDIPINO 10 mg comprimido", "BR0272229")
					.viaAdministracao("Oral", "25")
					.dose("1 comp 30 min antes do almoço")
					.doseEstruturada("P30D")
				.close()
			.close()
			.dadosDesfecho()
				.conduta("teste")
				.solicitacoesEncaminhamento()
					.encaminhamento("Interno no dia I")
					.encaminhamento("Interno no dia II")
				.close()
				.solicitacoesEncaminhamento()
					.encaminhamento("Testes II")
				.close()
			.close();

	}
}

package br.ufsc.bridge.res.write;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import br.ufsc.bridge.res.dab.domain.ResABAleitamentoMaternoEnum;
import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.domain.ResCriticidadeEnum;
import br.ufsc.bridge.res.dab.dto.ResABResumoConsulta;
import br.ufsc.bridge.res.dab.writer.json.CaracterizacaoConsultaABJsonBuilder;
import br.ufsc.bridge.res.dab.writer.json.ResumoConsultaABJsonBuilder;
import br.ufsc.bridge.res.util.json.ResJsonUtils;

public class ResumoConsultaABJsonTest {

	@Test
	public void test() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {

		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("exemplo-clinico.json");
		ResABResumoConsulta resumo = ResJsonUtils.readJson(IOUtils.toString(resourceAsStream), ResABResumoConsulta.class);
		System.out.println(resumo);
	}

	@Test
	public void test2() throws IOException {
		ResumoConsultaABJsonBuilder abBuilder = new ResumoConsultaABJsonBuilder().data(new Date());
		//@formatter:off
		CaracterizacaoConsultaABJsonBuilder<ResumoConsultaABJsonBuilder> caracterizacaoConsulta = abBuilder.caracterizacaoConsulta();
		caracterizacaoConsulta
				.tipoAtendimento(ResABTipoAtendimentoEnum.CONSULTA_AGENDADA_PROGRAMADA_CUIDADO_CONTINUADO)
				.localizacaoAtribuidaPaciente("aaaa", "ine")
				.profissional()
					.nome("testeeee")
					.cns("123456789")
					.cbo("000000")
					.cboDescricao("médico")
					.responsavel(true)
				.close()
				.profissional()
					.nome("testeeee 2")
					.cns("2 123456789")
					.cbo("2 000000")
					.cboDescricao("2 médico")
					.responsavel(false)
				.close()
				.dataHoraAdmissao(new Date())
			.close()
			.medicoesObservacoes()
				.pesoCorporal(new Date(), "11")
				.altura(new Date(), "22")
				.perimetroCefalico(new Date(), "33")
				.cicloMenstrual(new Date(), new Date())
				.gestacao(new Date(), "44")
				.sumarioObstetrico("555", "666")
				.aleitamentoMaterno(new Date(), ResABAleitamentoMaternoEnum.COMPLEMENTADO)
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
			.alergiaReacao()
				.alergia()
					.agente("porcaria")
					.categoria("filha da putagem")
					.criticidade(ResCriticidadeEnum.ALTO)
					.evento()
						.dataInstalacao(new Date())
						.evolucao("evoluiu para um alien")
						.manifestacao("ser saindo do embigo")
					.close()
					.evento()
						.evolucao("alien predador")
					.close()
				.close()
				.alergia()
					.agente("porcaria 2")
					.categoria("mais filha da putagem")
					.evento()
						.dataInstalacao(null)
						.evolucao(null)
						.dataInstalacao(null)
					.close()
				.close()
			.close()
			.procedimentoRealizado()
				.procedimento()
					.descricao("CONSULTA MEDICA EM ATENÇAO BASICA")
					.data(new Date())
					.codigo("0301010064")
				.close()
				.procedimento()
					.descricao("ultrasonografia alienigina")
					.data(new Date())
					.codigo("007")
				.close()
			.close()
			.prescricaoAtendimento()
				.medicamentoNaoEstruturado()
					.medicamentos(Arrays.asList("medi 1", "medi 2"))
				.close()
			.close()
//			.listaMedicamentos()
//				.itemMedicacao()
//					.medicamento("LAMIVUDINA 10 mg solução oral", "BR0328810")
//					.formaFarmaceutica("LAMIVUDINA 10 mg solução oral", "BR0328810")
//					.viaAdministracao("Oral", "25")
//					.dose("1 comp 30 min antes do almoço")
//					.doseEstruturada("P30D")
//				.close()
//				.itemMedicacao()
//					.medicamento("LERCANIDIPINO 10 mg comprimido", "BR0272229")
//					.formaFarmaceutica("LERCANIDIPINO 10 mg comprimido", "BR0272229")
//					.viaAdministracao("Oral", "25")
//					.dose("1 comp 30 min antes do almoço")
//					.doseEstruturada("P30D")
//				.close()
//			.close()
			.dadosDesfecho()
				.desfecho()
					.codigo("1234")
					.descricao("destruição global")
				.close()
				.desfecho()
					.codigo("4321")
					.descricao("destruir alguma coisa")
				.close()
			.close();

		String jsonString = abBuilder.getJsonString();
		System.out.println("--------------------------");
		System.out.println(jsonString);
	}
}

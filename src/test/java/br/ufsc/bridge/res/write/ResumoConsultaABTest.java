package br.ufsc.bridge.res.write;

import java.util.Date;

import org.junit.Test;

import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.domain.ResABTurnoEnum;
import br.ufsc.bridge.res.dab.write.ResumoConsultaABBuilder;
import br.ufsc.bridge.res.dab.write.caracterizacaoconsulta.CaracterizacaoConsultaABBuilder;

public class ResumoConsultaABTest {

	@Test
	public void test2() {
		ResumoConsultaABBuilder abBuilder = new ResumoConsultaABBuilder();
		//@formatter:off
		CaracterizacaoConsultaABBuilder<ResumoConsultaABBuilder> caracterizacaoConsulta = abBuilder.caracterizacaoConsulta();
		caracterizacaoConsulta
				.tipoAtendimento(ResABTipoAtendimentoEnum.CONSULTA_AGENDADA_PROGRAMADA_CUIDADO_CONTINUADO)
				.cnes("aaaa")
				.identificacaoProfissional()
					.cns("123456789")
					.cbo("000000")
					.responsavel(true)
				.close()
				.ine("321")
				.dataHoraAdmissao(new Date())
				.turnoAtendimento(ResABTurnoEnum.MANHA)
			.close()
			.medicoesObservacoes()
				.avaliacaoAntropometrica()
					.pesoCorporal(new Date(), "53")
					.altura(new Date(), "33")
				.close()
				.gestante()
					.cicloMenstrual(new Date(), new Date())
					.gestacao("22", new Date())
					.sumarioObstetrico("asdf", "fdas")
				.close()

			.close()

				;
		System.out.println(abBuilder.getXmlContent());


	}

}

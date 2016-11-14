package br.ufsc.bridge.res.write;

import org.junit.Test;

import br.ufsc.bridge.res.dab.ResumoConsultaAB;
import br.ufsc.bridge.res.dab.caracterizacaoconsulta.tipoatendimento.TipoAtendimentoEnum;

public class ResumoConsultaABTest {

	@Test
	public void test() {
		System.out.println(new ResumoConsultaAB.Builder(TipoAtendimentoEnum.AT0138).cnesLocalAtendimento("2017520").build().getXmlContent());
	}

}

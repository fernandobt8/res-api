package br.ufsc.bridge.res.read;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import br.ufsc.bridge.res.dispensacaomedicamentos.dto.DispensacaoMedicamentos;
import br.ufsc.bridge.res.dispensacaomedicamentos.dto.OrigemRegistroDispensacao;
import br.ufsc.bridge.res.util.json.ResJsonUtils;

public class DispensacaoMedicamentosJsonTest {

	private DispensacaoMedicamentos form;

	@Before
	public void setup() throws IOException {
		this.form = ResJsonUtils.readJson(
				Base64.encodeBase64String(IOUtils.toString(DispensacaoMedicamentosJsonTest.class.getResourceAsStream("/exemplo-dispensacao-medicamentos.json")).getBytes()),
				DispensacaoMedicamentos.class);
	}

	@Test
	public void checkReadValues() {
		//caracterizacao atendimento
		//estabelecimento
		//horadispensacao
		assertEquals(OrigemRegistroDispensacao.MUNICIPIO_SISTEMA_NACIONAL, this.form.getCaracterizacaoAtendimento().getOrigemRegistro());

		//lista medicamentos

		//lista insumos

		//peso
		//altura
		assertEquals("O241 - Diabetes mellitus pré-existente, não-insulino-dependente", this.form.getDiagnostico());

		//responsavel prescricao
		assertEquals("MARLI DOS SANTOS NEVES DAS NEVES", this.form.getProfissionalResponsavelPrescricao().getNome());
		assertEquals("203011369760008", this.form.getProfissionalResponsavelPrescricao().getCns());
		assertEquals("225130 - Médico da família", this.form.getProfissionalResponsavelPrescricao().getCbo());

		//responsavel dispensacao
		assertEquals("Fulano da Silva", this.form.getProfissionalResponsavelDispensacao().getNome());
		assertEquals("0000", this.form.getProfissionalResponsavelDispensacao().getNumeroRegistro());
		assertEquals("CRF", this.form.getProfissionalResponsavelDispensacao().getTipoConselhoProfissional());
		assertEquals("DF", this.form.getProfissionalResponsavelDispensacao().getUnidadeFederativa());

	}

}

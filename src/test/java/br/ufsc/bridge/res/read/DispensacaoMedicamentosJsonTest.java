package br.ufsc.bridge.res.read;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import br.ufsc.bridge.res.dispensacaomedicamentos.dto.AssistenciaFarmaceutica;
import br.ufsc.bridge.res.dispensacaomedicamentos.dto.DispensacaoMedicamentos;
import br.ufsc.bridge.res.dispensacaomedicamentos.dto.EstadoMedicamento;
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
		assertEquals("18264881000113", this.form.getCaracterizacaoAtendimento().getEstabelecimentoSaude());
		assertEquals(new Date(1567987200000L), this.form.getCaracterizacaoAtendimento().getHoraDispensacao());
		assertEquals(OrigemRegistroDispensacao.MUNICIPIO_SISTEMA_NACIONAL, this.form.getCaracterizacaoAtendimento().getOrigemRegistro());

		//lista medicamentos
		assertEquals(1, this.form.getMedicamentos().size());
		assertEquals("PT0S", this.form.getMedicamentos().get(0).getDuracaoUso());
		assertEquals("99007027000173", this.form.getMedicamentos().get(0).getFabricamente());
		assertEquals("Tomar 1 comprimido todas as manhãs em jejum", this.form.getMedicamentos().get(0).getFrequenciaUso());
		assertEquals("1234ABCD", this.form.getMedicamentos().get(0).getLote());
		assertEquals("BR0267671U0042 - GLIBENCLAMIDA 5 MG COMPRIMIDO", this.form.getMedicamentos().get(0).getMedicamento());
		assertEquals("Anemia falciforme", this.form.getMedicamentos().get(0).getProgramaSaude());
		assertEquals(0.0, this.form.getMedicamentos().get(0).getQuantidadeUnidadeFarmaceutica(), 0.001);
		assertEquals("R$0,10", this.form.getMedicamentos().get(0).getValorUnitario());
		assertEquals(AssistenciaFarmaceutica.BASICO, this.form.getMedicamentos().get(0).getAssistenciaFarmaceutica());
		assertEquals(new Date(1631156400000L), this.form.getMedicamentos().get(0).getDataValidade());
		assertEquals(EstadoMedicamento.ATIVO, this.form.getMedicamentos().get(0).getEstado());

		//lista insumos
		assertEquals(1, this.form.getInsumosSaude().size());
		assertEquals("99007027000173", this.form.getInsumosSaude().get(0).getFabricante());
		assertEquals("1234ABCD", this.form.getInsumosSaude().get(0).getLote());
		assertEquals(new Date(1631156400000L), this.form.getInsumosSaude().get(0).getValidade());
		assertEquals("R$0,10", this.form.getInsumosSaude().get(0).getValorUnitario());
		assertEquals("Anemia falciforme", this.form.getInsumosSaude().get(0).getProgramaSaude());

		assertEquals(0.0, this.form.getAltura(), 0.001);
		assertEquals(0.0, this.form.getPeso(), 0.001);
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

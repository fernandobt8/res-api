package br.ufsc.bridge.res.read;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import br.ufsc.bridge.res.registroimunobiologico.dto.Estrategia;
import br.ufsc.bridge.res.registroimunobiologico.dto.RegistroImunobiologico;
import br.ufsc.bridge.res.registroimunobiologico.dto.SituacaoCondicao;
import br.ufsc.bridge.res.registroimunobiologico.dto.ViaAdministracao;
import br.ufsc.bridge.res.util.json.ResJsonUtils;

public class ResRegistroImunobiologicoJsonTest {

	private RegistroImunobiologico form;

	@Before
	public void setup() throws IOException {
		this.form = ResJsonUtils.readJson(
				Base64.encodeBase64String(IOUtils.toString(ResRegistroImunobiologicoJsonTest.class.getResourceAsStream("/exemplo-registro-imunobiologico.json")).getBytes()),
				RegistroImunobiologico.class);
	}

	@Test
	public void checkReadValues() {
		assertEquals(new Date(1568386442000L), this.form.getDataAtendimento());
		assertEquals(new Date(1568386442000L), this.form.getCaracterizacaoAtendimento().getHoraAtendimento());
		assertEquals("Unidade básica de saúde (UBS)", this.form.getCaracterizacaoAtendimento().getLocalAtendimento());
		assertEquals("2429837", this.form.getCaracterizacaoAtendimento().getCnes());
		assertEquals("0000151300", this.form.getCaracterizacaoAtendimento().getIne());
		assertEquals("701206094586019", this.form.getCaracterizacaoAtendimento().getCnsProfissional());
		assertEquals("CAMILA DE SOUZA BARBOSA", this.form.getCaracterizacaoAtendimento().getNomeProfissional());
		assertEquals("225142", this.form.getCaracterizacaoAtendimento().getCbo());
		assertEquals(SituacaoCondicao.GESTANTE, this.form.getRegistroImunizacao().getSituacaoCondicao());
		assertEquals("BCG", this.form.getRegistroImunizacao().getImunobiologico());
		assertEquals(Estrategia.ROTINA, this.form.getRegistroImunizacao().getEstrategia());
		assertEquals("DU", this.form.getRegistroImunizacao().getDose());
		assertEquals(ViaAdministracao.ENDOVENOSA, this.form.getRegistroImunizacao().getViaAdministracao());
		assertEquals("Rede venosa", this.form.getRegistroImunizacao().getLocalAplicacao());
		assertEquals("078VFA039Z", this.form.getRegistroImunizacao().getLote());
		assertEquals("FIOCRUZ", this.form.getRegistroImunizacao().getFabricante());
		assertEquals(new Date(1568332800000L), this.form.getDataAdministracao());

	}
}

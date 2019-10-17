package br.ufsc.bridge.res.read;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import br.ufsc.bridge.res.dab.dto.CaracterizacaoAtendimento;
import br.ufsc.bridge.res.registroimunobiologico.dto.Estrategia;
import br.ufsc.bridge.res.registroimunobiologico.dto.RegistroImunobiologico;
import br.ufsc.bridge.res.registroimunobiologico.dto.SituacaoCondicao;
import br.ufsc.bridge.res.registroimunobiologico.dto.ViaAdministracao;
import br.ufsc.bridge.res.util.json.ResJsonUtils;

public class ResRegistroImunobiologicoJsonTest {

	private RegistroImunobiologico form;

	@Test
	public void checkReadValues() throws IOException {
		this.form = ResJsonUtils.readJson(
				Base64.encodeBase64String(IOUtils.toString(ResRegistroImunobiologicoJsonTest.class.getResourceAsStream("/exemplo-registro-imunobiologico.json")).getBytes()),
				RegistroImunobiologico.class);

		assertEquals(new Date(1568386442000L), this.form.getDataAtendimento());
		assertEquals(new Date(1568386442000L), this.form.getCaracterizacaoAtendimento().getHoraAtendimento());
		assertEquals("Unidade básica de saúde (UBS)", this.form.getCaracterizacaoAtendimento().getLocalAtendimento());
		assertEquals("2429837", this.form.getCaracterizacaoAtendimento().getCnes());
		assertEquals("0000151300", this.form.getCaracterizacaoAtendimento().getIne());
		assertEquals("701206094586019", this.form.getCaracterizacaoAtendimento().getCnsProfissional());
		assertEquals("CAMILA DE SOUZA BARBOSA", this.form.getCaracterizacaoAtendimento().getNomeProfissional());
		assertEquals("225142", this.form.getCaracterizacaoAtendimento().getCbo());
		assertEquals(SituacaoCondicao.GESTANTE, this.form.getRegistroImunizacao().get(0).getSituacaoCondicao().get(0));
		assertEquals("BCG", this.form.getRegistroImunizacao().get(0).getImunobiologico());
		assertEquals(Estrategia.ROTINA, this.form.getRegistroImunizacao().get(0).getEstrategia());
		assertEquals("DU", this.form.getRegistroImunizacao().get(0).getDose());
		assertEquals(ViaAdministracao.ENDOVENOSA, this.form.getRegistroImunizacao().get(0).getViaAdministracao());
		assertEquals("Rede venosa", this.form.getRegistroImunizacao().get(0).getLocalAplicacao());
		assertEquals("078VFA039Z", this.form.getRegistroImunizacao().get(0).getLote());
		assertEquals("FIOCRUZ", this.form.getRegistroImunizacao().get(0).getFabricante());
		assertEquals(new Date(1568332800000L), this.form.getDataAdministracao());
		assertEquals(1, this.form.getRegistroImunizacao().size());
		assertEquals(1, this.form.getRegistroImunizacao().get(0).getSituacaoCondicao().size());
	}

	@Test
	public void checkWriteAllValues() throws IOException {
		this.form = new RegistroImunobiologico();
		CaracterizacaoAtendimento caracterizacaoAtendimento = new CaracterizacaoAtendimento();
		caracterizacaoAtendimento.setHoraAtendimento(new Date(1568386442000L));
		caracterizacaoAtendimento.setCbo("123456");
		caracterizacaoAtendimento.setCnes("789456");
		caracterizacaoAtendimento.setCnsProfissional("0123456789");
		caracterizacaoAtendimento.setIne("INE");
		caracterizacaoAtendimento.setLocalAtendimento("UBS");
		caracterizacaoAtendimento.setNomeProfissional("Carlos");
		this.form.setCaracterizacaoAtendimento(caracterizacaoAtendimento);

		String json = this.form.getJson();
		Files.write(Paths.get("/tmp/registro-imunobiologico.json"), json.getBytes());

		RegistroImunobiologico readJson = ResJsonUtils.readJson(
				Base64.encodeBase64String(Files.readAllBytes(Paths.get("/tmp/registro-imunobiologico.json"))),
				RegistroImunobiologico.class);

		assertEquals(new Date(1568386442000L), readJson.getCaracterizacaoAtendimento().getHoraAtendimento());
		assertEquals("UBS", readJson.getCaracterizacaoAtendimento().getLocalAtendimento());
		assertEquals("789456", readJson.getCaracterizacaoAtendimento().getCnes());
		assertEquals("INE", readJson.getCaracterizacaoAtendimento().getIne());
		assertEquals("0123456789", readJson.getCaracterizacaoAtendimento().getCnsProfissional());
		assertEquals("Carlos", readJson.getCaracterizacaoAtendimento().getNomeProfissional());
		assertEquals("123456", readJson.getCaracterizacaoAtendimento().getCbo());
	}

	@Test
	public void checkWriteRequiredValues() throws IOException {
		this.form = new RegistroImunobiologico();
		CaracterizacaoAtendimento caracterizacaoAtendimento = new CaracterizacaoAtendimento();
		caracterizacaoAtendimento.setCbo("123456");
		caracterizacaoAtendimento.setCnes("789456");
		caracterizacaoAtendimento.setCnsProfissional("0123456789");
		caracterizacaoAtendimento.setIne("INE");
		caracterizacaoAtendimento.setLocalAtendimento("UBS");
		this.form.setCaracterizacaoAtendimento(caracterizacaoAtendimento);

		String json = this.form.getJson();
		Files.write(Paths.get("/tmp/registro-imunobiologico.json"), json.getBytes());

		RegistroImunobiologico readJson = ResJsonUtils.readJson(
				Base64.encodeBase64String(Files.readAllBytes(Paths.get("/tmp/registro-imunobiologico.json"))),
				RegistroImunobiologico.class);

		assertFalse(json.contains("Hora do atendimento"));
		assertFalse(json.contains("Nome do profissional responsável"));
		assertNull(readJson.getCaracterizacaoAtendimento().getHoraAtendimento());
		assertEquals("UBS", readJson.getCaracterizacaoAtendimento().getLocalAtendimento());
		assertEquals("789456", readJson.getCaracterizacaoAtendimento().getCnes());
		assertEquals("INE", readJson.getCaracterizacaoAtendimento().getIne());
		assertEquals("0123456789", readJson.getCaracterizacaoAtendimento().getCnsProfissional());
		assertNull(readJson.getCaracterizacaoAtendimento().getNomeProfissional());
		assertEquals("123456", readJson.getCaracterizacaoAtendimento().getCbo());
	}

}

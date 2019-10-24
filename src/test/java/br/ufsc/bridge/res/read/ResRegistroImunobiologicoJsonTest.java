package br.ufsc.bridge.res.read;

import static java.util.Arrays.asList;
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
import br.ufsc.bridge.res.registroimunobiologico.dto.RegistroImunizacao;
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
		assertEquals(new Date(1568332800000L), this.form.getRegistroImunizacao().get(0).getDataAdministracao());
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

		RegistroImunizacao reg1 = new RegistroImunizacao();
		reg1.setDose("Dose1");
		reg1.setEstrategia(Estrategia.ROTINA);
		reg1.setFabricante("Fab1");
		reg1.setImunobiologico("Imun1");
		reg1.setLocalAplicacao("Local1");
		reg1.setLote("Lote1");
		reg1.setSituacaoCondicao(asList(SituacaoCondicao.PUERPERA, SituacaoCondicao.VIAJANTE));
		reg1.setViaAdministracao(ViaAdministracao.ENDOVENOSA);
		reg1.setDataAdministracao(new Date(1568386442000L));

		RegistroImunizacao reg2 = new RegistroImunizacao();
		reg2.setDose("Dose2");
		reg2.setEstrategia(Estrategia.CAMPANHA);
		reg2.setFabricante("Fab2");
		reg2.setImunobiologico("Imun2");
		reg2.setLocalAplicacao("Local2");
		reg2.setLote("Lote2");
		reg2.setSituacaoCondicao(asList(SituacaoCondicao.COMUNICANTE_HANSENIASE));
		reg2.setViaAdministracao(ViaAdministracao.INTRAMUSCULAR);
		reg2.setDataAdministracao(new Date(1568386442000L));

		this.form.setRegistroImunizacao(asList(reg1, reg2));

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

		assertEquals("Dose1", readJson.getRegistroImunizacao().get(0).getDose());
		assertEquals(Estrategia.ROTINA, readJson.getRegistroImunizacao().get(0).getEstrategia());
		assertEquals("Fab1", readJson.getRegistroImunizacao().get(0).getFabricante());
		assertEquals("Imun1", readJson.getRegistroImunizacao().get(0).getImunobiologico());
		assertEquals("Local1", readJson.getRegistroImunizacao().get(0).getLocalAplicacao());
		assertEquals("Lote1", readJson.getRegistroImunizacao().get(0).getLote());
		assertEquals(2, readJson.getRegistroImunizacao().get(0).getSituacaoCondicao().size());
		assertEquals(new Date(1568386442000L), readJson.getRegistroImunizacao().get(0).getDataAdministracao());
		assertEquals(SituacaoCondicao.PUERPERA, readJson.getRegistroImunizacao().get(0).getSituacaoCondicao().get(0));
		assertEquals(SituacaoCondicao.VIAJANTE, readJson.getRegistroImunizacao().get(0).getSituacaoCondicao().get(1));
		assertEquals(ViaAdministracao.ENDOVENOSA, readJson.getRegistroImunizacao().get(0).getViaAdministracao());

		assertEquals("Dose2", readJson.getRegistroImunizacao().get(1).getDose());
		assertEquals(Estrategia.CAMPANHA, readJson.getRegistroImunizacao().get(1).getEstrategia());
		assertEquals("Fab2", readJson.getRegistroImunizacao().get(1).getFabricante());
		assertEquals("Imun2", readJson.getRegistroImunizacao().get(1).getImunobiologico());
		assertEquals("Local2", readJson.getRegistroImunizacao().get(1).getLocalAplicacao());
		assertEquals("Lote2", readJson.getRegistroImunizacao().get(1).getLote());
		assertEquals(1, readJson.getRegistroImunizacao().get(1).getSituacaoCondicao().size());
		assertEquals(ViaAdministracao.INTRAMUSCULAR, readJson.getRegistroImunizacao().get(1).getViaAdministracao());
		assertEquals(new Date(1568386442000L), readJson.getRegistroImunizacao().get(1).getDataAdministracao());
		assertEquals(SituacaoCondicao.COMUNICANTE_HANSENIASE, readJson.getRegistroImunizacao().get(1).getSituacaoCondicao().get(0));
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

		RegistroImunizacao reg1 = new RegistroImunizacao();
		reg1.setDose("Dose1");
		reg1.setEstrategia(Estrategia.ROTINA);
		reg1.setImunobiologico("Imun1");
		reg1.setDataAdministracao(new Date(1568386442000L));
		this.form.setRegistroImunizacao(asList(reg1));

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

		assertFalse(json.contains("Situação/condição"));
		assertFalse(json.contains("Via de administração"));
		assertFalse(json.contains("Lote"));
		assertFalse(json.contains("Fabricante"));
		assertFalse(json.contains("Local de aplicação"));
		assertEquals("Dose1", readJson.getRegistroImunizacao().get(0).getDose());
		assertEquals(Estrategia.ROTINA, readJson.getRegistroImunizacao().get(0).getEstrategia());
		assertEquals("Imun1", readJson.getRegistroImunizacao().get(0).getImunobiologico());
		assertEquals(new Date(1568386442000L), readJson.getRegistroImunizacao().get(0).getDataAdministracao());
	}

}

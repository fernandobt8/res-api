package br.ufsc.bridge.res.reader;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufsc.bridge.res.dab.reader.EHRReader;

public class EHRReaderTest {

	private static String SIMPLE_JSON;

	private EHRReader<SimpleExampleDto> reader = new EHRReader<>(SimpleExampleDto.class);

	@Getter
	@Setter
	@NoArgsConstructor
	public static class SimpleExampleDto {

		private String type;

		private String value;

		private String version;

		private String templateId;

		private String archetype;

	}

	@BeforeClass
	public static void setup() throws IOException {
		SIMPLE_JSON = IOUtils.toString(EHRReaderTest.class.getResourceAsStream("/json/simple-json.json"));
	}

	@Test
	public void testSimpleJson() {
		SimpleExampleDto dto = (SimpleExampleDto) this.reader.from("items")
				.select("name.value").where("asdasd == asdas").as("value")
				.select("_type").as("type")
				.select("archetype_details.rm_version").as("version")
				.select("archetype_details.template_id.value").as("templateId")
				.select("archetype_details.archetype_id.value").as("archetype")
				.build(SIMPLE_JSON);

		assertEquals("ADMIN_ENTRY", dto.getType());
		assertEquals("Admissão do paciente", dto.getValue());
		assertEquals("SumariodeAlta.v4.0", dto.getTemplateId());
		assertEquals("1.0.1", dto.getVersion());
		assertEquals("openEHR-EHR-ADMIN_ENTRY.admission-ms_br.v2", dto.getArchetype());
	}

}

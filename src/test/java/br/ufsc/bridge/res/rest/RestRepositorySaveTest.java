package br.ufsc.bridge.res.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.ufsc.bridge.res.domain.Sort;
import br.ufsc.bridge.res.domain.TipoDocumento;
import br.ufsc.bridge.res.service.rest.repository.RestRepositoryService;
import br.ufsc.bridge.res.service.rest.repository.dto.ItemDTO;
import br.ufsc.bridge.res.service.rest.repository.dto.SaveDTO;

@RunWith(JUnit4.class)
public class RestRepositorySaveTest {

	private SaveDTO dto;

	private RestRepositoryService service = new RestRepositoryService(URL);

	private static final String URL = "https://ehr-services.rnds.mbamobi.com.br/ehr-services/fhir/r4/DocumentReference";

	private static final String PROFISSIONAL = "PractitionerRole/a4";

	private static final String UNIDADE = "Organization/a9";

	private static final String PACIENTE = "Patient/789";

	@Before
	public void setup() {
		this.dto = SaveDTO.builder()
				.data(new Date())
				.pacienteId(PACIENTE)
				.unidadeId(UNIDADE)
				.profissionalId(PROFISSIONAL)
				.documento(this.DATA)
				.build();
	}

	@Test
	public void save() {
		String uuid = this.service.save(this.dto);
		Assert.assertNotNull(uuid);
	}

	@Test
	public void read() {
		// given
		String uuid = this.service.save(this.dto);

		// when
		SaveDTO dto = this.service.read(uuid);

		// then
		// assertEquals(DATA, dto.getDocumento()); FIXME: O endpoint sempre retorna o mesmo doc
		assertEquals(PACIENTE, dto.getPacienteId());
		assertEquals(PROFISSIONAL, dto.getProfissionalId());
		assertEquals(UNIDADE, dto.getUnidadeId());

	}

	@Test
	public void listAsc() throws KeyManagementException, NoSuchAlgorithmException {
		// given
		//		String uuid1 = this.service.save(this.dto);
		//		String uuid2 = this.service.save(this.dto);
		//		SSLContext context = SSLContext.getInstance("TLSv1.2");
		//		context.init(null, null, null);
		//		SSLContext.setDefault(context);
		// when
		List<ItemDTO> result = this.service.list(PACIENTE, Sort.ASC);

		// then
		//		assertTrue(result.size() >= 10);
		//		assertThat(result, not(hasItem(new ItemDTO(uuid1, URL + "/" + uuid1, TipoDocumento.SUMARIO_DE_ALTA))));
		//		assertThat(result, not(hasItem(new ItemDTO(uuid2, URL + "/" + uuid2, TipoDocumento.SUMARIO_DE_ALTA))));
	}

	@Test
	public void listDesc() throws InterruptedException, KeyManagementException, NoSuchAlgorithmException {
		// given
		String uuid1 = this.service.save(this.dto);
		String uuid2 = this.service.save(this.dto);
		Thread.sleep(1000); // Workaround para dar tempo do doc ser criado

		// when
		List<ItemDTO> result = this.service.list(PACIENTE, Sort.DESC);

		// then
		assertTrue(result.size() >= 10);
		assertThat(result, hasItem(new ItemDTO(uuid1, URL + "/" + uuid1, TipoDocumento.SUMARIO_ALTA)));
		assertThat(result, hasItem(new ItemDTO(uuid2, URL + "/" + uuid2, TipoDocumento.SUMARIO_ALTA)));
	}

	private final String DATA =
			"ewogICAiQHNjaGVtYUxvY2F0aW9uIjogImh0dHA6Ly9zY2hlbWFzLm9jZWFuZWhyLmNvbS90ZW1wbGF0ZXMgUmVzdWx0YWRvJTIwZGUlMjBFeGFtZXNfQ043X3YyLnhzZCIsCiAgICJAdGVtcGxhdGVfaWQiOiAiUmVzdWx0YWRvIGRlIEV4YW1lc19DTjdfdjIiLAogICAibmFtZSI6IHsKICAgICAgInZhbHVlIjogIlJlc3VsdGFkbyBkZSBleGFtZXMiCiAgIH0sCiAgICJsYW5ndWFnZSI6IHsKICAgICAgInRlcm1pbm9sb2d5X2lkIjogewogICAgICAgICAidmFsdWUiOiAiSVNPXzYzOS0xIgogICAgICB9LAogICAgICAiY29kZV9zdHJpbmciOiAicHQiCiAgIH0sCiAgICJ0ZXJyaXRvcnkiOiB7CiAgICAgICJ0ZXJtaW5vbG9neV9pZCI6IHsKICAgICAgICAgInZhbHVlIjogIklTT18zMTY2LTEiCiAgICAgIH0sCiAgICAgICJjb2RlX3N0cmluZyI6ICJCUiIKICAgfSwKICAgImNhdGVnb3J5IjogewogICAgICAidmFsdWUiOiAiZXZlbnQiLAogICAgICAiZGVmaW5pbmdfY29kZSI6IHsKICAgICAgICAgInRlcm1pbm9sb2d5X2lkIjogewogICAgICAgICAgICAidmFsdWUiOiAib3BlbmVociIKICAgICAgICAgfSwKICAgICAgICAgImNvZGVfc3RyaW5nIjogIjQzMyIKICAgICAgfQogICB9LAogICAiY29tcG9zZXIiOiB7CiAgICAgICJAdHlwZSI6ICJvZTpQQVJUWV9TRUxGIgogICB9LAogICAiY29udGV4dCI6IHsKICAgICAgInN0YXJ0X3RpbWUiOiB7CiAgICAgICAgICJ2YWx1ZSI6ICIyMDE3LTA4LTE3VDEyOjU3OjMxLjAwMC0wMzowMCIKICAgICAgfSwKICAgICAgInNldHRpbmciOiB7CiAgICAgICAgICJ2YWx1ZSI6ICJvdGhlciBjYXJlIiwKICAgICAgICAgImRlZmluaW5nX2NvZGUiOiB7CiAgICAgICAgICAgICJ0ZXJtaW5vbG9neV9pZCI6IHsKICAgICAgICAgICAgICAgInZhbHVlIjogIm9wZW5laHIiCiAgICAgICAgICAgIH0sCiAgICAgICAgICAgICJjb2RlX3N0cmluZyI6ICIyMzgiCiAgICAgICAgIH0KICAgICAgfQogICB9LAogICAiUmVzdWx0YWRvX2RvX2V4YW1lIjogewogICAgICAibmFtZSI6IHsKICAgICAgICAgInZhbHVlIjogIlJlc3VsdGFkbyBkbyBleGFtZSIKICAgICAgfSwKICAgICAgImxhbmd1YWdlIjogewogICAgICAgICAidGVybWlub2xvZ3lfaWQiOiB7CiAgICAgICAgICAgICJ2YWx1ZSI6ICJJU09fNjM5LTEiCiAgICAgICAgIH0sCiAgICAgICAgICJjb2RlX3N0cmluZyI6ICJwdCIKICAgICAgfSwKICAgICAgImVuY29kaW5nIjogewogICAgICAgICAidGVybWlub2xvZ3lfaWQiOiB7CiAgICAgICAgICAgICJ2YWx1ZSI6ICJJQU5BX2NoYXJhY3Rlci1zZXRzIgogICAgICAgICB9LAogICAgICAgICAiY29kZV9zdHJpbmciOiAiVVRGLTgiCiAgICAgIH0sCiAgICAgICJzdWJqZWN0IjogbnVsbCwKICAgICAgInByb3RvY29sIjogewogICAgICAgICAiTGFib3JhdMOzcmlvX3Jlc3BvbnPDoXZlbCI6IHsKICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJMYWJvcmF0w7NyaW8gcmVzcG9uc8OhdmVsIgogICAgICAgICAgICB9LAogICAgICAgICAgICAiTm9tZV9kYV9vcmdhbml6YcOnw6NvIjogewogICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgInZhbHVlIjogIk5vbWUgZGEgb3JnYW5pemHDp8OjbyIKICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAidmFsdWUiOiAiTGFib3JhdG9yaW8gQ29yZSIKICAgICAgICAgICAgICAgfQogICAgICAgICAgICB9LAogICAgICAgICAgICAiQ05FUyI6IHsKICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJDTkVTIgogICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAidmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICIxMjM0NTY3IgogICAgICAgICAgICAgICB9CiAgICAgICAgICAgIH0sCiAgICAgICAgICAgICJEZXRhbGhlc19kb19jb250YXRvIjogewogICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgInZhbHVlIjogIkRldGFsaGVzIGRvIGNvbnRhdG8iCiAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICJSZXNwb25zw6F2ZWxfdMOpY25pY28iOiB7CiAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiUmVzcG9uc8OhdmVsIHTDqWNuaWNvIgogICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAiTm9tZV9kYV9wZXNzb2EiOiB7CiAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiTm9tZSBkYSBwZXNzb2EiCiAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICJOb21lX2NvbXBsZXRvIjogewogICAgICAgICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIk5vbWUgY29tcGxldG8iCiAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIkTDqWJvcmEgRmFyYWdlIEtudXBwIGRvcyBTYW50b3MgQm9semFuIgogICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgIkNvbnNlbGhvX2RlX2NsYXNzZSI6IHsKICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJDb25zZWxobyBkZSBjbGFzc2UiCiAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICJUaXBvX2RlX2NvbnNlbGhvIjogewogICAgICAgICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIlRpcG8gZGUgY29uc2VsaG8iCiAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgImRlZmluaW5nX2NvZGUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ0ZXJtaW5vbG9neV9pZCI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogImxvY2FsIgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiY29kZV9zdHJpbmciOiAiYXQwLjM2IgogICAgICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgIlVGIjogewogICAgICAgICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIlVGIgogICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJERiIKICAgICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAiTsO6bWVyb19kb19yZWdpc3RybyI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJOw7ptZXJvIGRvIHJlZ2lzdHJvIgogICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICIwMDAwIgogICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgIkNOUyI6IHsKICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJDTlMiCiAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIjIxNjAzMTQxMTUxMDAwMiIKICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgfQogICAgICAgICB9LAogICAgICAgICAiRGV0YWxoZXNfZGFfc29saWNpdGHDp8Ojb19kb190ZXN0ZSI6IHsKICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJEZXRhbGhlcyBkYSBzb2xpY2l0YcOnw6NvIGRvIHRlc3RlIgogICAgICAgICAgICB9LAogICAgICAgICAgICAiUmVxdWlzaXRhbnRlIjogewogICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgInZhbHVlIjogIlJlcXVpc2l0YW50ZSIKICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgIk5vbWVfZGFfcGVzc29hIjogewogICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIk5vbWUgZGEgcGVzc29hIgogICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAiTm9tZV9jb21wbGV0byI6IHsKICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJOb21lIGNvbXBsZXRvIgogICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJNYXJpdmFuIEFicmFoYW8iCiAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAiQ29uc2VsaG9fZGVfY2xhc3NlIjogewogICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIkNvbnNlbGhvIGRlIGNsYXNzZSIKICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgIlRpcG9fZGVfY29uc2VsaG8iOiB7CiAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiVGlwbyBkZSBjb25zZWxobyIKICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAgICAgICAiZGVmaW5pbmdfY29kZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgInRlcm1pbm9sb2d5X2lkIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAibG9jYWwiCiAgICAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICAgICJjb2RlX3N0cmluZyI6ICJhdDAuMjkiCiAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAiVUYiOiB7CiAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiVUYiCiAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIlNQIgogICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICJOw7ptZXJvX2RvX3JlZ2lzdHJvIjogewogICAgICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIk7Dum1lcm8gZG8gcmVnaXN0cm8iCiAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIjAwMDAiCiAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAiQ05TIjogewogICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIkNOUyIKICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiMjM4MTUxMzgwNTkwMDE4IgogICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICJDQk8iOiB7CiAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiQ0JPIgogICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAidmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICIyMjUxLTA5IgogICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgfQogICAgICAgICB9CiAgICAgIH0sCiAgICAgICJkYXRhIjogewogICAgICAgICAiUXVhbHF1ZXJfZXZlbnRvX2FzX1BvaW50X0V2ZW50IjogewogICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgInZhbHVlIjogIlF1YWxxdWVyIGV2ZW50byIKICAgICAgICAgICAgfSwKICAgICAgICAgICAgInRpbWUiOiB7CiAgICAgICAgICAgICAgICJ2YWx1ZSI6ICIyMDEwLTA4LTMwIgogICAgICAgICAgICB9LAogICAgICAgICAgICAiZGF0YSI6IHsKICAgICAgICAgICAgICAgIk5vbWVfZG9fZXhhbWVfZnNsYXNoX3BhaW5lbCI6IHsKICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJOb21lIGRvIGV4YW1lL3BhaW5lbCIKICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiQXNwYXJ0YXRlIGFtaW5vdHJhbnNmZXJhc2UgW0VuenltYXRpYyBhY3Rpdml0eS92b2xtZSBpbiBTZXJ1bSBvciBQbGFzbWEiLAogICAgICAgICAgICAgICAgICAgICAiZGVmaW5pbmdfY29kZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgInRlcm1pbm9sb2d5X2lkIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiTE9JTkMiCiAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICJjb2RlX3N0cmluZyI6ICIxOTIwLTgiCiAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAiRXNww6ljaW1lIjogewogICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIkVzcMOpY2ltZSIKICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgIkFtb3N0cmEiOiB7CiAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiQW1vc3RyYSIKICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiU29ybyIKICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAiRGF0YV9kYV9jb2xldGEiOiB7CiAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiRGF0YSBkYSBjb2xldGEiCiAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIjIwMTAtMDgtMzAiCiAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAiVGVzdGVfZGVfYW5hbGl0b19kZV9sYWJvcmF0w7NyaW8iOiB7CiAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiVGVzdGUgZGUgYW5hbGl0byBkZSBsYWJvcmF0w7NyaW8iCiAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICJBbmFsaXRvIjogewogICAgICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIkFuYWxpdG8iCiAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIkFzcGFydGF0ZSBhbWlub3RyYW5zZmVyYXNlIFtFbnp5bWF0aWMgYWN0aXZpdHkv4oCLdm9sdW1lXSBpbiBTZXJ1bSBvciBQbGFzbWEiLAogICAgICAgICAgICAgICAgICAgICAgICAiZGVmaW5pbmdfY29kZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgInRlcm1pbm9sb2d5X2lkIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiTE9JTkMiCiAgICAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICAgICJjb2RlX3N0cmluZyI6ICIxOTIwLTgiCiAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAiVmFsb3JfZG9fcmVzdWx0YWRvIjogewogICAgICAgICAgICAgICAgICAgICAiQHZhbHVlVHlwZSI6ICJEVl9RVUFOVElUWSIsCiAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiVmFsb3IgZG8gcmVzdWx0YWRvIgogICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAicXVhbnRpdHlfdmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICJtYWduaXR1ZGUiOiAiMjgiLAogICAgICAgICAgICAgICAgICAgICAgICAidW5pdHMiOiAiVS9MIgogICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICJDb21lbnTDoXJpbyI6IHsKICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJDb21lbnTDoXJpbyIKICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAibsOjbyBzZSBhcGxpY2EiCiAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgIkNvbmNsdXPDo28iOiB7CiAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiQ29uY2x1c8OjbyIKICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAibsOjbyBzZSBhcGxpY2EgMiIKICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAiT3JpZW50YcOnw7Vlc19zb2JyZV9mYWl4YV9kZV9yZWZlcsOqbmNpYSI6IHsKICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJPcmllbnRhw6fDtWVzIHNvYnJlIGZhaXhhIGRlIHJlZmVyw6puY2lhIgogICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJOb3ZvcyBjcml0w6lyaW9zIHJlY29tZW5kYWRvcyBwZWxhIFNvY2llZGFkZSBCcmFzaWxlaXJhIGRlIEVuZG9jcm9ub2xvZ2lhLCAyMDE3IgogICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICJEaXNwb3NpdGl2b3NfbcOpZGljb3MiOiB7CiAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiRGlzcG9zaXRpdm9zIG3DqWRpY29zIgogICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAiRXF1aXBhbWVudG9fdXRpbGl6YWRvIjogewogICAgICAgICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIkVxdWlwYW1lbnRvIHV0aWxpemFkbyIKICAgICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiRXNwZWN0cm9mb3TDtG1ldHJvIGRlIFVWL1ZJUyIKICAgICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAiTcOpdG9kb19kZV9hbsOhbGlzZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJNw6l0b2RvIGRlIGFuw6FsaXNlIgogICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJDaW7DqXRpY28gb3B0aW1pemFkbyBVLlYuIgogICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgIlJlc3BvbnPDoXZlbF9wZWxvX2V4YW1lIjogewogICAgICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIlJlc3BvbnPDoXZlbCBwZWxvIGV4YW1lIgogICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAiTm9tZV9kYV9wZXNzb2EiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiTm9tZSBkYSBwZXNzb2EiCiAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICJOb21lX2NvbXBsZXRvIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIk5vbWUgY29tcGxldG8iCiAgICAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIkxhw61zZSBGaWd1ZWlyZWRvIFJvbG8iCiAgICAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAiQ29uc2VsaG9fZGVfY2xhc3NlIjogewogICAgICAgICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogbnVsbAogICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAiVGlwb19kZV9jb25zZWxobyI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJUaXBvIGRlIGNvbnNlbGhvIgogICAgICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJkZWZpbmluZ19jb2RlIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAidGVybWlub2xvZ3lfaWQiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJsb2NhbCIKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImNvZGVfc3RyaW5nIjogImF0MC4yNCIKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICJVRiI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJVRiIKICAgICAgICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiREYiCiAgICAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgICAgIk7Dum1lcm9fZG9fcmVnaXN0cm8iOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiQ8OzZGlnbyIKICAgICAgICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiMDAwMCIKICAgICAgICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICJDTlMiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiQ05TIgogICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICIxODAyNjEyMTc4NjAwMDUiCiAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgIkNCTyI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJDQk8iCiAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIjIyMTItMDUiCiAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAiUmVzcG9uc8OhdmVsX3BlbGFfbGliZXJhw6fDo29fZG9fbGF1ZG8iOiB7CiAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiUmVzcG9uc8OhdmVsIHBlbGEgbGliZXJhw6fDo28gZG8gbGF1ZG8iCiAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICJOb21lX2RhX3Blc3NvYSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJOb21lIGRhIHBlc3NvYSIKICAgICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgICAgIk5vbWVfY29tcGxldG8iOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiTm9tZSBjb21wbGV0byIKICAgICAgICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiRmVybmFuZGEgTWFpYSBFd2VydG9uIgogICAgICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgIkNvbnNlbGhvX2RlX2NsYXNzZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJDb25zZWxobyBkZSBjbGFzc2UiCiAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICJUaXBvX2RlX2NvbnNlbGhvIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIlRpcG8gZGUgY29uc2VsaG8iCiAgICAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImRlZmluaW5nX2NvZGUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ0ZXJtaW5vbG9neV9pZCI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogImxvY2FsIgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiY29kZV9zdHJpbmciOiAiYXQwLjI5IgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgICAgIlVGIjogewogICAgICAgICAgICAgICAgICAgICAgICAgICAibmFtZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIlVGIgogICAgICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJERiIKICAgICAgICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAiTsO6bWVyb19kb19yZWdpc3RybyI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJDw7NkaWdvIgogICAgICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICIwMDAwIgogICAgICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgIkNOUyI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgIm5hbWUiOiB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6ICJDTlMiCiAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogIjk1MzE0MDgyNzkwMDAwNCIKICAgICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgIkNvbmNsdXPDo28iOiB7CiAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiQ29uY2x1c8OjbyIKICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgInZhbHVlIjogewogICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiZGVudHJvIGRvcyBwYWRyw7VlcyBkYSBub3JtYWxpZGFkZSIKICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAiQ29tZW50w6FyaW8iOiB7CiAgICAgICAgICAgICAgICAgICJuYW1lIjogewogICAgICAgICAgICAgICAgICAgICAidmFsdWUiOiAiQ29tZW50w6FyaW8iCiAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICJ2YWx1ZSI6IHsKICAgICAgICAgICAgICAgICAgICAgInZhbHVlIjogImV4YW1lIHJlYWxpemFkbyBlbSBqZWp1bSBkZSAxMmgiCiAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgfQogICAgICAgICAgICB9LAogICAgICAgICAgICAic3RhdGUiOiBudWxsCiAgICAgICAgIH0KICAgICAgfQogICB9Cn0=";

}

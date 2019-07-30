package br.ufsc.bridge.res.dab.write.builder;

import java.util.Date;

import br.ufsc.bridge.res.dab.write.builder.alergia.AlergiaReacoesAdversasBuilder;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.caracterizacaoconsulta.CaracterizacaoConsultaABBuilder;
import br.ufsc.bridge.res.dab.write.builder.desfecho.DadosDesfechoBuilder;
import br.ufsc.bridge.res.dab.write.builder.listamedicamentos.ListaMedicamentosBuilder;
import br.ufsc.bridge.res.dab.write.builder.listamedicamentos.ListaMedicamentosNaoEstruturadosBuilder;
import br.ufsc.bridge.res.dab.write.builder.medicoesobservacoes.MedicoesObservacoesBuilder;
import br.ufsc.bridge.res.dab.write.builder.problema.ProblemaDiagnosticoAvaliadoBuilder;
import br.ufsc.bridge.res.dab.write.builder.procedimentospequenascirurgias.ProcedimentosPequenasCirurgiasBuilder;
import br.ufsc.bridge.res.util.RDateUtil;

public class ResumoConsultaABBuilder extends ParentArquetypeWrapper<ResumoConsultaABBuilder> {

	private Date data;

	@Override
	protected String openTags() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Registro_de_Atendimento_Clínico xmlns:oe=\"http://schemas.openehr.org/v1\" xmlns=\"http://schemas.oceanehr.com/templates\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://schemas.oceanehr.com/templates RegistroAtendimentoClínico.v4.xsd\" template_id=\"RegistroAtendimentoClínico.v4.0\"><name><value>Registro de Atendimento Clínico</value></name><language><terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language><territory><terminology_id><value>ISO_3166-1</value></terminology_id><code_string>BR</code_string></territory><category><value>event</value><defining_code><terminology_id><value>openehr</value></terminology_id><code_string>433</code_string></defining_code></category><composer xsi:type=\"oe:PARTY_IDENTIFIED\"><oe:identifiers><oe:issuer>2.16.840.1.113883.13.236</oe:issuer><oe:assigner>2.16.840.1.113883.13"
				+ ".236</oe:assigner><oe:id>203011369760008</oe:id><oe:type>CNS</oe:type></oe:identifiers></composer><context><start_time><oe:value>";
	}

	private String closeTagsData() {
		return "</oe:value></start_time><setting><oe:value>primary medical care</oe:value><oe:defining_code><oe:terminology_id><oe:value>openehr</oe:value></oe:terminology_id><oe:code_string>228</oe:code_string></oe:defining_code></setting></context>";
	}

	@Override
	protected String closeTags() {
		return "</Encontro>";
	}

	public ResumoConsultaABBuilder data(Date data) {
		this.data = data;
		return this;
	}

	public CaracterizacaoConsultaABBuilder<ResumoConsultaABBuilder> caracterizacaoConsulta() {
		return new CaracterizacaoConsultaABBuilder<>(this);
	}

	public MedicoesObservacoesBuilder<ResumoConsultaABBuilder> medicoesObservacoes() {
		return new MedicoesObservacoesBuilder<>(this);
	}

	public ProblemaDiagnosticoAvaliadoBuilder<ResumoConsultaABBuilder> problemaDiagnostico() {
		return new ProblemaDiagnosticoAvaliadoBuilder<>(this);
	}

	public AlergiaReacoesAdversasBuilder<ResumoConsultaABBuilder> alergiaReacaoAdversa() {
		return new AlergiaReacoesAdversasBuilder<>(this);
	}

	public ProcedimentosPequenasCirurgiasBuilder<ResumoConsultaABBuilder> procedimentosPequenasCirurgias() {
		return new ProcedimentosPequenasCirurgiasBuilder<>(this);
	}

	// se voltar a utilizar medicamentos estruturados, entao colocar ele e o nao estruturado dentro de uma unica estrutura pai
	public ListaMedicamentosBuilder<ResumoConsultaABBuilder> listaMedicamentos() {
		return new ListaMedicamentosBuilder<>(this);
	}

	public ListaMedicamentosNaoEstruturadosBuilder<ResumoConsultaABBuilder> listaMedicamentosNaoEstruturados() {
		return new ListaMedicamentosNaoEstruturadosBuilder<>(this);
	}

	public DadosDesfechoBuilder<ResumoConsultaABBuilder> dadosDesfecho() {
		return new DadosDesfechoBuilder<>(this);
	}

	@Override
	public String getValue() {
		return RDateUtil.dateToISOEHR(this.data) + this.closeTagsData() + super.getValue();

	}
}

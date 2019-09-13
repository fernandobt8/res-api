package br.ufsc.bridge.res.dab;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;

import br.ufsc.bridge.res.dab.domain.JsonPathValueConverter;
import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnumJsonPathValueConverter;

@Retention(RetentionPolicy.RUNTIME)
public @interface JsonPathProperty {

	String value();

	Group group() default Group.EMPTY;

	Class<? extends JsonPathValueConverter> converter() default StringJsonPathValueConverter.class;

	@AllArgsConstructor
	public static enum Group {
		EMPTY(""),
		CARACTERIZACAO_DO_ATENDIMENTO("$.content[?(@.name.value == 'Caracterização do atendimento')]"),
		ADMISSAO_DO_PACIENTE(".items[?(@.name.value == 'Admissão do paciente')]"),
		LOCALIZACAO_ATRIBUIDA_AO_PACIENTE(Group.ADMISSAO_DO_PACIENTE.path + ".data.items[?(@.name.value == 'Localização atribuída ao paciente')]"),
		INSTITUICAO(Group.LOCALIZACAO_ATRIBUIDA_AO_PACIENTE.path + ".items[?(@.name.value == 'Instituição')]"),
		OBSERVACOES("$.content[?(@.name.value == 'Observações')]"),
		OBSERVACOES_MEDICOES(Group.OBSERVACOES.path + ".items[?(@.name.value == 'Medições')]"),
		OBSERVACOES_INFORMACOES_ADICIONAIS(Group.OBSERVACOES.path + ".items[?(@.name.value == 'Informações adicionais')]"),
		OBSERVACOES_INFORMACOES_ADICIONAIS_CICLO_MENSTRUAL(Group.OBSERVACOES_INFORMACOES_ADICIONAIS.path + ".items[?(@._archetype_node_id == 'openEHR-EHR-OBSERVATION.menstrual_cycle.v0')]"),
		OBSERVACOES_INFORMACOES_ADICIONAIS_GESTACAO(Group.OBSERVACOES_INFORMACOES_ADICIONAIS.path + ".items[?(@._archetype_node_id == 'openEHR-EHR-OBSERVATION.gestation.v1')]"),
		OBSERVACOES_SUMARIO_OBSTETRICO(Group.OBSERVACOES_INFORMACOES_ADICIONAIS.path + ".items[?(@._archetype_node_id == 'openEHR-EHR-EVALUATION.obstetric_summary.v0')]"),
		;

		@Getter
		private String path;
	}

}

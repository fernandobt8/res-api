package br.ufsc.bridge.res.dab.dto;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.XPathFactoryAssist;

@Getter
@Setter
@NoArgsConstructor
public class ResABMedicamento {
	private String nomeMedicamento;
	private String codigoMedicamentoCatmat;
	private String descricaoFormaFarmaceutica;
	private String codigoFormaFarmaceutica;
	private String descricaoViaAdministracao;
	private String codigoViaAdministracao;
	private String descricaoDose;
	private String codigoDoseEstruturada;

	public ResABMedicamento(XPathFactoryAssist xPathMedicamento) throws XPathExpressionException {
		this.nomeMedicamento = xPathMedicamento.getString("./Medicamento/value/value");
		this.codigoMedicamentoCatmat = xPathMedicamento.getString("./Medicamento/value/defining_code/code_string");
		this.descricaoFormaFarmaceutica = xPathMedicamento.getString("./Forma_farmacêutica/value/value");
		this.codigoFormaFarmaceutica = xPathMedicamento.getString("./Forma_farmacêutica/value/defining_code/code_string");
		this.descricaoViaAdministracao = xPathMedicamento.getString("./Via_de_administração/value/value");
		this.codigoViaAdministracao = xPathMedicamento.getString("./Via_de_administração/value/defining_code/code_string");
		this.descricaoDose = xPathMedicamento.getString("./Dose/value/value");
		this.codigoDoseEstruturada = xPathMedicamento.getString("./Dose_estruturada/Duração_do_tratamento/value/value");
	}
}

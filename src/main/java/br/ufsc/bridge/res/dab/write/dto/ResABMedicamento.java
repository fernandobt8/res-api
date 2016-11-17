package br.ufsc.bridge.res.dab.write.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResABMedicamento {

	private String nomeMedicamento;
	private String codigoMedicamentoCatmat;
	private String descricaoFormaFarmaceutica;
	private String codigoFormaFarmaceutica;
	private String descricaoViaAdministracao;
	private String codigoViaAdministracao;
	private String descricaoDose;
	private String codigoDoseEstruturada;

}

package br.ufsc.bridge.res.dab.write.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.ufsc.bridge.res.dab.domain.ResABCondutaEnum;
import br.ufsc.bridge.res.dab.domain.ResABTurnoEnum;

@Getter
@Setter
public class ResABResumoConsulta {

	private Date dataAtendimento;

	private ResABTurnoEnum tipoAtendimento;
	private String cnes;
	private String ine;
	private Date dataAdmissao;
	private ResABTurnoEnum turno;
	private List<ResABIdentificacaoProfissional> profissionais;

	private String peso;
	private String altura;

	private Date dum;
	private String idadeGestacional;
	private String gestasPrevias;
	private String partos;

	private List<ResABProblemaDiagnostico> problemaDiagnostico;

	private List<ResABAlergiaReacoes> alergias;

	private List<ResABProcedimento> procedimentos;

	private List<ResABCondutaEnum> conduta;

	private List<ResABMedicamento> medicamentos;
}

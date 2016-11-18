package br.ufsc.bridge.res.dab.write.dto;

import lombok.Getter;
import lombok.Setter;
import br.ufsc.bridge.res.dab.domain.ResABTipoProblemaDiagnostico;

@Getter
@Setter
public class ResABProblemaDiagnostico {
	private String descricao;
	private ResABTipoProblemaDiagnostico tipo;
	private String codigo;
}

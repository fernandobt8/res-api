package br.ufsc.bridge.res.dab;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.caracterizacaoconsulta.ResABCaracterizacaoConsulta;
import br.ufsc.bridge.res.dab.desfecho.ResABDadosDesfecho;
import br.ufsc.bridge.res.dab.medicamento.ResABListaMedicamentos;
import br.ufsc.bridge.res.dab.medicoesobservacoes.ResABMedicoesObservacoes;
import br.ufsc.bridge.res.dab.problema.ResABProblemaDiagnosticoAvaliado;
import br.ufsc.bridge.res.dab.procedimento.ResABProcedimentosPequenasCirurgias;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Encontro", namespace = "http://schemas.oceanehr.com/templates")
public class ResABEncontro {

	public static final String OPEN_EHR_NAMESPACE = "http://schemas.openehr.org/v1";

	@XmlElement(name = "Caracterização_da_consulta")
	private List<ResABCaracterizacaoConsulta> caracterizacaoConsulta;

	@XmlElement(name = "Medições_e_observações")
	private List<ResABMedicoesObservacoes> medicoesObservacoes;

	@XmlElement(name = "Problemas__fslash__diagnósticos_avaliados")
	private List<ResABProblemaDiagnosticoAvaliado> problemaDiagnosticoAvaliado;

	@XmlElement(name = "Procedimentos_ou_pequenas_cirurgias")
	private List<ResABProcedimentosPequenasCirurgias> procedimentosPequenasCirurgias;

	@XmlElement(name = "Lista_de_medicamentos")
	private List<ResABListaMedicamentos> listaMedicamento;

	@XmlElement(name = "Dados_do_desfecho")
	private List<ResABDadosDesfecho> dadosDesfecho;
}

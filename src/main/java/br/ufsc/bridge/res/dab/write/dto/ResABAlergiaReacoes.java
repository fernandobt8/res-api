package br.ufsc.bridge.res.dab.write.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.domain.ResABGravidadeEnum;

@Getter
@Setter
public class ResABAlergiaReacoes {
	private String agente;
	private ResABGravidadeEnum gravidade;
	private String categoria;
	private List<ResABEventoReacao> eventoReacao;
}

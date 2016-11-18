package br.ufsc.bridge.res.dab.dto;

import java.util.ArrayList;
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
	private List<ResABEventoReacao> eventoReacao = new ArrayList<>();
}

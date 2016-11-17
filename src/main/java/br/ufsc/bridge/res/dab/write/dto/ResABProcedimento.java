package br.ufsc.bridge.res.dab.write.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResABProcedimento {
	private String nome;
	private Date data;
	private String codigo;
	private List<String> resultadoObservacoes;
}

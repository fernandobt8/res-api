package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Insumo {

	private String lote;

	private String fabricante;

	private Date validade;

	private String valorUnitario;

	private String programaSaude;

}

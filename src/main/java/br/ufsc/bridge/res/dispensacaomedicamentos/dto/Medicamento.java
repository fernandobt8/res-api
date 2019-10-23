package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Medicamento {

	private AssistenciaFarmaceutica assistenciaFarmaceutica;

	private String medicamento;

	private String quantidadeUnidadeFarmaceutica;

	private String frequenciaUso;

	private String duracaoUso;

	private EstadoMedicamento estado;

	private Date dataValidade;

	private String lote;

	private String fabricamente;

	private String programaSaude;

	private String valorUnitario;
}

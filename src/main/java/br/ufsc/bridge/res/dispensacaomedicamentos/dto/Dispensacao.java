package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import java.util.ArrayList;
import java.util.List;

public class Dispensacao {

	private CaracterizacaoAtendimento caracterizacaoAtendimento;

	private List<Medicamento> medicamentos = new ArrayList<>();

	private List<Insumo> insumosSaude = new ArrayList<>();

	private Double peso;

	private Double altura;

	private String diagnostico;

	private ResponsavelPrescricao profissionalResponsavelPrescricao;

	private ResponsavelDispensacao profissionalResponsavelDispensacao;

}

package br.ufsc.bridge.res.registroimunobiologico.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.dab.domain.RacaCor;
import br.ufsc.bridge.res.dab.domain.Sexo;

@Getter
@Setter
@NoArgsConstructor
public class IdentificacaoPaciente implements Serializable {

	private String cns;

	private String cpf;

	private String nomeCompleto;

	private String nomeCompletoMae;

	private Sexo sexo;

	private Date dataNascimento;

	private RacaCor racaCor;

	private String pais;

	private String municipio;

}

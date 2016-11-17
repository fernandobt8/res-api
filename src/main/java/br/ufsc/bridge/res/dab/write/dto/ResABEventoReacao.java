package br.ufsc.bridge.res.dab.write.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResABEventoReacao {
	private String manifestacao;
	private Date dataInstalacao;
	private String evolucaoAlergia;
}

package br.ufsc.bridge.res.dto.registry;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistryItem {

	private String repositoryUniqueId;
	private String documentUniqueId;

	private Date serviceStartTime;
	private String nomeProfissional;
	private String cbo;
	private String unidadeSaude;

}

package br.ufsc.bridge.res.service.dto.registry;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import br.ufsc.bridge.res.dab.TipoDocumento;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistryItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoDocumento documentType;
	private String repositoryUniqueId;
	private String documentUniqueId;

	private Date serviceStartTime;
	private String cnsProfissional;
	private String nomeProfissional;
	private String cbo;
	private String cnesUnidadeSaude;
	private String nomeUnidadeSaude;
	private String repositoryURL;

}

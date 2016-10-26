package br.ufsc.bridge.res.dab.medicamento;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABListaMedicamentos {

	@XmlElement(name = "Linha_de_Medicação")
	private List<ResABLinhaMedicacao> linhaMedicacao;
}

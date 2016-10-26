package br.ufsc.bridge.res.dab.procedimento;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABProcedimentosPequenasCirurgias {

	@XmlElement(name = "Procedimento")
	private List<ResABProcedimento> procedimentos;
}

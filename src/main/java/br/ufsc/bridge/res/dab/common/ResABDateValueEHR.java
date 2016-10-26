package br.ufsc.bridge.res.dab.common;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.ResABEncontro;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABDateValueEHR {

	@XmlElement(name = "value", namespace = ResABEncontro.OPEN_EHR_NAMESPACE)
	private Date value;
}

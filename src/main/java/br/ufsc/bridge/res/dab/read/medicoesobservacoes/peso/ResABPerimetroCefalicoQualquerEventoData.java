package br.ufsc.bridge.res.dab.read.medicoesobservacoes.peso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABPerimetroCefalicoQualquerEventoData {

	@XmlElement(name = "_exclm___-__Perímetro_cefálico")
	private ResABPerimetroCefalicoValue perimetroCefalico;
}

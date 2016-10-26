package br.ufsc.bridge.res.dab.medicoesobservacoes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.medicoesobservacoes.altura.ResABAlturaComprimento;
import br.ufsc.bridge.res.dab.medicoesobservacoes.perimetrocefalico.ResABPesoCorporal;
import br.ufsc.bridge.res.dab.medicoesobservacoes.peso.ResABPerimetroCefalico;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABAvaliacaoAntropometrica {

	@XmlElement(name = "Peso_corporal")
	private ResABPesoCorporal pesoCorporal;

	@XmlElement(name = "Altura__fslash__comprimento")
	private ResABAlturaComprimento alturaComprimento;

	@XmlElement(name = "Perímetro_cefálico")
	private ResABPerimetroCefalico perimetroCefalico;
}

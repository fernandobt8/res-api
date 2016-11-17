package br.ufsc.bridge.res.dab.read.medicoesobservacoes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.read.medicoesobservacoes.crianca.ResABCrianca;
import br.ufsc.bridge.res.dab.read.medicoesobservacoes.gestante.ResABGestante;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABMedicoesObservacoes {

	@XmlElement(name = "Avaliação_antropométrica")
	private ResABAvaliacaoAntropometrica avaliacaoAntropometrica;

	@XmlElement(name = "Gestante")
	private ResABGestante gestante;

	@XmlElement(name = "Crianca")
	private ResABCrianca crianca;
}

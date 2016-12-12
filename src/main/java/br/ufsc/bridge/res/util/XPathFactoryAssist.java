package br.ufsc.bridge.res.util;

import java.util.Date;
import java.util.Iterator;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import lombok.AllArgsConstructor;

import org.apache.commons.lang3.BooleanUtils;
import org.w3c.dom.Node;

public class XPathFactoryAssist {

	private Node node;
	private XPath xPath;

	public XPathFactoryAssist(Node document) {
		this.node = document;

		XPathFactory xPathFactory = XPathFactory.newInstance();
		this.xPath = xPathFactory.newXPath();
	}

	public XPathFactoryAssist getXPathAssist(String expression) throws XPathExpressionException {
		return new XPathFactoryAssist(this.getNode(expression));
	}

	public Node getNode(String expression) throws XPathExpressionException {
		return (Node) this.xPath.compile(expression).evaluate(this.node, XPathConstants.NODE);
	}

	public Long count(String expression) throws XPathExpressionException {
		return Long.valueOf(this.xPath.compile("count(" + expression + ")").evaluate(this.node));
	}

	public Date getDateEHR(String expression) throws XPathExpressionException {
		return RDateUtil.isoEHRToDate(this.getString(expression));
	}

	public Date getDateXDSb(String expression) throws XPathExpressionException {
		return RDateUtil.isoXDSbToDate(this.getString(expression));
	}

	public Boolean getBoolean(String expression) throws XPathExpressionException {
		return BooleanUtils.toBoolean(this.getString(expression));
	}

	public String getString(String expression) throws XPathExpressionException {
		return this.xPath.compile(expression).evaluate(this.node);
	}

	public Iterable<XPathFactoryAssist> iterable(String expression) {
		return new XPathIterable(expression);
	}

	@AllArgsConstructor
	private class XPathIterable implements Iterable<XPathFactoryAssist> {
		private String expression;

		@Override
		public Iterator<XPathFactoryAssist> iterator() {
			return new Itr(this.expression);
		}
	}

	private class Itr implements Iterator<XPathFactoryAssist> {
		private Long totalNodes;
		private Long countNode = 1L;
		private String expression;

		public Itr(String expression) {
			this.expression = expression;
			try {
				this.totalNodes = XPathFactoryAssist.this.count(expression);
			} catch (XPathExpressionException e) {
				throw new RuntimeException("Erro inesperado ao contar nodes para expression - " + expression);
			}
		}

		@Override
		public boolean hasNext() {
			return this.countNode <= this.totalNodes;
		}

		@Override
		public XPathFactoryAssist next() {
			try {
				return XPathFactoryAssist.this.getXPathAssist("(" + this.expression + ")" + "[" + this.countNode++ + "]");
			} catch (XPathExpressionException e) {
				throw new IndexOutOfBoundsException("Index " + this.countNode + " não existente para expression - " + this.expression);
			}
		}

		@Override
		public void remove() {
		}
	}
}

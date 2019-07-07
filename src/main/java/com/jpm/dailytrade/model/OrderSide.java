package com.jpm.dailytrade.model;

import java.util.Arrays;

/**
 * Made this an ENUM which is what CS and UBS were both doing on these types
 */
public enum OrderSide {
	BUY("B"), SELL("S");

	private String text;

	OrderSide(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static OrderSide fromString(String text) {

		if (text != null) {
			return (Arrays.stream(OrderSide.values()).filter(op -> text.equalsIgnoreCase(op.getText())).findFirst()
					.orElseThrow(() -> new IllegalArgumentException(
							"No enumeration constant with text " + text + " found!")));

		} else {
			throw new NullPointerException("Null pointer supplied.");
		}
	}
}

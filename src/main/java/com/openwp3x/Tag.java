package com.openwp3x;

public enum Tag {
	
	negocios("Negócios"),
	educacao("Educação"),
	comunidade("Comunidade"), 
	geral("Geral"), esportes("Esportes");
	
	private Tag(String label) {
		this.label = label;
	}
	
	final private String label;

	public String getLabel() {
		return label;
	}

}

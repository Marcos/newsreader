package com.openwp3x.model;

public enum TagType {
	
	negocios("Negócios"),
	educacao("Educação"),
	comunidade("Comunidade"), 
	geral("Geral"), esportes("Esportes");
	
	private TagType(String label) {
		this.label = label;
	}
	
	final private String label;

	public String getLabel() {
		return label;
	}

}

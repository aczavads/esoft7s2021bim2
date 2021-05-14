package esoft7s2021bim2.aula20210514.serialização;

import java.io.Serializable;

public class ArtigoCientífico implements Serializable {
	private static final long serialVersionUID = 1L;
	private String título;
	private int quantidadeDePáginas;

	public ArtigoCientífico(String título, int quantidadeDePáginas) {
		this.título = título;
		this.quantidadeDePáginas = quantidadeDePáginas;
	}
	
	public String getTítulo() {
		return título;
	}
	
	public void setTítulo(String título) {
		this.título = título;
	}
	public int getQuantidadeDePáginas() {
		return quantidadeDePáginas;
	}
	
	public void setQuantidadeDePáginas(int quantidadeDePáginas) {
		this.quantidadeDePáginas = quantidadeDePáginas;
	}

}

package br.com.caelum.leilao.service;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;

public class LanceTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveTerLanceComValorZero() {
		new Lance(new Usuario("Lucas"), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveTerLanceComValorNegativo() {
		new Lance(new Usuario("Lucas"), -10);
	}

}

package br.com.caelum.leilao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Computador");
		
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Pedro"), 2000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Computador");
		
		leilao.propoe(new Lance(new Usuario("Pedro"), 2000.0));
		leilao.propoe(new Lance(new Usuario("João"), 3000.0));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Notebook");
		
		Usuario pedro = new Usuario("Pedro");
		
		leilao.propoe(new Lance(pedro, 2000.0));
		leilao.propoe(new Lance(pedro, 3000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor());
	}
	
	@Test
	public void naoDeveAceitarMaisDeCincoLancesDoMesmoUsuario() {
		Leilao leilao = new Leilao("Notebook");
		
		Usuario pedro = new Usuario("Pedro");
		Usuario joao = new Usuario("João");
		
		leilao.propoe(new Lance(pedro, 2000.0));
		leilao.propoe(new Lance(joao, 3000.0));
		
		leilao.propoe(new Lance(pedro, 4000.0));
		leilao.propoe(new Lance(joao, 5000.0));
		
		leilao.propoe(new Lance(pedro, 6000.0));
		leilao.propoe(new Lance(joao, 7000.0));
		
		leilao.propoe(new Lance(pedro, 8000.0));
		leilao.propoe(new Lance(joao, 9000.0));
		
		leilao.propoe(new Lance(pedro, 10000.0));
		leilao.propoe(new Lance(joao, 11000.0));
		
		leilao.propoe(new Lance(pedro, 12000.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size() - 1).getValor());
	}
	
}

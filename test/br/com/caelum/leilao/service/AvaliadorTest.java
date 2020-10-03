package br.com.caelum.leilao.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	private Usuario mateus;

	//Executa antes de cada exemplo de teste
	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("Joao");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
        this.mateus = new Usuario("mateus");
        System.out.println("Criando o avaliador...");
	}
	
	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3").constroi();
		
		this.leiloeiro.avalia(leilao);
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
        
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
    						.lance(maria, 250.0)
    						.lance(joao, 300.0)
    						.lance(jose, 400.0)
    						.constroi();

        setUp();
        this.leiloeiro.avalia(leilao);

        double maiorEsperado = 400;
        double menorEsperado = 250;

        assertEquals(maiorEsperado, this.leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, this.leiloeiro.getMenorLance(), 0.0001);
    }
	
	@Test
    public void deveEntenderLeilaoComApenasUmLance() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 1000.0)
				.constroi();

		setUp();
        this.leiloeiro.avalia(leilao);
        
        assertEquals(1000.0, this.leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(1000.0, this.leiloeiro.getMenorLance(), 0.0001);
    }
	
	@Test
    public void deveEncontrarOsTresMaioresLances() {
		 Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
								.lance(joao, 100.0)
								.lance(maria, 200.0)
								.lance(joao, 300.0)
								.lance(maria, 400.0)
								.constroi();

		 setUp();
        this.leiloeiro.avalia(leilao);

        List<Lance> maiores = this.leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.0001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.0001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.0001);
    }
	
	@Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
 
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 200.0)
				.lance(maria, 450.0)
				.lance(joao, 120.0)
				.lance(maria, 700.0)
				.lance(mateus, 630.0)
				.lance(mateus, 230.0)
				.constroi();

		setUp();
        this.leiloeiro.avalia(leilao);

        assertEquals(700.0, this.leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, this.leiloeiro.getMenorLance(), 0.0001);
    }
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}
}

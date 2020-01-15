package br.com.softplan.observer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static br.com.softplan.util.StringUtils.VAZIO;

import java.util.ArrayList;
import java.util.List;

public class GeradorObservacaoTest {
	
	private GeradorObservacao geradorObservacao;
	private List numeros;
	static final String UMA_NOTA = "Fatura da nota fiscal de simples remessa: ";
	static final String DUAS_OU_MAIS_NOTAS = "Fatura das notas fiscais de simples remessa: ";
	
	@Before
	public void setup() {
		geradorObservacao = new GeradorObservacao();
		numeros = new ArrayList();
	}
	
	@Test
	public void deveRetornarVazioInformandoNull() {
		Assert.assertTrue("Deve retornar uma string vázia.", VAZIO.equals(geradorObservacao.geraObservacao(null)));
	}
	
	@Test
	public void deveRetornarVazioInformandoVazio() {
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string vázia.", VAZIO.equals(retorno));
	}
	
	@Test
	public void deveRetornarVazioInformandoZero() {
		numeros.add(0);
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string vázia.", ".".equals(retorno));
	}
	
	@Test
	public void deveRetornarVazioInformandoNumeroNegativo() {
		numeros.add(-1);
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string vázia.", ".".equals(retorno));
	}
	
	@Test
	public void deveRetornarVazioInformandoListaComLetras() {
		numeros.add("a");
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string vázia.", ".".equals(retorno));
	}
	
	@Test
	public void deveRetornarVazioInformandoNumeroUm() {
		numeros.add(1);
		final String esperado = UMA_NOTA+1+".";
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string não vázia.", esperado.equals(retorno));
	}

	@Test
	public void deveRetornarVazioInformandoDoisNumeros() {
		numeros.add(1);
		numeros.add(2);
		final String esperado = DUAS_OU_MAIS_NOTAS+1+" e "+2+".";
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string não vázia.", esperado.equals(retorno));
	}
	
	@Test
	public void deveRetornarVazioInformandoTresNumeros() {
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);
		final String esperado = DUAS_OU_MAIS_NOTAS+1+", "+2+" e "+3+".";
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string não vázia.", esperado.equals(retorno));
	}
}

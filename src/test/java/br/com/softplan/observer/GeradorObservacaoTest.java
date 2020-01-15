package br.com.softplan.observer;

import static br.com.softplan.util.StringUtils.VAZIO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GeradorObservacaoTest {
	
	private GeradorObservacao geradorObservacao;
	static final String UMA_NOTA = "Fatura da nota fiscal de simples remessa: ";
	static final String DUAS_OU_MAIS_NOTAS = "Fatura das notas fiscais de simples remessa: ";
	
	@Before
	public void setup() {
		geradorObservacao = new GeradorObservacao();
	}
	
	@Test
	public void deveRetornarVazioInformandoNull() {
		Assert.assertTrue("Deve retornar uma string vázia.", VAZIO.equals(geradorObservacao.geraObservacao(null)));
	}
	
	@Test
	public void deveRetornarVazioInformandoVazio() {
		List numeros = new ArrayList();
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string vázia.", VAZIO.equals(retorno));
	}
	
	@Test
	public void deveRetornarVazioInformandoZero() {
		List numeros = new ArrayList();
		numeros.add(0);
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string vázia.", ".".equals(retorno));
		numeros.clear();
	}
	
	@Test
	public void deveRetornarVazioInformandoNumeroNegativo() {
		List numeros = new ArrayList();
		numeros.add(-1);
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string vázia.", ".".equals(retorno));
	}
	
	@Test
	public void deveRetornarVazioInformandoListaComLetras() {
		List numeros = new ArrayList();
		numeros.add("a");
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string vázia.", ".".equals(retorno));
		numeros.clear();
	}
	
	@Test
	public void deveRetornarVazioInformandoNumeroUm() {
		List numeros = new ArrayList<Integer>();
		numeros.add(1);
		final String esperado = UMA_NOTA+1+".";
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string não vázia.", esperado.equals(retorno));
	}

	@Test
	public void deveRetornarVazioInformandoDoisNumeros() {
		List numeros = new ArrayList();
		numeros.add(1);
		numeros.add(2);
		final String esperado = DUAS_OU_MAIS_NOTAS+1+" e "+2+".";
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string não vázia.", esperado.equals(retorno));
	}
	
	@Test
	public void deveRetornarVazioInformandoTresNumeros() {
		List numeros = new ArrayList();
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);
		final String esperado = DUAS_OU_MAIS_NOTAS+1+", "+2+" e "+3+".";
		final String retorno = geradorObservacao.geraObservacao(numeros);
		Assert.assertTrue("Deve retornar uma string não vázia.", esperado.equals(retorno));
	}
	
	@Test
	public void deveRetornarUmaMesagemComSucessoInformandoUmValor() {
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);
		List<BigDecimal> valoresInformados = new ArrayList<BigDecimal>();
		valoresInformados.add(new BigDecimal("10.55"));
		final String esperado = UMA_NOTA+numeros.get(0)+" cujo valor é R$ "+ valoresInformados.get(0)+".";
		final String retorno = geradorObservacao.geraObservacaoCliente(numeros, new HashMap<Integer,BigDecimal>(), valoresInformados);
		Assert.assertTrue("Deve retornar uma string não vázia.", esperado.equals(retorno));
	}
	
	@Test
	public void deveRetornarUmaMesagemComSucessoInformandoDoisValores() {
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);
		numeros.add(2);
		List<BigDecimal> valoresInformados = new ArrayList<BigDecimal>();
		BigDecimal valor1 = new BigDecimal("10.55");
		valoresInformados.add(valor1);
		BigDecimal valor2 = new BigDecimal("12.53");
		valoresInformados.add(valor2);
		
		final String esperado = DUAS_OU_MAIS_NOTAS+numeros.get(0)+" cujo valor é R$ "+ valor1+" e "+numeros.get(1)+" cujo valor é R$ "+ valor2+".";
		final String retorno = geradorObservacao.geraObservacaoCliente(numeros, new HashMap<Integer,BigDecimal>(), valoresInformados);
		Assert.assertTrue("Deve retornar uma string não vázia.", esperado.equals(retorno));
	}
	
	@Test
	public void deveRetornarUmaMesagemComSucessoInformandoTresValores() {
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);
		List<BigDecimal> valoresInformados = new ArrayList<BigDecimal>();
		BigDecimal valor1 = new BigDecimal("10.55");
		valoresInformados.add(valor1);
		BigDecimal valor2 = new BigDecimal("12.53");
		valoresInformados.add(valor2);
		BigDecimal valor3 = new BigDecimal("13.63");
		valoresInformados.add(valor3);
		
		final String esperado = DUAS_OU_MAIS_NOTAS+numeros.get(0)+" cujo valor é R$ "+ valor1+", "+numeros.get(1)+" cujo valor é R$ "+ valor2+
				" e "+numeros.get(2)+" cujo valor é R$ "+ valor3+".";
		final String retorno = geradorObservacao.geraObservacaoCliente(numeros, new HashMap<Integer,BigDecimal>(), valoresInformados);
		Assert.assertTrue("Deve retornar uma string não vázia.", esperado.equals(retorno));
	}
}

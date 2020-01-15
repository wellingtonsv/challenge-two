package br.com.softplan.observer;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeradorObservacao { 

	//Textos pré-definidos
	static final String umoNota = "Fatura da nota fiscal de simples remessa: ";
	//Identificador da entidade
	String texto;
		
	//Gera observações, com texto pre-definido, incluindo os números, das notas fiscais, recebidos no parâmetro
	public String geraObservacao(List lista) 
	{
		texto = "";
		if (null!= lista && !lista.isEmpty()) 
		{
			return retornaCodigos(lista) + ".";
		}		
		return "";		
	}

	//Cria observação
	private String retornaCodigos(List lista) {
		if (lista.size() >= 2) {
			texto = "Fatura das notas fiscais de simples remessa: ";
		} else {
			texto = umoNota;
		}
		
		//Acha separador
		StringBuilder cod = new StringBuilder();
		Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
		Matcher matcher = pattern.matcher(lista.iterator().next().toString());
	    if(matcher.matches())
	    	return "";
	    
		for (Iterator<Integer> iterator = lista.iterator(); iterator.hasNext();) {
			Integer c = iterator.next();
			String s = "";
			
			if(c <= 0)
				return "";
			if( cod.toString() == null || cod.toString().length() <= 0 )
				s =  "";
				else if( iterator.hasNext() )
					s =  ", ";
				else
					s =  " e ";
			
			cod.append(s + c);
		}
		
		return texto + cod;
	}
}
package cotacao;

import java.math.BigDecimal;

public class Principal {

	public static void main(String[] args) {
		
		CotacaoBacen cotacao = new CotacaoBacen();
		
		BigDecimal b = new BigDecimal(0);
		BigDecimal c = new BigDecimal(0);
		BigDecimal d = new BigDecimal(0);
		
		b = cotacao.currencyQuotation("USD", "EUR", 100, "11/04/2017");

		System.out.println(b);
		
		c = cotacao.currencyQuotation("USD", "EUR", 100, "07/04/2015");

		System.out.println(c);
		
		d = cotacao.currencyQuotation("USD", "EUR", 100, "08/04/2016");

		System.out.println(d);
		
		
	}

}

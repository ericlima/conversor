package cotacao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

public class CotacaoBacen {

	String urlBacen = "http://www4.bcb.gov.br/Download/fechamento/";
	
	Hashtable<String, Quotation> quotations = new Hashtable<String, Quotation>();
	
	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation) {
		
		BigDecimal retorno = new BigDecimal(0);
		
		if (!validateDate(quotation)) {
			throw new RuntimeException("Invalid date");
		}
		
		Date quotationDate = returnLaborDay(quotation);
		
		loadQuotations(returnDateAsString(quotationDate));
		
		Quotation qf = quotations.get(from);
		Quotation qt = quotations.get(to);
		
		if ((qf == null) || (qt == null)) {
			throw new RuntimeException("Invalid parameters from/to");
		}
		
		if (value.intValue() < 0) {
			throw new RuntimeException("Invalid value");
		}
		
		BigDecimal vf = new BigDecimal(0);
		BigDecimal vt = new BigDecimal(0);
		
		vf = qf.getParityBuy();
		vt = qt.getParityBuy();
		
		retorno = vf.divide(vt, 2, RoundingMode.HALF_UP);
		
		return retorno;
		
	}
	
	private String returnDateAsString(Date date) {
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		String retorno = df.format(date);
		
		return retorno;
	}
	
	private Date returnLaborDay(String quotation) {
		Date retorno = null;
		
		if(quotation == null){
			throw new RuntimeException("Invalid date");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		try {

			retorno = sdf.parse(quotation);

			Calendar c = Calendar.getInstance();
			
			c.setTime(retorno);
			
			retorno = c.getTime();
			
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			
			if (dayOfWeek == Calendar.SUNDAY) {
				c.add(Calendar.DATE, -2);
				retorno = c.getTime();
			}
			if (dayOfWeek == Calendar.SATURDAY) {
				c.add(Calendar.DATE, -1);
				retorno = c.getTime();
			}
			
		} catch (ParseException e) {
			throw new RuntimeException("Invalid date");
		}

		return retorno;
	}
	
	private boolean validateDate(String quotation) {
		boolean retorno = false;
		
		if(quotation == null){
			retorno = false;
			return retorno;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		try {

			Date date = sdf.parse(quotation);
			retorno = true;

		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		}

		return retorno;
	}
	
	private void loadQuotations(String quotation) {
		
		String fileName = this.urlBacen + quotation + ".csv";
		String linha = "";
		
		try {
			
			URL url = new URL(fileName);
			
			URLConnection uc = url.openConnection();
			
			InputStreamReader inStream = new InputStreamReader(uc.getInputStream());
			
            BufferedReader buff= new BufferedReader(inStream);

            while ((linha = buff.readLine()) != null) {
            	
            	linha = buff.readLine();
            	
            	if (linha != null) {
            	
	            	String[] s = linha.replace(",", ".").split(";");
	            	
	            	this.quotations.put(s[3],
	            			new Quotation(
	            			s[3],
	            			s[2].charAt(0),
	            			new BigDecimal(s[4]), 
	            			new BigDecimal(s[5]), 
	            			new BigDecimal(s[6]), 
	            			new BigDecimal(s[7])
	            			)
	            		);
            	}
            	
            	//System.out.println(linha);
            }
            			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
}

/**
 * 
 */
package com.iportal.cart.biz.helper;

/**
 * 
 * Validador de Cedulas de indentidad y RUCS
 * Este metodo debe ser convertido al validador
 * de Struts para futuros requerimientos
 * @author hernan
 * @version 1.0
 *
 */
public class IdValidatorHelper {

	/**
	 * ejecuta el algoritmo de validacion modulo 10
	 * de digito verificador.
	 * 
	 * @param id cedula de ciudadania 
	 * @return true si cedula pasa el algoritmo validador
	 * 		   false si la c�dula no pasa el algoritmo
	 */
	public boolean isIdentification (String id) {
		boolean resp = false;
		
		// Inicializa la variable para sumatoria
		int sum = 0 ;
		// Hace la sumatoria de valores
		int digit = 0;
		int multiply = 0;
		
		for (int i = 0; i < id.length()-1; i++)
		{
			digit = Integer.parseInt(id.substring(i, i+1));
			
			//a = i % 2;
			if ( (i%2) == 0){			// Son los PARES y se multiplican por 2
				multiply = digit*2;
				if ( multiply > 9 ) {
						sum += multiply - 9;
				}
				else {
					sum += multiply;
				}
			}
			else { // Son los IMPARES y solo se suman
				sum += digit;
			}
		}
		
		// Obtiene el valor del d�gito verificador calculado
		int mod10 = sum % 10;
		
		if (mod10 != 0) {
			mod10 = 10 - mod10;
		}
		
		// Extrae el d�gito verificador que viene en el ID
		int lastDigit = Integer.parseInt(id.substring(9));
		
		// Compara los d�gitos verificadores
		if (mod10 == lastDigit)
			resp = true;
		else
			resp = false;		
		
		return resp;
	}
	
	
	/**
	 * Valida el RUC de una empresa ya sea privada o publica
	 * @param ruc ruc a ser validado
	 * @return true RUC VALIDO
	 * 		  false RUC INVALIDO 
	 */
	public boolean isRuc (String ruc) {
		boolean resp = false;
		// Inicializa la variable para sumatoria
		int sum = 0 ;
		
		// Discrimina el RUC PERSONAL
		int rucType = Integer.parseInt(ruc.substring(2,3));// Toma el tercer d�gito
		
		if (rucType < 6) {// Es RUC PERSONAL
			String personalRuc = ruc.substring(0, 10);// Toma solo los 10 primeros d�gitos, como c�dula
			return this.isIdentification(personalRuc);	
			
		}
		// Procesa los RUC para privadas y p�blicas
		if (rucType != 9 && rucType != 6) {			// RUC INV�LIDO
			return false;
		}
		
		// Hace la sumatoria por tipo de RUC con sus respectivos factores
		if (rucType == 9)							// RUC para PRIVADAS
			{
			sum += (4 * Integer.parseInt(ruc.substring (0, 1))); //  parseInt(IDENTIFICACION.substr(0 , 1)));
			sum += (3 * Integer.parseInt(ruc.substring (1 , 2)));
			sum += (2 * Integer.parseInt(ruc.substring (2 , 3)));
			sum += (7 * Integer.parseInt(ruc.substring (3 , 4)));
			sum += (6 * Integer.parseInt(ruc.substring (4 , 5)));
			sum += (5 * Integer.parseInt(ruc.substring (5 , 6)));
			sum += (4 * Integer.parseInt(ruc.substring (6 , 7)));
			sum += (3 * Integer.parseInt(ruc.substring (7 , 8)));
			sum += (2 * Integer.parseInt(ruc.substring (8 , 9)));
			}
		else											// RUC para P�BLICAS
			{
			sum += (3 * Integer.parseInt(ruc.substring (0 , 1)));
			sum += (2 * Integer.parseInt(ruc.substring (1 , 2)));
			sum += (7 * Integer.parseInt(ruc.substring (2 , 3)));
			sum += (6 * Integer.parseInt(ruc.substring (3 , 4)));
			sum += (5 * Integer.parseInt(ruc.substring (4 , 5)));
			sum += (4 * Integer.parseInt(ruc.substring (5 , 6)));
			sum += (3 * Integer.parseInt(ruc.substring (6 , 7)));
			sum += (2 * Integer.parseInt(ruc.substring (7 , 8)));
			}
		
		// Obtiene el valor del d�gito verificador calculado
		int mod11 = sum % 11;
		if (mod11 != 0)
			mod11 = 11 - mod11;
		
		int lastDigit = -1;
		// Extrae el d�gito verificador que viene en el ID por tipo de RUC
		if (rucType == 9) {	// RUC para PRIVADAS
			lastDigit = Integer.parseInt( ruc.substring( 9, 10)); // Toma el d�cimo d�gito
		}
		else { // RUC para P�BLICAS
			lastDigit = Integer.parseInt( ruc.substring( 8, 9)); // Toma el noveno d�gito
		}
		
		// Compara los d�gitos verificadores
		if (mod11 == lastDigit) {
			resp = true;
		}
		else {
			resp = false;
		}

		return resp;
	}
	
}

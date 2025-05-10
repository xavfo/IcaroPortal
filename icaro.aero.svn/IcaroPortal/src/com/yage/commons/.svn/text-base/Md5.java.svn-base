/*
 * Created on Apr 27, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.yage.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author jmiguel
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Md5 {
	
	private static Log logger = LogFactory.getLog(Md5.class);
	
	/**
	 * Calcula el hash de una cadena de texto utilizando el algoritmo
	 * MD5 y retorna el resultado como una cadena de numeros
	 * hexadecimales.
	 *
	 * @param cadena la cadena a ser procesada.
	 * @return el hash de la cadena.
	 */
	public static String hash(String cadena) {
		if (cadena == null) {
			return null;
		}

		try {
			// Crear un objeto para acceder al algoritmo:
			MessageDigest md = MessageDigest.getInstance("MD5");

			// Crear el hash:
			md.update(cadena.getBytes());

			// Retornar el hash en forma hexadecimal:
			return toHex(md.digest());

		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * Convierte un arreglo de bytes en una cadena representando cada byte
	 * como un numero hexadecimal sin signo.
	 * <p>
	 * Methodo desarrollado por Santeri Paavolainen, Helsinki Finland 1996<br>
	 * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
	 * Distribuido bajo licencia LGPL.
	 *
	 * @param hash un arreglo de bytes a ser convertido
	 * @return la cadena hexadecimal que equivale al arreglo de bytes.
	 */
	private static final String toHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}

		return buf.toString();
	}

}

/*
*
* Copyright (c) 2009-2009, Julio Cesar Vitorino, Todos os direitos reservados.
*
* This software is the confidential and proprietary information of Sun
* Microsystems, Inc. ("Confidential Information"). You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with Sun.
*
* SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
* THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
* TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
* PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
* ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
* DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*/

package br.com.jcv.backend.utility;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * <h1>Introspeccao</h1>
 * 
 * <p>Classe responsável por realizar introspecção em objetos.
 * 
 * @author Julio Cesar Vitorino
 * @since 29 June 2009
 * @version 1.0
 */
public class Introspeccao {


	/**
	 * @param logger
	 * @param dto
	 */
	public static void debugdto(Logger logger, Object dto) {
		if (logger.isDebugEnabled()) {
			logger.debug("--- realizando introspecção no objeto " + dto.getClass().getName());
			
			Class classe = dto.getClass();
			Field[] fields = classe.getDeclaredFields();
			for (int i = 0; i < fields.length; i++)
		      {  
				Field f = fields[i];
		         Class tipo = f.getType();
		         String atributo = f.getName();
		         Object conteudo = null;
		         try {
					conteudo = f.get(dto);
					logger.debug(tipo.getName() + " " + atributo + " = " + conteudo.toString() );
				} catch (IllegalArgumentException e1) {
					logger.warn(e1.getMessage());
				} catch (IllegalAccessException e1) {
					logger.warn(e1.getMessage());
				} catch (NullPointerException npe) {
					logger.debug(tipo.getName() + " " + atributo + " = null" );
				}
		      }
		}
	}
}

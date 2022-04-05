
package br.com.jcv.backend.utility;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import br.com.jcv.backend.anotacoes.Obrigatorio;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;

public class ValidadorAnotacaoObrigatorio {
	
	public static void validar(Object obj) throws AlugueRelaxeException {
		//if (obj == null){
			//TODO lanca uma excecao com mensagem a ser definida
		//}
		Class classe = obj.getClass();
		Field[] fields = classe.getFields();   
		for (Field field : fields) {                   
			if (field.isAnnotationPresent(Obrigatorio.class)) {
				field.setAccessible(true); 
				Obrigatorio annotationsField = field.getAnnotation(Obrigatorio.class);
				try {
					Object campo = field.get(obj);
					
					// Verificação de campo nulo
					if ((campo == null) && 
						(annotationsField.notNull())
						) {
						Map<String,String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, field.getName());
						throw AlugueRelaxeException.getInstance(MSGCODE.CAMPO_VAZIO,
								MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_VAZIO, parametros),
								null);
					}
					
					// Verificacao do tamanho do campo
					if ((campo != null) && (((String)campo).length() > annotationsField.tamanho())) {
						Map<String,String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, field.getName());
						parametros.put(Constantes.P2, String.valueOf(annotationsField.tamanho()));
						throw AlugueRelaxeException.getInstance(MSGCODE.CAMPO_COM_TAMANHO_INCORRETO,
								MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INCORRETO, parametros),
								null);
					}
					
					// Verificação de dominio
					if ( (campo != null) && 
						 (annotationsField.dominio().length > 0)
					   ) {
						boolean erro = true;
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < annotationsField.dominio().length; i++){
							sb.append(annotationsField.dominio()[i]).append("-");
							if (campo.equals(annotationsField.dominio()[i])) {
								erro = false;
								break;
							}
						}
						if (erro) {
							Map<String,String> parametros = new HashMap<String, String>();
							parametros.put(Constantes.P1, field.getName());
							parametros.put(Constantes.P2, sb.toString());
							throw AlugueRelaxeException.getInstance(MSGCODE.DOMINIO_INCORRETO,
									MensagemCache.getInstance().getMensagem(MSGCODE.DOMINIO_INCORRETO, parametros),
									null);
						}
					}
				} catch (IllegalArgumentException e) {
					// TODO Tratar exceção.
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Tratar exceção.
					e.printStackTrace();
				}
			}
		}
	}
		
}


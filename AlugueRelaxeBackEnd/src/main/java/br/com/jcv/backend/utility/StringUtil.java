package br.com.jcv.backend.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.CharacterIterator;
import java.text.Normalizer;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.exception.AlugueRelaxeException;

public class StringUtil {
	public static final int TIPO_RETORNO_LOGRADOURO = 0;
	public static final int TIPO_RETORNO_ENDERECO = 1;

	private static final String __CARACTERES_INVALIDOS = "!@#$%é&*()é`;:'}{[]^~-.,<>?/\\| ";
	private static final String __CARACTERES_CONVERTER = "ééééééééééééééééééééééééééééééééééééééééééééééééééééééééééééééé";
	private static final String __CARACTERES_CONVERTIDOS = "aeiouaeiouaeiouaooucaoAEIOUAEIOUAEIOUAOOUCAOAAEIDNYOPaaeidnyopy";

	public static String normalizar(String str){
        str = Normalizer.normalize(str, Normalizer.Form.NFD);   
        str = str.replaceAll("[^\\p{ASCII}]", "");   
        str = str.replaceAll("['<>\\|/\"]","");   
        str = str.replaceAll("\\s","-");   
        str = str.replaceAll("[*]*","");   
        str = str.replaceAll("/","");   

        return str;   
	}

	public static String normalizar2(String str){
        str = Normalizer.normalize(str, Normalizer.Form.NFD);   
        str = str.replaceAll("[^\\p{ASCII}]", "");   
        str = str.replaceAll("['<>\\|/\"]","");   
        str = str.replaceAll("\\s"," ");   
        str = str.replaceAll("[*]*","");   
        str = str.replaceAll("/","");   

        return str;   
	}

	public static String getPrimeiroNome(String str) {
		int pos = str.indexOf(' ');
		String resultado = str;

		if (pos > 0) {
			resultado = str.substring(0,pos);
		}
		return resultado;
	}

	public static Object[] getTelefoneInText(String text) throws Exception {   
        Collection<Pattern> padroesTelefone = new ArrayList<Pattern>();   
           
        padroesTelefone.add(Pattern.compile("(\\(\\d{2,3}\\)|\\d{2,3})([-\\s]?\\d{4}){2}"));   
        padroesTelefone.add(Pattern.compile("/\\(?\\d{3}\\)?\\d{3}-\\d{4}/"));   
        padroesTelefone.add(Pattern.compile("\\d{3}-\\d{3}-\\d{4}"));   
        padroesTelefone.add(Pattern.compile("\\d{4}-?\\d{4}"));   
        padroesTelefone.add(Pattern.compile("\\(\\d{3}\\)\\s?\\d{3}-\\d{4}"));   
        padroesTelefone.add(Pattern.compile("\\(?\\d{3}\\)?\\d{4}-\\d{4}"));   
        padroesTelefone.add(Pattern.compile("\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})"));   
        padroesTelefone.add(Pattern.compile("\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})"));   
        padroesTelefone.add(Pattern.compile("\\(?(\\d{2})\\)?[- ]?(\\d{3})[- ]?(\\d{4})"));   
        padroesTelefone.add(Pattern.compile("\\(?(\\d{2})\\)?[- ]?(\\d{4})[- ]?(\\d{4})"));   
        padroesTelefone.add(Pattern.compile("'/(\\(?[0-9]{3}\\)?|[- ]? )[ ][0-9]{3}[-. ]?[0-9]{4}/"));   
        padroesTelefone.add(Pattern.compile("'/(\\(?[0-9]{2}\\)?|[- ]? )[ ][0-9]{3}[-. ]?[0-9]{4}/"));   
        padroesTelefone.add(Pattern.compile("'/(\\(?[0-9]{2}\\)?|[- ]? )[ ][0-9]{4}[-. ]?[0-9]{4}/"));   
        padroesTelefone.add(Pattern.compile("'/(\\(?[0-9]{3}\\)?|[- ]? )[ ][0-9]{4}[-. ]?[0-9]{4}/"));   
           
        Matcher pesquisa;   
           
        Iterator<Pattern> i = padroesTelefone.iterator();   
        ArrayList<String> lst = new ArrayList<String>();
        
        while (i.hasNext()){   
            Pattern padrao = (Pattern)i.next();   
            pesquisa = padrao.matcher(text);   
            while (pesquisa.find()){   
                lst.add(pesquisa.group());
            }   
        }   
        
        return lst.toArray();
    }   	
	public static Object[] getUrlInText(String text) {       
        final String regex = "(http[s]?:\\/\\/|ftp:\\/\\/)?(www\\.)?[a-zA-Z0-9-\\.]+\\.(com|org|net|mil|edu|ca|co.uk|com.au|gov|br)";

         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(text);
         ArrayList<String> lst = new ArrayList<String>();
         while (matcher.find()) {
        	 lst.add(matcher.group());
         }

         // Removendo o �ltimo espa�o
         return lst.toArray(); 
	}

	public static String gerarCodigo(int posicoes) {
		String alimentador = "AB56FGHIijklNOPQLMabcdeRSTrstuvwUVCDE34WXYZmnopqxyz012789JKfgh";
		StringBuilder novoCodigo = new StringBuilder(); 
		
		Random rndgen = new Random();
		for( int i = 0; i < posicoes; i++) {
			int randomInt = rndgen.nextInt(alimentador.length());
			novoCodigo.append(alimentador.substring(randomInt,randomInt+1));
		}
		return novoCodigo.toString();
	}

	public static Object[] getEmailInText(String text) {       
        final String regex = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9]+)(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(text);
         ArrayList<String> listaEmail = new ArrayList<String>();
         while (matcher.find()) {
        	 listaEmail.add(matcher.group());
         }

         // Removendo o �ltimo espa�o
         return listaEmail.toArray(); 
	}

	public static String valorCorreto(double valor) {
		String retorno = null;
		String valorstr = String.valueOf(valor);
		int pos = valorstr.indexOf('.');
		if (pos == -1) {
			valorstr.concat(",00");
			retorno = valorstr;
		} else {
			String inteiro = valorstr.substring(0, pos);
			String decimal = valorstr.substring(pos + 1);
			if (decimal.length() >= 2) {
				decimal = decimal.substring(0, 2);
			} else {
				decimal = decimal.concat("0");
			}
			retorno = inteiro + "," + decimal;
		}
		
		return retorno;
	}

	public static String valorCorreto(double valor, String separador) {
		String retorno = null;
		String valorstr = String.valueOf(valor);
		int pos = valorstr.indexOf('.');
		if (pos == -1) {
			valorstr.concat(separador).concat("00");
			retorno = valorstr;
		} else {
			String inteiro = valorstr.substring(0, pos);
			String decimal = valorstr.substring(pos + 1);
			if (decimal.length() >= 2) {
				decimal = decimal.substring(0, 2);
			} else {
				decimal = decimal.concat("0");
			}
			retorno = inteiro + separador + decimal;
		}
		
		return retorno;
	}
	/**
	 * 
	 * removerCaracteresInvalidos - Remove caracteres indesejados baseados em
	 * __CARACTERES_INVALIDOS
	 * 
	 * @param String
	 * 
	 * @return String
	 * 
	 */
	public static String removerCaracteresInvalidos(String cStringRemover) {
		int nTamanho = cStringRemover.length();
		StringBuffer cRetorno = new StringBuffer(nTamanho);

		// remove os caracteres invalidos e os substitui por undeline
		for (int i = 0; i < nTamanho; i++) {
			int nPosicao = StringUtil.__CARACTERES_INVALIDOS.indexOf(cStringRemover.substring(
					i,
					i + 1));
			if (nPosicao == -1) {
				cRetorno.append(cStringRemover.substring(i, i + 1));
			} else {
				cRetorno.append("_");
			}
		}

		// Retorna a string somente com caracteres validos
		return (cRetorno.toString());
	}

	/**
	 * 
	 * Verifica se tem caracteres indesejados baseados no parémetro
	 * stringCaracterInvalidos. Caso stringCaracterInvalidos seja null, a
	 * __CARACTERES_INVALIDOS seré utilizada
	 * 
	 * @param stringCaracterInvalidos
	 *            a String que contem os caracteres invélidos.
	 * @param cStringVerificada
	 *            a String a ser verificada.
	 * 
	 * @return true se tiver caracter invélido, false caso contrério.
	 * 
	 */
	public static boolean verificarCaracteresInvalidos(
			String stringCaracterInvalidos,
			String cStringVerificada) {
		if (stringCaracterInvalidos == null) {
			stringCaracterInvalidos = __CARACTERES_INVALIDOS;
		}

		int nTamanho = cStringVerificada.length();

		// verifica se tem caracter invalido
		for (int i = 0; i < nTamanho; i++) {
			int nPosicao = stringCaracterInvalidos.indexOf(cStringVerificada.substring(i, i + 1));
			if (!(nPosicao == -1)) {
				return true;
			}
		}

		return false;
	}

	public static String removerCaracteresInvalidos(
			String cStringRemover,
			String[] excecao,
			String trocarpor) {
		int nTamanho = cStringRemover.length();
		StringBuffer cRetorno = new StringBuffer(nTamanho);

		// remove os caracteres invalidos e os substitui por undeline
		for (int i = 0; i < nTamanho; i++) {
			int nPosicao = StringUtil.__CARACTERES_INVALIDOS.indexOf(cStringRemover.substring(
					i,
					i + 1));
			if (nPosicao == -1) {
				cRetorno.append(cStringRemover.substring(i, i + 1));
			} else {
				boolean trocarOK = true;

				// caracter esté na array de exceééo?
				for (int j = 0; j < excecao.length; j++) {
					if (cStringRemover.substring(i, i + 1).equals(excecao[j])) {
						cRetorno.append(cStringRemover.substring(i, i + 1));
						trocarOK = false;
						break;
					}
				}
				if (trocarOK) {
					cRetorno.append(trocarpor);
				}
			}
		}

		// Retorna a string somente com caracteres validos
		return (cRetorno.toString());
	}

	public static String cut(String stringCut, int maxLen) {
		String retorno = stringCut;

		if (stringCut.length() > maxLen) {
			retorno = stringCut.substring(0, maxLen) + "...";
		}/* else {
			retorno = stringCut.trim(); // Este trim tira o '/n' se tiver.
		}*/

		return retorno;
	}

	/**
	 * @param cStringRemover
	 * @return String
	 */
	public static String charSetConvert(final String cStringRemover) {

		if (cStringRemover != null) {

			int nTamanho = cStringRemover.length();
			StringBuffer cRetorno = new StringBuffer(nTamanho);

			for (int i = 0; i < nTamanho; i++) {
				int nPosicao = StringUtil.__CARACTERES_CONVERTER.indexOf(cStringRemover.substring(
						i,
						i + 1));
				if (nPosicao == -1) {
					cRetorno.append(cStringRemover.substring(i, i + 1));
				} else {
					cRetorno.append(StringUtil.__CARACTERES_CONVERTIDOS.substring(
							nPosicao,
							nPosicao + 1));
				}
			}

			// Retorna a string somente com caracteres convertidos
			return cRetorno.toString().trim();
		}

		return null;
	}

	/**
	 * 
	 * rPad - Retorna uma cadeia de caracteres com o Caracter preenchido a
	 * direita
	 * 
	 * @param String,
	 *            String, int
	 * 
	 * @return String
	 * 
	 */
	public static String rPad(String cString, String sCaracter, int iTamanho) {
		StringBuffer sRetorno = new StringBuffer(iTamanho);
		StringBuffer sFillChar = new StringBuffer(iTamanho);

		// Popula o campo sFillChar com o caracter
		for (int i = 0; i < iTamanho; i++) {
			sFillChar.append(sCaracter);
		}

		// Se a string recebida exceder o tamanho desejado para alinhamento
		// devolve a string original
		if (cString.trim().length() > iTamanho) {
			sRetorno.append(cString);
		} else {
			sRetorno.append(cString.trim()).append(
					sFillChar.substring(0, iTamanho - cString.trim().length()));
		}

		// Retorno
		return (sRetorno.toString());
	}

	/**
	 * 
	 * lPad - Retorna uma cadeia de caracteres com o Caracter preenchido a
	 * esquerda
	 * 
	 * @param String,
	 *            String, int
	 * 
	 * @return String
	 * 
	 */
	public static String lPad(String cString, String sCaracter, int iTamanho) {
		StringBuffer sRetorno = new StringBuffer(iTamanho);
		StringBuffer sFillChar = new StringBuffer(iTamanho);

		// Popula o campo sFillChar com o caracter
		for (int i = 0; i < iTamanho; i++) {
			sFillChar.append(sCaracter);
		}

		// Se a string recebida exceder o tamanho desejado para alinhamento
		// devolve a string original
		if (cString.trim().length() > iTamanho) {
			sRetorno.append(cString);
		} else {
			sRetorno.append(sFillChar.substring(0, iTamanho - cString.trim().length())).append(
					cString.trim());
		}

		// Retorno
		return (sRetorno.toString());
	}

	/**
	 * 
	 * ReplaceInString - Substitui em uma string a ocorrencia de uma string por
	 * outra
	 * 
	 * @param String,
	 *            String, String
	 * 
	 * @return String
	 * 
	 */
	public static String replaceInString(String cStr, String cRegEx, String cReplace) {
		String cRetorno;
		Pattern p = Pattern.compile(cRegEx);
		Matcher m = p.matcher(cStr);
		cRetorno = m.replaceAll(cReplace);
		return (cRetorno);
	}

	/**
	 * 
	 * ReplaceString - Substitui em uma string a ocorrencia de uma string por
	 * outra
	 * 
	 * @param String, String, String
	 * 
	 * @return String
	 * 
	 * @deprecated
	 */
	public static String replaceString(String cStr, String oldString, String newString) {
		String p1;
		String p2;
		int pos = cStr.indexOf(oldString);

		if (pos == -1)
			return cStr;

		p1 = cStr.substring(0, pos);
		p2 = cStr.substring(pos + oldString.length());

		StringBuffer resultado = new StringBuffer();
		resultado.append(p1).append(newString).append(p2);
		return replaceString(resultado.toString(), oldString, newString);
	}
	
	public static String replaceString(String msgcode, Map<String,String> mpTroca) throws AlugueRelaxeException {
		String mensagem = msgcode;
		Iterator it = mpTroca.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        mensagem = StringUtil.replaceStringNew(mensagem,pairs.getKey().toString(),pairs.getValue().toString());
	    }
		return mensagem;
	}
	

	/**
	 * 
	 * ReplaceStringNew - Substitui em uma string a ocorrencia de uma string por
	 * outra
	 * 
	 * @param String, String, String
	 * 
	 * @return String
	 */
	public static String replaceStringNew(String mensagem, String chave, String token) {
		String result = "";
		int index = 0;
		List<Integer> indicesChave = new ArrayList<Integer>();

		// Localizar as chaves
		while (index != -1) {
			index = mensagem.indexOf(chave, index);
			if (index != -1) {
				indicesChave.add(index);
				index++;
			}
		}

		index = 0;
		// Trocar as chaves
		for (int i = 0; i < indicesChave.size(); i++) {
			result += mensagem.substring(index, indicesChave.get(i)) + token;
			index = indicesChave.get(i) + chave.length();
		}

		if (index < mensagem.length() - 1)
			result += mensagem.substring(index, mensagem.length());

		return result;
	}

	public static String extractString(String cstr, String search, int length) {
		int npos = cstr.indexOf(search);
		if (npos > -1) {
			String ret = cstr.substring(npos, npos + search.length() + length);
			return ret;
		} else {
			return "";
		}
	}

	/*
	 * Converts a byte to hex digit and writes to the supplied buffer
	 */
	private void byte2hex(byte b, StringBuffer buf) {
		char[] hexChars = {
				'0',
				'1',
				'2',
				'3',
				'4',
				'5',
				'6',
				'7',
				'8',
				'9',
				'A',
				'B',
				'C',
				'D',
				'E',
				'F' };
		int high = ((b & 0xf0) >> 4);
		int low = (b & 0x0f);
		buf.append(hexChars[high]);
		buf.append(hexChars[low]);
	}

	/*
	 * Converts a byte array to hex string
	 */
	public String toHexString(byte[] block) {
		StringBuffer buf = new StringBuffer();

		int len = block.length;

		for (int i = 0; i < len; i++) {
			byte2hex(block[i], buf);
			if (i < len - 1) {
				buf.append(":");
			}
		}
		return buf.toString();
	}

	public static final boolean isNumeric(final String s) {
		return s != null && s.matches("^[+|-]?\\d+$") ? true : false;
		/*
		 * final char[] numbers = s.toCharArray(); for (int x = 0; x <
		 * numbers.length; x++) { final char c = numbers[x]; if ((c >= '0') &&
		 * (c <= '9')) continue; return false; // invalid } return true; //
		 * valid
		 */

		/* Modo 2 */
		// return ((theNum+'').match(/^(+|-)?\d+$/) != null);
	}

	public static final boolean isDecimal(final String s) {
		return s != null && s.matches("^[-+]?\\d+(\\.\\d+)?$") ? true : false;

		/* Modo 2 - para entrada do usuério */
		// return
		// ((theNum+'').match(/^(((+|-)?\d+(\.\d*)?)|((+|-)?(\d*\.)?\d+))$/) !=
		// null);
		// Modo 3 - Légica Algoritmo
		/*
		 * boolean isValid = true;
		 *  // Initialise counters int count = 0; int index = 0;
		 *  // Loop round all characters in the input or until invald character
		 * detected while (index < userInput.length() && isValid) {
		 *  // Get the next character from the user input char c =
		 * userInput.charAt(index);
		 *  // Check if it's a decimal point if (c == 46) { count ++;
		 *  // Invalid if there's been more than one if (count > 1) { isValid =
		 * false; }
		 *  // Check if it's less than the ASCII for '0' or greater than the
		 * ASCII for '9' } else if (c < 48 || c > 57) { isValid = false; }
		 *  // Increment the character counter index ++; }
		 * 
		 * return isValid;
		 */
	}

	/*
	 * Verifica se o character é especial
	 */
	public static boolean isCharEspecial(char c) {

		if (__CARACTERES_INVALIDOS.indexOf(c) == -1) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Substitui caracteres especias &, <, >, ", ', \
	 * 
	 * @param aTagFragment
	 * @return string
	 */
	public static String escape(String aTagFragment) {
		final StringBuffer result = new StringBuffer();

		final StringCharacterIterator iterator = new StringCharacterIterator(aTagFragment);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			if (character == '<') {
				result.append("&lt;");
			} else if (character == '>') {
				result.append("&gt;");
			} else if (character == '\"') {
				result.append("&quot;");
			} else if (character == '\'') {
				result.append("&#039;");
			} else if (character == '\\') {
				result.append("&#092;");
			} else if (character == '&') {
				result.append("&amp;");
			} else {
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	public static boolean isCaminhoValido(final String nome) {
		byte[] abyte = new byte[] { (byte) 0x2A, /* * */
		(byte) 0x2F, /* / */
		(byte) 0x5C, /* \ */
		(byte) 0x3A, /* : */
		(byte) 0x3F, /* ? */
		(byte) 0x22, /* " */
		(byte) 0x3E, /* > */
		(byte) 0x7C, /* | */
		(byte) 0x3C, /* < */
		(byte) 0x27 }; /* ' */

		return StringUtil.isCaminhoValido(nome, abyte);
	}

	public static boolean isCaminhoValido(final String nome, final byte[] abyte) {
		String caracteresNaoPermitidos = new String(abyte);
		for (int i = 0; i < nome.length(); i++) {
			int nPosicao = caracteresNaoPermitidos.indexOf(nome.substring(i, i + 1));
			if (nPosicao >= 0) {
				return false;
			}
		}

		return true;
	}

	public static int countMatches(final String str, final String match) {
		return countMatches(str, match, 0);
	}

	public static StringBuffer getStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.getBuffer();
	}

	public static int countMatches(final String str, final String match, int ocor) {
		if (str != null) {
			int idx = 0;
			String substr = str;
			idx = substr.indexOf(match);

			if (idx > 0) {
				ocor++;
			} else if (idx < 0) {
				return ocor;
			}

			ocor += countMatches(str.substring(++idx), match);
		}
		return ocor;
	}

	public static String join(String token, String[] strings) {
		StringBuffer sb = new StringBuffer();

		for (int x = 0; x < (strings.length - 1); x++) {
			sb.append(strings[x]);
			sb.append(token);
		}
		sb.append(strings[strings.length - 1]);

		return (sb.toString());
	}

	/**
	 * Verifica se uma string é vazia ou nula
	 *
	 * @param string
	 * @return true se a string for vazia ou nula
	 */
	public static boolean isEmptyOrNull(String string) {

		if (string == null || string.trim().length() == 0) {
			return true;
		}

		return false;
	}

	/**
	 * Adiciona quebra de linha em uma string utilizando o tamanho max especificado.
	 *
	 * @param str
	 * @param size
	 * @return String
	 */
	public static String split(String str, int maxSize) {
		StringBuffer buffer = new StringBuffer();

		if (str.length() < maxSize) {
			//Caso a string recebida seja MENOR do que o tamanho méximo
			buffer.append(str);
		} else {
			//Caso a string recebida seja MAIOR do que o tamanho méximo
			String firstPiece = str.substring(0, maxSize);
			int splitIndex = firstPiece.lastIndexOf(" ");

			if (splitIndex < 0) {
				buffer.append(str);
			} else {
				//Achou um espaéo nos "N = maxSize" primeiros caracteres
				buffer.append(str.substring(0, splitIndex));
				buffer.append("\n");
				String lastPiece = str.substring(splitIndex, str.length());
				if (lastPiece.length() < maxSize) {
					//Caso a string restante seja MENOR do que o tamanho méximo
					buffer.append(lastPiece);
				} else {
					//Caso a string restante seja MAIOR do que o tamanho méximo
					buffer.append(StringUtil.split(lastPiece, maxSize));
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * Verifica se o ip valido
	 *
	 * @param s
	 * @return Caso ocorra algum erro na operação
	 */
	public static boolean isIpAddress(String s) {
		return s != null
				&& s.matches("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)") ? true
				: false;
	}

	public static String splitEndereco(String endereco, int tipoRetorno) {
		String retorno = null;
		int i = 0;
		i = endereco.indexOf(0x0020);
		if (tipoRetorno == TIPO_RETORNO_LOGRADOURO) {
			retorno = endereco.substring(0, i);
		} else if (tipoRetorno == TIPO_RETORNO_ENDERECO) {
			retorno = endereco.substring(i + 1, endereco.length());
		}
		
		return retorno;
	}

	public static String geraSHA1(String str) {
		// Aplica algoritimo SHA1 na string informada
		String strsha1 = "";
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.SHA1);
		try {
			strsha1 = bfs.criptografar(str);
		} catch (InvalidKeyException e) {
			// TODO Tratar exce��o.
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Tratar exce��o.
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Tratar exce��o.
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Tratar exce��o.
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Tratar exce��o.
			e.printStackTrace();
		}	
		return strsha1;
	}
	
}

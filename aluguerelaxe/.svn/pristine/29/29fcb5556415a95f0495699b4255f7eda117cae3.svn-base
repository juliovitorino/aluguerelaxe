
package br.com.jcv.aluguerelaxe.servlet;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.color.CMMException;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.com.jcv.aluguerelaxe.server.uploadmanager.UploadInfo;
import br.com.jcv.aluguerelaxe.server.uploadmanager.UploadManager;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadServiceImpl;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteThreadService;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/** 
1 - Cliente seleciona botao adicionar arquivo;
2 - Sistema apresenta dialogo de seleccao de arquivos;
3 - cliente procura por seu arquivo, clica nele e seleciona o botao abrir;
4 - cliente faz o processo quantas vezes desejar;
5 - cliente pressionar botão enviar fotos;
6 - sistema enviar arquivo por arquivo para o servidor;
7 - sistema recebe o arquivo ;
8 - Sistema verifica se é um arquivo de imagem;
9 - Sistema aplica algoritmo de redimensionamento das imagens para os seguintes tamanhos:

TB - Thumbnail W:225 x H:168
XG - Imagem grande W:640 x H:480
PP - Publicidade pagina principal W:300 x H:225
PD - Publicidade pagina de destaques W:100 x H:65
MI - Mini  W:82 x H:63

10 - Sistema grava as novas imagens no servidor e no banco de dados;
11 - Sistema atualiza banco de imagens.

n - Sistema envia e-mail para Sr. AlugueRelaxe avisando sobre a entrada das imagens
para verificacao de conteúdo pornografico (IMPORTANTE!)


 */
public class UploaderFotoThread extends HttpServlet {

	private static final long serialVersionUID = 6529787833592436783L;
	private static final String ITEM_ID_CLIENTE = "idUploadPK";
	private static final String ITEM_PID = "pid";
	private static final String ITEM_FILE = "file";

	public UploaderFotoThread() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		List items = null;
		//PrintWriter out = null;
		StringBuilder resposta;
		String path = null;
		String filename = null;

		/**
		 * Mudança das Variaveis de Instancia , porque em servlet pode
		 * dar problema.
		 */
		Map<String, Object> parametros = null;

		//String sid = null;
		resposta = new StringBuilder();
		

		// Checa se temos requisição de upload de arquivo
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// Cria factory para arquivos
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Cria o handler para o upload
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			//upload.setHeaderEncoding("ISO-8859-1");

			// restringe tamanho da transferência em 10 MB 
			upload.setSizeMax( 10 * 1024 * 1024);

			// Analisa requisição Items, variaveis via POST 
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				throw new ServletException(e);
			}

			// Processa itens carregados
			try {
				parametros = this.obtainAttributes(items.iterator());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				path = VariavelCache.getInstance().getValor(VariavelConstantes.UPLOAD_PATH);
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Verifica se o diretório destino existe
			String pathGaleriaFotos = null;
			try {
				pathGaleriaFotos = VariavelCache.getInstance().getValor(VariavelConstantes.PATH_GALERIA_FOTOS_THREAD);
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// configura restrições
			factory.setRepository(new File(path));

			filename = this.processaUpload(parametros, path, request);
			
			//TODO verifica se arquivo é uma imagem
			
			//----------------------------------------------------------
			// Copiar arquivo para area do cliente
			//----------------------------------------------------------
			String hash = (String) parametros.get(ITEM_ID_CLIENTE);
			String pastaFotosImovel = pathGaleriaFotos.concat(hash).concat("/");
			
			// Criar pasta se necessário
			File fromFile = new File(pastaFotosImovel + "done.txt");
			String parent = fromFile.getParent();
			
			File dir = new File(parent);
			if (!dir.exists()) {

				if (dir.mkdirs()) {
					// TODO Colocar logger
				} else  {
					throw new ServletException("Não foi possível criar diretório de imagens");
				}
			}
			
			// Copia arquivo da area de upload para galeria do cliente
			String arquivoMI = null;
			try {
				arquivoMI = redimensionarImagem(hash, filename, pastaFotosImovel, "MI", 160, 120);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
/*			
			try {
				// Envia email ao administrador avisando da foto, pois pode ter conteúdo pornográfico.
				// Prepara a montagem da URL
				String linkVerificarImagemUpload = VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD);
				linkVerificarImagemUpload = StringUtil.replaceStringNew(linkVerificarImagemUpload, "${clienteid}", idCliente);
				linkVerificarImagemUpload = StringUtil.replaceStringNew(linkVerificarImagemUpload, "${imovelid}", idImovel);
				linkVerificarImagemUpload = StringUtil.replaceStringNew(linkVerificarImagemUpload, "${imagemupload}", arquivoXG);
				
				Map<String,String> conteudo = new HashMap<String, String>();
				conteudo.put(TemplateConstantes.TCU_TAG_LINK_REPROVAR_UPLOAD, "trocar o conteudo");
				conteudo.put(TemplateConstantes.TCU_TAG_URL_UPLOAD_VERIFICAR, linkVerificarImagemUpload);
				
				Email email = EmailFactory.getInstanceEmailConteudoUpload(conteudo);
				
				ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
				lst.add(new EmailRecord(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE),
				"Administrador"));
				
				email.enviar(lst, null, "AlugueRelaxe - Verificar conteúdo de imagens de upload", null);
				
			} catch (AlugueRelaxeException are) {
				throw new ServletException("Erro no durante processo de upload", are);

			}
*/
			
			// Responde ao Browser
			response.setContentType("text/plain; charset=UTF-8");

		}
	}
	
	public String redimensionarImagem(String id, 
			String filename, 
			String pastaFotosImovel, 
			String tipoImagem, 
			int largura, 
			int altura) throws Exception  {
		String novoNomeArquivo = id.toLowerCase();
		
		// Copia arquivo da area de upload para galeria do cliente
		String itemMI = novoNomeArquivo + ".jpg";
		
		InputStream isImagem = new BufferedInputStream(new FileInputStream(filename));
		InputStream isImagemRedim = null;
		try{
			isImagemRedim = scaleImage(isImagem, largura, altura );
			OutputStream outImagemRedim = new BufferedOutputStream(new FileOutputStream(pastaFotosImovel.concat(itemMI)));
			
			byte[] buffer = new byte[4096];  
			int bytesRead;  
			while ((bytesRead = isImagemRedim.read(buffer)) != -1) {  
				outImagemRedim.write(buffer, 0, bytesRead);  
			}  
			isImagem.close();
			outImagemRedim.close();
			isImagemRedim.close();

		} catch(CMMException e) {
			
			copy(filename, pastaFotosImovel.concat(itemMI));		
		}
		
		// Atualiza o nome da imagem contato com anunciante
		ContatoAnuncianteThreadService cats = new ContatoAnuncianteThreadServiceImpl();
		cats.atualizarFotoThread(id, itemMI);

		return itemMI;
		
	}


	/**
	 * Método que executa o processo de upload.
	 *
	 * @param item Uma instância do arquivo (do tipo FileItem).
	 */
	private String processaUpload(
			Map<String, Object> parametros,
			String path,
			HttpServletRequest request) {
		InputStream fin = null;
		OutputStream fout = null;
		OutputStream foutpid = null;
		String filename = null;
		FileItem item = null;
		String idSessao = null;
		String pid = null;

		String uniqueId = criarUniqueId(parametros);
		
		try {
			item = (FileItem) parametros.get(ITEM_FILE);
			idSessao = (String) parametros.get(ITEM_ID_CLIENTE);
			pid = (String) parametros.get(ITEM_PID);
			System.out.println("Uploader -- IdSessao = " + idSessao); 
			fin = item.getInputStream();
			filename = path.concat(item.getName()).concat(uniqueId);
			fout = new FileOutputStream(filename);
			String filenamepid = path.concat(pid).concat(".progress");
			
			// Obtem uma instancia de UploadManager para controlar o andamento do upload
			// e cria uma entrada na instancia
			UploadManager um = UploadManager.getInstance();
			um.addRegistroUploadInfo(idSessao, item.getSize());
			
			double tamanho = item.getSize();
			double contador = 0;
			double calculo = 0;
			
			// Copy bytes from URL to output stream
			byte[] buffer = new byte[8192];
			byte[] bufferpid = new byte[1024];
			int bytes_read;
			while((bytes_read = fin.read(buffer)) != -1){
				contador += bytes_read;
				calculo = contador / tamanho * 100;
				
				foutpid = new FileOutputStream(filenamepid);
				bufferpid = String.valueOf((int)calculo).getBytes();
				foutpid.write(bufferpid,0,bufferpid.length);
				try {
					if (foutpid != null)
						foutpid.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				// Atualiza a instancia com as informacoes do andamento do upload
				um.updateUploadInfo(idSessao, new UploadInfo((long)tamanho, (long)contador, (int) calculo ));
				
				// grava as partes do arquivo em processo de upload e taxa de progresso (%)
				fout.write(buffer, 0, bytes_read);
			}
			um.updateUploadInfo(idSessao, new UploadInfo((long)tamanho, (long)tamanho, 100));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fin != null)
					fin.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			try {
				if (fout != null)
					fout.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return filename;
	}

	private String criarUniqueId(Map<String, Object> parametros){
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.AES);
		String item = ((FileItem) parametros.get(ITEM_FILE)).getName();
		String hash = (String) parametros.get(ITEM_ID_CLIENTE);
		
		String criptografar = null;
		try {
			synchronized (this) {
				criptografar = bfs.criptografar(item + hash + String.valueOf(System.currentTimeMillis()));
			}
		} catch (InvalidKeyException e) {
			criptografar = String.valueOf(System.currentTimeMillis());
		} catch (NoSuchAlgorithmException e) {
			criptografar = String.valueOf(System.currentTimeMillis());
		} catch (NoSuchPaddingException e) {
			criptografar = String.valueOf(System.currentTimeMillis());
		} catch (IllegalBlockSizeException e) {
			criptografar = String.valueOf(System.currentTimeMillis());
		} catch (BadPaddingException e) {
			criptografar = String.valueOf(System.currentTimeMillis());
		}
		return criptografar;
	}

	/**
	 * Método utilizado para obter atributos de apoio ao upload e a importação..
	 *
	 * @param request A instância de HttpServletRequest.
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, String[]> obtainAttributes(HttpServletRequest request) {
		return request.getParameterMap();
	}

	/**
	 * Método utilizado para obter atributos de apoio ao upload e a importação..
	 *
	 * @param request A instância de HttpServletRequest.
	 */
	private Map<String, Object> obtainAttributes(Iterator<?> iter) throws IOException,
			ClassNotFoundException {
		Map<String, Object> parametros = new HashMap<String, Object>();

		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next(); 

			if (!item.isFormField()) {
				parametros.put(ITEM_FILE, item);
			} else if (ITEM_ID_CLIENTE.equals(item.getFieldName())) {
				parametros.put(ITEM_ID_CLIENTE, item.getString());
			} else if (ITEM_PID.equals(item.getFieldName())) {
				parametros.put(ITEM_PID, item.getString());
			} 
		}

		return parametros;
	}

	/**
	 * Busca o valor no mapa de parametros
	 *
	 * @param mapa
	 * @param key
	 * @return Caso ocorra algum erro na operação
	 */
	protected String getParameterMap(Map<String, String[]> mapa, String key) {
		if (mapa.containsKey(key) && mapa.get(key).length > 0 && mapa.get(key) != null) {
			return mapa.get(key)[0];
		} else {
			return ""; // Evitando NullPointers
		}
	}
	
	public static void copy(String fromFileName, String toFileName) throws IOException {
		File fromFile = new File(fromFileName);
		File toFile = new File(toFileName);
		
		if (!fromFile.exists())
		    throw new IOException("FileCopy: " + "no such source file: "
		        + fromFileName);
		if (!fromFile.isFile())
		    throw new IOException("FileCopy: " + "can't copy directory: "
		        + fromFileName);
		if (!fromFile.canRead())
		    throw new IOException("FileCopy: " + "source file is unreadable: "
		        + fromFileName);
		
		if (toFile.isDirectory())
		    toFile = new File(toFile, fromFile.getName());
		
		String parent = toFile.getParent();
		if (parent == null)
		  parent = System.getProperty("user.dir");
		File dir = new File(parent);
		if (!dir.exists())
		  throw new IOException("FileCopy: "
		      + "destination directory doesn't exist: " + parent);
		if (dir.isFile())
		  throw new IOException("FileCopy: "
		      + "destination is not a directory: " + parent);
		if (!dir.canWrite())
		  throw new IOException("FileCopy: "
		      + "destination directory is unwriteable: " + parent);
	
		FileInputStream from = null;
		FileOutputStream to = null;
		try {
		  from = new FileInputStream(fromFile);
		  to = new FileOutputStream(toFile);
		  byte[] buffer = new byte[4096];
		  int bytesRead;
		
		  while ((bytesRead = from.read(buffer)) != -1)
		    to.write(buffer, 0, bytesRead); // write
		} finally {
		  if (from != null)
		    try {
		      from.close();
		    } catch (IOException e) {
		      ;
		    }
		  if (to != null)
		    try {
		      to.close();
		    } catch (IOException e) {
		      ;
		    }
		}
	}
	
	// usage: scaleImage(strutsForm.getPhoto().getInputStream(), 500, 500)));
	public static InputStream scaleImage(InputStream p_image, int p_width, int p_height) throws Exception {
		 
	     InputStream imageStream = new BufferedInputStream(p_image);
	     Image image = (Image) ImageIO.read(imageStream); 
	 
	     int thumbWidth = p_width;
	        int thumbHeight = p_height;        
	 
	        // Make sure the aspect ratio is maintained, so the image is not skewed
	        double thumbRatio = (double)thumbWidth / (double)thumbHeight;
	        int imageWidth = image.getWidth(null);
	        int imageHeight = image.getHeight(null);
	        double imageRatio = (double)imageWidth / (double)imageHeight;
	        if (thumbRatio < imageRatio) {
	          thumbHeight = (int)(thumbWidth / imageRatio);
	        } else {
	          thumbWidth = (int)(thumbHeight * imageRatio);
	        }
	 
	        // Draw the scaled image
	        BufferedImage thumbImage = new BufferedImage(thumbWidth, 
	          thumbHeight, BufferedImage.TYPE_INT_RGB);
	        Graphics2D graphics2D = thumbImage.createGraphics();
	        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	          RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
	 
	        // Write the scaled image to the outputstream
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	        JPEGEncodeParam param = encoder.
	          getDefaultJPEGEncodeParam(thumbImage);
	        int quality = 100; // Use between 1 and 100, with 100 being highest quality
	        quality = Math.max(0, Math.min(quality, 100));
	        param.setQuality((float)quality / 100.0f, false);
	        encoder.setJPEGEncodeParam(param);
	        encoder.encode(thumbImage);        
	        ImageIO.write(thumbImage, "jpg" , out); 
	 
	        // Read the outputstream into the inputstream for the return value
	        ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());        
	 
	        return bis;        
	    }	
}

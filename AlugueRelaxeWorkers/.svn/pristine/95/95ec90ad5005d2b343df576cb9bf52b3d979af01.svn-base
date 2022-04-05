package br.com.jcv.backend.workers.spammer;

import java.io.Serializable;
import java.util.List;

import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;

public class ConfigSpammerDTO implements Serializable {

	private static final long serialVersionUID = -3949989315059280496L;
	
	/**
	 * Template html para ser enviado durante o processo
	 */
	public String template;
	
	/**
	 * Tarefa do config - objetivo
	 */
	public String tarefa;
	
	/**
	 * Assunto do e-mail - campo subject
	 */
	public String assunto;
	
	/**
	 * lista xml contendo os e-mails para contatos
	 */
	public String catalogo;
	
	/**
	 * tempo de espera entre envio de emails em milisegundos
	 */
	public long tempoespera;

	/**
	 * Modo reverso - leitura da lista do ultimo e-mail ate o primeiro
	 */
	public boolean modoreverso;

	/**
	 * lista de publicadores da mensagem
	 */
	public List<PublicadorVO> publicadores;
	
	/**
	 * lista de servidores de smtp
	 */
	public List<SmtpServerVO> listsmtp;
	
	/**
	 * lista dos imóveis que terão suas informações enviadas durante o spammer
	 */
	public List<ImovelFichaCompletaDTO> listimovel;
	
	/**
	 * Modo de envio
	 */
	public String modoEnvio;
	
	/**
	 * Caminho completo da fila de envio quando modoEnvio = QUEUE
	 */
	public String queuePath;
	
}

package br.com.jcv.backend.robot;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.jcv.backend.eventlog.eventos.robots.EventoRobotDTO;
import br.com.jcv.backend.eventlog.eventos.robots.EventoRobotServicesImpl;
import br.com.jcv.backend.eventlog.exception.AlugueRelaxeEventlogException;
import br.com.jcv.backend.eventlog.services.EventoRobotServices;

/**
 * <h2>Robot</h2>
 *<p>Classe abstrata para a criação de um Robot. Esta classe implementa a interface
 * FuncoesRobot
 *@see FuncoesRobot
 *</p>
 * @author elmt
 *
 */
public abstract class Robot implements FuncoesRobot {
	
	private static Logger logger = Logger.getLogger(Robot.class); 
	private String status;
	private String nome;
	private String funcao;
	private Date birthday;
	private long pid;
	private int contador;
	
	private Map<String,String> parametros;
	
	/**
	 * <p>Construtor para a implementação da classe Robot. Já inicializar o nome do
	 * Robot, seu status e a data de nascimento.</p>
	 * <p>Insere também o novo Robot criado na lista de trabalhadores identificado
	 * pelo método <code>RobotWorkers.getInstance()</code></p>
	 *
	 * @param nome
	 */
	@Deprecated
	public Robot(String nome){
		this.nome = nome;
		this.status = CicloVidaRobot.ROBOT_CRIADO;
		this.birthday = new GregorianCalendar().getTime();
		this.parametros = new HashMap<String, String>(); 
		this.contador = 0;
		
		logger.info("Robot " + nome + " gerado em " + this.birthday.toString());
	}
	
	public Robot(String nome, String tarefa){
		this.nome = nome;
		this.status = CicloVidaRobot.ROBOT_CRIADO;
		this.birthday = new GregorianCalendar().getTime();
		this.parametros = new HashMap<String, String>(); 
		this.contador = 0;

		EventoRobotDTO erdto = new EventoRobotDTO();
		erdto.nomeRobot = nome;
		erdto.nomeTarefa = tarefa;
		erdto.indicadorAcionamento = "S";
		erdto.textoMensagemEvento = "Instância iniciada";
		
		try {
			EventoRobotServices<EventoRobotDTO> ers = new EventoRobotServicesImpl();
			pid = ers.criarTarefa(erdto);
		} catch (AlugueRelaxeEventlogException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Robot " + nome + " gerado em " + this.birthday.toString());
	}
	
	public void addParametros(String chave, String conteudo){
		this.parametros.put(chave, conteudo);
	}
	
	public void addContador() {
		this.contador++;
	}
	
	public int getContador(){
		return this.contador;
	}
	
	public String getParametros(String chave){
		return this.parametros.get(chave);
	}
	
	/**
	 *<p>Retorna a data de nascimento do Robot</p>
	 *
	 * @return Date
	 */
	public Date getBirthday() {
		return this.birthday;
	}
	
	
	/**
	 * <p>Retorna o nome do Robot</p>
	 *
	 * @return String
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * <p>Retorna o status do ciclo de vida do Robot</p>
	 *
	 * @return String
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * <p>Configura o status do ciclo de vida do Robot</p>
	 *
	 * @param String
	 */
	@Deprecated
	public void setStatus(String status) {
		this.status = status;
		logger.info("Robot " + nome + " com status = " + status);
	}
	
	public void setStatus(String status, String tarefa) {
		this.status = status;
		try {
			EventoRobotServices<EventoRobotDTO> ers = new EventoRobotServicesImpl();
			ers.atualizaStatus(this.pid, status, tarefa);
		} catch (AlugueRelaxeEventlogException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Robot " + nome + " com status = " + status);
	}
	
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	public String getFuncao() {
		return this.funcao;
	}
	
}


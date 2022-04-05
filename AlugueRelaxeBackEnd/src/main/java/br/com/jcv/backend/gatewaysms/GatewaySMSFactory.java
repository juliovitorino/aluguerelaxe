package br.com.jcv.backend.gatewaysms;

/**
 * Factory para implementacoes da interface IGatewaySMS
 * @author Julio
 *
 */
public class GatewaySMSFactory {
	
	private static final int MOBILE_PRONTO = 0;
	private static final int SMSEMPRESA = 1;
	private static final int ZENVIA = 2;
	
	private GatewaySMSFactory() {
	}
	
	public static IGatewaySMS getInstance(int idx) {
		switch(idx) {
			case MOBILE_PRONTO:
				return new MobileProntoGatewaySMS();
			case SMSEMPRESA:
				return new SMSEmpresaGatewaySMS();
			case ZENVIA:
				return new ZenviaGatewaySMS();
			default:
				return null;
		}
	}
	
}
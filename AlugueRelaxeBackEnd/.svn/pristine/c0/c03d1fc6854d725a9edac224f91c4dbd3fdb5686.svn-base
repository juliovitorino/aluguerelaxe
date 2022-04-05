package br.com.jcv.backend.imovel;

import java.util.HashMap;
import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.validador.Validador;

public class ImovelFichaCompletaValidador extends
		Validador<ImovelFichaCompletaDTO> {

	@Override
	public List<String> execute(ImovelFichaCompletaDTO dto) {
		try {
			if (dto == null) {
				HashMap<String, String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, "Ficha do Imóvel");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			} else {
				//--------------------------------------------------------------------------------------
				// campos nulos
				//--------------------------------------------------------------------------------------
				
				if (dto.imovel == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Informações sobre Imóvel]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				} else {
					if (dto.imovel.endereco == null){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Informações sobre endereço do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
					}
				}

				//--------------------------------------------------------------------------------------
				// Tamanhos de campos
				//--------------------------------------------------------------------------------------
				if (dto.imovel != null){
					validarTamanhoCampoString(dto.imovel.descricaoGeral,1,2000,"[Descrição geral do imóvel]");
					validarTamanhoCampoString(dto.imovel.descricaoQuartos,1,2000,"[Descrição dos quartos do imóvel]");
					validarTamanhoCampoString(dto.imovel.descricaoArredores,1,2000,"[Descrição dos arredores do imóvel]");
					validarTamanhoCampoString(dto.imovel.descricaoTituloAnuncio,1,200,"[Descrição do título do anúncio do imóvel]");
					validarTamanhoCampoString(dto.imovel.observacoes,0,2000,"[Observações]");

					if (dto.imovel.valorTarifaBasica <= 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Valor diaria base]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.PREENCHER_DIARIA_BASE_OBRIGATORIO));
					}
					// Verifica se tem notacao de e-mail dentro dos campos
					// -- Descricao Geral
					Object[] ocorEmailDescGeral = StringUtil.getEmailInText(dto.imovel.descricaoGeral);
					if (ocorEmailDescGeral.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição geral do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.EMAIL_NAO_PERMITIDO, parametros));
					}
					Object[] ocorUrlDescGeral = StringUtil.getUrlInText(dto.imovel.descricaoGeral);
					if (ocorUrlDescGeral.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição geral do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.URL_NAO_PERMITIDO, parametros));
					}
					Object[] ocorFoneDescGeral = StringUtil.getTelefoneInText(dto.imovel.descricaoGeral);
					if (ocorFoneDescGeral.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição geral do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.FONE_NAO_PERMITIDO, parametros));
					}
					
					// -- Descricao Quartos
					Object[] ocorEmailDescQuartos = StringUtil.getEmailInText(dto.imovel.descricaoQuartos);
					if (ocorEmailDescQuartos.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição dos quartos do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.EMAIL_NAO_PERMITIDO, parametros));
					}
					Object[] ocorUrlDescQuartos = StringUtil.getUrlInText(dto.imovel.descricaoQuartos);
					if (ocorUrlDescQuartos.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição dos quartos do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.URL_NAO_PERMITIDO, parametros));
					}
					Object[] ocorFoneDescQuartos = StringUtil.getTelefoneInText(dto.imovel.descricaoQuartos);
					if (ocorFoneDescQuartos.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição dos quartos do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.FONE_NAO_PERMITIDO, parametros));
					}
					
					// -- Descricao Arredores
					Object[] ocorEmailDescArredores = StringUtil.getEmailInText(dto.imovel.descricaoArredores);
					if (ocorEmailDescArredores.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição dos arredores do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.EMAIL_NAO_PERMITIDO, parametros));
					}
					Object[] ocorUrlDescArredores = StringUtil.getUrlInText(dto.imovel.descricaoArredores);
					if (ocorUrlDescArredores.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição dos arredores do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.URL_NAO_PERMITIDO, parametros));
					}
					Object[] ocorFoneDescArredores = StringUtil.getTelefoneInText(dto.imovel.descricaoArredores);
					if (ocorFoneDescArredores.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição dos arredores do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.FONE_NAO_PERMITIDO, parametros));
					}
					
					// -- Descricao Titulo Anuncio
					Object[] ocorEmailDescTitulo = StringUtil.getEmailInText(dto.imovel.descricaoTituloAnuncio);
					if (ocorEmailDescTitulo.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição do título do anúncio do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.EMAIL_NAO_PERMITIDO, parametros));
					}
					Object[] ocorUrlDescTitulo = StringUtil.getUrlInText(dto.imovel.descricaoTituloAnuncio);
					if (ocorUrlDescTitulo.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição do título do anúncio do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.URL_NAO_PERMITIDO, parametros));
					}
					Object[] ocorFoneDescTitulo = StringUtil.getTelefoneInText(dto.imovel.descricaoTituloAnuncio);
					if (ocorFoneDescTitulo.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Descrição do título do anúncio do imóvel]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.FONE_NAO_PERMITIDO, parametros));
					}
					
					// -- Observacao
					Object[] ocorEmailObs = StringUtil.getEmailInText(dto.imovel.observacoes);
					if (ocorEmailObs.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Observações]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.EMAIL_NAO_PERMITIDO, parametros));
					}
					Object[] ocorUrlObs = StringUtil.getUrlInText(dto.imovel.observacoes);
					if (ocorUrlObs.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Observações]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.URL_NAO_PERMITIDO, parametros));
					}
					Object[] ocorFoneObs = StringUtil.getTelefoneInText(dto.imovel.observacoes);
					if (ocorFoneObs.length > 0){
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Observações]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.FONE_NAO_PERMITIDO, parametros));
					}
					
					List<String> lstErrosEndereco = ValidadorFactory.getInstanceEndereco().execute(dto.imovel.endereco);
					merge(lstErrosEndereco);
					
					if ((dto.tabelaPreco != null) && (dto.tabelaPreco.size() > 0)){
						for (TabelaPrecoDTO item : dto.tabelaPreco) {
							List<String> lstErrosTabelaPreco = ValidadorFactory.getInstanceTabelaPreco().execute(item);
							merge(lstErrosTabelaPreco);
						}
					}
				}
			}
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return super.getList();
	}

}

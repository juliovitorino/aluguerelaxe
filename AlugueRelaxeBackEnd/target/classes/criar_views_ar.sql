/* View para planos pagos com faturas a vencer */
CREATE view VW_PLANOS_PG_FATURAS_A_VENCER (
    ID_IMOVEL,
    ID_CLIENTE,
    NOME_CLIENTE,
    ID_FATURA,
    VALOR_FATURA,
    VALOR_DESCONTO,
    DT_VENCIMENTO,
    DT_CADASTRO,
	NOME_PLANO,
	DESC_PLANO
) AS
select
    b.IMOV_CD_IMOVEL,
    d.CLIE_CD_CLIENTE,
    e.CLIE_NM_CLIENTE,
    a.IMPF_CD_IMOVEL_PLANO_FATURA,
    a.IMPF_VL_FATURA,
    a.IMPF_VL_DESCONTO,
    a.IMPF_DT_VENCIMENTO,
    a.IMPF_DT_CADASTRO,
	c.PLAN_NM_PLANO,
    c.PLAN_TX_DESCRICAO
    from IMOVEL_PLANO_FATURA a 
    inner join IMOVEL_PLANO b on a.IMPL_CD_IMOVEL_PLANO = b.IMPL_CD_IMOVEL_PLANO 
    inner join PLANO c on b.PLAN_CD_PLANO = c.PLAN_CD_PLANO 
    inner join IMOVEL d on b.IMOV_CD_IMOVEL = d.IMOV_CD_IMOVEL 
    inner join CLIENTE e on d.CLIE_CD_CLIENTE = e.CLIE_CD_CLIENTE
    where     a.IMPF_DT_PGTO is null
    and     a.IMPF_IN_STATUS = 'P'
    and     CURRENT_DATE < a.IMPF_DT_VENCIMENTO
    and     b.PLAN_CD_PLANO <> 1
    and     c.PLAN_IN_TIPO_COMPRA = 'N'
    and     d.IMOV_IN_STATUS = 'P';
	
/* View para planos de publicidades com faturas a vencer */
CREATE view VW_PUBLICIDADE_FATURAS_A_VENCER (
    ID_IMOVEL,
    ID_CLIENTE,
    NOME_CLIENTE,
    ID_FATURA,
    VALOR_FATURA,
    VALOR_DESCONTO,
    DT_VENCIMENTO,
    DT_CADASTRO,
	NOME_PLANO,
	DESC_PLANO
) AS
select
    b.IMOV_CD_IMOVEL,
    d.CLIE_CD_CLIENTE,
    e.CLIE_NM_CLIENTE,
    a.IMPF_CD_IMOVEL_PLANO_FATURA,
    a.IMPF_VL_FATURA,
    a.IMPF_VL_DESCONTO,
    a.IMPF_DT_VENCIMENTO,
    a.IMPF_DT_CADASTRO,
    c.PLAN_NM_PLANO,
    c.PLAN_TX_DESCRICAO
    from IMOVEL_PLANO_FATURA a 
    inner join IMOVEL_PLANO b on a.IMPL_CD_IMOVEL_PLANO = b.IMPL_CD_IMOVEL_PLANO 
    inner join PLANO c on b.PLAN_CD_PLANO = c.PLAN_CD_PLANO 
    inner join IMOVEL d on b.IMOV_CD_IMOVEL = d.IMOV_CD_IMOVEL 
    inner join CLIENTE e on d.CLIE_CD_CLIENTE = e.CLIE_CD_CLIENTE
    where     a.IMPF_DT_PGTO is null
    and     a.IMPF_IN_STATUS = 'P'
    and     CURRENT_DATE < a.IMPF_DT_VENCIMENTO
    and     c.PLAN_IN_TIPO_COMPRA <> 'N'
    and     d.IMOV_IN_STATUS = 'A';
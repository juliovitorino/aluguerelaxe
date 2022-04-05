connect "firebird.aluguerelaxe.com.br:/firebird/aluguerelaxe.gdb" user 'aluguerelaxe' password 'fork3t56';
set names ISO8859_1;
set sql dialect 3;


select '<mailing-record>' ||
       '<nome>' || b.CLIE_NM_CLIENTE || '</nome>' ||
       '<email>' || b.CLIE_TX_EMAIL || '</email>' ||
       '<textolivre1>' || a.IMOV_CD_IMOVEL || '</textolivre1>' ||
       '<textolivre2>' || c.uf_cd_estado || '</textolivre2>' ||
       '<textolivre3>' || a.IMOV_TX_TITULO_ANUNCIO || '</textolivre3>' ||
       '<textolivre5>' || d.cida_nm_cidade || '</textolivre5>' ||
       '<textolivre4>' || count(*) || '</textolivre4>' ||
       '</mailing-record>'
from IMOVEL_IMAGEM_VIDEO imvi
inner join IMOVEL a on imvi.IMOV_CD_IMOVEL = a.IMOV_CD_IMOVEL
inner join CLIENTE b on a.CLIE_CD_CLIENTE = b.CLIE_CD_CLIENTE
inner join UF_CIDADE_ITEM c on c.ufci_cd_uf_cidade_item = a.ufci_cd_uf_cidade_item
inner join CIDADE d on d.cida_cd_cidade = c.cida_cd_cidade
where a.IMOV_IN_STATUS = 'A'
and   imvi.IMIV_IN_IMAGEM_VIDEO = 'TB'
group by b.CLIE_NM_CLIENTE,
         b.CLIE_TX_EMAIL,
         a.IMOV_CD_IMOVEL,
         c.uf_cd_estado,
         a.IMOV_TX_TITULO_ANUNCIO,
         d.cida_nm_cidade
having count(*) < 8;

commit;
exit;

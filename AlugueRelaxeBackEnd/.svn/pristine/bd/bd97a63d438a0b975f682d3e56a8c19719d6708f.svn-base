connect "firebird.aluguerelaxe.com.br:/firebird/aluguerelaxe.gdb" user 'aluguerelaxe' password 'fork3t56';

select  '<mailing-record>' ||
        '<nome>' || LEFT(b.clie_nm_cliente, POSITION(' ' IN (b.clie_nm_cliente || ' '))) || '</nome>' || /* primeiro no do cliente */
        '<email>' || b.CLIE_TX_EMAIL || '</email>' || /* email do cliente */
        '<textolivre1>' || a.IMOV_CD_IMOVEL ||'</textolivre1>' ||
        '<textolivre2>' || a.IMOV_DT_ATUALIZACAO ||'</textolivre2>' ||
        '<textolivre3>' || a.IMOV_TX_TITULO_ANUNCIO || '</textolivre3>' ||
        '<textolivre4>' || trunc(CURRENT_TIMESTAMP - a.IMOV_DT_ATUALIZACAO) ||'</textolivre4>' ||
    '</mailing-record>'
from IMOVEL a 
inner join CLIENTE b on a.CLIE_CD_CLIENTE = b.CLIE_CD_CLIENTE
where (CURRENT_TIMESTAMP - a.IMOV_DT_ATUALIZACAO) >= 90
and    a.IMOV_IN_STATUS = 'A';

        commit;
        exit;

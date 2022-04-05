connect "/var/aluguerelaxe/data/fb/aluguerelaxe-replicado.fdb" user 'aluguerelaxe' password 'fork3t56';

select  '<mailing-record>' ||
            '<nome>' || LEFT(c.clie_nm_cliente, POSITION(' ' IN (c.clie_nm_cliente || ' '))) || '</nome>' || /* primeiro no do cliente */
            '<email>' || c.CLIE_TX_EMAIL || '</email>' || /* email do cliente */
            '<textolivre1>' || lpad(extract(day from c.clie_dt_nascimento),2,'0') ||'/' || lpad(extract(month from c.clie_dt_nascimento),2,'0')||                      '</textolivre1>' ||
    '</mailing-record>'
    from CLIENTE c
    where c.CLIE_IN_STATUS = 'A'
    and extract(month from c.clie_dt_nascimento) = 9
    order by  c.CLIE_NM_CLIENTE;

        commit;
        exit;

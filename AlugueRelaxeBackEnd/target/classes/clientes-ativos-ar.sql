connect "/var/aluguerelaxe/data/fb/aluguerelaxe-replicado.fdb" user 'aluguerelaxe' password 'fork3t56';

	select  '<mailing-record>' ||
			'<nome>' || LEFT(c.clie_nm_cliente, POSITION(' ' IN (c.clie_nm_cliente || ' '))) || '</nome>' || /* primeiro no do cliente */
			'<email>' || c.CLIE_TX_EMAIL || '</email>' || /* email do cliente */
			'</mailing-record>'
	from CLIENTE c 
	where c.CLIE_IN_STATUS = 'A'
	order by c.CLIE_NM_CLIENTE;

        commit;
        exit;

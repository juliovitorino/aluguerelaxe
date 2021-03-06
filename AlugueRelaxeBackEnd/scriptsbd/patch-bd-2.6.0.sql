ALTER TABLE PLANO DROP CONSTRAINT INTEG_102;

alter table PLANO
add constraint INTEG_102
check (PLAN_IN_TIPO_COMPRA IN ('N','P','D','E','W','B','M','X','F','R','Z'));

alter table plano add	PLAN_NU_BYTES_VAULT  NUMERIC(14) DEFAULT 0 NOT NULL CHECK (PLAN_NU_BYTES_VAULT BETWEEN 0 AND 10995116277760);

alter table plano add 	PLAN_IN_RECURSO_01  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_02  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_03  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_04  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_05  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_06  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_07  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_08  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_09  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_10  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_11  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_12  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_13  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_14  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_15  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_16  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_17  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_18  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_19  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_20  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_21  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_22  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_23  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_24  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_25  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_26  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_27  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_28  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_29  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_30  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_31  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_32  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_33  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_34  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_35  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_36  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_37  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_38  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_39  VARCHAR(5) DEFAULT 'N000X' NOT NULL;
alter table plano add 	PLAN_IN_RECURSO_40  VARCHAR(5) DEFAULT 'N000X' NOT NULL;

ALTER TABLE PUBLICIDADE DROP CONSTRAINT INTEG_141;

alter table PUBLICIDADE
add constraint INTEG_141
check (PUBL_IN_TIPO_PUBLICIDADE IN ('PP','PD','SB','EM','SM','BN','FB','FF','PA'));



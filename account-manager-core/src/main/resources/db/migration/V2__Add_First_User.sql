INSERT INTO APPLICATION (ID, CREATED_AT, CREATED_BY, LIFE_CYCLE_STATUS, UPDATED_AT, UPDATED_BY, CODE, DESCRIPTION, CONTEXT_NAME, UUID) 
	   VALUES (1,NOW(),'682eb67387a84d54b9adf93247aefb55','ACTIVE',NULL,NULL,'01','Gestão de Acessos','/account-manager-web',''),
			  (2,NOW(),'682eb67387a84d54b9adf93247aefb55','ACTIVE',NULL,NULL,'02','Sistema de Gestão para Farmácias','/ncare-web','');

INSERT INTO ROLE (ID, CREATED_AT, CREATED_BY, LIFE_CYCLE_STATUS, UPDATED_AT, UPDATED_BY, DESCRIPTION, REMARKS, NAME, UUID) 
	   VALUES (1,NOW(),'682eb67387a84d54b9adf93247aefb55','ACTIVE',NULL,NULL,'Esta permisão permite um acesso total a aplicação',NULL,'Administrador',''),
	          (2,NOW(),'682eb67387a84d54b9adf93247aefb55','ACTIVE',NULL,NULL,'Apenas opera em tarefas definidas',NULL,'Operador','');

INSERT INTO APPLICATION_ROLE (ID, CREATED_AT, CREATED_BY, LIFE_CYCLE_STATUS, UPDATED_AT, UPDATED_BY, APPLICATION_ID, ROLE_ID, UUID) 
	   VALUES (1,NOW(),'682eb67387a84d54b9adf93247aefb55','ACTIVE',NULL,NULL,1,1,'');

INSERT INTO USER (ID, CREATED_AT, CREATED_BY, LIFE_CYCLE_STATUS, UPDATED_AT, UPDATED_BY, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, EMAIL, ENABLED, FULL_NAME, PASSWORD, SESSION_ID, USER_NAME, UUID) 
       VALUES (1,NOW(),'682eb67387a84d54b9adf93247aefb55','ACTIVE',NULL,NULL,'','','','steliomo@gmail.com','','Stelio Klesio Adriano Moiane','$2a$10$Kypqbsvr6/P2QniJxrB34.o3FKq0o/3qMsgruRQruMkbXqGozu1fu','8cf95259-d150-4812-9915-b16817fccf1c','steliomo', '08cc21901f18424ebb4a232f8963fc1b');

INSERT INTO USER_APPLICATION_ROLE (ID, CREATED_AT, CREATED_BY, LIFE_CYCLE_STATUS, UPDATED_AT, UPDATED_BY, APPLICATION_ROLE_ID, USER_ID, UUID) 
	   VALUES (1,NOW(),'682eb67387a84d54b9adf93247aefb55','ACTIVE',NULL,NULL,1,1,'');

INSERT INTO UNITS (ID, CREATED_AT, CREATED_BY, LIFE_CYCLE_STATUS, UPDATED_AT, UPDATED_BY, ADDRESS, CODE, CONTACT, EMAIL, NAME, NUIT, UUID) 
       VALUES (1,NOW(),'682eb67387a84d54b9adf93247aefb55','ACTIVE',NULL,NULL,'Rua da Mozal, Bairro Djuba, Q - 2, Casa nr 375.Matola - Rio','AMU000001','+258 82 2546100','stelio@gmail.com','Farmácia Alima', '102124774', '');

INSERT INTO UNIT_USER_APP_ROLES  VALUES (1,1);

INSERT INTO TRANSACTIONS (ID, CREATED_AT, CREATED_BY, LIFE_CYCLE_STATUS, UPDATED_AT, UPDATED_BY, CODE, NAME, APPLICATION_ID, UUID) 
	   VALUES (1,NOW(),'682eb67387a84d54b9adf93247aefb55','ACTIVE',NULL,NULL,'001','Realizar Vendas',2,''),
	          (2,NOW(),1,'ACTIVE',NULL,NULL,'002','Cadastrar Stocks',2,''),
	          (3,NOW(),1,'ACTIVE',NULL,NULL,'003','Listar Stocks',2,''),
	          (4,NOW(),1,'ACTIVE',NULL,NULL,'004','Remover/Editar Stocks',2,''),
	          (5,NOW(),1,'ACTIVE',NULL,NULL,'005','Manutenção de Medicamentos',2,'');
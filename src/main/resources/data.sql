INSERT INTO USUARIO(nome, email, senha) VALUES ('Aluno', 'aluno@email.com', '123456');

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida', 'Erro ao criar projeto', '2019-05-05 20:36:15', 'SOLUCIONADO', 1, 1); 
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida2', 'Projeto não compila', '2019-05-05 20:36:15', 'NAO_RESPONDIDO', 1, 1); 
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida3', 'Tag HTML', '2019-05-05 20:36:15', 'NAO_SOLUCIONADO', 1, 2); 
INSERT INTO Perfil (experiencia) VALUES ('experiencia');

INSERT INTO Usuario (perfil_id,nome, email, senha, numero_cadastro_pessoa, pais, funcao, is_ativo)
VALUES (1,'Murilo', 'murilo@hotmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '26828654000100', 'Brasil', 'dev', 1);

INSERT INTO Usuario (nome, email, senha, numero_cadastro_pessoa, pais, funcao, is_ativo)
VALUES ('Leonardo', 'leo@hotmail.com', '$2a$10$ZMM5Jes4mEKhZoRbu4lafuk5BSL0OMwbtYvcfq2LaxXthfBfdxWDq', '89138182000175', 'Brasil', 'empresa', 1);

INSERT INTO Flag (nome,area,categoria) VALUES ('Java','Back-end','developer');
INSERT INTO Flag (nome,area,categoria) VALUES ('React JS','Front-end','developer');
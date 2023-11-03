-- Lista de flags
INSERT INTO Flag (nome, area, categoria)
VALUES
-- Front-End
('HTML/CSS', 'front-end', 'hard-skill'),
('JavaScript', 'front-end', 'hard-skill'),
('React', 'front-end', 'hard-skill'),
('Angular', 'front-end', 'hard-skill'),
('Vue.js', 'front-end', 'hard-skill'),
('SASS/SCSS', 'front-end', 'hard-skill'),
-- Back-End
('Node.js', 'back-end', 'hard-skill'),
('Express.js', 'back-end', 'hard-skill'),
('Django', 'back-end', 'hard-skill'),
('Ruby on Rails', 'back-end', 'hard-skill'),
('ASP.NET', 'back-end', 'hard-skill'),
('Python', 'back-end', 'hard-skill'),
-- Mobile
('Kotlin (Android)', 'mobile', 'hard-skill'),
('Swift (iOS)', 'mobile', 'hard-skill'),
('Xamarin', 'mobile', 'hard-skill'),
('Java (Android)', 'mobile', 'hard-skill'),
('Objective-C (iOS)', 'mobile', 'hard-skill'),
('Flutter', 'mobile', 'hard-skill'),
-- Banco de Dados
('MySQL', 'banco de dados', 'hard-skill'),
('PostgreSQL', 'banco de dados', 'hard-skill'),
('MongoDB', 'banco de dados', 'hard-skill'),
('SQLite', 'banco de dados', 'hard-skill'),
('Microsoft SQL Server', 'banco de dados', 'hard-skill'),
('Oracle Database', 'banco de dados', 'hard-skill'),
-- Testes
('Jest', 'testes', 'hard-skill'),
('Selenium', 'testes', 'hard-skill'),
('JUnit', 'testes', 'hard-skill'),
('Pytest', 'testes', 'hard-skill'),
('Cypress', 'testes', 'hard-skill'),
('Mocha', 'testes', 'hard-skill'),
-- Análise de Dados
('Python (Pandas, NumPy, Matplotlib, Seaborn)', 'análise de dados', 'hard-skill'),
('R', 'análise de dados', 'hard-skill'),
('SQL', 'análise de dados', 'hard-skill'),
('Jupyter Notebook', 'análise de dados', 'hard-skill'),
('Tableau', 'análise de dados', 'hard-skill'),
('Power BI', 'análise de dados', 'hard-skill'),
-- DevOps
('Docker', 'devops', 'hard-skill'),
('Kubernetes', 'devops', 'hard-skill'),
('Jenkins', 'devops', 'hard-skill'),
('Git', 'devops', 'hard-skill'),
('Ansible', 'devops', 'hard-skill'),
('Terraform', 'devops', 'hard-skill'),
-- Soft Skills
('Resiliência', null, 'soft-skill'),
('Empatia', null, 'soft-skill'),
('Comunicação', null, 'soft-skill');

-- Lista de perfis
INSERT INTO Perfil (sobre_mim, experiencia, descricao, preco_medio, nome_github, link_github, link_linkedin) VALUES
('Sou um Cientista de Dados', 'Com mais de 5 anos de experiência em análise de dados', 'Interessado em projetos que envolvem análise de dados e aprendizado de máquina.', 450.0, 'meugithubusuario', 'https://github.com/meugithubusuario', 'https://www.linkedin.com/in/meulinkedinusuario'),
('Desenvolvedor Front-End', 'Criando experiências de usuário incríveis', 'Dedicado a projetar e implementar interfaces de usuário atraentes e funcionais. Especializado em HTML, CSS, JavaScript e frameworks front-end.', 600.0, 'frontenddev123', 'https://github.com/frontenddev123', 'https://www.linkedin.com/in/frontenddev123'),
('Cientista de Dados', '5+ anos em análise de dados', 'Apaixonado por dados e análises. Experiente em Python, SQL e ferramentas de visualização de dados.', 600.0, 'cientistadedados1', 'https://github.com/cientistadedados1', 'https://www.linkedin.com/in/cientistadedados1'),
('Desenvolvedor Full Stack', '8+ anos em desenvolvimento web', 'Especialista em JavaScript, React, Node.js e muito mais. Construir aplicações web é minha paixão.', 700.0, 'fullstackdev', 'https://github.com/fullstackdev', 'https://www.linkedin.com/in/fullstackdev'),
('Designer UI/UX', 'Designer criativo com foco na experiência do usuário', 'Crio designs bonitos e amigáveis ao usuário. Proficiente em Figma e Adobe XD.', 500.0, 'uxdesigner123', 'https://github.com/uxdesigner123', 'https://www.linkedin.com/in/uxdesigner123'),
('Engenheiro de Dados', '6+ anos em engenharia de dados', 'Experiente em construir pipelines de dados e processos ETL. Proficiente em tecnologias de big data.', 650.0, 'engenheirodedados123', 'https://github.com/engenheirodedados123', 'https://www.linkedin.com/in/engenheirodedados123'),
('Desenvolvedor Android', 'Desenvolvedor de aplicativos móveis com paixão pelo Android', 'Criando aplicativos inovadores para Android usando Kotlin e Java. Amo o mundo do desenvolvimento de aplicativos móveis.', 600.0, 'androiddev567', 'https://github.com/androiddev567', 'https://www.linkedin.com/in/androiddev567'),
('Designer UI/UX', 'Entusiasta do design com mentalidade criativa', 'Projetando interfaces intuitivas e amigáveis ao usuário. Proficiente em Sketch e Adobe XD.', 550.0, 'designer456', 'https://github.com/designer456', 'https://www.linkedin.com/in/designer456'),
('Desenvolvedor Full Stack', '5+ anos de experiência em desenvolvimento web', 'Habilidoso em tecnologias de front-end e back-end. Construir soluções web é minha paixão.', 700.0, 'fullstackdev2', 'https://github.com/fullstackdev2', 'https://www.linkedin.com/in/fullstackdev2'),
('Engenheiro de Aprendizado de Máquina', 'Entusiasta de IA com foco em aprendizado de máquina', 'Desenvolvendo modelos e soluções de aprendizado de máquina de ponta. Proficiente em Python e TensorFlow.', 750.0, 'mlengineer77', 'https://github.com/mlengineer77', 'https://www.linkedin.com/in/mlengineer77'),
('Designer UI', 'Criando belas interfaces de usuário', 'Apaixonado por criar designs visualmente atraentes e amigáveis ao usuário.', 600.0, 'designerdeui123', 'https://github.com/designerdeui123', 'https://www.linkedin.com/in/designerdeui123'),
('Desenvolvedor de Backend', '7+ anos em desenvolvimento server-side', 'Especialista em construir sistemas de backend robustos e escaláveis usando Node.js e Python.', 700.0, 'backenddev', 'https://github.com/backenddev', 'https://www.linkedin.com/in/backenddev'),
('Gerente de Projeto', 'Experiente em gerenciamento de projetos', 'Habilidoso em liderar equipes multifuncionais e entregar projetos de sucesso no prazo.', 650.0, 'gerentedeprojeto456', 'https://github.com/gerentedeprojeto456', 'https://www.linkedin.com/in/gerentedeprojeto456'),
('Analista de Dados', 'Analisando dados para obter insights', 'Proficiente em ferramentas e técnicas de análise de dados. Transformando dados em insights acionáveis.', 550.0, 'analistadedados007', 'https://github.com/analistadedados007', 'https://www.linkedin.com/in/analistadedados007'),
('Engenheiro de QA', 'Garantindo a qualidade do software', 'Dedicado a testar e garantir a qualidade de aplicativos de software. Habilidoso em testes automatizados.', 600.0, 'engenheiroqa88', 'https://github.com/engenheiroqa88', 'https://www.linkedin.com/in/engenheiroqa88'),
('Desenvolvedor Front-End', 'Experiência em criação de interfaces incríveis', 'Dedicado a projetar e desenvolver interfaces de usuário atraentes e funcionais. Especializado em HTML, CSS, JavaScript e frameworks front-end.', 550.0, 'frontender123', 'https://github.com/frontender123', 'https://www.linkedin.com/in/frontender123'),
('Desenvolvedor de Backend', '10+ anos de experiência em desenvolvimento server-side', 'Especialista em construir sistemas de backend escaláveis e eficientes. Dominando Node.js, Python e SQL.', 750.0, 'backendninja', 'https://github.com/backendninja', 'https://www.linkedin.com/in/backendninja'),
('Designer Gráfico', 'Transformando conceitos em designs visuais', 'Apaixonado por criar gráficos atraentes e conceitos visuais. Proficiente em Adobe Creative Suite.', 500.0, 'graphicdesigner456', 'https://github.com/graphicdesigner456', 'https://www.linkedin.com/in/graphicdesigner456'),
('Analista de Segurança Cibernética', 'Protegendo sistemas e dados', 'Comprometido com a segurança de TI e a proteção de sistemas contra ameaças cibernéticas. Especializado em testes de penetração e análise de vulnerabilidades.', 700.0, 'cybersecuritypro', 'https://github.com/cybersecuritypro', 'https://www.linkedin.com/in/cybersecuritypro'),
('Desenvolvedor Mobile', 'Criando aplicativos móveis de ponta', 'Desenvolvendo aplicativos móveis inovadores para Android e iOS usando Kotlin, Java e Swift. Apaixonado pelo mundo da mobilidade.', 650.0, 'mobiledev567', 'https://github.com/mobiledev567', 'https://www.linkedin.com/in/mobiledev567');

-- Flags de cada perfil
INSERT INTO Flag_Usuario (perfil_id, flag_id)
VALUES (1, 1),
       (1, 5),
       (1, 7),
       (1, 10),
       (1, 12),
       (1, 16),
       (1, 18),
       (1, 23),
       (1, 43),
       (1, 44),
       (1, 45),
       (2, 3),
       (2, 9),
       (2, 11),
       (2, 14),
       (2, 19),
       (2, 24),
       (3, 1),
       (3, 4),
       (3, 8),
       (3, 13),
       (3, 17),
       (3, 25),
       (4, 2),
       (4, 6),
       (4, 7),
       (4, 11),
       (4, 15),
       (4, 26),
       (5, 3),
       (5, 5),
       (5, 9),
       (5, 13),
       (5, 19),
       (5, 27),
       (6, 1),
       (6, 4),
       (6, 7),
       (6, 12),
       (6, 16),
       (6, 28),
       (7, 2),
       (7, 6),
       (7, 9),
       (7, 14),
       (7, 18),
       (7, 29),
       (8, 1),
       (8, 4),
       (8, 8),
       (8, 11),
       (8, 15),
       (8, 30),
       (9, 3),
       (9, 7),
       (9, 10),
       (9, 13),
       (9, 16),
       (9, 31),
       (10, 2),
       (10, 5),
       (10, 9),
       (10, 12),
       (10, 17),
       (10, 32),
       (11, 3),
       (11, 6),
       (11, 10),
       (11, 13),
       (11, 18),
       (11, 33),
       (12, 1),
       (12, 4),
       (12, 8),
       (12, 11),
       (12, 17),
       (12, 34),
       (13, 2),
       (13, 5),
       (13, 9),
       (13, 12),
       (13, 16),
       (13, 35),
       (14, 3),
       (14, 7),
       (14, 11),
       (14, 14),
       (14, 18),
       (14, 36),
       (15, 1),
       (15, 6),
       (15, 8),
       (15, 12),
       (15, 15),
       (15, 37),
       (16, 1),
       (16, 4),
       (16, 8),
       (16, 11),
       (16, 15),
       (16, 38),
       (17, 2),
       (17, 5),
       (17, 9),
       (17, 12),
       (17, 16),
       (17, 39),
       (18, 3),
       (18, 7),
       (18, 10),
       (18, 13),
       (18, 17),
       (18, 40),
       (19, 1),
       (19, 6),
       (19, 8),
       (19, 14),
       (19, 19),
       (19, 41),
       (20, 2),
       (20, 4),
       (20, 11),
       (20, 13),
       (20, 15),
       (20, 42);


-- Lista de usuários
INSERT INTO Usuario (perfil_id,nome, email, senha, numero_cadastro_pessoa, pais, funcao, is_ativo) VALUES
(1,'Murilo', 'murilo@hotmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '26828654000100', 'Brasil', 1, true),
(2,'Leonardo', 'leo@hotmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '89138182000175', 'Brasil', 0, true),
(3,'Ana', 'ana@gmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'Brasil', 0, true),
(4,'Lucas', 'lucas@yahoo.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'Brasil', 0, true),
(5,'Mariana', 'mariana@hotmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '55555555555555', 'Brasil', 0, true),
(6, 'Maria', 'maria@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'Brazil', 0, true),
(7, 'Carlos', 'carlos@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'Brazil', 0, true),
(8, 'Luisa', 'luisa@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '55555555555555', 'Brazil', 0, true),
(9, 'Rafael', 'rafael@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'Brazil', 0, true),
(10, 'Sofia', 'sofia@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'Brazil', 0, true),
(11, 'Gabriel', 'gabriel@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'Brazil', 0, true),
(12, 'Marta', 'marta@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'Brazil', 0, true),
(13, 'Pedro', 'pedro@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '55555555555555', 'Brazil', 0, true),
(14, 'Isabella', 'isabella@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'Brazil', 0, true),
(15, 'Lucas', 'lucas@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'Brazil', 0, true),
(16, 'Patricia', 'patricia@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '55555555555555', 'Brazil', 0, true),
(17, 'Fernando', 'fernando@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'Brazil', 0, true),
(18, 'Amanda', 'amanda@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'Brazil', 0, true),
(19, 'Rodrigo', 'rodrigo@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '55555555555555', 'Brazil', 0, true),
(20, 'Tatiana', 'tatiana@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'Brazil', 0, true);

-- Referencias
INSERT INTO Referencia_Perfil (avaliador_id, avaliado_id, is_favorito,is_recomendado) VALUES
(1,2, true, true),
(1,3, true, true),
(1,4, true, true),
(1,5, true, true),
(1,6, true, true),
(1,7, true, true),
(1,8, true, true),
(1,9, true, true),
(1,10,true, true);

-- Avaliacoes
INSERT INTO Avaliacao (perfil_id, comentario, qtd_estrela) VALUES
(1, 'Esse desenvolvedor é muito bom, me ajudou muito!', 5),
(1, 'Adorei o trabalho desse designer, super criativo!', 4),
(1, 'O serviço desse restaurante é excelente, recomendo.', 5),
(1, 'O atendimento na loja foi péssimo, nunca mais volto.', 1),
(1, 'O professor é muito dedicado, a aula foi ótima.', 5),
(1, 'A entrega foi atrasada, não fiquei satisfeito.', 2),
(1, 'A qualidade do produto é surpreendente, 5 estrelas!', 5),
(1, 'O filme foi incrível, recomendo a todos!', 4),
(1, 'O concerto da banda foi fenomenal, amei!', 5),
(1,  'O livro é entediante, não consegui terminar de ler.', 2),
(1,  'O app é prático e fácil de usar, nota 5!', 5),
(1,  'O show foi um desastre, péssima experiência.', 1),
(1,  'O artista é talentoso, adorei a exposição.', 4),
(1,  'A comida estava deliciosa, voltarei em breve.', 5),
(1,  'O serviço de entrega foi rápido e confiável, 5 estrelas!', 5),
(1,  'O suporte ao cliente foi péssimo, demoraram para resolver o problema.', 1),
(1, 'A qualidade do produto é surpreendente, 5 estrelas!', 5),
(1, 'O filme foi incrível, recomendo a todos!', 4),
(1, 'O concerto da banda foi fenomenal, amei!', 5),
(1,  'O livro é entediante, não consegui terminar de ler.', 2),
(1,  'O app é prático e fácil de usar, nota 5!', 5),
(1,  'O show foi um desastre, péssima experiência.', 1),
(1,  'O artista é talentoso, adorei a exposição.', 4),
(1,  'A comida estava deliciosa, voltarei em breve.', 5),
(1,  'O serviço de entrega foi rápido e confiável, 5 estrelas!', 5),
(1,  'O suporte ao cliente foi péssimo, demoraram para resolver o problema.', 1);


INSERT INTO Arquivo (data_upload, perfil_id, tipo_arquivo, nome_arquivo_original, nome_arquivo_salvo) VALUES
-- Perfil
('2023-11-03',1, 0, '1-murilo.jpg','2e8470ce-854e-40fe-b829-e52112e5176b_1-murilo.jpg'),
('2023-11-03',2, 0, '2-leo.jpg','0ca615d2-af0b-4777-ae4b-c7fd1f4cde53_2-leo.jpg'),
('2023-11-03',3, 0, '3-ana.jpg','e9f2dca3-4b12-40f0-99cd-0bb70362b7b6_3-ana.jpg'),
('2023-11-03',4, 0, '4-lucas.jpg','e7b65a8b-a87e-42e1-9e80-6001b2b35f41_4-lucas.jpg'),
('2023-11-03',5, 0, '5-mariana.jpg','c0c1c90d-920c-4607-8f1b-a5a353d4a426_5-mariana.jpg'),
('2023-11-03',6, 0, '6-maria.jpg','a88c123c-8905-47b0-bc35-2034ee071123_6-maria.jpg'),
('2023-11-03',7, 0, '7-carlos.jpg','79981474-1a47-464e-b05a-d4b8a43402f4_7-carlos.jpg'),
('2023-11-03',8, 0, '8-luisa.jpg','44b45e2b-a862-46a0-ad2a-8484581b6071_8-luisa.jpg'),
('2023-11-03',9, 0, '9-rafael.jpg','df84190f-50a0-4f3d-8494-7979de2a4d4b_9-rafael.jpg'),
('2023-11-03',10, 0, '10-sofia.jpg','ed169e08-f74f-432e-9c19-763be3adad4b_10-sofia.jpg'),
-- Wallpaper
('2023-11-03',1, 1, '1-b-murilo.jpg','cab545fc-85d1-41e2-92d8-74f16119e5c4_1-b-murilo.jpg'),
('2023-11-03',2, 1, '2-b-leo.jpg','bdd49348-c0e0-48f9-947f-a8fc653a367b_2-b-leo.jpg'),
('2023-11-03',3, 1, '3-b-ana.jpg','e01d642b-5c0a-4056-ae5b-69bffc197820_3-b-ana.jpg'),
('2023-11-03',4, 1, '4-b-lucas.jpg','beb0d4d6-43d3-4a19-aa9f-0478af9db77d_4-b-lucas.jpg'),
('2023-11-03',5, 1, '5-b-mariana.jpg','ca925051-d140-4fe5-93e9-b6a82e01c540_5-b-mariana.jpg'),
('2023-11-03',6, 1, '6-b-maria.jpg','0761d11e-0094-4c6f-ab0c-6aa9193c8d3f_6-b-maria.jpg'),
('2023-11-03',7, 1, '7-b-carlos.jpg','7673e740-d8f6-4faa-8735-2418281166ad_7-b-carlos.jpg'),
('2023-11-03',8, 1, '8-b-luisa.jpg','54ff219a-af35-4e84-b808-a88699203cb6_8-b-luisa.jpg'),
('2023-11-03',9, 1, '9-b-rafael.jpg','f21bb411-828e-4b03-9108-21caee6e3894_9-b-rafael.jpg'),
('2023-11-03',10,1, '10-b-sofia.jpg','01769f9a-2f4f-4bf2-b32f-1b64e79f34e5_10-b-sofia.jpg');






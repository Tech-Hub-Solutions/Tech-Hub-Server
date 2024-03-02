--DROP TABLE VW_EMPRESAS_INTERESSADAS
--
--CREATE VIEW VW_EMPRESAS_INTERESSADAS AS
--SELECT DISTINCT
---- campo unico
--  ROW_NUMBER() OVER (ORDER BY U.ID) AS ID,
--  U.ID as EMPRESA_ID,
--  U.NOME EMPRESA_NOME,
--  RP.AVALIADOR_ID,
--  RP.AVALIADO_ID,
--  (SELECT AVG(A.QTD_ESTRELA)
--   FROM AVALIACAO A
--   JOIN PERFIL P ON A.AVALIADOR_ID = P.ID
--   WHERE P.ID = RP.AVALIADOR_ID
--  ) AS MEDIA_AVALIACAO
--FROM USUARIO U
--JOIN PERFIL P ON U.PERFIL_ID = P.ID
--JOIN REFERENCIA_PERFIL RP ON RP.AVALIADOR_ID = P.ID
--WHERE RP.IS_FAVORITO = TRUE
--ORDER BY MEDIA_AVALIACAO DESC;

-- Lista de flags
INSERT INTO flag (nome, area, categoria)
VALUES
-- Front-End
('HTML/CSS', 'Front-end', 'hard-skill'),
('JavaScript', 'Front-end', 'hard-skill'),
('React', 'Front-end', 'hard-skill'),
('Angular', 'Front-end', 'hard-skill'),
('Vue.js', 'Front-end', 'hard-skill'),
('SASS/SCSS', 'Front-end', 'hard-skill'),
-- Back-End -7
('Node.js', 'Back-end', 'hard-skill'),
('Express.js', 'Back-end', 'hard-skill'),
('Django', 'Back-end', 'hard-skill'),
('Ruby on Rails', 'Back-end', 'hard-skill'),
('ASP.NET', 'Back-end', 'hard-skill'),
('PHP', 'Back-end', 'hard-skill'),
-- Mobile -13
('Kotlin (Android)', 'Mobile', 'hard-skill'),
('Swift (iOS)', 'Mobile', 'hard-skill'),
('Xamarin', 'Mobile', 'hard-skill'),
('Java (Android)', 'Mobile', 'hard-skill'),
('Objective-C (iOS)', 'Mobile', 'hard-skill'),
('Flutter', 'Mobile', 'hard-skill'),
-- Banco de Dados -19
('MySQL', 'Banco de Dados', 'hard-skill'),
('PostgreSQL', 'Banco de Dados', 'hard-skill'),
('MongoDB', 'Banco de Dados', 'hard-skill'),
('SQLite', 'Banco de Dados', 'hard-skill'),
('Microsoft SQL Server', 'Banco de Dados', 'hard-skill'),
('Oracle Database', 'Banco de Dados', 'hard-skill'),
-- Testes -26
('Jest', 'Testes', 'hard-skill'),
('Selenium', 'Testes', 'hard-skill'),
('JUnit', 'Testes', 'hard-skill'),
('Pytest', 'Testes', 'hard-skill'),
('Cypress', 'Testes', 'hard-skill'),
('Mocha', 'Testes', 'hard-skill'),
-- Análise de Dados -33
('Python', 'Análise de Dados', 'hard-skill'),
('R', 'Análise de Dados', 'hard-skill'),
('SQL', 'Análise de Dados', 'hard-skill'),
('Jupyter Notebook', 'Análise de Dados', 'hard-skill'),
('Tableau', 'Análise de Dados', 'hard-skill'),
('Power BI', 'Análise de Dados', 'hard-skill'),
-- DevOps -40
('Docker', 'Devops', 'hard-skill'),
('Kubernetes', 'Devops', 'hard-skill'),
('Jenkins', 'Devops', 'hard-skill'),
('Git', 'Devops', 'hard-skill'),
('Ansible', 'Devops', 'hard-skill'),
('Terraform', 'Devops', 'hard-skill'),
-- Inteligência Artificial -47
('TensorFlow', 'Inteligência Artificial', 'hard-skill'),
('PyTorch', 'Inteligência Artificial', 'hard-skill'),
('Scikit-learn', 'Inteligência Artificial', 'hard-skill'),
('Keras', 'Inteligência Artificial', 'hard-skill'),
('Redes Neurais', 'Inteligência Artificial', 'hard-skill'),
('Linguagem Natural', 'Inteligência Artificial', 'hard-skill'),
('Visão Computacional', 'Inteligência Artificial', 'hard-skill'),
('Aprendizado de Máquina', 'Inteligência Artificial', 'hard-skill'),
('Deep Learning', 'Inteligência Artificial', 'hard-skill'),
-- Segurança -57
('Firewalls', 'Segurança', 'hard-skill'),
('Antivírus', 'Segurança', 'hard-skill'),
('Criptografia', 'Segurança', 'hard-skill'),
('2FA', 'Segurança', 'hard-skill'),
('IDS/IPS', 'Segurança', 'hard-skill'),
('Web Application Firewall', 'Segurança', 'hard-skill'),
('VPN', 'Segurança', 'hard-skill'),
('DLP', 'Segurança', 'hard-skill'),
-- Soft Skills -66
('Adaptabilidade', 'Soft-skills', 'soft-skill'),
('Aprendizado Contínuo', 'Soft-skills', 'soft-skill'),
('Altruísmo', 'Soft-skills', 'soft-skill'),
('Ambição', 'Soft-skills', 'soft-skill'),
('Atenção aos Detalhes', 'Soft-skills', 'soft-skill'),
('Autodisciplina', 'Soft-skills', 'soft-skill'),
('Cidadania Corporativa', 'Soft-skills', 'soft-skill'),
('Colaboração', 'Soft-skills', 'soft-skill'),
('Comunicação', 'Soft-skills', 'soft-skill'),
('Comprometimento', 'Soft-skills', 'soft-skill'),
('Confiança', 'Soft-skills', 'soft-skill'),
('Criatividade', 'Soft-skills', 'soft-skill'),
('Diversidade e Inclusão', 'Soft-skills', 'soft-skill'),
('Ética de Trabalho', 'Soft-skills', 'soft-skill'),
-- 80
('Empatia', 'Soft-skills', 'soft-skill'),
('Entusiasmo', 'Soft-skills', 'soft-skill'),
('Equilíbrio Trabalho-Vida', 'Soft-skills', 'soft-skill'),
('Foco em Resultados', 'Soft-skills', 'soft-skill'),
('Foco no Cliente', 'Soft-skills', 'soft-skill'),
('Gestão do Tempo', 'Soft-skills', 'soft-skill'),
('Habilidade Analítica', 'Soft-skills', 'soft-skill'),
('Habilidade de Negociação', 'Soft-skills', 'soft-skill'),
('Humildade', 'Soft-skills', 'soft-skill'),
('Inovação', 'Soft-skills', 'soft-skill'),
('Integridade', 'Soft-skills', 'soft-skill'),
('Justiça', 'Soft-skills', 'soft-skill'),
('Liderança', 'Soft-skills', 'soft-skill'),
('Liderança Servidora', 'Soft-skills', 'soft-skill'),
('Mente Aberta', 'Soft-skills', 'soft-skill'),
('Motivação Intrínseca', 'Soft-skills', 'soft-skill'),
('Otimismo', 'Soft-skills', 'soft-skill'),
('Organização', 'Soft-skills', 'soft-skill'),
('Paciência', 'Soft-skills', 'soft-skill'),
-- 100
('Pensamento Crítico', 'Soft-skills', 'soft-skill'),
('Resiliência', 'Soft-skills', 'soft-skill'),
('Resolução de Conflitos', 'Soft-skills', 'soft-skill'),
('Resolução Proativa de Problemas', 'Soft-skills', 'soft-skill'),
('Responsabilidade', 'Soft-skills', 'soft-skill'),
('Respeito', 'Soft-skills', 'soft-skill'),
('Sustentabilidade', 'Soft-skills', 'soft-skill');

-- Lista de perfis
INSERT INTO perfil (descricao, experiencia, sobre_mim, preco_medio, nome_github, link_github, link_linkedin) VALUES
('Empresa especializada em inovação, oferecendo soluções de análise de dados e aprendizado de máquina.', 'Com mais de 5 anos de experiência no desenvolvimento de soluções avançadas em análise de dados e aprendizado de máquina','Estamos em busca de profissionais apaixonados por tecnologia e inovação', null, 'nossogithubempresa', 'https://github.com/nossogithubempresa', 'https://www.linkedin.com/company/nossolinkedinempresa'),
('Desenvolvedor Front-End', 'Trabalhei em mais de 20 projetos de front-end. Sou um profissional sênior.', 'Dedicado a projetar e implementar interfaces de usuário atraentes e funcionais. Especializado em HTML, CSS, JavaScript e frameworks front-end.', 600.0, 'frontenddev123', 'https://github.com/frontenddev123', 'https://www.linkedin.com/in/frontenddev123'),
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
('Desenvolvedor Mobile', 'Criando aplicativos móveis de ponta', 'Desenvolvendo aplicativos móveis inovadores para Android e iOS usando Kotlin, Java e Swift. Apaixonado pelo mundo da mobilidade.', 650.0, 'mobiledev567', 'https://github.com/mobiledev567', 'https://www.linkedin.com/in/mobiledev567'),
('Empresa 2', 'Empresa especializada em inovação, oferecendo soluções de análise de dados e aprendizado de máquina.', 'Estamos em busca de profissionais apaixonados por tecnologia e inovação', null, 'nossogithubempresa2', 'https://github.com/nossogithubempresa2', 'https://www.linkedin.com/company/nossolinkedinempresa2'),
('Empresa 3', 'Empresa especializada em inovação, oferecendo soluções de análise de dados e aprendizado de máquina.', 'Estamos em busca de profissionais apaixonados por tecnologia e inovação', null, 'nossogithubempresa3', 'https://github.com/nossogithubempresa3', 'https://www.linkedin.com/company/nossolinkedinempresa3'),
('Empresa 4', 'Empresa especializada em inovação, oferecendo soluções de análise de dados e aprendizado de máquina.', 'Estamos em busca de profissionais apaixonados por tecnologia e inovação', null, 'nossogithubempresa4', 'https://github.com/nossogithubempresa4', 'https://www.linkedin.com/company/nossolinkedinempresa4'),
('Empresa 5', 'Empresa especializada em inovação, oferecendo soluções de análise de dados e aprendizado de máquina.', 'Estamos em busca de profissionais apaixonados por tecnologia e inovação', null, 'nossogithubempresa5', 'https://github.com/nossogithubempresa5', 'https://www.linkedin.com/company/nossolinkedinempresa5'),
('Empresa 6', 'Empresa especializada em inovação, oferecendo soluções de análise de dados e aprendizado de máquina.', 'Estamos em busca de profissionais apaixonados por tecnologia e inovação', null, 'nossogithubempresa6', 'https://github.com/nossogithubempresa6', 'https://www.linkedin.com/company/nossolinkedinempresa6');


-- Flags de cada perfil
INSERT INTO flag_usuario (perfil_id, flag_id) VALUES
        -- hard skills
       (2, 3),(2, 9),(2, 11),(2, 14),(2, 19),(2, 24),
       (3, 1),(3, 4),(3, 8),(3, 13),(3, 17),(3, 25),
       (4, 2),(4, 6),(4, 7),(4, 11),(4, 15),(4, 26),
       (5, 3),(5, 5),(5, 9),(5, 13),(5, 19),(5, 27),
       (6, 1),(6, 4),(6, 7),(6, 12),(6, 16),(6, 28),
       (7, 2),(7, 6),(7, 9),(7, 14),(7, 18),(7, 29),
       (8, 1),(8, 4),(8, 8),(8, 11),(8, 15),(8, 30),
       (9, 3),(9, 7),(9, 10),(9, 13),(9, 16),(9, 31),
       (10, 2),(10, 5),(10, 9),(10, 12),(10, 17),(10, 32),
       (11, 3),(11, 6),(11, 10),(11, 13),(11, 18),(11, 33),
       (12, 1),(12, 4),(12, 8),(12, 11),(12, 17),(12, 34),
       (13, 2),(13, 5),(13, 9),(13, 12),(13, 16),(13, 35),
       (14, 3),(14, 7),(14, 11),(14, 14),(14, 18),(14, 36),
       (15, 1),(15, 6),(15, 8),(15, 12),(15, 15),(15, 37),
       (16, 1),(16, 4),(16, 8),(16, 11),(16, 15),(16, 38),
       (17, 2),(17, 5),(17, 9),(17, 12),(17, 16),(17, 39),
       (18, 3),(18, 7),(18, 10),(18, 13),(18, 17),(18, 40),
       (19, 1),(19, 6),(19, 8),(19, 14),(19, 19),(19, 41),
       (20, 2),(20, 4),(20, 11),(20, 13),(20, 15),(20, 42),
       -- soft-skills
       (1, 67), (1, 68), (1, 69), (1, 70), (1, 71), (1, 72), (1, 73), (1, 74), (1, 75), (1, 76),
       (2, 67), (2, 68), (2, 69), (2, 70), (2, 71), (2, 72), (2, 73), (2, 74), (2, 75), (2, 76),
       (3, 67), (3, 68), (3, 69), (3, 70), (3, 71), (3, 72), (3, 73), (3, 74), (3, 75), (3, 76),
       (4, 68), (4, 72), (4, 74), (4, 75), (4, 78), (4, 80), (4, 84), (4, 86), (4, 88), (4, 90),
       (5, 67), (5, 69), (5, 71), (5, 73), (5, 76), (5, 80), (5, 84), (5, 86), (5, 88), (5, 90),
       (6, 67), (6, 69), (6, 71), (6, 74), (6, 75), (6, 76), (6, 80), (6, 82), (6, 85), (6, 89),
       (7, 68), (7, 71), (7, 75), (7, 78), (7, 80), (7, 84), (7, 85), (7, 88), (7, 89), (7, 93),
       (8, 69), (8, 71), (8, 74), (8, 76), (8, 78), (8, 80), (8, 84), (8, 88), (8, 89), (8, 95),
       (9, 67), (9, 69), (9, 71), (9, 75), (9, 76), (9, 80), (9, 84), (9, 88), (9, 89), (9, 96),
       (10, 68), (10, 71), (10, 75), (10, 78), (10, 80), (10, 84), (10, 85), (10, 88), (10, 89), (10, 97),
       (11, 67), (11, 69), (11, 71), (11, 74), (11, 76), (11, 80), (11, 84), (11, 88), (11, 89), (11, 98),
       (12, 68), (12, 71), (12, 75), (12, 78), (12, 80), (12, 84), (12, 85), (12, 88), (12, 89), (12, 99),
       (13, 67), (13, 69), (13, 71), (13, 74), (13, 76), (13, 80), (13, 84), (13, 88), (13, 89), (13, 99),
       (14, 68), (14, 71), (14, 75), (14, 78), (14, 80), (14, 84), (14, 85), (14, 88), (14, 89), (14, 99),
       (15, 67), (15, 69), (15, 71), (15, 74), (15, 76), (15, 80), (15, 84), (15, 88), (15, 89), (15, 99),
       (16, 68), (16, 71), (16, 75), (16, 78), (16, 80), (16, 84), (16, 85), (16, 88), (16, 89), (16, 99),
       (17, 67), (17, 69), (17, 71), (17, 74), (17, 76), (17, 80), (17, 84), (17, 88), (17, 89), (17, 99),
       (18, 68), (18, 71), (18, 75), (18, 78), (18, 80), (18, 84), (18, 85), (18, 88), (18, 89), (18, 99),
       (19, 67), (19, 69), (19, 71), (19, 74), (19, 76), (19, 80), (19, 84), (19, 88), (19, 89), (19, 99),
       (20, 68), (20, 71), (20, 75), (20, 78), (20, 80), (20, 84), (20, 85), (20, 88), (20, 89), (20, 99);

-- Lista de usuários
INSERT INTO usuario (perfil_id,nome, email, senha, numero_cadastro_pessoa, pais, funcao, is_ativo) VALUES
(1,'DataInnovate Solutions', 'datainnovate@hotmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '26828654000100', 'BR', 'EMPRESA', true),
(2,'Leonardo', 'leo@hotmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '89138182000175', 'BR', 'FREELANCER', true),
(3,'Ana', 'ana@gmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'FR', 'FREELANCER', true),
(4,'Lucas', 'lucas@yahoo.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'FR', 'FREELANCER', true),
(5,'Mariana', 'mariana@hotmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '55555555555555', 'FR', 'FREELANCER', true),
(6, 'Maria', 'maria@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'DE', 'FREELANCER', true),
(7, 'Carlos', 'carlos@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'DE', 'FREELANCER', true),
(8, 'Luisa', 'luisa@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '55555555555555', 'PT', 'FREELANCER', true),
(9, 'Rafael', 'rafael@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'PT', 'FREELANCER', true),
(10, 'Sofia', 'sofia@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'PT', 'FREELANCER', true),
(11, 'Gabriel', 'gabriel@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'AR', 'FREELANCER', true),
(12, 'Marta', 'marta@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'AR', 'FREELANCER', true),
(13, 'Pedro', 'pedro@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '55555555555555', 'AR', 'FREELANCER', true),
(14, 'Isabella', 'isabella@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'ES', 'FREELANCER', true),
(15, 'Mateus', 'lucas@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'ES', 'FREELANCER', true),
(16, 'Patricia', 'patricia@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '55555555555555', 'IN', 'FREELANCER', true),
(17, 'Fernando', 'fernando@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'IN', 'FREELANCER', true),
(18, 'Amanda', 'amanda@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'US', 'FREELANCER', true),
(19, 'Rodrigo', 'rodrigo@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '55555555555555', 'US', 'FREELANCER', true),
(20, 'Tatiana', 'tatiana@example.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'US', 'FREELANCER', true),
(21, 'Empresa2', 'empresa2@gmai.com','$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '26828654000100', 'US', 'EMPRESA', true),
(22, 'Empresa3', 'empresa3@gmai.com','$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '26828654000100', 'US', 'EMPRESA', true),
(23, 'Empresa4', 'empresa4@gmai.com','$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '26828654000100', 'US', 'EMPRESA', true),
(24, 'Empresa5', 'empresa5@gmai.com','$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '26828654000100', 'US', 'EMPRESA', true),
(25, 'Empresa6', 'empresa6@gmai.com','$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '26828654000100', 'US', 'EMPRESA', true),
(null, 'Admin', 'admin@admin', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'BR', 'ADMIN', true);

-- Referencias
INSERT INTO referencia_perfil (avaliador_id, avaliado_id, is_favorito,is_recomendado) VALUES
(1,2, true, true),
(1,3, true, true),
(1,4, true, true),
(1,5, true, true),
(1,6, true, true),
(1,7, true, true),
(1,8, true, true),
(1,9, true, true),
(1,10,true, true),

(21,2, true, true),
(21,3, true, true),
(21,4, true, true),
(21,5, true, true),
(21,6, true, true),
(21,7, true, true),
(21,8, true, true),
(21,9, true, true),
(21,10,true, true),

(22,3, true, true),
(22,5, true, true),
(22,6, true, true),
(22,7, true, true),
(22,8, true, true),
(22,9, true, true),
(22,10,true, true),

(23,2, true, true),
(23,3, true, true),
(23,4, true, true),
(23,5, true, true),
(23,6, true, true),
(23,8, true, true),
(23,9, true, true),
(23,10,true, true),

(24,2, true, true),
(24,3, true, true),
(24,4, true, true),
(24,6, true, true),
(24,8, true, true),
(24,10,true, true),

(25,2, true, true),
(25,3, true, true),
(25,5, true, true),
(25,7, true, true),
(25,9, true, true),
(25,10,true, true);

-- Avaliacoes
INSERT INTO avaliacao (perfil_id, avaliador_id, comentario, qtd_estrela) VALUES
(1, 2, 'Trabalhar com essa empresa foi incrível, o projeto fluiu muito bem!', 5),
(1, 3, 'A empresa apresentou ideias inovadoras, o que tornou o trabalho mais interessante.', 4),
(1, 5, 'A parceria com essa empresa foi excelente, recomendo para outros freelancers.', 5),
(1, 6, 'A comunicação e o entendimento durante o projeto foram difíceis, não recomendo.', 1),
(1, 5, 'Colaborar com essa empresa foi ótimo, são muito dedicados aos objetivos.', 5),
(1, 4, 'Infelizmente, houve atraso no pagamento, o que impactou minha satisfação.', 2),
(1, 5, 'A qualidade dos projetos entregues por essa empresa é surpreendente, 5 estrelas!', 5),
(1, 3, 'Tive uma ótima experiência trabalhando com essa empresa, recomendo a todos os freelancers!', 4),
(1, 4, 'Participar do projeto liderado por essa empresa foi fenomenal, amei a experiência!', 5),
(1, 2, 'A empresa não forneceu as informações necessárias a tempo, tornando o trabalho entediante.', 2),
(1, 3, 'O sistema de trabalho dessa empresa é prático e fácil de entender, nota 5!', 5),
(1, 4, 'Infelizmente, o projeto em que participei foi um desastre, péssima experiência.', 1),
(1, 6, 'A empresa valoriza e destaca o talento dos freelancers, adorei colaborar com eles.', 4),
(1, 5, 'A experiência de trabalhar com essa empresa foi incrível, estou ansioso para futuros projetos.', 5),
(1, 4, 'O processo de entrega da empresa foi rápido e confiável, merece 5 estrelas!', 5),
(1, 3, 'O suporte ao freelancer foi péssimo, demoraram para resolver as questões.', 1),
(1, 5, 'A qualidade dos projetos entregues por essa empresa é surpreendente, 5 estrelas!', 5),
(1, 2, 'Tive uma ótima experiência trabalhando com essa empresa, recomendo a todos os freelancers!', 4),
(1, 3, 'Participar do projeto liderado por essa empresa foi fenomenal, amei a experiência!', 5),
(1, 4, 'O processo de entrega da empresa foi rápido e confiável, merece 5 estrelas!', 5),
(1, 7, 'O suporte ao freelancer foi péssimo, demoraram para resolver as questões.', 1),
-- AVALIAR EMPRESA
(17, 1, 'O processo de entrega da empresa foi rápido e confiável, merece 5 estrelas!', 5),
(18, 1, 'O suporte ao freelancer foi péssimo, demoraram para resolver as questões.', 1),
(19, 1, 'A empresa valoriza e destaca o talento dos freelancers, adorei colaborar com eles.', 4),
(2, 21, 'A empresa apresentou ideias inovadoras, o que tornou o trabalho mais interessante.', 4),
(3, 21, 'Excelente empresa, recomendo para outros freelancers.', 5),
(4, 21, 'A comunicação e o entendimento durante o projeto foram difíceis, não recomendo.', 1),
(5, 22, 'Colaborar com essa empresa foi ótimo, são muito dedicados aos objetivos.', 5),
(6, 22, 'Infelizmente, houve atraso no pagamento, o que impactou minha satisfação.', 2),
(7, 22, 'A qualidade dos projetos entregues por essa empresa é surpreendente, 5 estrelas!', 5),
(8, 23, 'Tive uma ótima experiência trabalhando com essa empresa, recomendo a todos os freelancers!', 4),
(9, 23, 'Participar do projeto liderado por essa empresa foi fenomenal, amei a experiência!', 5),
(10, 23, 'O processo de entrega da empresa foi rápido e confiável, merece 5 estrelas!', 5),
(11, 24, 'O suporte ao freelancer foi péssimo, demoraram para resolver as questões.', 1),
(12, 24, 'A empresa valoriza e destaca o talento dos freelancers, adorei colaborar com eles.', 4),
(13, 24, 'A experiência de trabalhar com essa empresa foi incrível, estou ansioso para futuros projetos.', 5),
(14, 25, 'A qualidade dos projetos entregues por essa empresa é surpreendente, 5 estrelas!', 5),
(15, 25, 'Tive uma ótima experiência trabalhando com essa empresa, recomendo a todos os freelancers!', 4),
(16, 25, 'Participar do projeto liderado por essa empresa foi fenomenal, amei a experiência!', 5);


INSERT INTO arquivo (data_upload, perfil_id, tipo_arquivo, nome_arquivo_original, nome_arquivo_salvo) VALUES
-- Perfil
('2023-11-03',1, 'PERFIL', '1-datainnovate.jpg','9f819b22-4994-4817-8acc-70c771873876_1-datainnovate.png'),
('2023-11-03',2, 'PERFIL', '2-leo.jpg','0ca615d2-af0b-4777-ae4b-c7fd1f4cde53_2-leo.jpg'),
('2023-11-03',3, 'PERFIL', '3-ana.jpg','e9f2dca3-4b12-40f0-99cd-0bb70362b7b6_3-ana.jpg'),
('2023-11-03',4, 'PERFIL', '4-lucas.jpg','e7b65a8b-a87e-42e1-9e80-6001b2b35f41_4-lucas.jpg'),
('2023-11-03',5, 'PERFIL', '5-mariana.jpg','c0c1c90d-920c-4607-8f1b-a5a353d4a426_5-mariana.jpg'),
('2023-11-03',6, 'PERFIL', '6-maria.jpg','a88c123c-8905-47b0-bc35-2034ee071123_6-maria.jpg'),
('2023-11-03',7, 'PERFIL', '7-carlos.jpg','79981474-1a47-464e-b05a-d4b8a43402f4_7-carlos.jpg'),
('2023-11-03',8, 'PERFIL', '8-luisa.jpg','44b45e2b-a862-46a0-ad2a-8484581b6071_8-luisa.jpg'),
('2023-11-03',9, 'PERFIL', '9-rafael.jpg','df84190f-50a0-4f3d-8494-7979de2a4d4b_9-rafael.jpg'),
('2023-11-03',10, 'PERFIL', '10-sofia.jpg','ed169e08-f74f-432e-9c19-763be3adad4b_10-sofia.jpg'),
('2023-11-03',11, 'PERFIL', '11-gabriel.jpg','d184e259-4929-459b-a99c-c4619048eda1_11-gabriel.jpg'),
('2023-11-03',12, 'PERFIL', '12-marta.jpg','d39ac45f-f04f-4519-978e-1f22f88098f4_12-marta.jpg'),
('2023-11-03',13, 'PERFIL', '13-pedro.jpg','665f2c6f-cea9-4002-9adc-085aba323820_13-pedro.jpg'),
('2023-11-03',14, 'PERFIL', '14-isabela.jpg','ce149d4a-df23-4936-ae6d-401a375e87af_14-isabela.jpg'),
('2023-11-03',15, 'PERFIL', '15-mateus.jpg','827a8af9-5fb4-45de-865f-4916f58e2439_15-Mateus.jpg'),
('2023-11-03',16, 'PERFIL', '16-patricia.jpg','530af636-d576-45a0-bdad-35c9fa5aa472_16-patricia.jpg'),
('2023-11-03',17, 'PERFIL', '17-fernando.jpg','153b45cf-c629-49db-8395-f593b145866e_17-fernando.jpg'),
-- Wallpaper
('2023-11-03',1, 'WALLPAPER', '1-b-murilo.jpg','43f1aa06-9add-4f66-b932-552c4934bdda_1-b-datainnovate.jpg'),
('2023-11-03',2, 'WALLPAPER', '2-b-leo.jpg','bdd49348-c0e0-48f9-947f-a8fc653a367b_2-b-leo.jpg'),
('2023-11-03',3, 'WALLPAPER', '3-b-ana.jpg','e01d642b-5c0a-4056-ae5b-69bffc197820_3-b-ana.jpg'),
('2023-11-03',4, 'WALLPAPER', '4-b-lucas.jpg','beb0d4d6-43d3-4a19-aa9f-0478af9db77d_4-b-lucas.jpg'),
('2023-11-03',5, 'WALLPAPER', '5-b-mariana.jpg','ca925051-d140-4fe5-93e9-b6a82e01c540_5-b-mariana.jpg'),
('2023-11-03',6, 'WALLPAPER', '6-b-maria.jpg','0761d11e-0094-4c6f-ab0c-6aa9193c8d3f_6-b-maria.jpg'),
('2023-11-03',7, 'WALLPAPER', '7-b-carlos.jpg','7673e740-d8f6-4faa-8735-2418281166ad_7-b-carlos.jpg'),
('2023-11-03',8, 'WALLPAPER', '8-b-luisa.jpg','54ff219a-af35-4e84-b808-a88699203cb6_8-b-luisa.jpg'),
('2023-11-03',9, 'WALLPAPER', '9-b-rafael.jpg','f21bb411-828e-4b03-9108-21caee6e3894_9-b-rafael.jpg'),
('2023-11-03',10,'WALLPAPER', '10-b-sofia.jpg','01769f9a-2f4f-4bf2-b32f-1b64e79f34e5_10-b-sofia.jpg'),
('2023-11-03',11,'WALLPAPER', '11-b-gabriel.jpg','041ad24c-46ae-4dfd-a98a-c103ea47412c_11-b-gabriel.jpg'),
('2023-11-03',12,'WALLPAPER', '12-b-marta.jpg','ab6c26df-ac71-46c7-a33e-5bbc50b7f8e1_12-b-marta.jpg'),
('2023-11-03',13,'WALLPAPER', '13-b-pedro.jpg','1835c96b-37c5-4db4-9249-f9bdbf6456a3_13-b-pedro.jpg'),
('2023-11-03',14,'WALLPAPER', '14-b-isabela.jpg','9a2f3ff3-ba32-4b98-9595-89daa99c8643_14-b-isabela.jpg'),
('2023-11-03',15,'WALLPAPER', '15-b-mateus.jpg','08a4f5fd-17b1-4f82-9e8c-302e4cf8313d_15-b-mateus.jpg'),
('2023-11-03',16,'WALLPAPER', '16-b-patricia.jpg','8b8f244e-610a-4674-a916-3251d7bf6244_16-b-patricia.jpg'),
('2023-11-03',17,'WALLPAPER', '17-b-fernando.jpg','bce96dd1-ccad-4ec0-8718-60445ae18a6c_17-b-fernando.jpg');
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
('Terraform', 'devops', 'hard-skill');

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
INSERT INTO Flag_Usuario (perfil_id, flag_id) VALUES
(1, 1), (1, 5), (1, 7), (1, 10), (1, 12), (1, 16), (1, 18), (1, 23),
(2, 3), (2, 9), (2, 11), (2, 14), (2, 19), (2, 24),
(3, 1), (3, 4), (3, 8), (3, 13), (3, 17), (3, 25),
(4, 2), (4, 6), (4, 7), (4, 11), (4, 15), (4, 26),
(5, 3), (5, 5), (5, 9), (5, 13), (5, 19), (5, 27),
(6, 1), (6, 4), (6, 7), (6, 12), (6, 16), (6, 28),
(7, 2), (7, 6), (7, 9), (7, 14), (7, 18), (7, 29),
(8, 1), (8, 4), (8, 8), (8, 11), (8, 15), (8, 30),
(9, 3), (9, 7), (9, 10), (9, 13), (9, 16), (9, 31),
(10, 2), (10, 5), (10, 9), (10, 12), (10, 17), (10, 32),
(11, 3), (11, 6), (11, 10), (11, 13), (11, 18), (11, 33),
(12, 1), (12, 4), (12, 8), (12, 11), (12, 17), (12, 34),
(13, 2), (13, 5), (13, 9), (13, 12), (13, 16), (13, 35),
(14, 3), (14, 7), (14, 11), (14, 14), (14, 18), (14, 36),
(15, 1), (15, 6), (15, 8), (15, 12), (15, 15), (15, 37),
(16, 1), (16, 4), (16, 8), (16, 11), (16, 15), (16, 38),
(17, 2), (17, 5), (17, 9), (17, 12), (17, 16), (17, 39),
(18, 3), (18, 7), (18, 10), (18, 13), (18, 17), (18, 40),
(19, 1), (19, 6), (19, 8), (19, 14), (19, 19), (19, 41),
(20, 2), (20, 4), (20, 11), (20, 13), (20, 15), (20, 42);



-- Lista de usuários
INSERT INTO Usuario (perfil_id,nome, email, senha, numero_cadastro_pessoa, pais, funcao, is_ativo) VALUES
(1,'Murilo', 'murilo@hotmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '26828654000100', 'Brasil', 'FREELANCER', 1),
(2,'Leonardo', 'leo@hotmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '89138182000175', 'Brasil', 'FREELANCER', 1),
(3,'Ana', 'ana@gmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '12345678901234', 'Brasil', 'FREELANCER', 1),
(4,'Lucas', 'lucas@yahoo.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '98765432109876', 'Brasil', 'FREELANCER', 1),
(5,'Mariana', 'mariana@hotmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '55555555555555', 'Brasil', 'FREELANCER', 1),
(6, 'Maria', 'maria@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '12345678901234', 'Brazil', 'FREELANCER', 1),
(7, 'Carlos', 'carlos@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '98765432109876', 'Brazil', 'FREELANCER', 1),
(8, 'Luisa', 'luisa@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '55555555555555', 'Brazil', 'FREELANCER', 1),
(9, 'Rafael', 'rafael@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '12345678901234', 'Brazil', 'FREELANCER', 1),
(10, 'Sofia', 'sofia@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '98765432109876', 'Brazil', 'FREELANCER', 1),
(11, 'Gabriel', 'gabriel@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '12345678901234', 'Brazil', 'FREELANCER', 1),
(12, 'Marta', 'marta@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '98765432109876', 'Brazil', 'FREELANCER', 1),
(13, 'Pedro', 'pedro@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '55555555555555', 'Brazil', 'FREELANCER', 1),
(14, 'Isabella', 'isabella@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '12345678901234', 'Brazil', 'FREELANCER', 1),
(15, 'Lucas', 'lucas@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '98765432109876', 'Brazil', 'FREELANCER', 1),
(16, 'Patricia', 'patricia@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '55555555555555', 'Brazil', 'FREELANCER', 1),
(17, 'Fernando', 'fernando@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '12345678901234', 'Brazil', 'FREELANCER', 1),
(18, 'Amanda', 'amanda@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '98765432109876', 'Brazil', 'FREELANCER', 1),
(19, 'Rodrigo', 'rodrigo@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '55555555555555', 'Brazil', 'FREELANCER', 1),
(20, 'Tatiana', 'tatiana@example.com', '$2a$10$AqwUTV.rKnb6ryDe.VVfDOV8N9cCBwzcsAOAnRLkXAI4Nqhx/k7Du', '12345678901234', 'Brazil', 'FREELANCER', 1);


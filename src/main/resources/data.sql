INSERT INTO Perfil (sobre_mim, experiencia, descricao, preco_medio, nome_github, link_github, link_linkedin) VALUES
 ('SOBRE MIM', 'EXPERICENCIA', 'I am particularly interested in projects that involve data analysis and machine learning.', 450.0, 'mygithubusername', 'https://github.com/mygithubusername', 'https://www.linkedin.com/in/mylinkedinusername'),
 ('SOBRE MIM', 'EXPERICENCIA','I am open to collaborating with dynamic teams and addressing unique challenges.', 550.0, 'mygithubuser', 'https://github.com/mygithubuser', 'https://www.linkedin.com/in/mylinkedinuser');


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


INSERT INTO Flag_Usuario (flag_id, perfil_id) VALUES
(1,1),
(2,1),
(3,1),
(4,1),
(5,1),
(6,1),
(7,2),
(8,2),
(9,2),
(10,2),
(14,1),
(14,2);

INSERT INTO Usuario (perfil_id,nome, email, senha, numero_cadastro_pessoa, pais, funcao, is_ativo)
VALUES (1,'Murilo', 'murilo@hotmail.com', '$2a$10$feRbfBjRcoDMaXXkBuc06u3lA5ng3CMkPXsHapLKXtezQYh4UURDq', '26828654000100', 'Brasil', 'dev', 1);

INSERT INTO Usuario (perfil_id,nome, email, senha, numero_cadastro_pessoa, pais, funcao, is_ativo)
VALUES (2,'Leonardo', 'leo@hotmail.com', '$2a$10$ZMM5Jes4mEKhZoRbu4lafuk5BSL0OMwbtYvcfq2LaxXthfBfdxWDq', '89138182000175', 'Brasil', 'empresa', 1);

INSERT INTO Flag (nome,area,categoria) VALUES ('Java','Back-end','developer');
INSERT INTO Flag (nome,area,categoria) VALUES ('React JS','Front-end','developer');
INSERT INTO cliente( nome, rg, cpf, telefone, email, endereco) VALUES ('Daenerys Targaryen', '3.654.987-8', '123.456.789-89', '(67) 99999-8888', 'targaryen@gmail.com', 'Ilha pedra do Dragão, Casa Targaryen');
INSERT INTO cliente( nome, rg, cpf, telefone, email, endereco) VALUES ('Jon Snow', '3.654.987-8', '123.456.789-89', '(67) 99999-8888', 'joaodasneves@gmail.com', 'Castle Black');
INSERT INTO cliente( nome, rg, cpf, telefone, email, endereco) VALUES ('Jaime Lannister', '3.654.987-8', '123.456.789-89', '(67) 99999-8888', 'regicida@gmail.com', 'Rochedo Casterly - Casa Lanister');
INSERT INTO cliente( nome, rg, cpf, telefone, email, endereco) VALUES ('Daenerys Targaryen', '3.654.987-8', '123.456.789-89', '(67) 99999-8888', 'targaryen@gmail.com', 'Ilha pedra do Dragão, Casa Targaryen');

INSERT INTO raca (raca) VALUES ('Poodle');
INSERT INTO raca (raca) VALUES ('Pinscher');
INSERT INTO raca (raca) VALUES ('Labrador');
INSERT INTO raca (raca) VALUES ('Yorkshire');
INSERT INTO raca (raca) VALUES ('Shih Tzu');
INSERT INTO raca (raca) VALUES ('Maltês');
INSERT INTO raca (raca) VALUES ('Pug');
INSERT INTO raca (raca) VALUES ('Golden Retriever');
INSERT INTO raca (raca) VALUES ('Bulldog Francês');
INSERT INTO raca (raca) VALUES ('Lulu da Pomerânia');
INSERT INTO raca (raca) VALUES ('Rottweiler');
INSERT INTO raca (raca) VALUES ('Bulldog Inglês');
INSERT INTO raca (raca) VALUES ('Lhasa Apso');

INSERT INTO animal (idade, img, nome, observacao, peso, porte, sexo, proprietario_id, raca_id) VALUES (12, 'https://img.olx.com.br/thumbs256x256/52/528905018311816.jpg', 'Thor', 'Não dar carne', 23, 'Grande', 'Macho', 1, 3);
INSERT INTO animal (idade, img, nome, observacao, peso, porte, sexo, proprietario_id, raca_id) VALUES(12, 'https://img.olx.com.br/thumbs256x256/19/198903009289266.jpg', 'Brile', 'Não dar leite', 21, 'Medio', 'Femea', 2, 3);
INSERT INTO animal (idade, img, nome, observacao, peso, porte, sexo, proprietario_id, raca_id) VALUES(8, 'https://img.olx.com.br/thumbs256x256/71/712806117380029.jpg', 'Senador', 'Lobo do mato', 43, 'Gigante', 'Macho', 4, 3);
INSERT INTO animal (idade, img, nome, observacao, peso, porte, sexo, proprietario_id, raca_id) VALUES(12, 'https://img.olx.com.br/thumbs256x256/38/383922006080815.jpg', 'Deputado', 'Não dar ovo', 23, 'Pequeno', 'Macho', 3, 2);

INSERT INTO servico (descricao, valor ) VALUES ('Banho', '15.00');
INSERT INTO servico (descricao, valor ) VALUES ('Tosa', '30.00');
INSERT INTO servico (descricao, valor ) VALUES ('Tosa Higienica', '25.00');
INSERT INTO servico (descricao, valor ) VALUES ('Adestramento', '35.00');
INSERT INTO servico (descricao, valor ) VALUES ('Taxi Dog', '10.00');

INSERT INTO agenda (data_agendamento, forma_de_pagamento, hora_agendamento, status, animal_id) VALUES ('2019-05-05', 'DINHEIRO', '11:10:10', 'Pendente', '1');
INSERT INTO agenda (data_agendamento, forma_de_pagamento, hora_agendamento, status, animal_id) VALUES ('2019-05-25', 'CHEQUE', '10:30:10', 'Pronto', '2');
INSERT INTO agenda (data_agendamento, forma_de_pagamento, hora_agendamento, status, animal_id) VALUES ('2019-05-27', 'DINHEIRO', '9:45:10', 'Pronto', '3');
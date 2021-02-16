insert into Produto (id, nome, preco, descricao) values (1, 'Kindle', 499.0, 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into Produto (id, nome, preco, descricao) values (3, 'Câmera GoPro Hero 7', 1400.0, 'Desempenho 2x melhor.');
insert into Cliente (id, nome) values (1, 'Fernando Medeiros');
insert into Cliente (id, nome) values (2, 'Marcos Mariano');
insert into Pedido (id, cliente_id, dataPedido, total, status) values (1, 1, sysdate(), 100.0, 'AGUARDANDO');

insert into ItemPedido (id, pedido_id, produto_id, precoProduto, quantidade) values (1, 1, 1, 5.0, 2);

insert into Categoria (id, nome) values (1, 'Eletrônicos');

insert into pagamento (pedido_id, status, numero_cartao) values (2, 'PROCESSANDO', '123');
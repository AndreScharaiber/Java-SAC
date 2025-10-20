# Java-SAC

O projeto consiste em um Sistema de Gerenciamento de SAC, desenvolvido em Java, com o objetivo de simular o funcionamento de um atendimento ao cliente, gerenciando tanto as solicitações já realizadas (histórico) quanto os clientes que ainda aguardam atendimento (fila).

O foco principal do código é demonstrar, de forma prática, o uso das estruturas de dados Pilha e Fila, ambas implementadas manualmente por meio de listas encadeadas, utilizando uma classe auxiliar chamada "No". Dessa forma, o projeto evita o uso de funções prontas.

O desenvolvimento foi totalmente feito pensando nas restrições apresentadas, o que traz um grau maior de dificuldade, por eliminar os vícios deixados pela matéria de P.O.O em relação a ArrayLists e outras bibliotecas do java.util.

A parte da Pilha, representando o Histórico de Solicitações, é implementada na classe HistoricoSolicitacoes, que contém métodos para adicionar novos registros e remover o mais recente. Já a Fila, que simboliza a Fila de Atendimento, é implementada na classe FilaAtendimento, com métodos para inserir clientes no final da fila e remover aqueles que estão na frente, simulando a ordem natural de atendimento.

O programa principal oferece um menu interativo com seis opções de operação, além da opção de encerrar o sistema (opção 9). Esse menu permite que o usuário teste livremente as funcionalidades, visualizando na prática o comportamento das duas estruturas de dados em um contexto simples e funcional.

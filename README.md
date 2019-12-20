# Sistema Gerenciador de Atividades Extra Curriculares

Este projeto foi desenvolvido na linguagem de programação Java, onde possui o objetivo de auxiliar Secretários Acadêmicos de Universidades que desempenham a função de analisar, armazenar e fornecer feedback sobre atividades Extra Curriculares dos alunos. As principais funcionalidades desse sistema são:
 
#### - Armazenar informações sobre as atividades Extra Curriculares dos Alunos;
#### - Receber certificados via email;
#### - Enviar relatórios sobre as atividades Extra Curriculares dos Alunos via email;
#### - Gerar Relatórios Gerais sobre as atividades do aluno ou sobre determinadas Categorias.

## Pré Requisitos

#### - Instale o XAMPP que é um pacote com os principais servidores de código aberto, utilizaremos o serviço de banco de dados MySQL;
#### - Crie um email fictício e altere as permições necessárias para que o sistema consiga receber e enviar emails através dele.


## Padrões de projeto utilizados

#### - MVC
#### - DAO
#### - Singleton
#### - Template Method
#### - Observer


## Representação da Tela Principal do sistema

![alt text](https://github.com/WillianaLeite/gerenciarAtividadesComplementares/blob/master/Image_TelaInicial.PNG)




Link para Tutorial no youtube: - https://youtu.be/eaQqGqoPWO0





## Observações
Para a utilização deste código você deve alterar os caminhos de cada imagem pois algumas estão com caminhos absolutos, apesar de ser uma prática ruim, por algum motivo a IDE que utilizei o Netebeans não reconhece caminhos relativos. A maior parte das imagens estão localizadas na Tela Principal, e na geração de relatórios onde insiro a logo da Universidade. A modificação dos caminhos da Tela Principal deve ser feita nas telas de configuração do swing, ao clicar com botão direito em cima do componente e selicionar a opção configuração, em seguida selecione a opção ícone e altere para o seu caminho. A alteração do caminho da imagem dos relatórios deve ser feita via código, nas funções gerar Relatório. Feito isso, o sistema deve funcionar normalmente!  

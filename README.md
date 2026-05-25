# Jogo de Digitação

Projeto desenvolvido em Java com interface gráfica Swing, utilizando o padrão MVC.

## Objetivo

O objetivo do jogo é ajudar o usuário a praticar digitação.  
Uma frase é exibida na tela e o jogador deve digitá-la exatamente igual, respeitando letras maiúsculas, minúsculas, espaços, pontuação e acentuação.

A cada confirmação, o programa informa se a frase digitada está correta ou errada e passa para a próxima frase.

## Tecnologias utilizadas

- Java
- Java Swing
- MVC
- Manipulação de arquivos
- ArrayList com Generics
- Tratamento de exceções
- Git/GitHub

## Estrutura do projeto
        │
        ├── JogoDigitacao
        │   └── JogoDigitacao.java
        │
        ├── controller
        │   └── Controller.java
        │
        ├── model
        │   ├── Frase.java
        │   ├── ArquivoFrases.java
        │   └── ArquivoScore.java
        │
        └── view
            └── TelaDigitacao.java
## Model

Responsável pelas classes de dados e arquivos.

Frase.java: representa uma frase do desafio e possui o método de comparação.

ArquivoFrases.java: lê o arquivo frases.txt e armazena as frases em um ArrayList<Frase>.

ArquivoScore.java: lê e salva o maior score no arquivo score.txt.
## View

Responsável pela interface gráfica.

TelaDigitacao.java: tela do jogo, contendo o texto da frase, o campo de digitação e o botão confirmar.

## Controller
Responsável pela regra do jogo.

Controller.java: controla os eventos, compara frases, atualiza o score, passa para a próxima frase e finaliza o jogo.

## Funcionamento do jogo
    O programa lê as frases do arquivo frases.txt.
    
    Cada frase é armazenada em um ArrayList<Frase>.
    
    A primeira frase é exibida na tela.
    
    O jogador digita a frase no campo de texto.
    
    Ao clicar em Confirmar, a frase digitada é comparada com a frase exibida.
    
    O programa mostra uma mensagem informando se a frase está correta ou errada.
    
    Independentemente do resultado, o jogo passa para a próxima frase.
    
    Ao final das frases, o programa mostra:
      - score do jogador;
      
      - maior score já registrado.
    
    Caso o score atual seja maior que o score máximo, o arquivo score.txt é atualizado.

## Funcionalidades implementadas
    Leitura de frases a partir de arquivo .txt
    
    Armazenamento das frases em ArrayList<Frase>
    
    Comparação exata da frase digitada
    
    Uso de Generics
    
    Tratamento de exceções
    
    Interface gráfica com Java Swing
    
    Organização em MVC
    
    Score do jogador
    
    Salvamento do score máximo em arquivo
    
    Botão Confirmar
    
    Tecla Enter para confirmar
    
    Bloqueio das teclas Backspace e Delete
    
    Mudança de cor do texto ao passar o mouse sobre o botão:
    
      - verde quando a frase está correta;
    
      - vermelho quando a frase está errada.

# Convenções do projeto

Regras curtas para manter o código simples e fácil de explicar. Em caso de dúvida, vale a regra mais simples.

## 1. Responsabilidade única

Cada classe tem um motivo para mudar, e cada método faz uma coisa.

Teste rápido: se for preciso usar "e" para descrever o que a classe ou o método faz, provavelmente ele faz coisa demais.

## 2. Camadas e o que cada uma faz

| Camada | Faz | Não faz |
|---|---|---|
| Controller | Recebe a requisição, chama o service, devolve a resposta | Regra de negócio, acesso a banco, chamada à IA |
| Service | Regra de negócio e coordenação | Detalhes de HTTP, SQL ou do formato do provedor de IA |
| Repository | Leitura e gravação no banco | Regra de negócio |
| Integração (cliente de IA) | Conversa com o provedor de IA, atrás de uma interface | Decidir o que fazer com o resultado |
| Dto | Formato dos dados que entram e saem da API | Lógica |
| Entidade/Modelo | Representa o dado do domínio | Chamar outras camadas |

Fluxo permitido: Controller -> Service -> Repository ou Integração. Nunca no sentido contrário, e o Controller nunca fala direto com Repository ou Integração.

## 3. Pacotes

Criar cada pacote só quando houver uma classe para colocar nele. Estrutura prevista dentro de `br.com.andre.triagem_ia`:

- `controller`
- `service`
- `repository`
- `integracao`
- `dto`
- `dominio`
- `excecao`

## 4. Métodos

- Poucas linhas, um nível de abstração por método.
- Poucos parâmetros. Se passar de três, agrupar em um objeto.
- Nome com verbo que diz o que faz: `classificarTexto`, e não `processar`.
- Evitar flags booleanas como parâmetro. Dois métodos claros são melhores que um com `if` interno.

## 5. Nomes

- Classes em substantivo, métodos em verbo.
- Nomes que dispensam comentário.
- Sem abreviações obscuras.
- Constantes em maiúsculas com sublinhado.

## 6. Comentários

- O padrão é não comentar. Se o código precisa de comentário para ser entendido, melhorar o nome ou dividir o método.
- Comentário só para explicar um **porquê** que o código não consegue mostrar, como uma restrição externa ou uma decisão não óbvia.
- Nunca comentar o que a linha faz, nem deixar código antigo comentado. O Git guarda o histórico.

## 7. Emojis e texto decorativo

Sem emojis em código, comentários, logs, mensagens de commit, README e mensagens de erro da API.

## 8. Segurança básica

- Chaves, senhas e URLs de banco só em variáveis de ambiente.
- Arquivos `.env` e similares no `.gitignore` antes do primeiro commit.
- Validar toda entrada que chega pela API.
- Não enviar dados reais de pessoas ao provedor de IA. Usar só textos fictícios.

## 9. Tratamento de erros

- Falhas esperadas (limite do provedor de IA, tempo esgotado, resposta fora do formato) viram exceções do próprio projeto, tratadas em um único lugar.
- Nunca engolir exceção com bloco vazio.
- A resposta de erro da API não expõe detalhes internos.

## 10. Testes

- Teste do service usa um cliente de IA simulado, para não depender da rede nem do limite gratuito.
- Cada teste verifica um comportamento.
- Nome do teste descreve o cenário.

## 11. Commits

- Minúsculas, sem acento, começando com verbo, em português.
- Um assunto por commit.
- Exemplo: `adiciona endpoint de consulta de triagens`.

## 12. Antes de cada commit

- O projeto compila e os testes passam.
- Não há segredo nem arquivo gerado (`target`, `.idea`) na lista de arquivos.
- Nenhum comentário ou código morto sobrando.

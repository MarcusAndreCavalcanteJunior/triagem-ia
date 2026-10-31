# triagem-ia

Serviço backend em Java que recebe um texto, usa um modelo de linguagem para classificar categoria e prioridade, valida a resposta, grava o resultado em banco de dados e permite consultas com filtros.

## Status

Em desenvolvimento. Esta seção é atualizada a cada etapa concluída.

## Objetivo

Construir, de ponta a ponta, um serviço que integra um modelo de linguagem a um backend, com atenção a arquitetura em camadas, tratamento de falhas, testes e segurança básica.

## Tecnologias

| Item | Escolha | Situação |
|---|---|---|
| Linguagem | Java 17 | em uso |
| Framework | Spring Boot | em uso |
| Build | Maven | em uso |
| Banco de dados | MySQL (Aiven, plano gratuito) | a configurar |
| Migrations | Flyway | a configurar |
| Provedor de IA | Gemini (plano gratuito), atrás de uma interface | a configurar |
| Testes | JUnit, com cliente de IA simulado | a configurar |
| Container | Docker | a configurar |
| Pipeline | a definir | a configurar |

## Arquitetura

A preencher quando as camadas existirem. Regras de organização em [CONVENCOES.md](CONVENCOES.md).

## Como executar

A preencher.

## Variáveis de ambiente

A preencher com os nomes das variáveis. Valores e chaves nunca entram no repositório.

## Endpoints

A preencher.

## Decisões de projeto

Registrar aqui, conforme surgirem, o que foi decidido e por quê. Exemplo de formato:

- Decisão:
- Motivo:
- Alternativa descartada:

## Limitações conhecidas

- O plano gratuito do banco no Aiven pode desligar a instância por inatividade, e a primeira chamada depois disso pode falhar.
- O plano gratuito do provedor de IA tem limite de requisições por minuto e por dia, e o conteúdo enviado pode ser usado pelo provedor para melhorar seus produtos. Por isso o projeto só deve processar textos fictícios.

## Testes

A preencher.

## Autor

André

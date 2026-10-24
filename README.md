# MOTIVA — Sprint 4

API REST do monitoramento de vegetação nas rodovias. O protótipo de console da Sprint 3 passou a ser consumido por HTTP, com Spring Boot, Spring Data JPA e as tabelas que já existem no Oracle.

## Como executar

É preciso ter Java 17 e Maven instalados.

A senha não fica no código. Defina as credenciais do Oracle FIAP antes de subir a API:

```powershell
$env:ORACLE_USERNAME="RM563253"
$env:ORACLE_PASSWORD="SUA_SENHA"
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

O `application.properties` aponta para `oracle.fiap.com.br:1521:ORCL` e usa `spring.jpa.hibernate.ddl-auto=none`. O Hibernate não cria nem altera tabela. O script `schema.sql` cria, no usuário `RM563253`, as tabelas `TRECHO_RODOVIA`, `EQUIPE_MANUTENCAO`, `INTERVENCAO_OPERACIONAL`, `RELATORIO_PRIORIDADE` e `ITEM_RELATORIO`, com as sequences `SEQ_TRECHO_RODOVIA`, `SEQ_EQUIPE_MANUTENCAO`, `SEQ_INTERVENCAO_OPERACIONAL`, `SEQ_RELATORIO_PRIORIDADE` e `SEQ_ITEM_RELATORIO`.

## Endpoints

| Método | Rota | Ação | Sucesso |
|---|---|---|---|
| GET | `/api/trechos` | Lista os trechos. `?tipo=UMIDO` ou `?tipo=SECO` filtra pelo derived query | 200 |
| GET | `/api/trechos/{id}` | Busca um trecho | 200 ou 404 |
| POST | `/api/trechos` | Cria um trecho | 201 |
| PUT | `/api/trechos/{id}` | Atualiza um trecho | 200 ou 404 |
| DELETE | `/api/trechos/{id}` | Remove um trecho | 204 ou 404 |
| GET | `/api/equipes` | Lista as equipes. `?tipo=MANUAL` ou `?tipo=MECANIZADA` filtra | 200 |
| GET | `/api/equipes/{id}` | Busca uma equipe | 200 ou 404 |
| POST | `/api/equipes` | Cria uma equipe | 201 |
| PUT | `/api/equipes/{id}` | Atualiza uma equipe | 200 ou 404 |
| DELETE | `/api/equipes/{id}` | Remove uma equipe | 204 ou 404 |
| GET | `/api/intervencoes` | Lista as intervenções. `?tipo=PULVERIZACAO` ou `?tipo=ROCADA_MECANIZADA` filtra | 200 |
| GET | `/api/intervencoes/{id}` | Busca uma intervenção | 200 ou 404 |
| POST | `/api/intervencoes` | Cria uma intervenção | 201 |
| PUT | `/api/intervencoes/{id}` | Atualiza uma intervenção | 200 ou 404 |
| DELETE | `/api/intervencoes/{id}` | Remove uma intervenção | 204 ou 404 |
| POST | `/api/relatorios` | Gera o relatório dos trechos atuais e grava o histórico | 201 |
| GET | `/api/relatorios` | Lista o histórico | 200 |
| GET | `/api/relatorios/periodo?inicio=...&fim=...` | Lista o histórico no período | 200 |

Datas do período no formato `2026-10-01T00:00:00`.

Validação recusada devolve **400**. Apagar um trecho ou uma intervenção que já está num relatório também devolve **400**, porque a chave estrangeira impede o delete.

`tipoCrescimento` é gravado igual ao `tipoTerreno`, como na Sprint 3. Os tipos aceitos são `UMIDO` e `SECO`. Equipe aceita `MANUAL` ou `MECANIZADA`. Intervenção aceita `PULVERIZACAO` ou `ROCADA_MECANIZADA`.

## Regras que ficam no service

A altura da vegetação não pode ser negativa (validação da Sprint 1). Dias sem manutenção também não.

O motor de prioridade da Sprint 2 classifica o trecho assim:

- roçada mecanizada: altura a partir de 40 cm e sem invasoras
- pulverização: há invasoras, ou a altura está entre 25 cm e 40 cm
- abaixo de 25 cm e sem invasoras: o trecho não entra no relatório

A prioridade (0 a 100) usa os mesmos pesos da Sprint 2: altura 50%, dias sem manutenção 30% e taxa de crescimento 20% (úmido 2,5 cm/dia, seco 0,8 cm/dia). Invasora soma 15, com teto de 100.

## Exemplos cURL

No PowerShell, use `curl.exe` (o comando `curl` desse terminal é outro programa).

```bash
curl.exe http://localhost:8080/api/trechos

curl.exe -X POST http://localhost:8080/api/trechos -H "Content-Type: application/json" -d "{\"km\":88.5,\"tipoTerreno\":\"SECO\",\"alturaVegetacaoCm\":20.0,\"equipadoIot\":true,\"possuiInvasoras\":false,\"diasSemManutencao\":10,\"nivelPrioridade\":0}"

curl.exe http://localhost:8080/api/trechos/1

curl.exe -X PUT http://localhost:8080/api/trechos/{id} -H "Content-Type: application/json" -d "{\"km\":88.5,\"tipoTerreno\":\"UMIDO\",\"alturaVegetacaoCm\":30.0,\"equipadoIot\":true,\"possuiInvasoras\":false,\"diasSemManutencao\":12,\"nivelPrioridade\":0}"

curl.exe -X DELETE http://localhost:8080/api/trechos/{id}

curl.exe -X POST http://localhost:8080/api/equipes -H "Content-Type: application/json" -d "{\"nome\":\"Equipe Norte\",\"tipoEquipe\":\"MANUAL\",\"disponivel\":true}"

curl.exe -X POST http://localhost:8080/api/intervencoes -H "Content-Type: application/json" -d "{\"tipoIntervencao\":\"ROCADA_MECANIZADA\",\"nomeServico\":\"Rocada Mecanizada\"}"

curl.exe -X POST http://localhost:8080/api/relatorios

curl.exe http://localhost:8080/api/relatorios

curl.exe "http://localhost:8080/api/relatorios/periodo?inicio=2020-01-01T00:00:00&fim=2030-01-01T00:00:00"
```

## Registro de requisições

As tabelas foram criadas no usuário `RM563253` com o `schema.sql`. Nesse schema, `GET /api/trechos`, `GET /api/equipes` e `GET /api/intervencoes` responderam **200**, e `POST /api/relatorios` respondeu **201** com as prioridades 84, 66 e 59.

As chamadas abaixo foram feitas antes, no schema em que a Sprint 3 já estava carregada. Os registros de teste foram apagados depois; os trechos originais (KM 10,5, 25, 42,7 e 60,5) permaneceram.

1. `POST /api/trechos` com KM 88,5 e altura 20 → **201**, corpo com `id`, `tipoTerreno` SECO e `tipoCrescimento` SECO.
2. `PUT /api/trechos/{id}` mudando o terreno para UMIDO e a altura para 30 → **200**.
3. `GET /api/trechos?tipo=UMIDO` → **200**, só trechos úmidos.
4. `POST /api/trechos` com altura -1 → **400** `{"status":400,"mensagem":"O nível de vegetação não pode ser negativo."}`.
5. `GET /api/trechos/99999` → **404** `{"status":404,"mensagem":"Trecho não encontrado."}`.
6. `POST /api/trechos` repetindo o KM 88,5 → **400** `{"status":400,"mensagem":"Já existe trecho cadastrado no KM 88.5"}`.
7. `POST /api/equipes` → **201**. `PUT /api/equipes/{id}` → **200**.
8. `POST /api/intervencoes` → **201**. `GET /api/intervencoes/{id}` → **200**.
9. `POST /api/relatorios` → **201**. O histórico gravou, em ordem de prioridade, KM 10,5 com 84 (roçada), KM 60,5 com 66 (pulverização) e KM 25 com 59 (pulverização). O KM 42,7 (12 cm, sem invasoras) ficou de fora.
10. `GET /api/relatorios` → **200**. `GET /api/relatorios/periodo?inicio=2020-01-01T00:00:00&fim=2030-01-01T00:00:00` → **200**, com o relatório novo e os anteriores.
11. `DELETE /api/trechos/1` → **400**, porque o trecho já está ligado a um item de relatório.
12. `DELETE` dos registros criados no teste → **204**.

## Reflexão

**Por que o Repository é uma interface e não uma classe? Quem escreve a implementação e quando?**

A interface só declara o que a aplicação precisa, por exemplo `findByTipoTerreno`. O Spring Data JPA, na subida da aplicação, gera uma classe que implementa essa interface. A gente não escreve o JDBC dessa classe: o framework monta a implementação quando o contexto sobe.

**O pattern DAO da Sprint 3 morreu na migração ou só mudou de forma?**

Ele mudou de forma. Na Sprint 3 cada DAO era uma classe com SQL, `PreparedStatement` e `ResultSet`. Agora o repository ocupa esse lugar: continua sendo a camada que fala com o banco, só que a implementação nasce do framework em vez de ser escrita à mão.

**Por que a validação de nivelVegetacao >= 0 deve ficar no Service e não no Controller?**

Essa regra é do domínio, não do HTTP. O service é quem cria e atualiza o trecho. Se a regra estiver só no controller, outro caminho que chame o service grava vegetação negativa. O controller só traduz a recusa para o status 400.

**No JDBC puro vocês escreviam SQL. Onde está o SQL do findByTipo()? Quem o gerou?**

Não há SQL desse método no projeto. O nome `findByTipoTerreno` descreve a consulta, e o Spring Data gera o SQL ao iniciar a aplicação, a partir do nome do método e do mapeamento da entidade.

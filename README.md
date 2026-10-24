# MOTIVA — Sprint 4

API REST do monitoramento de vegetação nas rodovias, com Spring Boot e Spring Data JPA.

## Equipe

| Integrante | RM |
|---|---|
| Caio Cordeiro Salgado | RM565400 |
| Hector van Tol Taver | RM562881 |
| Juan Gigliotti da Cunha | RM563253 |
| Rafael Alves da Silva | RM561878 |
| Raissa Fabrício Lima | RM563772 |

## Como executar

Java 17 e Maven. As credenciais do Oracle FIAP ficam em variáveis de ambiente, não no código.

```powershell
$env:ORACLE_USERNAME="SEU_RM"
$env:ORACLE_PASSWORD="SUA_SENHA"
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`. O banco é `oracle.fiap.com.br:1521:ORCL`. O Hibernate não cria nem altera tabelas (`ddl-auto=none`): usa as tabelas já existentes. Se o usuário ainda não tiver o schema, execute o `schema.sql`.

## Endpoints

| Método | Rota | Ação | Sucesso |
|---|---|---|---|
| GET | `/api/trechos` | Lista os trechos. `?tipo=UMIDO` ou `?tipo=SECO` filtra | 200 |
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

O período usa data e hora no formato `2026-10-01T00:00:00`. Dado inválido devolve **400**.

## Exemplos cURL

```bash
curl http://localhost:8080/api/trechos

curl -X POST http://localhost:8080/api/trechos -H "Content-Type: application/json" -d "{\"km\":88.5,\"tipoTerreno\":\"SECO\",\"alturaVegetacaoCm\":20.0,\"equipadoIot\":true,\"possuiInvasoras\":false,\"diasSemManutencao\":10,\"nivelPrioridade\":0}"

curl http://localhost:8080/api/trechos/1

curl -X PUT http://localhost:8080/api/trechos/1 -H "Content-Type: application/json" -d "{\"km\":10.5,\"tipoTerreno\":\"UMIDO\",\"alturaVegetacaoCm\":45.0,\"equipadoIot\":true,\"possuiInvasoras\":false,\"diasSemManutencao\":30,\"nivelPrioridade\":3}"

curl -X DELETE http://localhost:8080/api/trechos/1

curl -X POST http://localhost:8080/api/equipes -H "Content-Type: application/json" -d "{\"nome\":\"Equipe Norte\",\"tipoEquipe\":\"MANUAL\",\"disponivel\":true}"

curl -X POST http://localhost:8080/api/intervencoes -H "Content-Type: application/json" -d "{\"tipoIntervencao\":\"ROCADA_MECANIZADA\",\"nomeServico\":\"Rocada Mecanizada\"}"

curl -X POST http://localhost:8080/api/relatorios

curl http://localhost:8080/api/relatorios

curl "http://localhost:8080/api/relatorios/periodo?inicio=2020-01-01T00:00:00&fim=2030-01-01T00:00:00"
```

## Reflexão

**Por que o Repository é uma interface e não uma classe? Quem escreve a implementação e quando?**

A interface só declara o que a aplicação precisa, por exemplo `findByTipoTerreno`. O Spring Data JPA gera a classe que implementa essa interface quando a aplicação sobe. O SQL não é escrito no projeto.

**O pattern DAO da Sprint 3 morreu na migração ou só mudou de forma?**

Ele mudou de forma. Na Sprint 3 o DAO era uma classe com SQL, `PreparedStatement` e `ResultSet`. O repository continua sendo a camada que acessa o banco, só que a implementação é gerada pelo framework.

**Por que a validação de nivelVegetacao >= 0 deve ficar no Service e não no Controller?**

A regra pertence ao domínio. O service é quem cria e atualiza o trecho. Se a validação ficar só no controller, outro caminho que chame o service pode gravar vegetação negativa. O controller apenas devolve 400 quando o service recusa o dado.

**No JDBC puro vocês escreviam SQL. Onde está o SQL do findByTipo()? Quem o gerou?**

Não existe SQL desse método no código. O Spring Data gera a consulta a partir do nome `findByTipoTerreno` e do mapeamento da entidade, na inicialização da aplicação.

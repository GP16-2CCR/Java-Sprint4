# Registro de requisições

Registro de 10 chamadas reais feitas em 23/10/2026 na API local (`http://localhost:8080`).
Cobre o CRUD e o relatório pedidos na Sprint 4.
O trecho e a equipe criados aqui foram removidos no final. O relatório gerado ficou no histórico.

## 1. Listar trechos

```
GET /api/trechos
HTTP 200
```

```json
[
  {
    "alturaVegetacaoCm": 45.0,
    "diasSemManutencao": 30,
    "equipadoIot": true,
    "id": 1,
    "km": 10.5,
    "nivelPrioridade": 3,
    "possuiInvasoras": false,
    "tipoCrescimento": "UMIDO",
    "tipoTerreno": "UMIDO"
  },
  {
    "alturaVegetacaoCm": 28.0,
    "diasSemManutencao": 15,
    "equipadoIot": false,
    "id": 2,
    "km": 25.0,
    "nivelPrioridade": 2,
    "possuiInvasoras": true,
    "tipoCrescimento": "SECO",
    "tipoTerreno": "SECO"
  },
  {
    "alturaVegetacaoCm": 12.0,
    "diasSemManutencao": 5,
    "equipadoIot": true,
    "id": 3,
    "km": 42.7,
    "nivelPrioridade": 0,
    "possuiInvasoras": false,
    "tipoCrescimento": "SECO",
    "tipoTerreno": "SECO"
  },
  {
    "alturaVegetacaoCm": 35.0,
    "diasSemManutencao": 20,
    "equipadoIot": true,
    "id": 4,
    "km": 60.5,
    "nivelPrioridade": 0,
    "possuiInvasoras": false,
    "tipoCrescimento": "UMIDO",
    "tipoTerreno": "UMIDO"
  }
]
```

## 2. Criar trecho

```
POST /api/trechos
HTTP 201
```

```json
{
  "alturaVegetacaoCm": 10.0,
  "diasSemManutencao": 4,
  "equipadoIot": false,
  "id": 6,
  "km": 91.5,
  "nivelPrioridade": 0,
  "possuiInvasoras": false,
  "tipoCrescimento": "SECO",
  "tipoTerreno": "SECO"
}
```

## 3. Buscar trecho por id

```
GET /api/trechos/6
HTTP 200
```

```json
{
  "alturaVegetacaoCm": 10.0,
  "diasSemManutencao": 4,
  "equipadoIot": false,
  "id": 6,
  "km": 91.5,
  "nivelPrioridade": 0,
  "possuiInvasoras": false,
  "tipoCrescimento": "SECO",
  "tipoTerreno": "SECO"
}
```

## 4. Atualizar trecho

```
PUT /api/trechos/6
HTTP 200
```

```json
{
  "alturaVegetacaoCm": 18.0,
  "diasSemManutencao": 6,
  "equipadoIot": true,
  "id": 6,
  "km": 91.5,
  "nivelPrioridade": 0,
  "possuiInvasoras": false,
  "tipoCrescimento": "SECO",
  "tipoTerreno": "SECO"
}
```

## 5. Criar equipe

```
POST /api/equipes
HTTP 201
```

```json
{
  "disponivel": true,
  "id": 4,
  "nome": "Equipe Registro",
  "tipoEquipe": "MANUAL"
}
```

## 6. Gerar relatório

```
POST /api/relatorios
HTTP 201
```

```json
{
  "dataGeracao": "2026-10-23T21:47:31.9955007",
  "id": 3,
  "itens": [
    {
      "id": 7,
      "intervencao": {
        "id": 2,
        "nomeServico": "Roçada Mecanizada",
        "tipoIntervencao": "ROCADA_MECANIZADA"
      },
      "prioridade": 84,
      "trecho": {
        "alturaVegetacaoCm": 45.0,
        "diasSemManutencao": 30,
        "equipadoIot": true,
        "id": 1,
        "km": 10.5,
        "nivelPrioridade": 3,
        "possuiInvasoras": false,
        "tipoCrescimento": "UMIDO",
        "tipoTerreno": "UMIDO"
      }
    },
    {
      "id": 8,
      "intervencao": {
        "id": 1,
        "nomeServico": "Pulverização Química",
        "tipoIntervencao": "PULVERIZACAO"
      },
      "prioridade": 66,
      "trecho": {
        "alturaVegetacaoCm": 35.0,
        "diasSemManutencao": 20,
        "equipadoIot": true,
        "id": 4,
        "km": 60.5,
        "nivelPrioridade": 0,
        "possuiInvasoras": false,
        "tipoCrescimento": "UMIDO",
        "tipoTerreno": "UMIDO"
      }
    },
    {
      "id": 9,
      "intervencao": {
        "id": 1,
        "nomeServico": "Pulverização Química",
        "tipoIntervencao": "PULVERIZACAO"
      },
      "prioridade": 59,
      "trecho": {
        "alturaVegetacaoCm": 28.0,
        "diasSemManutencao": 15,
        "equipadoIot": false,
        "id": 2,
        "km": 25.0,
        "nivelPrioridade": 2,
        "possuiInvasoras": true,
        "tipoCrescimento": "SECO",
        "tipoTerreno": "SECO"
      }
    }
  ]
}
```

## 7. Listar histórico

```
GET /api/relatorios
HTTP 200
```

```json
[
  {
    "dataGeracao": "2026-10-23T21:47:31.995501",
    "id": 3,
    "itens": [
      {
        "id": 7,
        "intervencao": {
          "id": 2,
          "nomeServico": "Roçada Mecanizada",
          "tipoIntervencao": "ROCADA_MECANIZADA"
        },
        "prioridade": 84,
        "trecho": {
          "alturaVegetacaoCm": 45.0,
          "diasSemManutencao": 30,
          "equipadoIot": true,
          "id": 1,
          "km": 10.5,
          "nivelPrioridade": 3,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 8,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 66,
        "trecho": {
          "alturaVegetacaoCm": 35.0,
          "diasSemManutencao": 20,
          "equipadoIot": true,
          "id": 4,
          "km": 60.5,
          "nivelPrioridade": 0,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 9,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 59,
        "trecho": {
          "alturaVegetacaoCm": 28.0,
          "diasSemManutencao": 15,
          "equipadoIot": false,
          "id": 2,
          "km": 25.0,
          "nivelPrioridade": 2,
          "possuiInvasoras": true,
          "tipoCrescimento": "SECO",
          "tipoTerreno": "SECO"
        }
      }
    ]
  },
  {
    "dataGeracao": "2026-10-23T21:46:16.611373",
    "id": 2,
    "itens": [
      {
        "id": 4,
        "intervencao": {
          "id": 2,
          "nomeServico": "Roçada Mecanizada",
          "tipoIntervencao": "ROCADA_MECANIZADA"
        },
        "prioridade": 84,
        "trecho": {
          "alturaVegetacaoCm": 45.0,
          "diasSemManutencao": 30,
          "equipadoIot": true,
          "id": 1,
          "km": 10.5,
          "nivelPrioridade": 3,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 5,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 66,
        "trecho": {
          "alturaVegetacaoCm": 35.0,
          "diasSemManutencao": 20,
          "equipadoIot": true,
          "id": 4,
          "km": 60.5,
          "nivelPrioridade": 0,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 6,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 59,
        "trecho": {
          "alturaVegetacaoCm": 28.0,
          "diasSemManutencao": 15,
          "equipadoIot": false,
          "id": 2,
          "km": 25.0,
          "nivelPrioridade": 2,
          "possuiInvasoras": true,
          "tipoCrescimento": "SECO",
          "tipoTerreno": "SECO"
        }
      }
    ]
  },
  {
    "dataGeracao": "2026-10-23T21:39:54.751633",
    "id": 1,
    "itens": [
      {
        "id": 1,
        "intervencao": {
          "id": 2,
          "nomeServico": "Roçada Mecanizada",
          "tipoIntervencao": "ROCADA_MECANIZADA"
        },
        "prioridade": 84,
        "trecho": {
          "alturaVegetacaoCm": 45.0,
          "diasSemManutencao": 30,
          "equipadoIot": true,
          "id": 1,
          "km": 10.5,
          "nivelPrioridade": 3,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 2,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 66,
        "trecho": {
          "alturaVegetacaoCm": 35.0,
          "diasSemManutencao": 20,
          "equipadoIot": true,
          "id": 4,
          "km": 60.5,
          "nivelPrioridade": 0,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 3,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 59,
        "trecho": {
          "alturaVegetacaoCm": 28.0,
          "diasSemManutencao": 15,
          "equipadoIot": false,
          "id": 2,
          "km": 25.0,
          "nivelPrioridade": 2,
          "possuiInvasoras": true,
          "tipoCrescimento": "SECO",
          "tipoTerreno": "SECO"
        }
      }
    ]
  }
]
```

## 8. Consultar por período

```
GET /api/relatorios/periodo?inicio=2020-01-01T00:00:00&fim=2030-01-01T00:00:00
HTTP 200
```

```json
[
  {
    "dataGeracao": "2026-10-23T21:47:31.995501",
    "id": 3,
    "itens": [
      {
        "id": 7,
        "intervencao": {
          "id": 2,
          "nomeServico": "Roçada Mecanizada",
          "tipoIntervencao": "ROCADA_MECANIZADA"
        },
        "prioridade": 84,
        "trecho": {
          "alturaVegetacaoCm": 45.0,
          "diasSemManutencao": 30,
          "equipadoIot": true,
          "id": 1,
          "km": 10.5,
          "nivelPrioridade": 3,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 8,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 66,
        "trecho": {
          "alturaVegetacaoCm": 35.0,
          "diasSemManutencao": 20,
          "equipadoIot": true,
          "id": 4,
          "km": 60.5,
          "nivelPrioridade": 0,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 9,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 59,
        "trecho": {
          "alturaVegetacaoCm": 28.0,
          "diasSemManutencao": 15,
          "equipadoIot": false,
          "id": 2,
          "km": 25.0,
          "nivelPrioridade": 2,
          "possuiInvasoras": true,
          "tipoCrescimento": "SECO",
          "tipoTerreno": "SECO"
        }
      }
    ]
  },
  {
    "dataGeracao": "2026-10-23T21:46:16.611373",
    "id": 2,
    "itens": [
      {
        "id": 4,
        "intervencao": {
          "id": 2,
          "nomeServico": "Roçada Mecanizada",
          "tipoIntervencao": "ROCADA_MECANIZADA"
        },
        "prioridade": 84,
        "trecho": {
          "alturaVegetacaoCm": 45.0,
          "diasSemManutencao": 30,
          "equipadoIot": true,
          "id": 1,
          "km": 10.5,
          "nivelPrioridade": 3,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 5,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 66,
        "trecho": {
          "alturaVegetacaoCm": 35.0,
          "diasSemManutencao": 20,
          "equipadoIot": true,
          "id": 4,
          "km": 60.5,
          "nivelPrioridade": 0,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 6,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 59,
        "trecho": {
          "alturaVegetacaoCm": 28.0,
          "diasSemManutencao": 15,
          "equipadoIot": false,
          "id": 2,
          "km": 25.0,
          "nivelPrioridade": 2,
          "possuiInvasoras": true,
          "tipoCrescimento": "SECO",
          "tipoTerreno": "SECO"
        }
      }
    ]
  },
  {
    "dataGeracao": "2026-10-23T21:39:54.751633",
    "id": 1,
    "itens": [
      {
        "id": 1,
        "intervencao": {
          "id": 2,
          "nomeServico": "Roçada Mecanizada",
          "tipoIntervencao": "ROCADA_MECANIZADA"
        },
        "prioridade": 84,
        "trecho": {
          "alturaVegetacaoCm": 45.0,
          "diasSemManutencao": 30,
          "equipadoIot": true,
          "id": 1,
          "km": 10.5,
          "nivelPrioridade": 3,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 2,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 66,
        "trecho": {
          "alturaVegetacaoCm": 35.0,
          "diasSemManutencao": 20,
          "equipadoIot": true,
          "id": 4,
          "km": 60.5,
          "nivelPrioridade": 0,
          "possuiInvasoras": false,
          "tipoCrescimento": "UMIDO",
          "tipoTerreno": "UMIDO"
        }
      },
      {
        "id": 3,
        "intervencao": {
          "id": 1,
          "nomeServico": "Pulverização Química",
          "tipoIntervencao": "PULVERIZACAO"
        },
        "prioridade": 59,
        "trecho": {
          "alturaVegetacaoCm": 28.0,
          "diasSemManutencao": 15,
          "equipadoIot": false,
          "id": 2,
          "km": 25.0,
          "nivelPrioridade": 2,
          "possuiInvasoras": true,
          "tipoCrescimento": "SECO",
          "tipoTerreno": "SECO"
        }
      }
    ]
  }
]
```

## 9. Remover trecho de teste

```
DELETE /api/trechos/6
HTTP 204
```

Resposta sem corpo.

## 10. Remover equipe de teste

```
DELETE /api/equipes/4
HTTP 204
```

Resposta sem corpo.

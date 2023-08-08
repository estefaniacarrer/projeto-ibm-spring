# Sistema de Reservas/ Casa de Temporada

Projeto realizado para gerenciar reservas em uma casa de temporada através de uma API REST.
 
Podemos realizar os seguintes comandos:

✅ Cadastrar novas reservas;

✅ Listar reservas por Id;

✅️ Listar todas as reservas existentes;

✅️ Atualizar os dados de cada reserva.

✅ Cancelar reservas.


## Requisitos Técnicos

✅ Java 17 ou superior

✅ Spring Boot 3.1.2

✅ Persistência de dados utilizando H2 Database


## Endpoints

### Criar uma Reserva

Método: POST Endpoint: /reservas

Corpo da solicitação (JSON):

```
{
    "nomeHospede": "Alice da Silva",
    "dataInicio": "2023-08-15",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 4
}
```

Resposta (JSON):

```
{
    "id": 1,
    "nomeHospede": "Alice da Silva",
    "dataInicio": "2023-08-15",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
}
```

### Obter todas as Reservas

Método: GET Endpoint: /reservas

Resposta (JSON):

```
[
    {
        "id": 1,
        "nomeHospede": "Alice da Silva",
        "dataInicio": "2023-08-15",
        "dataFim": "2023-08-17",
        "quantidadePessoas": 4,
        "status": "CONFIRMADA"
    },
    {
        "id": 2,
        "nomeHospede": "José de Oliveira",
        "dataInicio": "2023-09-01",
        "dataFim": "2023-09-05",
        "quantidadePessoas": 2,
        "status": "PENDENTE"
    },
    ...
]
```

### Obter uma Reserva Específica por ID

Método: GET Endpoint: /reservas/{id}

Resposta (JSON):

```
{
    "id": 1,
    "nomeHospede": "Alice da Silva",
    "dataInicio": "2023-08-15",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
}
```

### Atualizar uma Reserva Existente

Método: PUT Endpoint: /reservas/{id}

Corpo da solicitação (JSON):

```
{
    "nomeHospede": "Alice da Silva",
    "dataInicio": "2023-08-15",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 4,
    "status": "PENDENTE"
}
```

Resposta (JSON):

```
{
    "id": 1,
    "nomeHospede": "Alice da Silva",
    "dataInicio": "2023-08-12",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
}
```

### Cancelar uma Reserva

Método: DELETE Endpoint: /reservas/{id}/cancelar

Resposta (JSON):

```
{
    "id": 1,
    "nomeHospede": "Alice da Silva",
    "dataInicio": "2023-08-12",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 4,
    "status": "CANCELADA"
}
```

## Como Executar

1. Clone este repositório para o seu ambiente local.
2. Importe o projeto em sua IDE preferida como um projeto Maven.
3. Execute o aplicativo Spring Boot.
4. Acesse os endpoints da API conforme especificado acima.

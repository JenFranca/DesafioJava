# Desafio Java
Este projeto é um desafio em Java e é uma das etapas de processo seletivo.

#### Parte 1
Este módulo é apresentado a partir da escolha das opções a seguir:
- Votos (opção 1)
- BubbleSort (opção 2)
- Fatorial (opção 3)
- SomaMultiplos (opção 4)
#### Parte 2
Este módulo consiste em uma API RESTful - Catálogo de Veículos.

# Pré requisitos
[Git](https://git-scm.com/downloads)

[Java 21.0.2](https://www.oracle.com/java/technologies/javase/21-0-2-relnotes.html)

[VSCode](https://code.visualstudio.com/)

[Extension pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

Clone o projeto no diretório desejado

```
git clone <url>
```

# Como executar

#### Parte 1
Abra o projeto no VSCode e com o arquivo ```Main.java``` aberto, aperte F5 para exibir o menu e escolha a opção desejada.

##### Parte 2
Abra o projeto no VSCode e com o arquivo ```CatalogoVeiculos.java``` aberto, aperte F5 para executar a API em http://localhost:8000.

# Endpoints da API - Catálogo de Veículos
1. Cadastrar veículo - POST

URL: http://localhost:8000<strong>/veiculos</strong>  
Exemplo de Body:

    {
      "marca": "Honda",
      "modelo": "Civic",
      "ano": 2010,
      "cor": "Prata",
      "vendido": false
    }

Exemplo de resposta:

    {
        "id":1,
        "marca":"Honda",
        "modelo":"Civic",
        "cor":"Prata",
        "ano":2010,
        "vendido":false,
        "criadoEm":"2025-08-20T23:24:11.063083600"
    }

2. Veículos não vendidos - GET

URL: http://localhost:8000<strong>/veiculos/nao-vendidos</strong>  
Exemplo de resposta:

    {
    "naoVendidos": 0
    }

3. Distribuição por década - GET

URL: http://localhost:8000<strong>/veiculos/por-decada</strong>  
Exemplo de resposta:

    {
    "2010s": 2,
    "1990s": 1
    }

4. Distribuição por marca - GET

URL: http://localhost:8000<strong>/veiculos/por-marca</strong>  
Exemplo de resposta:

    {
    "Honda":3
    }

5. Veículos cadastrados na última semana - GET

URL: http://localhost:8000<strong>/veiculos/recentes</strong>  
Exemplo de resposta:

    [
    {
        "id": 1,
        "marca": "Honda",
        "modelo": "Civic",
        "cor": "Prata",
        "ano": 1990,
        "vendido": true,
        "criadoEm": "2025-08-20T23:24:11.063083600"
    },
    {
        "id": 2,
        "marca": "Honda",
        "modelo": "Civic",
        "cor": "Prata",
        "ano": 2010,
        "vendido": true,
        "criadoEm": "2025-08-20T23:24:26.920443200"
    },
    {
        "id": 3,
        "marca": "Honda",
        "modelo": "Civic",
        "cor": "Prata",
        "ano": 2011,
        "vendido": false,
        "criadoEm": "2025-08-20T23:25:57.101495300"
    }
    ]

6. Filtrar veículos (marca, ano, cor) - GET

URL: http://localhost:8000<strong>/veiculos/filtro</strong>
Parâmetros aceitos:

| Chave      | Valor (exemplo) |
| ----------- | ----------- |
| marca      | "Honda"       |
| ano   | 2010        |
| cor   | "Preto"        |

Exemplo de resposta:

    [
    {
        "id": 2,
        "marca": "Honda",
        "modelo": "Civic",
        "cor": "Prata",
        "ano": 2010,
        "vendido": true,
        "criadoEm": "2025-08-20T23:24:26.920443200"
    }
    ]

7. Listar todos os veículos cadastrados - GET

URL: http://localhost:8000<strong>/veiculos</strong>  
Exemplo de resposta:

    [
    {
        "id": 1,
        "marca": "Honda",
        "modelo": "Civic",
        "cor": "Prata",
        "ano": 1990,
        "vendido": true,
        "criadoEm": "2025-08-20T23:24:11.063083600"
    },
    {
        "id": 2,
        "marca": "Honda",
        "modelo": "Civic",
        "cor": "Prata",
        "ano": 2010,
        "vendido": true,
        "criadoEm": "2025-08-20T23:24:26.920443200"
    },
    {
        "id": 3,
        "marca": "Honda",
        "modelo": "Civic",
        "cor": "Prata",
        "ano": 2011,
        "vendido": false,
        "criadoEm": "2025-08-20T23:25:57.101495300"
    }
    ]

8. Atualizar veículo - PUT

URL: http://localhost:8000<strong>/veiculos/*{id}*</strong>  
Exemplo de Body:

    {
      "marca": "Honda",
      "modelo": "Civic",
      "ano": 2010,
      "cor": "Prata",
      "vendido": false
    }

Exemplo de resposta:

    {
        "id":1,
        "marca":"Ford",
        "modelo":"Focus",
        "cor":"Preto",
        "ano":2012,
        "vendido":true,
        "criadoEm":"2025-08-20T23:24:11.063083600"
    }

9. Remover veículo - DELETE

URL: http://localhost:8000<strong>/veiculos/*{id}*</strong>  

Exemplo de resposta:

    {
    "mensagem": "Removido"
    }

10. Atualizar status de venda de veículo - PATCH

URL: http://localhost:8000<strong>/veiculos/*{id}*</strong>  

Exemplo de body:

    {
    "vendido": "true"
    }

Exemplo de resposta:

    {
    "id": 2,
    "marca": "Honda",
    "modelo": "Civic",
    "cor": "Prata",
    "ano": 2010,
    "vendido": true,
    "criadoEm": "2025-08-20T23:24:26.920443200"
    }
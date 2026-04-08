# Microservices E-commerce

Projeto de estudos de arquitetura de **microserviços** com Spring Boot, Spring Cloud Gateway, Eureka e Spring Data JPA.

---

## 🏗️ Arquitetura

```
Cliente (Postman / Browser / App)
              │
              ▼
   ┌─────────────────────┐
   │   Gateway  :8080    │  ← único ponto de entrada
   └──────────┬──────────┘
              │ consulta Eureka para resolver lb://
              ▼
   ┌─────────────────────┐
   │  Eureka Server      │  ← registro e descoberta de serviços
   │       :8761         │
   └─────────────────────┘
        ▲       ▲       ▲
        │       │       │  (cada serviço se registra aqui ao subir)
        │       │       │
┌───────┴──┐ ┌──┴──────┐ ┌┴────────────┐
│ products │ │ orders  │ │  payments   │
│  :8081   │ │  :8082  │ │   :8083     │
└──────────┘ └─────────┘ └─────────────┘
   H2 DB        H2 DB        H2 DB
```

---

## 📁 Estrutura do Projeto

```
microservices-ecommerce
├── eureka-server       → Servidor de descoberta de serviços
├── gateway             → API Gateway (porta 8080)
├── products-service    → Serviço de produtos (porta 8081)
├── orders-service      → Serviço de pedidos (porta 8082)
├── payments-service    → Serviço de pagamentos (porta 8083)
└── pom.xml             → POM raiz multi-módulo
```

---

## 🔩 Cada Serviço em Detalhe

### 1. `eureka-server` — Porta `8761`
**Responsabilidade:** Registro e descoberta de serviços.

- Anotação `@EnableEurekaServer` ativa o servidor
- `register-with-eureka: false` — ele **não se registra em si mesmo**
- `fetch-registry: false` — ele **não busca registros de outros**
- Dashboard acessível em `http://localhost:8761`
- Todos os outros serviços se conectam a ele ao iniciar

---

### 2. `gateway` — Porta `8080`
**Responsabilidade:** Porta de entrada única, roteamento de requisições.

- `@EnableDiscoveryClient` — se registra no Eureka
- Usa `lb://nome-do-servico` para rotear com **load balance**
- Regras de roteamento:

| Requisição do cliente | Encaminhado para |
|----------------------|-----------------|
| `/products/**` | `products-service` |
| `/orders/**` | `orders-service` |
| `/payments/**` | `payments-service` |

- `discovery.locator.enabled: true` — permite descoberta automática de novos serviços

---

### 3. `products-service` — Porta `8081`
**Responsabilidade:** CRUD de produtos do catálogo.

- Camadas: `ProductController` → `ProductService` → `ProductRepository` → H2
- Banco: `jdbc:h2:mem:productsdb`

| Método | Path | Ação |
|--------|------|------|
| GET | `/products` | Lista todos |
| GET | `/products/{id}` | Busca por ID |
| POST | `/products` | Cria |
| PUT | `/products/{id}` | Atualiza |
| DELETE | `/products/{id}` | Remove |

---

### 4. `orders-service` — Porta `8082`
**Responsabilidade:** Gerenciamento de pedidos.

- Camadas: `OrderController` → `OrderService` → `OrderRepository` → H2
- Banco: `jdbc:h2:mem:ordersdb`
- Status possíveis: `PENDING` → `CONFIRMED` ou `CANCELLED`

| Método | Path | Ação |
|--------|------|------|
| GET | `/orders` | Lista todos |
| GET | `/orders/{id}` | Busca por ID |
| POST | `/orders` | Cria |
| PATCH | `/orders/{id}/status` | Atualiza status |
| DELETE | `/orders/{id}` | Remove |

---

### 5. `payments-service` — Porta `8083`
**Responsabilidade:** Processamento de pagamentos vinculados a pedidos.

- Camadas: `PaymentController` → `PaymentService` → `PaymentRepository` → H2
- Banco: `jdbc:h2:mem:paymentsdb`
- Status possíveis: `PENDING` → `APPROVED` ou `REJECTED`

| Método | Path | Ação |
|--------|------|------|
| GET | `/payments` | Lista todos |
| GET | `/payments/{id}` | Busca por ID |
| GET | `/payments/order/{orderId}` | Por pedido |
| POST | `/payments` | Cria |
| PATCH | `/payments/{id}/status` | Atualiza status |

---

## 🗄️ Persistência — Spring Data JPA + H2

Cada serviço tem seu **banco isolado em memória**. O Spring Data JPA abstrai toda a camada de acesso a dados — os `Repository` extendem `JpaRepository<Entidade, Long>` e ganham automaticamente todos os métodos CRUD sem escrever SQL.

O console H2 de cada serviço fica disponível em `/h2-console` para inspecionar os dados em tempo real.

---

## 📦 Estrutura Maven — Multi-módulo

O `pom.xml` raiz define:
- **Parent:** `spring-boot-starter-parent 3.2.5`
- **BOM:** `spring-cloud-dependencies 2023.0.1`

Cada `pom.xml` filho herda as versões do pai — não é necessário declarar versão nas dependências Spring Boot/Cloud.

---

## 🚀 Ordem de Inicialização

```
1º  eureka-server   → os outros precisam dele para se registrar
2º  gateway         → precisa do Eureka para resolver lb://
3º  products-service
4º  orders-service
5º  payments-service
```

---

## 🔁 Exemplo de Fluxo Completo

```bash
# 1. Criar produto
POST http://localhost:8080/products
{ "name": "Notebook", "price": 3500.00, "stock": 10 }

# 2. Criar pedido
POST http://localhost:8080/orders
{ "productId": 1, "quantity": 2, "totalPrice": 7000.00 }

# 3. Registrar pagamento
POST http://localhost:8080/payments
{ "orderId": 1, "amount": 7000.00 }

# 4. Aprovar pagamento
PATCH http://localhost:8080/payments/1/status?status=APPROVED

# 5. Confirmar pedido
PATCH http://localhost:8080/orders/1/status?status=CONFIRMED
```

> 💡 **Todas as chamadas passam pela porta `8080`** (Gateway) — o cliente nunca acessa os serviços diretamente.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|-----------|--------|-----|
| Java | 17 | Linguagem |
| Spring Boot | 3.2.5 | Framework base |
| Spring Cloud Gateway | 2023.0.1 | API Gateway |
| Spring Cloud Netflix Eureka | 2023.0.1 | Service Discovery |
| Spring Data JPA | 3.2.5 | Persistência |
| H2 Database | - | Banco em memória |
| Maven | - | Build multi-módulo |

---

## 🗂️ Projeto

```
microservices-ecommerce
├── eureka-server
├── gateway
├── products-service
├── orders-service
└── payments-service
```
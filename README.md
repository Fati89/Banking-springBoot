# Digital Banking

## 1. Développement Backend
### Introduction
Cette partie présente l'état d'avancement de la phase backend du projet **Digital Banking**. La couche métier, la persistance des données, ainsi que les endpoints REST ont été entièrement implémentés et validés. Ce rapport sert de point de référence avant le lancement du développement frontend.

## 1.1.Technologies
Le backend repose sur une architecture modulaire en couches, favorisant la maintenabilité et la scalabilité.

- **Framework principal** : Spring Boot
- **Persistance** : Spring Data JPA / Hibernate
- **Pattern utilisé** : DTO, Repository, Service, REST
- **Base de données** : *(Préciser : H2, MySQL)*
- **Outils de test** : Postman / Swagger / *(autre)*


---

## 1.2. Architeture générale 
<img width="307" height="331" alt="1-architecture" src="https://github.com/user-attachments/assets/e9b183f5-22b6-4fc8-bdeb-47e8ddc86f8b" />


---

## 1.3. Modèle de Données (Entités JPA)
Le domaine métier a été modélisé à travers les entités suivantes, avec gestion des relations et de l'héritage stratégique pour les types de comptes.

- `CustomerService` : Informations du client
  <img width="531" height="254" alt="image" src="https://github.com/user-attachments/assets/a1a15bf7-54bb-467a-bc09-a15e0b26c81f" />

- `BankAccount` : Entité parente commune aux comptes
  <img width="533" height="375" alt="image" src="https://github.com/user-attachments/assets/7e593749-948b-4a76-8c27-39d546a83915" />

- `CurrentAccount` : Compte courant avec découvert autorisé
  <img width="412" height="154" alt="image" src="https://github.com/user-attachments/assets/6e8fad87-c844-4755-8e73-9d79e914fbd8" />

- `SavingAccount` : Compte épargne avec taux d'intérêt
  <img width="399" height="142" alt="image" src="https://github.com/user-attachments/assets/7f452bad-eee3-4e37-8bbc-c6334ab19292" />

- `AccountOperation` : Historique des mouvements (débit/crédit)
  <img width="489" height="304" alt="image" src="https://github.com/user-attachments/assets/4cf22377-7c7f-4bc3-9c82-dd52a1826993" />

---

## 1.4. Couche d'Accès aux Données (Repositories)
Les interfaces Spring Data JPA ont été créées pour encapsuler les opérations CRUD et les requêtes personnalisées.

- `CustomerRepository`
- `BankAccountRepository`
- `AccountOperationRepository`
  
  <img width="743" height="120" alt="image" src="https://github.com/user-attachments/assets/0860bae6-77d8-4a96-9c97-c9fe47b10ef4" />

  Les données dans la base de données H2
  <img width="691" height="511" alt="2-bdd" src="https://github.com/user-attachments/assets/cf4582ed-71f8-450e-ba6d-e2ea9d5fdd59" />



---

## 1.5. Couche DTO & Mapping
Afin de découpler la couche métier de la couche présentation et d'éviter l'exposition directe des entités JPA, un ensemble de DTOs a été mis en place :

- `CustomerDTO`
  <img width="218" height="180" alt="image" src="https://github.com/user-attachments/assets/0e7cbc67-e53b-45f1-923c-2713aa235242" />

- `BankAccountDTO`, `CurrentAccountDTO`, `SavingAccountDTO`
  <img width="290" height="212" alt="image" src="https://github.com/user-attachments/assets/e27fceac-824b-458b-a1ef-632026324730" />

  <img width="456" height="120" alt="image" src="https://github.com/user-attachments/assets/e5a64114-dc89-4250-a72d-45889fc157bf" />

  <img width="441" height="111" alt="image" src="https://github.com/user-attachments/assets/4adc3fe1-4ab8-4302-9d3a-e7b9004e1ca3" />

- `AccountOperationDTO`
  <img width="338" height="183" alt="image" src="https://github.com/user-attachments/assets/8b9ee41d-724e-496e-b0e5-031bbb9c0304" />

- `AccountHistoryDTO` (avec support de la pagination)
  <img width="490" height="226" alt="image" src="https://github.com/user-attachments/assets/d644e1a1-4cfe-419a-8e07-2fe7e10cb389" />

- `BankAccountMapper` (avec support de la pagination)
  <img width="744" height="295" alt="image" src="https://github.com/user-attachments/assets/77811af1-f162-43b6-a4bf-8308dc93c9a8" />


---

## 1.6. Couche Métier (Services)
Le cœur fonctionnel du projet est centralisé dans `BankAccountService`. Il orchestre la logique métier, la validation et la gestion des exceptions.

### 1.6.1. Gestion des Clients
- Création, lecture, mise à jour, suppression
- Récupération de la liste complète
 **Tests avec Postman**
  <img width="566" height="301" alt="3-get" src="https://github.com/user-attachments/assets/3a8d3cdd-b85c-425e-8a3f-f23623ab9e16" />

  <img width="564" height="548" alt="4-post" src="https://github.com/user-attachments/assets/eab7c3aa-2aa5-48a7-8915-db81e152b830" />

  **Test avec Swagger**
  <img width="1332" height="581" alt="5-swagger" src="https://github.com/user-attachments/assets/85eb561a-0ccc-4968-9694-351453b12879" />

  **Api-docs**
  <img width="694" height="687" alt="6-api-docs" src="https://github.com/user-attachments/assets/d0c2a229-0e08-4b08-8431-deee1cdf29c0" />




### 1.6.2. Gestion des Comptes
- Création de compte courant 
- Consultation des comptes par client ou globalement

### 1.6.3. Opérations Bancaires
- Crédit / Débit avec vérification du solde et du découvert
- Virement inter-comptes 
- Historique des opérations (liste simple ou paginée)

<img width="748" height="412" alt="image" src="https://github.com/user-attachments/assets/b0bd3d56-d8bf-40d2-8757-dbd5cce8215a" />


---

## 1.7. API REST (Contrôleurs)
L'exposition des fonctionnalités se fait via deux contrôleurs RESTful, suivant les conventions HTTP et retournant des réponses structurées.

### 1.7.1. `CustomerController`
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET`   | `/customers` | Liste tous les clients |
| `GET`   | `/customers/{id}` | Détails d'un client |
| `POST`  | `/customers` | Création d'un client |
| `PUT`   | `/customers/{id}` | Mise à jour d'un client |
| `DELETE`| `/customers/{id}` | Suppression d'un client |

<img width="750" height="471" alt="image" src="https://github.com/user-attachments/assets/4f047c74-1c06-4023-b713-c277da7e0fb1" />


### 1.7.2. `BankAccountRestAPI`
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET`   | `/accounts` | Liste tous les comptes |
| `GET`   | `/accounts/{accountId}` | Détails d'un compte |
| `GET`   | `/accounts/{accountId}/operations` | Historique complet |
| `GET`   | `/accounts/{accountId}/pageOperations` | Historique paginé |

<img width="759" height="455" alt="image" src="https://github.com/user-attachments/assets/86d18be4-7ec8-4639-af62-c222b4a7dc73" />


---

## 1.8. Fonctionnalités Validées
- [x] Création et gestion complète des clients
- [x] Création de comptes courant/épargne liés à un client
- [x] Opérations de crédit, débit et virement avec contrôles métier
- [x] Gestion des exceptions (`CustomerNotFoundException`, `BankAccountNotFoundException`, `BalanceNotSufficientException`)
- [x] Historique des opérations avec pagination
- [x] Architecture découplée (Entité ↔ DTO)

*[Insérer capture d'écran : Réponses JSON réussies ou tests unitaires]*

---


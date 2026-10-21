# AutoLoc API — Notes de l'atelier 5

## A. Tableau des endpoints

Les routes sont préfixées par `/api`, configuré dans l'application. Les codes d'erreur indiqués correspondent aux comportements attendus par l'atelier.

| Contrôleur | Méthode et route | Service appelé | Succès | Erreurs possibles |
|---|---|---|---|---|
| ContratController | GET `/api/contrats` | `findAll()` | 200 OK | — |
| ContratController | GET `/api/contrats/{id}` | `findById(id)` | 200 OK | 404 Not Found |
| ContratController | POST `/api/contrats` | `create(contrat)` | 201 Created | 400 Bad Request |
| ContratController | PUT `/api/contrats/{id}` | `update(id, contrat)` | 200 OK | 400, 404 |
| ContratController | DELETE `/api/contrats/{id}` | `deleteById(id)` | 204 No Content | 404 |
| PaiementController | GET `/api/paiements` | `findAll()` | 200 OK | — |
| PaiementController | GET `/api/paiements/{id}` | `findById(id)` | 200 OK | 404 Not Found |
| PaiementController | POST `/api/paiements` | Aucun : méthode non exposée | 405 Method Not Allowed | 405 |
| AgenceController | GET `/api/agences` | `findAll()` | 200 OK | — |
| AgenceController | GET `/api/agences/{id}` | `findById(id)` | 200 OK | 404 |
| AgenceController | POST `/api/agences` | `create(agence)` | 201 Created | 400 |
| AgenceController | PUT `/api/agences/{id}` | `update(id, agence)` | 200 OK | 400, 404 |
| AgenceController | DELETE `/api/agences/{id}` | `deleteById(id)` | 204 No Content | 404 |
| EmployeController | GET `/api/employes` | `findAll()` | 200 OK | — |
| EmployeController | GET `/api/employes/{id}` | `findById(id)` | 200 OK | 404 |
| EmployeController | POST `/api/employes` | `create(employe)` | 201 Created | 400 |
| EmployeController | PUT `/api/employes/{id}` | `update(id, employe)` | 200 OK | 400, 404 |
| EmployeController | DELETE `/api/employes/{id}` | `deleteById(id)` | 204 No Content | 404 |
| VehiculeController | GET `/api/vehicules` | `findAll()` | 200 OK | — |
| VehiculeController | GET `/api/vehicules/{id}` | `findById(id)` | 200 OK | 404 |
| VehiculeController | POST `/api/vehicules` | `create(vehicule)` | 201 Created | 400 |
| VehiculeController | PUT `/api/vehicules/{id}` | `update(id, vehicule)` | 200 OK | 400, 404 |
| VehiculeController | DELETE `/api/vehicules/{id}` | `deleteById(id)` | 204 No Content | 404 |
| ReservationController | GET `/api/reservations` | `getAllReservations()` | 200 OK | — |
| ReservationController | GET `/api/reservations/{id}` | `getReservationById(id)` | 200 OK | 404 |
| ReservationController | POST `/api/reservations` | `ajouterReservation(reservation)` | 201 Created | 400 |
| ReservationController | PUT `/api/reservations/{id}` | `modifierReservation(id, reservation)` | 200 OK | 400, 404 |
| ReservationController | DELETE `/api/reservations/{id}` | `supprimerReservation(id)` | 204 No Content | 404 |
| ClientController | GET `/api/clients` | `retrieveAllClients()` | 200 OK | — |
| ClientController | GET `/api/clients/{id}` | `retrieveClient(id)` | 200 OK | 404 |
| ClientController | POST `/api/clients` | `addClient(client)` | 201 Created | 400 |
| ClientController | POST `/api/clients/batch` | `addClients(clients)` | 201 Created | 400 |
| ClientController | PUT `/api/clients/{id}` | `updateClient(client)` | 200 OK | 400, 404 |
| ClientController | DELETE `/api/clients/{id}` | `removeClient(id)` | 204 No Content | 404 |
| MaintenanceController | GET `/api/maintenances` | `findAll()` | 200 OK | — |
| MaintenanceController | GET `/api/maintenances/{id}` | `findById(id)` | 200 OK | 404 |
| MaintenanceController | POST `/api/maintenances` | `create(maintenance)` | 201 Created | 400 |
| MaintenanceController | PUT `/api/maintenances/{id}` | `update(id, maintenance)` | 200 OK | 400, 404 |
| MaintenanceController | DELETE `/api/maintenances/{id}` | `deleteById(id)` | 204 No Content | 404 |
| EquipementController | GET `/api/equipements` | `findAll()` | 200 OK | — |
| EquipementController | GET `/api/equipements/{id}` | `findById(id)` | 200 OK | 404 |
| EquipementController | POST `/api/equipements` | `create(equipement)` | 201 Created | 400 |
| EquipementController | PUT `/api/equipements/{id}` | `update(id, equipement)` | 200 OK | 400, 404 |
| EquipementController | DELETE `/api/equipements/{id}` | `deleteById(id)` | 204 No Content | 404 |

Les codes 400 et 404 dépendent de la gestion des exceptions configurée dans l'application. Les routes et méthodes du tableau doivent correspondre au code final.

## B. Annotations Jackson et justification

### Association Contrat ↔ Paiement

L'association est bidirectionnelle : un contrat contient une liste de paiements et chaque paiement référence son contrat.

- `@JsonManagedReference` est placée sur `Contrat.paiements`. Elle permet de sérialiser la liste des paiements.
- `@JsonBackReference` est placée sur `Paiement.contrat`. Elle empêche le retour récursif vers le contrat lors de la sérialisation JSON.

Ces deux annotations évitent une boucle infinie entre le contrat et ses paiements.

L'association utilise également `cascade = CascadeType.ALL` et `orphanRemoval = true` sur la collection de paiements. Les opérations de persistance sur le contrat sont propagées aux paiements associés et un paiement retiré de la collection peut être supprimé de la base.

### Différence entre @JsonBackReference et @JsonIgnore

`@JsonBackReference` gère le côté retour d'une association bidirectionnelle lorsqu'il est associé à `@JsonManagedReference`.

`@JsonIgnore` exclut une propriété de la sérialisation JSON. Il est adapté lorsqu'on ne souhaite pas exposer une association dans la réponse, par exemple la collection `Agence.vehicules` si cette propriété porte effectivement cette annotation dans le projet.

Le choix dépend du modèle d'association et du JSON attendu par l'API.

## C. Différences entre Postman et Swagger

**Postman** est un client HTTP qui permet d'envoyer des requêtes à l'API, de fournir un corps JSON, puis de vérifier le statut HTTP, les en-têtes et la réponse.

Exemple : envoyer `GET {{baseUrl}}/api/contrats` pour lister les contrats, ou `GET {{baseUrl}}/api/paiements/9999` pour vérifier le comportement lorsqu'un paiement n'existe pas.

**OpenAPI** est une spécification décrivant les routes, les paramètres, les corps et les réponses de l'API. **SpringDoc** génère cette documentation à partir du code Spring.

**Swagger UI** est l'interface web qui présente cette documentation et permet de tester les endpoints avec le bouton « Try it out ».

Dans ce projet, Swagger UI est accessible à `/api/swagger-ui.html` et le document OpenAPI à `/api/v3/api-docs`, compte tenu du chemin de contexte `/api`.

En résumé, Postman sert surtout à préparer et rejouer des scénarios de test, tandis que Swagger facilite la consultation interactive du contrat de l'API.

## D. Analyse avec SonarQube for IDE

J'ai lancé l'analyse de mon code avec SonarQube for IDE sur le package `web.controller`.

Résultat de l'analyse :
- Issues : aucune anomalie détectée (*No issues to display*).
- Security Hotspots : aucun détecté.
- Taint Vulnerabilities : aucune détectée.

Cette analyse ne m'a signalé aucune anomalie à corriger. Par conséquent, je n'ai pas effectué de correction spécifique basée sur un signalement SonarQube.

La consigne demandant deux anomalies et leurs corrections ne peut pas être satisfaite à partir de ce rapport seul. Il faudrait vérifier si une autre analyse ou une autre configuration permet de détecter des problèmes réels, sans en inventer.
## E. Questions de compréhension

### 1. Quel composant reçoit toutes les requêtes et comment retrouve-t-il la méthode du contrôleur ?

Le `DispatcherServlet` de Spring MVC reçoit les requêtes HTTP. Il s'appuie sur les mappings enregistrés à partir des annotations comme `@RequestMapping`, `@GetMapping` et `@PostMapping` pour sélectionner la méthode correspondant au chemin et au verbe HTTP.

### 2. Pourquoi ContratController dépend-il de IContratService plutôt que du repository ou de ContratServiceImpl ?

Le contrôleur dépend de l'interface `IContratService` pour respecter la séparation des couches et réduire le couplage. Il délègue la logique métier au service, qui utilise le repository pour accéder aux données. L'interface facilite également les tests et le remplacement de l'implémentation.

### 3. Pourquoi POST renvoie-t-il 201, DELETE 204, et une ressource absente 404 plutôt que 500 ?

`201 Created` indique qu'une ressource a été créée. `204 No Content` indique que l'opération a réussi sans corps de réponse, ce qui convient à une suppression. `404 Not Found` signifie que la ressource demandée n'existe pas. `500 Internal Server Error` est réservé aux erreurs serveur inattendues et ne doit pas remplacer une erreur métier connue.

### 4. Pourquoi PaiementController n'expose-t-il que des GET ? Quelle réponse reçoit POST /api/paiements ?

Dans cet atelier, les paiements sont en lecture seule via l'API. Le contrôleur expose uniquement `GET /api/paiements` et `GET /api/paiements/{id}`. Un `POST /api/paiements` doit donc recevoir `405 Method Not Allowed`, car aucune méthode POST n'est définie pour cette route.

### 5. Quels risques présente l'exposition directe des entités JPA ? Pourquoi utiliser deux stratégies Jackson différentes ?

L'exposition directe des entités JPA peut révéler des champs internes, coupler le format de l'API au modèle de base de données, provoquer des boucles de sérialisation ou charger des relations inattendues. Des DTO peuvent limiter ces risques.

Pour Contrat ↔ Paiement, `@JsonManagedReference` et `@JsonBackReference` permettent de conserver le lien JSON dans un sens tout en évitant la récursion. Pour Agence ↔ Vehicule, `@JsonIgnore` sur la collection inverse peut être préférable si l'on veut masquer cette collection et éviter de la sérialiser. Ces stratégies répondent à des besoins d'exposition JSON différents.

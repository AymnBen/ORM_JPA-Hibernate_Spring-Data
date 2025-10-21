<h1># 🧾 Compte Rendu du Projet Spring Boot : Gestion de Produits</h1>

<h1>## 🎯 Objectif du projet</h1>
L’objectif de ce projet est de développer une petite application **Spring Boot** permettant la **gestion des produits**.  
Elle offre la possibilité d’ajouter, consulter, rechercher, modifier et supprimer des produits à l’aide de **Spring Data JPA** et d’une base de données **H2**, puis de **migrer** vers **MySQL**.

---

<h1>## 🏗️ Étapes de réalisation</h1>

<h2>### 1. Création du projet</h2>
Le projet a été créé à partir de **Spring Initializr** avec les dépendances suivantes :
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**

Ces dépendances permettent :
- de gérer les entités avec JPA,
- de manipuler les données avec Spring Data,
- de disposer d’une base de données mémoire (H2) pour les tests,
- et de simplifier le code grâce à Lombok.

---

<h2>### 2. Création de l’entité JPA `Product`</h2>
L’entité `Product` représente un produit avec les attributs suivants : <br>
- `id` : identifiant unique (type `Long`) <br>
- `name` : nom du produit (type `String`) <br>
- `price` : prix du produit (type `double`) <br>
- `quantity` : quantité disponible (type `int`)

<h2>### 3. Configuration de la base de données H2</h2>
- La configuration a été faite dans le fichier application.properties :

spring.datasource.url=jdbc:h2:mem:product-db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create

- Cela permet de créer une base de données temporaire en mémoire pour tester l’application sans installation.

<h2>### 4. Création du dépôt JPA (ProductRepository)</h2>
- L’interface ProductRepository hérite de JpaRepository et permet d’effectuer les opérations CRUD de base.
- Des méthodes de recherche personnalisées ont été ajoutées :
  public interface ProductRepository extends JpaRepository<Product, Long> {
      List<Product> findByNameContains(String name);
      List<Product> findByPriceGreaterThan(double price);
  }

<h2>### 5. Test des fonctionnalités dans la classe principale</h2>
- Les différentes opérations (ajout, affichage, mise à jour, suppression) ont été testées dans la méthode run() de la classe StudentAppApplication.
    
     @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(null, "Computer", 4000, 4));
        productRepository.save(new Product(null, "Printer", 1200, 7));
        productRepository.save(new Product(null, "Phone", 3200, 40));
    
        System.out.println("----- Liste des produits -----");
        productRepository.findAll().forEach(System.out::println);
    
        System.out.println("----- Produit avec ID=1 -----");
        Product p = productRepository.findById(1L).orElse(null);
        System.out.println(p);
    
        System.out.println("----- Recherche par nom -----");
        productRepository.findByNameContains("Phone").forEach(System.out::println);
    
        if (p != null) {
            p.setPrice(4500);
            productRepository.save(p);
        }
    
        productRepository.deleteById(2L);
    }

<h2>### 6. Migration vers MySQL</h2>
➤ Ajouter la dépendance MySQL dans pom.xml
<p>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency></p>

➤ Modifier la configuration application.properties


spring.application.name=students-app
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/product-db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect <br> <br>
<img width="547" height="295" alt="image" src="https://github.com/user-attachments/assets/39fadd03-558d-4527-ba1b-ea6dd9d817f8" /> <br><br>




<h2>🧠 Bilan du projet</h2>
Ce projet a permis de :
- Comprendre le fonctionnement de Spring Boot et Spring Data JPA,
- Manipuler une base de données H2 en mémoire pour les tests,
- Effectuer les opérations CRUD sur une entité,
- Apprendre à migrer vers une base MySQL réelle.

Le projet illustre les bases du développement d’une application Spring Boot orientée données. 



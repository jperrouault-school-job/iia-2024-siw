# Pré-requis
* PHP version > 8.2.0
* Composer
* Symfony-CLI (optionel)

# Créer le projet Symfony
```bash
composer create-project symfony/skeleton:"7.0.*" <nom-projet>
```
OU
```bash
symfony new  <nom-projet> --version="7.0.*"
```

# Se déplacer dans le répertoire projet
cd  <nom-projet>

# Installer les dépendances
```bash
composer require api
composer require symfony/maker-bundle --dev
composer require symfony/debug-bundle --dev
composer require symfony/uid
composer require lexik/jwt-authentication-bundle
```

# Générer une Entité User
```bash
php bin/console make:entity Utilisateur
```

# Pour créer la base de données
```bash
php bin/console doctrine:database:create
```

# Pour préparer les migrations
```bash
php bin/console make:migration
```

# Pour mettre à jour le schéma
```bash
php bin/console doctrine:schema:update --force
```

# Générer un Controller
```bash
php bin/console make:controller UtilisateurApiController
```


# Générer une clé JWT
```bash
php bin/console lexik:jwt:generate-keypair
```

https://symfony.com/bundles/LexikJWTAuthenticationBundle/current/index.html


# Démarrer le serveur PHP
```bash
symfony server:start
```
OU
```bash
php -S localhost:8080 -t public/index.php
```
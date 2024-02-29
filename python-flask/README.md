# Pré-requis #
* Python 3
* Pip

# Installer les composants #
```
pip install flask sqlalchemy flask-sqlalchemy pymysql
pip install flask_migrate # pour Migrer la base de données
pip install psycopg2 # pour PostgreSQL
```

# Migrer la base de données #

```bash
flask db init
flask db migrate
flask db upgrade
```


# Démarrer l'application #

```bash
python -m flask run --debug
```

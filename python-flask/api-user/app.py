from flask import Flask
from flask_migrate import Migrate
import logging

from route.utilisateur import utilisateur_bp
from model.Utilisateur import db

logging.basicConfig(level=logging.DEBUG, format='%(asctime)s %(message)s')

app = Flask(__name__)
app.config.from_object('config')

db.init_app(app)
migrate = Migrate(app, db)

app.register_blueprint(utilisateur_bp, url_prefix='/api/utilisateur')

if __name__ == '__main__':
    app.debug = True
    app.run()
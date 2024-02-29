from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.dialects.postgresql import UUID
import uuid

db = SQLAlchemy()

class Utilisateur(db.Model):
    __tablename__ = 'user'

    id = db.Column('usr_id', UUID(as_uuid=True), primary_key=True, default=uuid.uuid4)
    username = db.Column('usr_username', db.String)
    password = db.Column('usr_password', db.String)
    email = db.Column('usr_email', db.String)
    dateNaissance = db.Column('usr_date_naissance', db.Date)
    
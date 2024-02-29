from flask import abort, request
from model.Utilisateur import Utilisateur, db
import logging

def findAll():
    results = []

    logging.debug("Looking for users list ...")

    users = Utilisateur.query.all()

    for user in users:
        results.append({ 'id': user.id, 'name': user.username })

    return results


def findById(id):
    logging.debug("Looking for user %s ..." % (id))

    user = db.session.query(Utilisateur).filter_by(id=id).first()

    if user is None:
        abort(404)

    return { 'id': user.id, 'name': user.username, 'email': user.email, 'dateNaissance': user.dateNaissance }


def create():
    userRequest = request.json
    logging.debug("Creating user ...")

    user = Utilisateur(
        username=userRequest['name'],
        password=userRequest['password'],
        email=userRequest['email'],
        dateNaissance=userRequest['dateNaissance']
    )

    db.session.add(user)
    db.session.commit()

    return { 'id': user.id }


def editById(id):
    userRequest = request.json
    logging.debug("Editing user %s ..." % (id))

    user = db.session.query(Utilisateur).filter_by(id=id).first()

    if user is None:
        abort(404)

    user.username = userRequest['name']
    user.password = userRequest['password']
    user.email = userRequest['email']
    user.dateNaissance = userRequest['dateNaissance']

    db.session.add(user)
    db.session.commit()

    return { 'id': user.id }

def deleteById(id):
    logging.debug("Removing user %s ..." % (id))

    user = db.session.query(Utilisateur).filter_by(id=id).first()

    if user is None:
        abort(404)
        
    db.session.delete(user)
    db.session.commit()

    return { 'success': True }
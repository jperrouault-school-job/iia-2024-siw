from flask import Blueprint

from api.UtilisateurController import findAll, create, findById, editById, deleteById

utilisateur_bp = Blueprint('utilisateur_bp', __name__)

utilisateur_bp.route('', methods=['GET'])(findAll)
utilisateur_bp.route('/<string:id>', methods=['GET'])(findById)
utilisateur_bp.route('', methods=['POST'])(create)
utilisateur_bp.route('/<string:id>', methods=['PUT'])(editById)
utilisateur_bp.route('/<string:id>', methods=['DELETE'])(deleteById)

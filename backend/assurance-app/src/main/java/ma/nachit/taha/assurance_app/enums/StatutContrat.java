package ma.nachit.taha.assurance_app.enums;

/**
 * Statut du cycle de vie d'un contrat d'assurance.
 * - EN_COURS : contrat créé mais pas encore validé par un employé/admin
 * - VALIDE   : contrat actif, le client est couvert
 * - RESILIE  : contrat terminé (par l'assureur ou par le client)
 */
public enum StatutContrat {
    EN_COURS,
    VALIDE,
    RESILIE
}

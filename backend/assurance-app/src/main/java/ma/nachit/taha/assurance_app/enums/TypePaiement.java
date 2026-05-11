package ma.nachit.taha.assurance_app.enums;

/**
 * Type d'un paiement effectué sur un contrat d'assurance.
 * - MENSUALITE             : prélèvement mensuel récurrent
 * - PAIEMENT_ANNUEL        : règlement en une fois pour l'année
 * - PAIEMENT_EXCEPTIONNEL  : paiement ponctuel hors échéancier
 */
public enum TypePaiement {
    MENSUALITE,
    PAIEMENT_ANNUEL,
    PAIEMENT_EXCEPTIONNEL
}

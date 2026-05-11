package ma.nachit.taha.assurance_app.enums;

/**
 * Niveau de couverture proposé par un contrat d'assurance santé.
 * Ordre croissant : BASIQUE < INTERMEDIAIRE < PREMIUM
 * - BASIQUE       : prestations minimales
 * - INTERMEDIAIRE : couverture standard
 * - PREMIUM       : couverture étendue (haut de gamme)
 */
public enum NiveauCouverture {
    BASIQUE,
    INTERMEDIAIRE,
    PREMIUM
}

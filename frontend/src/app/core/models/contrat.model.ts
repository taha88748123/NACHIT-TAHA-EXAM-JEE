export type StatutContrat = 'EN_COURS' | 'VALIDE' | 'RESILIE';
export type TypeLogement = 'APPARTEMENT' | 'MAISON' | 'LOCAL_COMMERCIAL';
export type NiveauCouverture = 'BASIQUE' | 'INTERMEDIAIRE' | 'PREMIUM';
export type TypeContrat = 'AUTO' | 'HABITATION' | 'SANTE';

export interface Contrat {
  id?: number;
  dateSouscription: string;
  statut: StatutContrat;
  dateValidation?: string | null;
  montantCotisation: number;
  dureeContrat: number;
  tauxCouverture: number;
  clientId: number;
  typeContrat?: TypeContrat;

  numImmatriculation?: string;
  marque?: string;
  modele?: string;

  typeLogement?: TypeLogement;
  adresse?: string;
  superficie?: number;

  niveauCouverture?: NiveauCouverture;
  nombrePersonnesCouvertes?: number;
}

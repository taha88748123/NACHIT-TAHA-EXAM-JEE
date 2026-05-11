import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import { Contrat, TypeContrat } from '../../core/models/contrat.model';
import { Client } from '../../core/models/client.model';
import { ClientService } from '../../core/services/client.service';

export interface ContratDialogData {
  contrat?: Contrat;
}

export interface ContratDialogResult {
  type: TypeContrat;
  contrat: Contrat;
}

@Component({
  selector: 'app-contrat-form-dialog',
  imports: [
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatRadioModule
  ],
  templateUrl: './contrat-form-dialog.html',
  styleUrl: './contrat-form-dialog.scss'
})
export class ContratFormDialog implements OnInit {

  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<ContratFormDialog>);
  private clientService = inject(ClientService);
  data: ContratDialogData = inject(MAT_DIALOG_DATA);

  clients: Client[] = [];
  isEdit = !!this.data.contrat;

  form = this.fb.nonNullable.group({
    type: [this.data.contrat?.typeContrat ?? ('AUTO' as TypeContrat), Validators.required],
    dateSouscription: [this.data.contrat?.dateSouscription ?? new Date().toISOString().substring(0, 10), Validators.required],
    statut: [this.data.contrat?.statut ?? 'EN_COURS', Validators.required],
    montantCotisation: [this.data.contrat?.montantCotisation ?? 0, [Validators.required, Validators.min(0)]],
    dureeContrat: [this.data.contrat?.dureeContrat ?? 12, [Validators.required, Validators.min(1)]],
    tauxCouverture: [this.data.contrat?.tauxCouverture ?? 70, [Validators.required, Validators.min(0), Validators.max(100)]],
    clientId: [this.data.contrat?.clientId ?? 0, Validators.required],

    numImmatriculation: [this.data.contrat?.numImmatriculation ?? ''],
    marque: [this.data.contrat?.marque ?? ''],
    modele: [this.data.contrat?.modele ?? ''],

    typeLogement: [this.data.contrat?.typeLogement ?? 'APPARTEMENT'],
    adresse: [this.data.contrat?.adresse ?? ''],
    superficie: [this.data.contrat?.superficie ?? 0],

    niveauCouverture: [this.data.contrat?.niveauCouverture ?? 'BASIQUE'],
    nombrePersonnesCouvertes: [this.data.contrat?.nombrePersonnesCouvertes ?? 1]
  });

  ngOnInit(): void {
    this.clientService.getAll().subscribe(c => this.clients = c);
  }

  get type(): TypeContrat {
    return this.form.controls.type.value;
  }

  save(): void {
    if (this.form.invalid) return;
    const v = this.form.getRawValue();

    const contrat: Contrat = {
      ...this.data.contrat,
      dateSouscription: v.dateSouscription,
      statut: v.statut as Contrat['statut'],
      montantCotisation: v.montantCotisation,
      dureeContrat: v.dureeContrat,
      tauxCouverture: v.tauxCouverture,
      clientId: v.clientId
    };

    if (v.type === 'AUTO') {
      contrat.numImmatriculation = v.numImmatriculation;
      contrat.marque = v.marque;
      contrat.modele = v.modele;
    } else if (v.type === 'HABITATION') {
      contrat.typeLogement = v.typeLogement as Contrat['typeLogement'];
      contrat.adresse = v.adresse;
      contrat.superficie = v.superficie;
    } else {
      contrat.niveauCouverture = v.niveauCouverture as Contrat['niveauCouverture'];
      contrat.nombrePersonnesCouvertes = v.nombrePersonnesCouvertes;
    }

    const result: ContratDialogResult = { type: v.type, contrat };
    this.dialogRef.close(result);
  }

  close(): void {
    this.dialogRef.close();
  }
}

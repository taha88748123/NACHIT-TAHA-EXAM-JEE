import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { Paiement } from '../../core/models/paiement.model';
import { Contrat } from '../../core/models/contrat.model';
import { ContratService } from '../../core/services/contrat.service';

@Component({
  selector: 'app-paiement-form-dialog',
  imports: [
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule
  ],
  template: `
    <h2 mat-dialog-title>Nouveau paiement</h2>

    <mat-dialog-content>
      <form [formGroup]="form" class="dialog-form">
        <mat-form-field appearance="outline">
          <mat-label>Contrat</mat-label>
          <mat-select formControlName="contratId">
            @for (c of contrats; track c.id) {
              <mat-option [value]="c.id">
                #{{ c.id }} — {{ c.typeContrat }} — client #{{ c.clientId }}
              </mat-option>
            }
          </mat-select>
        </mat-form-field>

        <mat-form-field appearance="outline">
          <mat-label>Date</mat-label>
          <input matInput type="date" formControlName="date">
        </mat-form-field>

        <mat-form-field appearance="outline">
          <mat-label>Montant (MAD)</mat-label>
          <input matInput type="number" formControlName="montant">
        </mat-form-field>

        <mat-form-field appearance="outline">
          <mat-label>Type</mat-label>
          <mat-select formControlName="type">
            <mat-option value="MENSUALITE">Mensualité</mat-option>
            <mat-option value="PAIEMENT_ANNUEL">Paiement annuel</mat-option>
            <mat-option value="PAIEMENT_EXCEPTIONNEL">Paiement exceptionnel</mat-option>
          </mat-select>
        </mat-form-field>
      </form>
    </mat-dialog-content>

    <mat-dialog-actions align="end">
      <button mat-button (click)="close()">Annuler</button>
      <button mat-flat-button color="primary" [disabled]="form.invalid" (click)="save()">
        Enregistrer
      </button>
    </mat-dialog-actions>
  `,
  styles: [`
    .dialog-form {
      display: flex;
      flex-direction: column;
      gap: 12px;
      min-width: 380px;
      padding-top: 8px;
    }
  `]
})
export class PaiementFormDialog implements OnInit {

  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<PaiementFormDialog>);
  private contratService = inject(ContratService);

  contrats: Contrat[] = [];

  form = this.fb.nonNullable.group({
    contratId: [0, Validators.required],
    date: [new Date().toISOString().substring(0, 10), Validators.required],
    montant: [0, [Validators.required, Validators.min(0)]],
    type: ['MENSUALITE', Validators.required]
  });

  ngOnInit(): void {
    this.contratService.getAll().subscribe(c => this.contrats = c);
  }

  save(): void {
    if (this.form.invalid) return;
    const v = this.form.getRawValue();
    const paiement: Paiement = {
      contratId: v.contratId,
      date: v.date,
      montant: v.montant,
      type: v.type as Paiement['type']
    };
    this.dialogRef.close(paiement);
  }

  close(): void {
    this.dialogRef.close();
  }
}

import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Client } from '../../core/models/client.model';

export interface ClientDialogData {
  client?: Client;
}

@Component({
  selector: 'app-client-form-dialog',
  imports: [
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  template: `
    <h2 mat-dialog-title>{{ data.client ? 'Modifier le client' : 'Nouveau client' }}</h2>

    <mat-dialog-content>
      <form [formGroup]="form" class="dialog-form">
        <mat-form-field appearance="outline">
          <mat-label>Nom complet</mat-label>
          <input matInput formControlName="nom">
        </mat-form-field>

        <mat-form-field appearance="outline">
          <mat-label>Email</mat-label>
          <input matInput formControlName="email" type="email">
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
      min-width: 360px;
      padding-top: 8px;
    }
  `]
})
export class ClientFormDialog {

  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<ClientFormDialog>);
  data: ClientDialogData = inject(MAT_DIALOG_DATA);

  form = this.fb.nonNullable.group({
    nom: [this.data.client?.nom ?? '', Validators.required],
    email: [this.data.client?.email ?? '', [Validators.required, Validators.email]]
  });

  save(): void {
    if (this.form.invalid) return;
    const result: Client = {
      ...this.data.client,
      ...this.form.getRawValue()
    };
    this.dialogRef.close(result);
  }

  close(): void {
    this.dialogRef.close();
  }
}

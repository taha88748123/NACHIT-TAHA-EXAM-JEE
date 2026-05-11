import { Component, OnInit, inject, signal } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { PaiementService } from '../../core/services/paiement.service';
import { Paiement } from '../../core/models/paiement.model';
import { PaiementFormDialog } from './paiement-form-dialog';

@Component({
  selector: 'app-paiements-list',
  imports: [
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatSnackBarModule,
    MatDialogModule
  ],
  templateUrl: './paiements-list.html',
  styleUrl: './paiements-list.scss'
})
export class PaiementsList implements OnInit {

  private service = inject(PaiementService);
  private dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  paiements = signal<Paiement[]>([]);
  loading = signal(false);
  displayedColumns = ['id', 'contratId', 'date', 'montant', 'type', 'actions'];

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading.set(true);
    this.service.getAll().subscribe({
      next: data => {
        this.paiements.set(data);
        this.loading.set(false);
      },
      error: () => {
        this.loading.set(false);
        this.snackBar.open('Erreur lors du chargement des paiements', 'OK', { duration: 3000 });
      }
    });
  }

  openCreate(): void {
    const ref = this.dialog.open(PaiementFormDialog);
    ref.afterClosed().subscribe((result: Paiement | undefined) => {
      if (result) {
        this.service.create(result).subscribe({
          next: () => {
            this.snackBar.open('Paiement créé', 'OK', { duration: 2000 });
            this.load();
          },
          error: () => this.snackBar.open('Erreur lors de la création', 'OK', { duration: 3000 })
        });
      }
    });
  }

  delete(p: Paiement): void {
    if (!p.id) return;
    if (!confirm(`Supprimer le paiement #${p.id} ?`)) return;
    this.service.delete(p.id).subscribe({
      next: () => {
        this.snackBar.open('Paiement supprimé', 'OK', { duration: 2000 });
        this.load();
      },
      error: () => this.snackBar.open('Erreur lors de la suppression', 'OK', { duration: 3000 })
    });
  }
}

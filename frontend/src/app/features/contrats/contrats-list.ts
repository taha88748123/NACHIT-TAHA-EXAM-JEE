import { Component, OnInit, inject, signal } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ContratService } from '../../core/services/contrat.service';
import { Contrat } from '../../core/models/contrat.model';
import { ContratDialogResult, ContratFormDialog } from './contrat-form-dialog';

@Component({
  selector: 'app-contrats-list',
  imports: [
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatChipsModule,
    MatSnackBarModule,
    MatDialogModule
  ],
  templateUrl: './contrats-list.html',
  styleUrl: './contrats-list.scss'
})
export class ContratsList implements OnInit {

  private service = inject(ContratService);
  private dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  contrats = signal<Contrat[]>([]);
  loading = signal(false);
  displayedColumns = ['id', 'type', 'statut', 'clientId', 'montantCotisation', 'dateSouscription', 'actions'];

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading.set(true);
    this.service.getAll().subscribe({
      next: data => {
        this.contrats.set(data);
        this.loading.set(false);
      },
      error: () => {
        this.loading.set(false);
        this.snackBar.open('Erreur lors du chargement des contrats', 'OK', { duration: 3000 });
      }
    });
  }

  openCreate(): void {
    const ref = this.dialog.open(ContratFormDialog, { data: {} });
    ref.afterClosed().subscribe((result: ContratDialogResult | undefined) => {
      if (result) {
        this.service.create(result.type, result.contrat).subscribe({
          next: () => {
            this.snackBar.open('Contrat créé', 'OK', { duration: 2000 });
            this.load();
          },
          error: () => this.snackBar.open('Erreur lors de la création', 'OK', { duration: 3000 })
        });
      }
    });
  }

  openEdit(contrat: Contrat): void {
    const ref = this.dialog.open(ContratFormDialog, { data: { contrat } });
    ref.afterClosed().subscribe((result: ContratDialogResult | undefined) => {
      if (result && contrat.id) {
        this.service.update(result.type, contrat.id, result.contrat).subscribe({
          next: () => {
            this.snackBar.open('Contrat modifié', 'OK', { duration: 2000 });
            this.load();
          },
          error: () => this.snackBar.open('Erreur lors de la modification', 'OK', { duration: 3000 })
        });
      }
    });
  }

  valider(contrat: Contrat): void {
    if (!contrat.id) return;
    this.service.valider(contrat.id).subscribe({
      next: () => {
        this.snackBar.open('Contrat validé', 'OK', { duration: 2000 });
        this.load();
      },
      error: () => this.snackBar.open('Erreur lors de la validation', 'OK', { duration: 3000 })
    });
  }

  delete(contrat: Contrat): void {
    if (!contrat.id) return;
    if (!confirm(`Supprimer le contrat #${contrat.id} ?`)) return;
    this.service.delete(contrat.id).subscribe({
      next: () => {
        this.snackBar.open('Contrat supprimé', 'OK', { duration: 2000 });
        this.load();
      },
      error: () => this.snackBar.open('Erreur lors de la suppression', 'OK', { duration: 3000 })
    });
  }

  statutColor(statut: string): string {
    switch (statut) {
      case 'VALIDE': return 'primary';
      case 'EN_COURS': return 'accent';
      case 'RESILIE': return 'warn';
      default: return '';
    }
  }
}

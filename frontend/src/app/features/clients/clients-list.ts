import { Component, OnInit, inject, signal } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ClientService } from '../../core/services/client.service';
import { Client } from '../../core/models/client.model';
import { ClientFormDialog } from './client-form-dialog';

@Component({
  selector: 'app-clients-list',
  imports: [
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatSnackBarModule,
    MatDialogModule
  ],
  templateUrl: './clients-list.html',
  styleUrl: './clients-list.scss'
})
export class ClientsList implements OnInit {

  private service = inject(ClientService);
  private dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  clients = signal<Client[]>([]);
  loading = signal(false);
  displayedColumns = ['id', 'nom', 'email', 'actions'];

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading.set(true);
    this.service.getAll().subscribe({
      next: data => {
        this.clients.set(data);
        this.loading.set(false);
      },
      error: () => {
        this.loading.set(false);
        this.snackBar.open('Erreur lors du chargement des clients', 'OK', { duration: 3000 });
      }
    });
  }

  openCreate(): void {
    const ref = this.dialog.open(ClientFormDialog, { data: {} });
    ref.afterClosed().subscribe((result: Client | undefined) => {
      if (result) {
        this.service.create(result).subscribe({
          next: () => {
            this.snackBar.open('Client créé', 'OK', { duration: 2000 });
            this.load();
          },
          error: () => this.snackBar.open('Erreur lors de la création', 'OK', { duration: 3000 })
        });
      }
    });
  }

  openEdit(client: Client): void {
    const ref = this.dialog.open(ClientFormDialog, { data: { client } });
    ref.afterClosed().subscribe((result: Client | undefined) => {
      if (result && client.id) {
        this.service.update(client.id, result).subscribe({
          next: () => {
            this.snackBar.open('Client modifié', 'OK', { duration: 2000 });
            this.load();
          },
          error: () => this.snackBar.open('Erreur lors de la modification', 'OK', { duration: 3000 })
        });
      }
    });
  }

  delete(client: Client): void {
    if (!client.id) return;
    if (!confirm(`Supprimer le client "${client.nom}" ?`)) return;
    this.service.delete(client.id).subscribe({
      next: () => {
        this.snackBar.open('Client supprimé', 'OK', { duration: 2000 });
        this.load();
      },
      error: () => this.snackBar.open('Erreur lors de la suppression', 'OK', { duration: 3000 })
    });
  }
}

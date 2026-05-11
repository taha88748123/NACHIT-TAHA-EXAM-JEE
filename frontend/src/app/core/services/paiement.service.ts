import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Paiement } from '../models/paiement.model';

@Injectable({ providedIn: 'root' })
export class PaiementService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:7979/api/paiements';

  getAll(): Observable<Paiement[]> {
    return this.http.get<Paiement[]>(this.apiUrl);
  }

  getById(id: number): Observable<Paiement> {
    return this.http.get<Paiement>(`${this.apiUrl}/${id}`);
  }

  getByContrat(contratId: number): Observable<Paiement[]> {
    return this.http.get<Paiement[]>(`${this.apiUrl}/contrat/${contratId}`);
  }

  create(paiement: Paiement): Observable<Paiement> {
    return this.http.post<Paiement>(this.apiUrl, paiement);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

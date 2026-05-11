import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contrat, StatutContrat, TypeContrat } from '../models/contrat.model';

@Injectable({ providedIn: 'root' })
export class ContratService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:7979/api/contrats';

  getAll(): Observable<Contrat[]> {
    return this.http.get<Contrat[]>(this.apiUrl);
  }

  getById(id: number): Observable<Contrat> {
    return this.http.get<Contrat>(`${this.apiUrl}/${id}`);
  }

  getByStatut(statut: StatutContrat): Observable<Contrat[]> {
    return this.http.get<Contrat[]>(`${this.apiUrl}/statut/${statut}`);
  }

  getByClient(clientId: number): Observable<Contrat[]> {
    return this.http.get<Contrat[]>(`${this.apiUrl}/client/${clientId}`);
  }

  create(type: TypeContrat, contrat: Contrat): Observable<Contrat> {
    const path = this.pathForType(type);
    return this.http.post<Contrat>(`${this.apiUrl}/${path}`, contrat);
  }

  update(type: TypeContrat, id: number, contrat: Contrat): Observable<Contrat> {
    const path = this.pathForType(type);
    return this.http.put<Contrat>(`${this.apiUrl}/${path}/${id}`, contrat);
  }

  valider(id: number): Observable<Contrat> {
    return this.http.put<Contrat>(`${this.apiUrl}/${id}/valider`, {});
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  private pathForType(type: TypeContrat): string {
    switch (type) {
      case 'AUTO': return 'automobile';
      case 'HABITATION': return 'habitation';
      case 'SANTE': return 'sante';
    }
  }
}

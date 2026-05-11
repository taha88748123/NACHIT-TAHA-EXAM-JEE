import { Injectable, inject, signal, computed } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { AuthRequest, AuthResponse } from '../models/auth.model';

const STORAGE_KEY = 'assurance_auth';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private http = inject(HttpClient);
  private router = inject(Router);

  private apiUrl = 'http://localhost:7979/auth';

  private authState = signal<AuthResponse | null>(this.loadFromStorage());

  isAuthenticated = computed(() => this.authState() !== null);
  username = computed(() => this.authState()?.username ?? null);
  roles = computed(() => this.authState()?.roles ?? []);

  login(credentials: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, credentials).pipe(
      tap(response => {
        this.authState.set(response);
        localStorage.setItem(STORAGE_KEY, JSON.stringify(response));
      })
    );
  }

  logout(): void {
    this.authState.set(null);
    localStorage.removeItem(STORAGE_KEY);
    this.router.navigate(['/login']);
  }

  getToken(): string | null {
    return this.authState()?.accessToken ?? null;
  }

  hasRole(role: string): boolean {
    return this.roles().includes(role);
  }

  private loadFromStorage(): AuthResponse | null {
    const raw = localStorage.getItem(STORAGE_KEY);
    return raw ? JSON.parse(raw) : null;
  }
}

import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./features/login/login').then(m => m.Login)
  },
  {
    path: '',
    canActivate: [authGuard],
    loadComponent: () => import('./features/home/home').then(m => m.Home)
  },
  {
    path: 'clients',
    canActivate: [authGuard],
    loadComponent: () => import('./features/clients/clients-list').then(m => m.ClientsList)
  },
  {
    path: 'contrats',
    canActivate: [authGuard],
    loadComponent: () => import('./features/contrats/contrats-list').then(m => m.ContratsList)
  },
  {
    path: 'paiements',
    canActivate: [authGuard],
    loadComponent: () => import('./features/paiements/paiements-list').then(m => m.PaiementsList)
  },
  {
    path: '**',
    redirectTo: ''
  }
];

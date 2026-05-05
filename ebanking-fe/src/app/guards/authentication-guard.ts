import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { Auth as Authentification } from '../services/auth';


//pour utilser ce  guards il faut l'ajouter dans les routes voulu (voir les routes )
export const authentificationGuard: CanActivateFn = (route, state) => {
  const authService = inject(Authentification);//injecte le  service pour recupérer le authentificated car s'il a passé par login ce boolen sera rempli
  const router = inject(Router);
  if (authService.isAuthenticated) {//cad il a passé par login
    return true;
  } else {
    return router.navigateByUrl("/login");//redireger vers login
  }
};

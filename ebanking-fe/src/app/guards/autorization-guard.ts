import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { Auth as Authentification } from '../services/auth';

export const authorizationGuard: CanActivateFn = (route, state) => {
  const authService = inject(Authentification);
  const router = inject(Router);

  if (authService.roles.includes('ADMIN')) {
    return true;
  } else {
    return router.navigateByUrl('admin/not-authorized'); // redirection
  }
};

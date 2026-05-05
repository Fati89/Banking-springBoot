import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Auth as Authentification } from '../services/auth';
import { catchError, throwError } from 'rxjs';

export const appHttpInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(Authentification); //on a injecter le service auth prendre jwt pour les ajouter dans les requetes

  const token = authService.accessToken; //le jwt

  console.log(' ****** ');
  console.log(req.url);

  // on doit pas ajouter le tocken dans l'url de login car c'est elle qui donne jwt
  if (!req.url.includes('/auth/login')) {
    const token = authService.accessToken; //tocken de service qui nous la donner apres login

    const clonedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });

    //return next(clonedReq);//avant d'utilser localstorage

    return next(clonedReq).pipe(//si la requete deja contient un erreur cad en travaillons son tocken a expire
      catchError((err) => {
        //  si token expiré ou invalide
        if (err.status === 401) {
          authService.logout(); //
        }

        return throwError(() => err);
      }),
    );
  }

  //  login sans token
  return next(req);
};

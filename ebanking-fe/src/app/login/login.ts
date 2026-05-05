import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Auth as Authentification } from '../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  FormLogin!:FormGroup

  constructor(private fb:FormBuilder,
              private serviceauth:Authentification,
              private router:Router) {
  }

  ngOnInit(){
    this.FormLogin=this.fb.group(
      {
        username:this.fb.control(null,[Validators.required,Validators.minLength(4)]),
        password:this.fb.control(null,Validators.required)
      }
    )
  }


  protected handeLoginCustomer() {
    console.log(this.FormLogin.value)
    let username=this.FormLogin.value.username;
    let password=this.FormLogin.value.password

    this.serviceauth.login(username, password).subscribe({
      next: (data) => {
        console.log('Succès', data);
        this.serviceauth.loadProfile(data) // appeler le service qui decoder jwt et   extraire les info username et roles et les stocke car on vautilser jwt pour l'envoyer dans les autres requetes
        this.router.navigateByUrl("/admin") // redirection vers une page admin

      },
      error: (err) => {
        console.log('Erreur', err);
      },
    });
  }



}

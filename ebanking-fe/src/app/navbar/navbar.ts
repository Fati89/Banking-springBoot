import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import { Auth as Authentification } from '../services/auth';

@Component({
  selector: 'app-navbar',
  standalone: false,
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {

  constructor(public authService: Authentification,private router :Router) {}//on a fait le service authentifiaction public pour qu'il soit accessible dans html de nav

  ngOnInit() {}


  handelogout() {
    this.authService.logout();//pour laisser tiut de l'authentification dans son service
  }

}


import {Component, OnInit, signal} from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { Auth as Authentification } from './services/auth';
import { Navbar } from './navbar/navbar';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('Banking');

  constructor(private authserviec: Authentification) {}
  ngOnInit() {
    this.authserviec.loadJWTokenFromlocalStorage()
  }
}

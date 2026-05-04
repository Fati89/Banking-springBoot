import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { Navbar } from './navbar/navbar';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('ebanking-fe');
}

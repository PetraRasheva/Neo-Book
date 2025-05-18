import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TopBarComponent } from './top-bar/top-bar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TopBarComponent],
  template: `
    <app-top-bar></app-top-bar>
    <main>
      <router-outlet></router-outlet>
    </main>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {}

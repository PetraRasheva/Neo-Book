import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { KeycloakAngularModule } from 'keycloak-angular';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, KeycloakAngularModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.sass'
})
export class AppComponent {
  title = 'neo-book-ui';
}

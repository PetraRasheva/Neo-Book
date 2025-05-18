import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, UserRole } from './keycloak.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { KeycloakAngularModule } from 'keycloak-angular';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.sass'],
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatCardModule, KeycloakAngularModule]
})
export class AuthComponent implements OnInit {
  isAuthenticated = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  async ngOnInit() {
    this.isAuthenticated = await this.authService.isLoggedIn();
    if (this.isAuthenticated) {
      await this.navigateBasedOnRole();
    }
  }

  async login() {
    await this.authService.login();
  }

  async register() {
    await this.authService.register();
  }

  private async navigateBasedOnRole() {
    const roles = await this.authService.getUserRoles();
    if (roles.length > 0) {
      const primaryRole = roles[0];
      await this.router.navigate([`/${primaryRole}`]);
    }
  }
}
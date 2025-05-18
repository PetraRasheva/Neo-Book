import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';

export type UserRole = 'student' | 'teacher' | 'headmaster';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private keycloak: KeycloakService) {
    console.log('KeycloakService constructor', keycloak);
  }

  public async login(): Promise<void> {
    try {
      console.log('KeycloakService login', this.keycloak);
      await this.keycloak.login({
        redirectUri: window.location.origin
      });
    } catch (error) {
      console.error('Failed to login', error);
    }
  }

  public async logout(): Promise<void> {
    try {
      await this.keycloak.logout(window.location.origin);
    } catch (error) {
      console.error('Failed to logout', error);
    }
  }

  public async register(): Promise<void> {
    try {
      await this.keycloak.register({
        redirectUri: window.location.origin
      });
    } catch (error) {
      console.error('Failed to register', error);
    }
  }

  public async isLoggedIn(): Promise<boolean> {
    return await this.keycloak.isLoggedIn();
  }

  public getUserProfile(): Promise<KeycloakProfile | null> {
    return this.keycloak.loadUserProfile();
  }

  public getToken(): Promise<string> {
    return this.keycloak.getToken();
  }

  public async getUserRoles(): Promise<UserRole[]> {
    const roles = await this.keycloak.getUserRoles();
    return roles.filter(role =>
      ['student', 'teacher', 'headmaster'].includes(role)
    ) as UserRole[];
  }

  public async hasRole(role: UserRole): Promise<boolean> {
    const roles = await this.getUserRoles();
    return roles.includes(role);
  }

  public async hasAnyRole(roles: UserRole[]): Promise<boolean> {
    const userRoles = await this.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
}
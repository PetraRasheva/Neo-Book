import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService, UserRole } from './keycloak.service';

@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  async canActivate(
    route: ActivatedRouteSnapshot
  ): Promise<boolean | UrlTree> {
    const isLoggedIn = await this.authService.isLoggedIn();

    if (!isLoggedIn) {
      return this.router.parseUrl('/auth');
    }

    const expectedRoles = route.data['roles'] as UserRole[];
    const hasRequiredRole = await this.authService.hasAnyRole(expectedRoles);

    if (hasRequiredRole) {
      return true;
    }

    // If user is logged in but doesn't have the required role,
    // redirect them based on their role
    const userRoles = await this.authService.getUserRoles();
    if (userRoles.length > 0) {
      const primaryRole = userRoles[0];
      return this.router.parseUrl(`/${primaryRole}`);
    }

    return this.router.parseUrl('/auth');
  }
}
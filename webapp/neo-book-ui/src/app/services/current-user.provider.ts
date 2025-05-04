import { Roles } from '../dtos/roles';

export class CurrentUserProvider {
  getCurrentUser() {
    return {
      role: Roles.Admin
    };
  }
}
import { Routes } from '@angular/router';
import { RoleGuard } from './auth/role.guard';
import { SchoolsListComponent } from './schools-list/schools-list.component';
import { SchoolDetailsComponent } from './school-details/school-details.component';
import { TeachersListComponent } from './teachers-list/teachers-list.component';
import { TeacherDetailsComponent } from './teacher-details/teacher-details.component';
import { AuthComponent } from './auth/auth.component';
import { ClassDetailsComponent } from './class-details/class-details.component';

export const routes: Routes = [
  // Auth routes
  { path: 'auth', component: AuthComponent },

  // Headmaster routes
  {
    path: 'headmaster',
    // canActivate: [RoleGuard],
    data: { roles: ['headmaster'] },
    children: [
      { path: 'teachers', component: TeachersListComponent },
      { path: 'teachers/:id', component: TeacherDetailsComponent },
      { path: 'schools', component: SchoolsListComponent },
      { path: 'schools/:id', component: SchoolDetailsComponent },
      { path: '', redirectTo: 'schools', pathMatch: 'full' }
    ]
  },

  // Teacher routes
  {
    path: 'teacher',
    canActivate: [RoleGuard],
    data: { roles: ['teacher'] },
    children: [
      { path: '', redirectTo: 'schedule', pathMatch: 'full' }
    ]
  },

  // Student routes
  {
    path: 'student',
    canActivate: [RoleGuard],
    data: { roles: ['student'] },
    children: [
      { path: '', redirectTo: 'schedule', pathMatch: 'full' }
    ]
  },

  // Class routes
  {
    path: 'class/:id',
    component: ClassDetailsComponent
  },

  // Default route
  { path: '', redirectTo: '/schools/1', pathMatch: 'full' }
];

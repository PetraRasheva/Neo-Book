import { Routes } from '@angular/router';
import { RoleGuard } from './auth/role.guard';
import { SchoolsListComponent } from './schools-list/schools-list.component';
import { SchoolDetailsComponent } from './school-details/school-details.component';
import { TeachersListComponent } from './teachers-list/teachers-list.component';

export const routes: Routes = [
  // Public routes
  // { path: 'login', component: LoginComponent },
  // { path: 'register', component: RegisterComponent },

  // Admin routes
  {
    path: 'admin',
    canActivate: [RoleGuard],
    data: { roles: ['admin'] },
    children: [
      // { path: 'dashboard', component: AdminDashboardComponent },
      // { path: 'students', component: StudentListComponent },
      { path: 'teachers', component: TeachersListComponent },
      // { path: 'classes', component: ClassManagementComponent },
      // { path: 'subjects', component: SubjectManagementComponent },
      { path: 'schools', component: SchoolsListComponent },
      { path: 'schools/:id', component: SchoolDetailsComponent }
    ]
  },

  // Teacher routes
  {
    path: 'teacher',
    canActivate: [RoleGuard],
    data: { roles: ['teacher'] },
    children: [
      // { path: 'dashboard', component: TeacherDashboardComponent },
      // { path: 'classes', component: TeacherClassListComponent },
      // { path: 'assignments', component: AssignmentManagementComponent },
      // { path: 'grades', component: GradeEntryComponent },
    ]
  },

  // Student routes
  {
    path: 'student',
    canActivate: [RoleGuard],
    data: { roles: ['student'] },
    children: [
      // { path: 'dashboard', component: StudentDashboardComponent },
      // { path: 'assignments', component: StudentAssignmentListComponent },
      // { path: 'grades', component: StudentGradeViewComponent },
      // { path: 'schedule', component: StudentScheduleComponent },
    ]
  },

  // Parent routes
  {
    path: 'parent',
    canActivate: [RoleGuard],
    data: { roles: ['parent'] },
    children: [
      // { path: 'dashboard', component: ParentDashboardComponent },
      // { path: 'child-grades', component: ChildGradeViewComponent },
      // { path: 'messages', component: ParentMessagesComponent },
    ]
  },

  // Default route
  { path: '', redirectTo: '/admin/schools', pathMatch: 'full' },
  // { path: '**', component: PageNotFoundComponent }
];

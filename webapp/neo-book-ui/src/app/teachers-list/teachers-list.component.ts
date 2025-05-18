import { Component, computed, Input, Output, EventEmitter } from '@angular/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { groupBy } from '../utils/groupBy';
import { Teacher } from '../dtos/teacher';

@Component({
  selector: 'app-teachers-list',
  standalone: true,
  imports: [
    MatExpansionModule,
    MatListModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    CommonModule
  ],
  templateUrl: './teachers-list.component.html',
  styleUrls: ['./teachers-list.component.css']
})
export class TeachersListComponent {
  @Input() teachers: Teacher[] = [];
  @Input() isEditing = false;
  @Output() teacherUpdated = new EventEmitter<Teacher>();

  teacherSubjectsMap = computed(() => groupBy('subject', this.teachers));
  subjects = computed(() => Object.keys(this.teacherSubjectsMap()));

  constructor(private router: Router) {}

  updateTeacher(teacher: Teacher): void {
    this.teacherUpdated.emit(teacher);
  }

  viewTeacherDetails(teacher: Teacher): void {
    if (!this.isEditing) {
      this.router.navigate(['/headmaster/teachers', teacher.id]);
    }
  }
}

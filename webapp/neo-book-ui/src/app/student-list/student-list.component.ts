import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatListModule } from '@angular/material/list';
import { Student } from '../dtos/student';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [
    CommonModule,
    MatListModule
  ],
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent {
  @Input() students: Student[] = [];
  @Input() isEditing = false;

  sortedStudents() {
    return [...this.students].sort((a, b) => a.name.localeCompare(b.name));
  }
}
import { Component, OnInit, signal, WritableSignal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ActivatedRoute, Router } from '@angular/router';
import { Teacher } from '../dtos/teacher';
import { SchoolService } from '../services/school.service';

@Component({
  selector: 'app-teacher-details',
  standalone: true,
  providers: [
    SchoolService
  ],
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule
  ],
  templateUrl: './teacher-details.component.html',
  styleUrls: ['./teacher-details.component.css']
})
export class TeacherDetailsComponent implements OnInit {
  teacher: WritableSignal<Teacher | null> = signal(null);
  schoolName: WritableSignal<string> = signal('');
  isEditing = signal(false);
  editedTeacher: Teacher | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private schoolService: SchoolService
  ) {}

  ngOnInit(): void {
    this.loadTeacherDetails();
  }

  private loadTeacherDetails(): void {
    this.schoolService.getSchoolDetails().subscribe(details => {
      const teacherId = Number(this.route.snapshot.paramMap.get('id'));
      const teacher = details.teachers.find(t => t.id === teacherId);
      if (teacher) {
        this.teacher.set(teacher);
        this.schoolName.set(details.name);
      } else {
        // Teacher not found, redirect to the list
        this.router.navigate(['/headmaster/teachers']);
      }
    });
  }

  startEditing(): void {
    if (this.teacher()) {
      this.editedTeacher = { ...this.teacher()! };
      this.isEditing.set(true);
    }
  }

  cancelEditing(): void {
    this.editedTeacher = null;
    this.isEditing.set(false);
  }

  saveChanges(): void {
    if (this.editedTeacher) {
      this.schoolService.updateTeacher(this.editedTeacher).subscribe(updatedTeacher => {
        this.teacher.set(updatedTeacher);
        this.isEditing.set(false);
        this.editedTeacher = null;
      });
    }
  }
}
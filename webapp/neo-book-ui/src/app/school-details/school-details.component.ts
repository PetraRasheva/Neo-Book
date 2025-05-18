import { Component, OnInit, signal, WritableSignal } from '@angular/core';
import { TeachersListComponent } from "../teachers-list/teachers-list.component";
import { ClassListComponent } from "../class-list/class-list.component";
import { Teacher } from '../dtos/teacher';
import { Headmaster } from '../dtos/headmaster';
import { Class } from '../dtos/class';
import { SchoolService, SchoolDetails } from '../services/school.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-school-details',
  standalone: true,
  providers: [
    SchoolService
  ],
  imports: [
    TeachersListComponent,
    ClassListComponent,
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule
  ],
  templateUrl: './school-details.component.html',
  styleUrls: ['./school-details.component.css']
})
export class SchoolDetailsComponent implements OnInit {
  teachers: WritableSignal<Teacher[]> = signal([]);
  headmaster: WritableSignal<Headmaster> = signal({ name: '', id: 0 });
  classes: WritableSignal<Class[]> = signal([]);
  schoolName: WritableSignal<string> = signal('');
  isEditing = signal(false);
  editedDetails: SchoolDetails | null = null;

  constructor(
    private schoolService: SchoolService,
    private route: ActivatedRoute
  ) {
    console.log('School details component constructor');
  }

  ngOnInit(): void {
    console.log('School details component initialized');
    this.route.params.subscribe(() => {
      this.loadSchoolDetails();
    });
  }

  private loadSchoolDetails(): void {
    this.schoolService.getSchoolDetails().subscribe(details => {
      this.teachers.set(details.teachers);
      this.headmaster.set(details.headmaster);
      this.schoolName.set(details.name);
      this.classes.set(details.classes);
    });
  }

  startEditing(): void {
    this.editedDetails = {
      id: 1, // Using test ID for now
      name: this.schoolName(),
      teachers: [...this.teachers()],
      headmaster: { ...this.headmaster() },
      classes: [...this.classes()]
    };
    this.isEditing.set(true);
  }

  cancelEditing(): void {
    this.editedDetails = null;
    this.isEditing.set(false);
    this.loadSchoolDetails(); // Reload original data
  }

  saveChanges(): void {
    if (this.editedDetails) {
      this.schoolService.updateSchoolDetails(this.editedDetails).subscribe(updatedDetails => {
        this.teachers.set(updatedDetails.teachers);
        this.headmaster.set(updatedDetails.headmaster);
        this.schoolName.set(updatedDetails.name);
        this.classes.set(updatedDetails.classes);
        this.isEditing.set(false);
        this.editedDetails = null;
      });
    }
  }

  onTeacherUpdated(teacher: Teacher): void {
    if (this.editedDetails) {
      const index = this.editedDetails.teachers.findIndex(t => t.id === teacher.id);
      if (index !== -1) {
        this.editedDetails.teachers[index] = { ...teacher };
        this.teachers.set([...this.editedDetails.teachers]);
      }
    }
  }

  onClassSelected(selectedClass: Class): void {
    console.log('Selected class:', selectedClass);
    // Handle class selection - could navigate to class details or show a dialog
  }
}

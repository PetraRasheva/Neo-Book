import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { Class } from '../dtos/class';
import { StudentListComponent } from '../student-list/student-list.component';

@Component({
  selector: 'app-class-list',
  standalone: true,
  imports: [
    CommonModule,
    MatExpansionModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    RouterModule,
    StudentListComponent
  ],
  templateUrl: './class-list.component.html',
  styleUrls: ['./class-list.component.css']
})
export class ClassListComponent {
  @Input() classes: Class[] = [];
  @Input() isEditing = false;
  @Output() classSelected = new EventEmitter<Class>();

  sortedClasses() {
    return [...this.classes].sort((a, b) => {
      if (a.grade !== b.grade) {
        return a.grade - b.grade;
      }
      return a.letter.localeCompare(b.letter);
    });
  }

  getClassDisplayName(schoolClass: Class): string {
    return `${schoolClass.grade}${schoolClass.letter}`;
  }

  selectClass(schoolClass: Class): void {
    this.classSelected.emit(schoolClass);
  }
}
import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { ParentService } from '../../services/parent.service';
import { Parent } from '../../dtos/parent';
import { Student } from '../../dtos/student';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-parent-dashboard',
  templateUrl: './parent-dashboard.component.html',
  styleUrls: ['./parent-dashboard.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    DatePipe
  ]
})
export class ParentDashboardComponent implements OnInit {
  parent: Parent | null = null;
  selectedStudent: Student | null = null;
  studentProgress: any = null;

  constructor(private parentService: ParentService) {}

  ngOnInit() {
    // In a real app, we would get the parent ID from the authentication service
    const mockParentId = 1;
    this.loadParentData(mockParentId);
  }

  private loadParentData(parentId: number) {
    this.parentService.getParentById(parentId).subscribe({
      next: (parent) => {
        this.parent = parent;
        if (parent.children.length > 0) {
          this.selectStudent(parent.children[0]);
        }
      },
      error: (error) => console.error('Error loading parent data:', error)
    });
  }

  selectStudent(student: Student) {
    this.selectedStudent = student;
    this.loadStudentProgress(student.id);
  }

  private loadStudentProgress(studentId: number) {
    this.parentService.getStudentProgress(studentId).subscribe({
      next: (progress) => this.studentProgress = progress,
      error: (error) => console.error('Error loading student progress:', error)
    });
  }
}
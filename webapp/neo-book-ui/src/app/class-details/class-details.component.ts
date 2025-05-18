import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { ActivatedRoute } from '@angular/router';
import { SchoolService } from '../services/school.service';
import { Class } from '../dtos/class';
import { Student, Subject } from '../dtos/student';
import { ScheduleViewComponent } from '../schedule-view/schedule-view.component';

@Component({
  selector: 'app-class-details',
  standalone: true,
  providers: [
    SchoolService
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatListModule,
    MatDividerModule,
    MatButtonModule,
    ScheduleViewComponent
  ],
  templateUrl: './class-details.component.html',
  styleUrls: ['./class-details.component.css']
})
export class ClassDetailsComponent implements OnInit {
  class: Class | null = null;
  showSchedule = false;

  constructor(
    private route: ActivatedRoute,
    private schoolService: SchoolService
  ) {}

  ngOnInit(): void {
    const classId = Number(this.route.snapshot.paramMap.get('id'));
    this.schoolService.getSchoolDetails().subscribe(details => {
      this.class = details.classes.find(c => c.id === classId) || null;
    });
  }

  getSubjectsList(subjects: Subject[]): string {
    return subjects.map(s => s.name).join(', ');
  }

  viewClassSchedule(): void {
    this.showSchedule = true;
  }

  closeSchedule(): void {
    this.showSchedule = false;
  }

  getClassDisplayName(schoolClass: Class): string {
    return `${schoolClass.grade}${schoolClass.letter}`;
  }
}
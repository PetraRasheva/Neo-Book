import { Component, Input, OnInit } from '@angular/core';
import { CalendarDateFormatter, CalendarModule } from 'angular-calendar';
import { CalendarSchedulerEvent, SchedulerDateFormatter, SchedulerModule } from 'angular-calendar-scheduler';
import { SubjectAssignment } from '../dtos/subject-assignment';
import { weeklySchedule } from './mock-schedule';

@Component({
  selector: 'app-schedule-view',
  imports: [CalendarModule, SchedulerModule],
  providers: [
    { provide: CalendarDateFormatter, useClass: SchedulerDateFormatter}
  ],
  templateUrl: './schedule-view.component.html',
  styleUrl: './schedule-view.component.sass'
})
export class ScheduleViewComponent implements OnInit {
  currentStartOfTheWeek = this.getCurrentStartOfTheWeek();

  events: CalendarSchedulerEvent[] = [];

  @Input()
  subjectAssignments: SubjectAssignment[] = weeklySchedule;

  ngOnInit() {
    this.events = this.subjectAssignments.map(subjectAssignment => this.toEvent(subjectAssignment));
  }

   private getCurrentStartOfTheWeek() {
    const now = new Date();
    return new Date(now.setDate(now.getDate() - now.getDay() + 1));
  }

  private toEvent({ startTime, endTime, subject, id, teacher}: SubjectAssignment): CalendarSchedulerEvent {
    return {
      start: startTime,
      end: endTime,
      id: `${id}`,
      title: subject,
      content: teacher.name,
      color: {
        primary: 'blue',
        secondary: 'white'
      }
    };
  }
}

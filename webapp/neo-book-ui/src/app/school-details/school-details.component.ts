import { Component, OnInit, signal, WritableSignal } from '@angular/core';
import { TeachersListComponent } from "../teachers-list/teachers-list.component";
import { Teacher } from '../dtos/teacher';
import { Headmaster } from '../dtos/headmaster';
import { ScheduleViewComponent } from '../schedule-view/schedule-view.component';

interface SchoolDetails {
  headmaster: Headmaster;
  teachers: Teacher[];
};

@Component({
  selector: 'app-school-details',
  imports: [TeachersListComponent, ScheduleViewComponent],
  templateUrl: './school-details.component.html',
})
export class SchoolDetailsComponent implements OnInit {

  teachers: WritableSignal<Teacher[]> = signal([]);

  ngOnInit(): void {
    // API call then set the signal
    this.teachers.set([
      { name: 'Ganka Penkova', subject: 'Maths', id: 1 },
      { name: 'Kalofer Minchev', subject: 'English', id: 2 },
      { name: 'Shicc Tervelova', subject: 'Maths', id: 3 },
    ])
  }
}

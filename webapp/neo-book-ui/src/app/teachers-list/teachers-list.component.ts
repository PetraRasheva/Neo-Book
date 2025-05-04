import { Component, computed, Input } from '@angular/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { groupBy } from '../utils/groupBy';

interface Teacher {
  name: string;
  subject: string;
  id: number;
}

@Component({
  selector: 'app-teachers-list',
  imports: [MatExpansionModule, MatListModule],
  templateUrl: './teachers-list.component.html',
})
export class TeachersListComponent {
  @Input() teachers: Teacher[] = [];

  teacherSubjectsMap = computed(() => groupBy('subject', this.teachers));

  subjects = computed(() => Object.keys(this.teacherSubjectsMap()))
}

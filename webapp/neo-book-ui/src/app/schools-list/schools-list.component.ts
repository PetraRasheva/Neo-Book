import { Component, inject, OnInit, signal, WritableSignal } from '@angular/core';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';
import { School } from '../dtos/school';

@Component({
  selector: 'app-schools-list',
  imports: [MatGridListModule, MatCardModule],
  templateUrl: './schools-list.component.html',
})
export class SchoolsListComponent implements OnInit {

  private readonly router = inject(Router);

  ngOnInit(): void {
    this.schools.set([
      { name: 'Test School 1', address: 'Mila Rodina', id: 1 },
      { name: 'Test School 2', address: 'Enos', id: 2 }
    ])
  }

  schools: WritableSignal<School[]> = signal([]);

  openSchool(schoolId: number) {
    this.router.navigate(['admin', `schools`, schoolId]);
  }
}

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { SchoolService } from '../services/school.service';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-top-bar',
  standalone: true,
  providers: [
    SchoolService
  ],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    RouterModule
  ],
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {
  schoolName$: Observable<string>;

  constructor(private schoolService: SchoolService) {
    this.schoolName$ = this.schoolService.getSchoolDetails().pipe(
      map(details => details?.name || 'Neo-Book')
    );
  }

  ngOnInit(): void {}
}
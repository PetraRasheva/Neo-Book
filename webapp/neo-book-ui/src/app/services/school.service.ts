import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Teacher } from '../dtos/teacher';
import { Headmaster } from '../dtos/headmaster';
import { Class } from '../dtos/class';
import { Subject } from '../dtos/student';

const TEST_SUBJECTS: Subject[] = [
  { id: 1, name: 'Mathematics', teacherId: 1 },
  { id: 2, name: 'Physics', teacherId: 2 },
  { id: 3, name: 'Literature', teacherId: 3 },
  { id: 4, name: 'History', teacherId: 4 },
  { id: 5, name: 'Biology', teacherId: 5 },
  { id: 6, name: 'Chemistry', teacherId: 6 },
  { id: 7, name: 'English', teacherId: 7 },
  { id: 8, name: 'Physical Education', teacherId: 8 }
];

const TEST_TEACHERS: Teacher[] = [
  { id: 1, name: 'Maria Petrova', subject: 'Mathematics' },
  { id: 2, name: 'Ivan Dimitrov', subject: 'Physics' },
  { id: 3, name: 'Elena Ivanova', subject: 'Literature' },
  { id: 4, name: 'Stefan Georgiev', subject: 'History' },
  { id: 5, name: 'Nina Todorova', subject: 'Biology' },
  { id: 6, name: 'Peter Kolev', subject: 'Chemistry' },
  { id: 7, name: 'Diana Markova', subject: 'English' },
  { id: 8, name: 'Boris Angelov', subject: 'Physical Education' }
];

const TEST_HEADMASTER: Headmaster = {
  id: 1,
  name: 'Dr. Alexander Popov'
};

const TEST_CLASSES: Class[] = [
  {
    id: 1,
    grade: 9,
    letter: 'A',
    headTeacher: TEST_TEACHERS[0],
    students: [
      {
        id: 1,
        name: 'John Smith',
        age: 15,
        subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[6]],
        scheduleId: 101,
        gpa: 5.5
      },
      {
        id: 2,
        name: 'Emma Wilson',
        age: 14,
        subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[6]],
        scheduleId: 102,
        gpa: 6.0
      },
      {
        id: 3,
        name: 'Michael Brown',
        age: 15,
        subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[6]],
        scheduleId: 103,
        gpa: 4.8
      }
    ]
  },
  {
    id: 2,
    grade: 9,
    letter: 'B',
    headTeacher: TEST_TEACHERS[4],
    students: [
      {
        id: 4,
        name: 'Sarah Davis',
        age: 14,
        subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[4], TEST_SUBJECTS[5], TEST_SUBJECTS[6]],
        scheduleId: 104,
        gpa: 5.2
      },
      {
        id: 5,
        name: 'James Johnson',
        age: 15,
        subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[4], TEST_SUBJECTS[5], TEST_SUBJECTS[6]],
        scheduleId: 105,
        gpa: 4.5
      },
      {
        id: 6,
        name: 'Emily White',
        age: 14,
        subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[4], TEST_SUBJECTS[5], TEST_SUBJECTS[6]],
        scheduleId: 106,
        gpa: 5.8
      }
    ]
  },
  {
    id: 3,
    grade: 10,
    letter: 'A',
    headTeacher: TEST_TEACHERS[2],
    students: [
      {
        id: 7,
        name: 'David Miller',
        age: 16,
        subjects: [TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[3], TEST_SUBJECTS[7]],
        scheduleId: 107,
        gpa: 5.0
      },
      {
        id: 8,
        name: 'Sophie Taylor',
        age: 15,
        subjects: [TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[3], TEST_SUBJECTS[7]],
        scheduleId: 108,
        gpa: 5.9
      },
      {
        id: 9,
        name: 'Daniel Anderson',
        age: 16,
        subjects: [TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[3], TEST_SUBJECTS[7]],
        scheduleId: 109,
        gpa: 4.2
      }
    ]
  }
];

const TEST_SCHOOL_DETAILS = {
  id: 1,
  name: 'First English Language School',
  headmaster: TEST_HEADMASTER,
  teachers: TEST_TEACHERS,
  classes: TEST_CLASSES
};

@Injectable()
export class SchoolService {
  private apiUrl = 'api/school';

  constructor(private http: HttpClient) {}

  getSchoolDetails(): Observable<SchoolDetails> {
    // Return test data for now
    console.log('Getting school details');
    return of(TEST_SCHOOL_DETAILS);
  }

  updateSchoolDetails(details: SchoolDetails): Observable<SchoolDetails> {
    // Simulate API call with test data
    console.log('Updating school details:', details);
    return of(details);
  }

  getTeachers(): Observable<Teacher[]> {
    return of(TEST_TEACHERS);
  }

  updateTeacher(teacher: Teacher): Observable<Teacher> {
    const index = TEST_TEACHERS.findIndex(t => t.id === teacher.id);
    if (index !== -1) {
      TEST_TEACHERS[index] = { ...teacher };
    }
    return of(teacher);
  }
}

export interface SchoolDetails {
  id: number;
  name: string;
  headmaster: Headmaster;
  teachers: Teacher[];
  classes: Class[];
}
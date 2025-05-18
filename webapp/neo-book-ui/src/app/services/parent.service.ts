import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { Parent } from '../dtos/parent';
import { Student } from '../dtos/student';

// Test student data
const TEST_STUDENTS: Student[] = [
  {
    id: 1,
    name: 'John Smith',
    age: 15,
    class: {
      id: 1,
      grade: 9,
      letter: 'A',
      headTeacher: {
        id: 1,
        name: 'Maria Petrova',
        subject: 'Mathematics'
      },
      students: []
    },
    subjects: [
      { id: 1, name: 'Mathematics', teacherId: 1 },
      { id: 2, name: 'Physics', teacherId: 2 },
      { id: 3, name: 'Literature', teacherId: 3 }
    ],
    scheduleId: 101,
    gpa: 5.5,
    parentIds: [1]
  },
  {
    id: 5,
    name: 'James Johnson',
    age: 15,
    class: {
      id: 2,
      grade: 9,
      letter: 'B',
      headTeacher: {
        id: 5,
        name: 'Nina Todorova',
        subject: 'Biology'
      },
      students: []
    },
    subjects: [
      { id: 1, name: 'Mathematics', teacherId: 1 },
      { id: 5, name: 'Biology', teacherId: 5 },
      { id: 6, name: 'Chemistry', teacherId: 6 }
    ],
    scheduleId: 105,
    gpa: 4.5,
    parentIds: [1, 2]
  },
  {
    id: 8,
    name: 'Sophie Taylor',
    age: 15,
    class: {
      id: 3,
      grade: 10,
      letter: 'A',
      headTeacher: {
        id: 3,
        name: 'Elena Ivanova',
        subject: 'Literature'
      },
      students: []
    },
    subjects: [
      { id: 2, name: 'Physics', teacherId: 2 },
      { id: 3, name: 'Literature', teacherId: 3 },
      { id: 8, name: 'Physical Education', teacherId: 8 }
    ],
    scheduleId: 108,
    gpa: 5.9,
    parentIds: [1]
  }
];

// Test parents data
const TEST_PARENTS: Parent[] = [
  {
    id: 1,
    name: 'Robert Smith',
    email: 'robert.smith@email.com',
    phone: '555-0101',
    children: TEST_STUDENTS.filter(student => student.parentIds.includes(1))
  },
  {
    id: 2,
    name: 'Mary Wilson',
    email: 'mary.wilson@email.com',
    phone: '555-0102',
    children: []
  }
];

@Injectable({
  providedIn: 'root'
})
export class ParentService {
  private apiUrl = 'api/parents';

  constructor(private http: HttpClient) {}

  getParentById(id: number): Observable<Parent> {
    // Simulate API call with test data
    const parent = TEST_PARENTS.find(p => p.id === id);
    if (!parent) {
      return throwError(() => new Error(`Parent with ID ${id} not found`));
    }
    return of(parent);
  }

  getParentsByStudentId(studentId: number): Observable<Parent[]> {
    // In a real implementation, this would filter parents based on student ID
    return of(TEST_PARENTS.filter(p =>
      p.children.some(child => child.id === studentId)
    ));
  }

  updateParentInfo(parent: Parent): Observable<Parent> {
    // Simulate API call
    const index = TEST_PARENTS.findIndex(p => p.id === parent.id);
    if (index !== -1) {
      TEST_PARENTS[index] = { ...parent };
    }
    return of(parent);
  }

  getStudentProgress(studentId: number): Observable<StudentProgress> {
    // Simulate getting detailed student progress
    return of({
      currentGPA: 5.0,
      attendanceRate: 95,
      recentGrades: [
        { subject: 'Mathematics', grade: 5.5, date: '2024-03-15' },
        { subject: 'Physics', grade: 4.8, date: '2024-03-14' },
        { subject: 'Literature', grade: 5.2, date: '2024-03-13' }
      ],
      upcomingTests: [
        { subject: 'Chemistry', date: '2024-03-20', topic: 'Organic Chemistry' },
        { subject: 'History', date: '2024-03-22', topic: 'World War II' }
      ]
    });
  }
}

interface StudentProgress {
  currentGPA: number;
  attendanceRate: number;
  recentGrades: {
    subject: string;
    grade: number;
    date: string;
  }[];
  upcomingTests: {
    subject: string;
    date: string;
    topic: string;
  }[];
}
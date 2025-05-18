import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Teacher } from '../dtos/teacher';
import { Headmaster } from '../dtos/headmaster';
import { Class } from '../dtos/class';
import { Subject, Student } from '../dtos/student';
import { Parent } from '../dtos/parent';

// Test parents data
const TEST_PARENTS: Parent[] = [
  {
    id: 1,
    name: 'Robert Smith',
    email: 'robert.smith@email.com',
    phone: '555-0101',
    children: [] // Will be populated after student creation
  },
  {
    id: 2,
    name: 'Mary Wilson',
    email: 'mary.wilson@email.com',
    phone: '555-0102',
    children: [] // Will be populated after student creation
  },
  {
    id: 3,
    name: 'James Brown',
    email: 'james.brown@email.com',
    phone: '555-0103',
    children: [] // Will be populated after student creation
  },
  {
    id: 4,
    name: 'Sarah Davis',
    email: 'sarah.davis@email.com',
    phone: '555-0104',
    children: [] // Will be populated after student creation
  }
];

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

// Create students with parent references
const createTestStudents = () => {
  const students: Student[] = [
    {
      id: 1,
      name: 'John Smith',
      age: 15,
      subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[6]],
      scheduleId: 101,
      gpa: 5.5,
      parentIds: [1] // Robert Smith's child
    },
    {
      id: 2,
      name: 'Emma Wilson',
      age: 14,
      subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[6]],
      scheduleId: 102,
      gpa: 6.0,
      parentIds: [2] // Mary Wilson's child
    },
    {
      id: 3,
      name: 'Michael Brown',
      age: 15,
      subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[6]],
      scheduleId: 103,
      gpa: 4.8,
      parentIds: [3] // James Brown's child
    },
    {
      id: 4,
      name: 'Sarah Davis Jr',
      age: 14,
      subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[4], TEST_SUBJECTS[5], TEST_SUBJECTS[6]],
      scheduleId: 104,
      gpa: 5.2,
      parentIds: [4] // Sarah Davis's child
    },
    {
      id: 5,
      name: 'James Johnson',
      age: 15,
      subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[4], TEST_SUBJECTS[5], TEST_SUBJECTS[6]],
      scheduleId: 105,
      gpa: 4.5,
      parentIds: [1, 2] // Shared custody between Robert Smith and Mary Wilson
    },
    {
      id: 6,
      name: 'Emily White',
      age: 14,
      subjects: [TEST_SUBJECTS[0], TEST_SUBJECTS[4], TEST_SUBJECTS[5], TEST_SUBJECTS[6]],
      scheduleId: 106,
      gpa: 5.8,
      parentIds: [3] // James Brown's second child
    },
    {
      id: 7,
      name: 'David Miller',
      age: 16,
      subjects: [TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[3], TEST_SUBJECTS[7]],
      scheduleId: 107,
      gpa: 5.0,
      parentIds: [4] // Sarah Davis's second child
    },
    {
      id: 8,
      name: 'Sophie Taylor',
      age: 15,
      subjects: [TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[3], TEST_SUBJECTS[7]],
      scheduleId: 108,
      gpa: 5.9,
      parentIds: [1] // Robert Smith's second child
    },
    {
      id: 9,
      name: 'Daniel Anderson',
      age: 16,
      subjects: [TEST_SUBJECTS[1], TEST_SUBJECTS[2], TEST_SUBJECTS[3], TEST_SUBJECTS[7]],
      scheduleId: 109,
      gpa: 4.2,
      parentIds: [2] // Mary Wilson's second child
    }
  ];

  // Populate parent's children arrays
  students.forEach(student => {
    student.parentIds.forEach(parentId => {
      const parent = TEST_PARENTS.find(p => p.id === parentId);
      if (parent) {
        parent.children.push(student);
      }
    });
  });

  return students;
};

const TEST_STUDENTS = createTestStudents();

const TEST_CLASSES: Class[] = [
  {
    id: 1,
    grade: 9,
    letter: 'A',
    headTeacher: TEST_TEACHERS[0],
    students: [TEST_STUDENTS[0], TEST_STUDENTS[1], TEST_STUDENTS[2]]
  },
  {
    id: 2,
    grade: 9,
    letter: 'B',
    headTeacher: TEST_TEACHERS[4],
    students: [TEST_STUDENTS[3], TEST_STUDENTS[4], TEST_STUDENTS[5]]
  },
  {
    id: 3,
    grade: 10,
    letter: 'A',
    headTeacher: TEST_TEACHERS[2],
    students: [TEST_STUDENTS[6], TEST_STUDENTS[7], TEST_STUDENTS[8]]
  }
];

// Add class reference to students
TEST_CLASSES.forEach(class_ => {
  class_.students.forEach(student => {
    student.class = class_;
  });
});

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

  // New method to get parents
  getParents(): Observable<Parent[]> {
    return of(TEST_PARENTS);
  }
}

export interface SchoolDetails {
  id: number;
  name: string;
  headmaster: Headmaster;
  teachers: Teacher[];
  classes: Class[];
}
import { Student } from './student';
import { Teacher } from './teacher';

export interface Class {
  id: number;
  grade: number;  // 1-12
  letter: string; // A-E
  students: Student[];
  headTeacher: Teacher;
}
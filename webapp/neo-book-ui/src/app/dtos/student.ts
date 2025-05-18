import { Class } from './class';

export interface Subject {
  id: number;
  name: string;
  teacherId: number;
}

export interface Student {
  id: number;
  name: string;
  age: number;
  class?: Class;
  subjects: Subject[];
  scheduleId?: number;
  gpa: number;
  parentIds: number[];  // Reference to parent IDs
}
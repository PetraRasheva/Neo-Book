import { Teacher } from './teacher';

export interface SubjectAssignment {
  id: number;
  dayOfWeek: number;
  startTime: Date;
  endTime: Date;
  teacher: Teacher;
  subject: string;
}
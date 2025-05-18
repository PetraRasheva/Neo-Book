import { SubjectAssignment } from '../dtos/subject-assignment';
import { Teacher } from '../dtos/teacher';

const teachers: Teacher[] = [
  { id: 1, name: 'Alice Johnson', subject: 'Maths' },
  { id: 2, name: 'Bob Smith', subject: 'Biology' },
  { id: 3, name: 'Catherine Lee', subject: 'History' },
  { id: 4, name: 'David Brown', subject: 'Geography' },
  { id: 5, name: 'Emily Davis', subject: 'Philosophy' },
];

const monday = new Date();

function createDate(dayOffset: number, hour: number, minute = 0): Date {
  const date = new Date(monday);
  date.setDate(date.getDate() + dayOffset);
  date.setHours(hour, minute, 0, 0);
  return date;
}

export const weeklySchedule: SubjectAssignment[] = [
  // Monday (dayOfWeek = 1)
  {
    id: 1,
    dayOfWeek: 1,
    startTime: createDate(0, 8, 0),
    endTime: createDate(0, 9, 0),
    teacher: teachers[0],
    subject: 'Mathematics',
  },
  {
    id: 2,
    dayOfWeek: 1,
    startTime: createDate(0, 9, 15),
    endTime: createDate(0, 10, 15),
    teacher: teachers[1],
    subject: 'English',
  },

  // Tuesday (dayOfWeek = 2)
  {
    id: 3,
    dayOfWeek: 2,
    startTime: createDate(1, 8, 0),
    endTime: createDate(1, 9, 0),
    teacher: teachers[2],
    subject: 'Science',
  },
  {
    id: 4,
    dayOfWeek: 2,
    startTime: createDate(1, 9, 15),
    endTime: createDate(1, 10, 15),
    teacher: teachers[3],
    subject: 'History',
  },

  // Wednesday (dayOfWeek = 3)
  {
    id: 5,
    dayOfWeek: 3,
    startTime: createDate(2, 8, 0),
    endTime: createDate(2, 9, 0),
    teacher: teachers[4],
    subject: 'Physical Education',
  },
  {
    id: 6,
    dayOfWeek: 3,
    startTime: createDate(2, 9, 15),
    endTime: createDate(2, 10, 15),
    teacher: teachers[0],
    subject: 'Mathematics',
  },

  // Thursday (dayOfWeek = 4)
  {
    id: 7,
    dayOfWeek: 4,
    startTime: createDate(3, 8, 0),
    endTime: createDate(3, 9, 0),
    teacher: teachers[1],
    subject: 'English',
  },
  {
    id: 8,
    dayOfWeek: 4,
    startTime: createDate(3, 9, 15),
    endTime: createDate(3, 10, 15),
    teacher: teachers[2],
    subject: 'Chemistry',
  },

  // Friday (dayOfWeek = 5)
  {
    id: 9,
    dayOfWeek: 5,
    startTime: createDate(4, 8, 0),
    endTime: createDate(4, 9, 0),
    teacher: teachers[3],
    subject: 'Geography',
  },
  {
    id: 10,
    dayOfWeek: 5,
    startTime: createDate(4, 9, 15),
    endTime: createDate(4, 10, 15),
    teacher: teachers[4],
    subject: 'Art',
  },
];

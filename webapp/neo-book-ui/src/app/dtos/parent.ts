import { Student } from './student';

export interface Parent {
  id: number;
  name: string;
  email: string;
  phone: string;
  children: Student[];
}
import { Answer } from './answer.model';

export interface Justification {
  correct: boolean;
    id?: number;
    justificationText: string;
    marked: boolean;
    valid: boolean;
    error?: string;
    answer?: Answer;
}
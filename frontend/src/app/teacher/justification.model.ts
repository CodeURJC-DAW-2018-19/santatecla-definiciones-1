import { Answer } from './answer.model';

export interface Justification {
    id?: number;
    justificationText: string;
    marked: boolean;
    valid: boolean;
    error: string;
    answer?: Answer;
}
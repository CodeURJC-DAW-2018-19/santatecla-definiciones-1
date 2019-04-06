import { Answer } from '../teacher/answer.model';
import { JustificationStudent } from './justificationStudent.model';

export interface Question {
    id?: number;
    questionText: string;
    type: number;
    yesNoQuestion: boolean;
    userResponse: boolean;
    marked: boolean;
    correct: boolean;
    answer?: Answer;
    justification?: JustificationStudent;
}
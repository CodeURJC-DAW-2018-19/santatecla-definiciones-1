import { Justification } from "./justification.model";

export interface Answer {
    id: number;
    answerText: string;
    marked: boolean
    correct: boolean;
    justifications: Justification[];
}
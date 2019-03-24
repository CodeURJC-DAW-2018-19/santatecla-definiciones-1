export interface Question {
    id: number;
    questionText: string;
    type: number;
    yesNoQuestion: boolean;
    userResponse: boolean;
    marked: boolean;
    correct: boolean;
}
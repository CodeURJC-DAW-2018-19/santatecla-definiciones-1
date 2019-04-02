export interface StudentAnswer {
    answerText?: string, //If yes/no question, is null
    answerOption?: string, //If not yes/no question, is null
    questionText: string,
    answerId?: number, //can be null if question type is 0
    justificationQuestionId?: number, //can be null if question type not 3
    questionType: number
}
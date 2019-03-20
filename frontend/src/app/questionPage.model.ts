import { Question } from "./question.model";
import { SortPage } from "./sortPage.model";

export interface QuestionPage {
    size: number;
    number: number;
    totalElements: number;
    last: boolean;
    totalPages: number;
    sort: SortPage;
    first: boolean;
    numberOfElements: number;
    content: Question[];
}
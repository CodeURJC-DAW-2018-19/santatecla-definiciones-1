import { Answer } from "./answer.model";
import { SortPage } from "../sortPage.model";

export interface AnswerPage {
    size: number;
    number: number;
    totalElements: number;
    last: boolean;
    totalPages: number;
    sort: SortPage;
    first: boolean;
    numberOfElements: number;
    content: Answer[];
}
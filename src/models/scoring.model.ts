import { Choice } from "./enums/choice";

export class Scoring {
    selection: Choice;
    possibleResults: {
        [key in Choice]: number
    }
    constructor(selection: Choice, possibleResults: { [key in Choice]: number} ) {
        this.selection = selection;
        this.possibleResults = possibleResults;
    }
}
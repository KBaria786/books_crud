import { Author } from "src/app/authors/model/author";
import { Genre } from "src/app/genres/model/genre";
import { Publisher } from "src/app/publishers/model/publisher";

export interface Book {
    id: number;
    isbn13: string;
    title: string;
    description: string;
    numPages: number;
    publicationDate: Date;
    publisher: Publisher;
    authors: Author[];
    genres: Genre[];
    createdAt: Date;
    updatedAt: Date;
    deleted: boolean;
}

import { User } from ".";

export class ImageDetail {
    id!: number;
    url!:string;
    title!: string;
    description!: string;
    tags!: string;
    pictureDetails!: string;
    visibility!: string;
    numofdownloads!:number;
    userid!: User
}
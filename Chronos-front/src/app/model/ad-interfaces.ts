

export interface Ad{
id: number;
title:string;
description: string;
availability:string;
createAd: Date;
userId: number;
categoryName:string;
}

export interface Category{
    id: number;
    name: string;
    photo: string;
}
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
    icon: string;
}

export interface CreateAd{
    title:string;
    description: string;
    availability:string;
    userId: number;
    categoryId: number;
}
    
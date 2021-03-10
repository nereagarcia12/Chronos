import { Ad } from "./ad-interfaces";
import { User } from "./user-interface";

export interface Transaction {
    id: number;
    originUserId: number;
    receiverUserId: number;
    amount: number;
    description: string;
    adId: number;
    status: string;
  }

  export interface CreateTransaction {
    originUserId: number;
    receiverUserId: number;
    amount: number;
    description: string;
    adId: number;
  }

  export interface CompleteTransaction {
    id: number;
    originUserId: User;
    receiverUserId: User;
    amount: number;
    description: string;
    adId: Ad;
    status: string;
  }
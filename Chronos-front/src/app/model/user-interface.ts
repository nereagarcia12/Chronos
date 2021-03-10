
export interface User {
  id: number;
  name: string;
  email: string;
  phone: string;
  city: string;
  createdAt: Date;
  pendingTransaction: boolean;
}

export interface CreateUser {
    name: string;
    email: string;
    password: string;
    phone: string;
    city: string;
  }

export interface User {
  id: number;
  name: string;
  email: string;
  phone: string;
  city: string;
  createdAt: Date;
  pendingTransaction: boolean;
}
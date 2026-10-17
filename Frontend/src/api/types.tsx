export interface Category {
  categoryId: string;
  categoryName: string;
  description: string | null;
}

export interface Service {
  serviceId: string;
  serviceName: string;
  description: string;
  fixedPrice: number;
  estimatedDurationMinutes: number | null;
  active: boolean;
}

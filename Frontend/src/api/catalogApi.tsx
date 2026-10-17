import type { Category, Service } from "./types";

const API_BASE_URL =
  import.meta.env.VITE_API_BASE_URL ?? "http://localhost:8080";

async function getJson<T>(path: string): Promise<T> {
  const response = await fetch(`${API_BASE_URL}${path}`);

  if (!response.ok) {
    throw new Error(
      `API request failed: ${response.status} ${response.statusText}`
    );
  }

  return response.json() as Promise<T>;
}

export function getCategories(): Promise<Category[]> {
  return getJson<Category[]>("/api/categories");
}

export function getServicesByCategory(
  categoryId: string
): Promise<Service[]> {
  return getJson<Service[]>(
    `/api/categories/${encodeURIComponent(categoryId)}/services`
  );
}

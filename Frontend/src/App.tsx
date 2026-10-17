import { useEffect, useState } from "react";
import { getCategories, getServicesByCategory } from "./api/catalogApi";
import type { Category, Service } from "./api/types";

function App() {
  const [categories, setCategories] = useState<Category[]>([]);
  const [services, setServices] = useState<Service[]>([]);
  const [selectedCategoryId, setSelectedCategoryId] = useState<string | null>(
    null
  );

  const [loadingCategories, setLoadingCategories] = useState(true);
  const [loadingServices, setLoadingServices] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    getCategories()
      .then(setCategories)
      .catch(() => setError("Unable to load categories. Please try again."))
      .finally(() => setLoadingCategories(false));
  }, []);

  async function handleCategoryClick(categoryId: string) {
    setSelectedCategoryId(categoryId);
    setServices([]);
    setError("");
    setLoadingServices(true);

    try {
      const result = await getServicesByCategory(categoryId);
      setServices(result);
    } catch {
      setError("Unable to load services. Please try again.");
    } finally {
      setLoadingServices(false);
    }
  }

  const selectedCategory = categories.find(
    (category) => category.categoryId === selectedCategoryId
  );

  return (
    <main
      style={{
        maxWidth: "1000px",
        margin: "0 auto",
        padding: "32px 20px",
        fontFamily: "Arial, sans-serif",
        color: "#222",
      }}
    >
      <header style={{ marginBottom: "32px" }}>
        <h1 style={{ marginBottom: "8px" }}>TaskEasy</h1>
        <p style={{ color: "#666", margin: 0 }}>
          Reliable help for everyday home services.
        </p>
      </header>

      <section>
        <h2>What do you need help with?</h2>

        {loadingCategories && <p>Loading categories...</p>}

        {!loadingCategories && categories.length === 0 && !error && (
          <p>No categories are available right now.</p>
        )}

        <div
          style={{
            display: "grid",
            gridTemplateColumns: "repeat(auto-fit, minmax(180px, 1fr))",
            gap: "16px",
            marginTop: "20px",
          }}
        >
          {categories.map((category) => (
            <button
              key={category.categoryId}
              onClick={() => handleCategoryClick(category.categoryId)}
              style={{
                padding: "20px",
                textAlign: "left",
                borderRadius: "10px",
                border:
                  selectedCategoryId === category.categoryId
                    ? "2px solid #2563eb"
                    : "1px solid #ddd",
                background:
                  selectedCategoryId === category.categoryId
                    ? "#eff6ff"
                    : "#fff",
                cursor: "pointer",
              }}
            >
              <strong>{category.categoryName}</strong>
              {category.description && (
                <p style={{ color: "#666", marginBottom: 0 }}>
                  {category.description}
                </p>
              )}
            </button>
          ))}
        </div>
      </section>

      {selectedCategory && (
        <section style={{ marginTop: "40px" }}>
          <h2>{selectedCategory.categoryName} services</h2>

          {loadingServices && <p>Loading services...</p>}

          {!loadingServices && services.length === 0 && !error && (
            <p>No services are available in this category yet.</p>
          )}

          <div
            style={{
              display: "grid",
              gridTemplateColumns: "repeat(auto-fit, minmax(220px, 1fr))",
              gap: "16px",
              marginTop: "20px",
            }}
          >
            {services.map((service) => (
              <article
                key={service.serviceId}
                style={{
                  border: "1px solid #ddd",
                  borderRadius: "10px",
                  padding: "20px",
                }}
              >
                <h3 style={{ marginTop: 0 }}>{service.serviceName}</h3>
                <p style={{ color: "#666" }}>{service.description}</p>

                <p>
                  <strong>
                    {new Intl.NumberFormat("en-IN", {
                      style: "currency",
                      currency: "INR",
                      maximumFractionDigits: 2,
                    }).format(service.fixedPrice)}
                  </strong>
                </p>

                {service.estimatedDurationMinutes != null && (
                  <p style={{ color: "#666", fontSize: "14px" }}>
                    Estimated duration: {service.estimatedDurationMinutes}{" "}
                    minutes
                  </p>
                )}
              </article>
            ))}
          </div>
        </section>
      )}

      {error && (
        <p role="alert" style={{ color: "crimson", marginTop: "20px" }}>
          {error}
        </p>
      )}
    </main>
  );
}

export default App;

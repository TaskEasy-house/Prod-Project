import { useState, type FormEvent } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import type { Service } from "../api/types";

interface LocationState {
  service?: Service;
}

function ServiceRequestPage() {
  const { serviceId } = useParams<{ serviceId: string }>();
  const location = useLocation();
  const navigate = useNavigate();

  const state = location.state as LocationState | null;
  const serviceFromState = state?.service;

  const service =
    serviceFromState && serviceFromState.serviceId === serviceId
      ? serviceFromState
      : undefined;

  const [problemDescription, setProblemDescription] = useState("");
  const [error, setError] = useState("");
  const [status, setStatus] = useState("");

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();

    const description = problemDescription.trim();
    if (description.length < 10) {
      setError("Please describe your problem in at least 10 characters.");
      setStatus("");
      return;
    }

    setError("");

    // This captures the form data, but does not submit it to a backend.
    const requestDetails = {
      serviceId,
      problemDescription: description,
    };
    console.info("Request details ready to submit:", requestDetails);

    setStatus(
      "Your requirements are ready, but request submission is not connected yet."
    );
  }

  return (
    <main
      style={{
        maxWidth: "700px",
        margin: "40px auto",
        padding: "24px",
        fontFamily: "Arial, sans-serif",
      }}
    >
      <button type="button" onClick={() => navigate("/")}>
        Back to services
      </button>

      <h1>Tell us what you need</h1>

      {service ? (
        <section
          style={{
            border: "1px solid #ddd",
            borderRadius: "12px",
            padding: "24px",
            marginTop: "24px",
          }}
        >
          <h2>{service.serviceName}</h2>
          <p style={{ color: "#666" }}>{service.description}</p>
          <h3>
            {new Intl.NumberFormat("en-IN", {
              style: "currency",
              currency: "INR",
            }).format(service.fixedPrice)}
          </h3>
          {service.estimatedDurationMinutes != null && (
            <p>Estimated duration: {service.estimatedDurationMinutes} minutes</p>
          )}
        </section>
      ) : (
        <p>Service ID: {serviceId}</p>
      )}

      <form onSubmit={handleSubmit} style={{ marginTop: "32px" }}>
        <label htmlFor="problemDescription">
          <strong>Describe the problem you’re facing</strong>
        </label>

        <textarea
          id="problemDescription"
          value={problemDescription}
          onChange={(event) => {
            setProblemDescription(event.target.value);
            setError("");
            setStatus("");
          }}
          placeholder="Example: My ceiling fan is making unusual noise and needs repair."
          maxLength={1000}
          minLength={10}
          required
          rows={5}
          style={{
            display: "block",
            width: "100%",
            boxSizing: "border-box",
            marginTop: "12px",
            padding: "14px",
            border: "1px solid #ccc",
            borderRadius: "8px",
            font: "inherit",
          }}
        />

        <p style={{ color: "#777", fontSize: "13px" }}>
          {problemDescription.length}/1000 characters
        </p>

        {error && (
          <p role="alert" style={{ color: "red" }}>
            {error}
          </p>
        )}

        {status && <p role="status">{status}</p>}

        <button type="submit">Continue</button>
      </form>
    </main>
  );
}

export default ServiceRequestPage;
# Logistics Frontend

This is a React + Vite frontend for the Logistics & Shipment Tracking platform. It integrates with the existing Java Spring Boot microservices via REST APIs.

Features
- React 19 + Vite
- Tailwind CSS
- Context API for authentication (JWT)
- Role-based protected routes
- Axios centralized instance and service modules

Prerequisites
- Node.js 18+
- The backend services (Auth, User, Shipment, Tracking) running and reachable.

Setup
1. Copy environment example and set the API base URL:

```bash
cd frontend
cp .env.example .env
# edit .env and set VITE_API_BASE_URL
```

2. Install dependencies and run:

```bash
npm install
npm run dev
```

Project Structure

- `src/components` — reusable UI components (Button, Input, Card, Table, Loader)
- `src/context` — `AuthContext` for authentication state
- `src/pages` — application pages (public, private, admin)
- `src/services` — API wrappers using centralized axios instance
- `tailwind.config.cjs`, `postcss.config.cjs` — Tailwind setup

API Endpoints
The service modules call typical REST endpoints under the base URL. Adjust the endpoints in `src/services/*.js` if your backend uses different paths. Example endpoints used:

- `POST /api/auth/login` — login
- `POST /api/auth/register` — register
- `GET /api/auth/me` or `/api/users/me` — profile
- `GET /api/shipments`, `POST /api/shipments`, `GET /api/shipments/:id` — shipments
- `GET /api/tracking/:trackingNumber` — tracking timeline

Notes
- Do not run or modify the backend here. This frontend assumes existing REST APIs.
- The code attaches the JWT Bearer token automatically to requests and handles 401 responses by logging out.

If you want, I can run `npm install` and start the dev server for you, or adapt endpoint paths to match your backend routes.

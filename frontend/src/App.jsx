import React, { Suspense, lazy } from 'react'
import { Routes, Route } from 'react-router-dom'
import MainLayout from './components/Layout/MainLayout'
import ProtectedRoute from './components/ProtectedRoute'
import Loader from './components/Loader'

const Login = lazy(() => import('./pages/public/Login'))
const Register = lazy(() => import('./pages/public/Register'))
const Dashboard = lazy(() => import('./pages/Dashboard'))
const CreateShipment = lazy(() => import('./pages/CreateShipment'))
const MyShipments = lazy(() => import('./pages/MyShipments'))
const ShipmentDetails = lazy(() => import('./pages/ShipmentDetails'))
const TrackShipment = lazy(() => import('./pages/TrackShipment'))
const Profile = lazy(() => import('./pages/Profile'))
const AdminDashboard = lazy(() => import('./pages/admin/AdminDashboard'))
const AllShipments = lazy(() => import('./pages/admin/AllShipments'))
const UpdateShipmentStatus = lazy(() => import('./pages/admin/UpdateShipmentStatus'))
const Users = lazy(() => import('./pages/admin/Users'))

function App() {
  return (
    <Suspense fallback={<Loader />}>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route
          path="/"
          element={
            <ProtectedRoute>
              <MainLayout>
                <Dashboard />
              </MainLayout>
            </ProtectedRoute>
          }
        />

        <Route
          path="/create"
          element={
            <ProtectedRoute>
              <MainLayout>
                <CreateShipment />
              </MainLayout>
            </ProtectedRoute>
          }
        />

        <Route
          path="/shipments"
          element={
            <ProtectedRoute>
              <MainLayout>
                <MyShipments />
              </MainLayout>
            </ProtectedRoute>
          }
        />

        <Route
          path="/shipments/:id"
          element={
            <ProtectedRoute>
              <MainLayout>
                <ShipmentDetails />
              </MainLayout>
            </ProtectedRoute>
          }
        />

        <Route
          path="/track"
          element={
            <ProtectedRoute>
              <MainLayout>
                <TrackShipment />
              </MainLayout>
            </ProtectedRoute>
          }
        />

        <Route
          path="/profile"
          element={
            <ProtectedRoute>
              <MainLayout>
                <Profile />
              </MainLayout>
            </ProtectedRoute>
          }
        />

        <Route
          path="/admin"
          element={
            <ProtectedRoute roles={["ADMIN"]}>
              <MainLayout>
                <AdminDashboard />
              </MainLayout>
            </ProtectedRoute>
          }
        />

        <Route
          path="/admin/shipments"
          element={
            <ProtectedRoute roles={["ADMIN"]}>
              <MainLayout>
                <AllShipments />
              </MainLayout>
            </ProtectedRoute>
          }
        />

        <Route
          path="/admin/shipments/:id/update"
          element={
            <ProtectedRoute roles={["ADMIN"]}>
              <MainLayout>
                <UpdateShipmentStatus />
              </MainLayout>
            </ProtectedRoute>
          }
        />

        <Route
          path="/admin/users"
          element={
            <ProtectedRoute roles={["ADMIN"]}>
              <MainLayout>
                <Users />
              </MainLayout>
            </ProtectedRoute>
          }
        />
      </Routes>
    </Suspense>
  )
}

export default App

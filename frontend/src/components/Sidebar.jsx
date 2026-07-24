import React from 'react'
import { NavLink } from 'react-router-dom'

const Sidebar = () => {
  return (
    <aside className="w-64 bg-white border-r hidden md:block">
      <div className="p-6">
        <div className="mb-6 font-bold text-xl">LOGISTICS</div>
        <nav className="flex flex-col gap-2 text-sm">
          <NavLink to="/" end className={({ isActive }) => (isActive ? 'font-medium' : '')}>
            Dashboard
          </NavLink>
          <NavLink to="/create" className={({ isActive }) => (isActive ? 'font-medium' : '')}>
            Create Shipment
          </NavLink>
          <NavLink to="/shipments" className={({ isActive }) => (isActive ? 'font-medium' : '')}>
            My Shipments
          </NavLink>
          <NavLink to="/track" className={({ isActive }) => (isActive ? 'font-medium' : '')}>
            Track Shipment
          </NavLink>
          <NavLink to="/profile" className={({ isActive }) => (isActive ? 'font-medium' : '')}>
            Profile
          </NavLink>
        </nav>
      </div>
    </aside>
  )
}

export default Sidebar

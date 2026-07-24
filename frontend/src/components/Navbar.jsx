import React from 'react'
import { useAuth } from '../context/AuthContext'
import { LogOut } from 'lucide-react'

const Navbar = () => {
  const { user, logout } = useAuth()
  return (
    <header className="bg-white border-b">
      <div className="container flex items-center justify-between h-16">
        <div className="font-semibold text-lg">Logistics Dashboard</div>
        <div className="flex items-center gap-4">
          <div className="text-sm text-gray-700">{user?.name || 'Guest'}</div>
          <button onClick={logout} className="p-2 rounded-md hover:bg-gray-100">
            <LogOut size={18} />
          </button>
        </div>
      </div>
    </header>
  )
}

export default Navbar

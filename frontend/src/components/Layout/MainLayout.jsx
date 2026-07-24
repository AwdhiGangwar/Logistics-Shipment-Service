import React from 'react'
import Navbar from '../Navbar'
import Sidebar from '../Sidebar'

const MainLayout = ({ children }) => {
  return (
    <div className="min-h-screen flex bg-gray-50">
      <Sidebar />
      <div className="flex-1">
        <Navbar />
        <main className="p-6">{children}</main>
      </div>
    </div>
  )
}

export default MainLayout

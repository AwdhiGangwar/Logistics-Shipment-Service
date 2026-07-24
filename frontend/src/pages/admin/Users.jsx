import React from 'react'

const Users = () => {
  return (
    <div className="container">
      <h2 className="text-xl font-semibold mb-4">Users</h2>
      <div className="bg-white rounded-md shadow p-6">
        <p className="text-sm text-gray-600">
          The backend does not currently expose a user listing endpoint. This page requires a backend route such as <code>GET /api/users</code> to display all users.
        </p>
      </div>
    </div>
  )
}

export default Users

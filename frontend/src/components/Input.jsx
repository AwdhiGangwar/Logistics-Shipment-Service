import React from 'react'

const Input = ({ label, ...props }) => (
  <label className="block">
    {label && <div className="text-sm mb-1 text-gray-700">{label}</div>}
    <input className="border px-3 py-2 rounded-md w-full" {...props} />
  </label>
)

export default Input

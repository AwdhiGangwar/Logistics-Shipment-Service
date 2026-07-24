import React from 'react'

const Button = ({ children, className = '', ...props }) => (
  <button
    className={`bg-blue-600 text-white px-4 py-2 rounded-md disabled:opacity-60 ${className}`}
    {...props}
  >
    {children}
  </button>
)

export default Button

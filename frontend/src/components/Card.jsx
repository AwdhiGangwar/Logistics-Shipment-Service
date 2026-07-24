import React from 'react'

const Card = ({ title, children }) => (
  <div className="bg-white shadow-sm rounded-md p-4">
    {title && <h3 className="font-semibold mb-2">{title}</h3>}
    {children}
  </div>
)

export default Card

import React, { useEffect, useState } from 'react'
import shipmentService from '../services/shipmentService'
import Table from '../components/Table'
import { Link } from 'react-router-dom'

const MyShipments = () => {
  const [data, setData] = useState([])

  useEffect(() => {
    ;(async () => {
      try {
        const res = await shipmentService.getShipments()
        setData(res.content || res)
      } catch (e) {}
    })()
  }, [])

  const columns = [
    { key: 'trackingNumber', title: 'Tracking Number' },
    { key: 'receiverId', title: 'Receiver ID' },
    { key: 'status', title: 'Status' },
    { key: 'createdAt', title: 'Created Date' }
  ]

  return (
    <div className="container">
      <h2 className="text-xl font-semibold mb-4">My Shipments</h2>
      <Table columns={columns} data={data} renderRowActions={row => <Link to={`/shipments/${row.id}`} className="text-blue-600">View</Link>} />
    </div>
  )
}

export default MyShipments

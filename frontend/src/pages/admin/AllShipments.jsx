import React, { useEffect, useState } from 'react'
import shipmentService from '../../services/shipmentService'
import Table from '../../components/Table'
import { Link } from 'react-router-dom'

const AllShipments = () => {
  const [data, setData] = useState([])

  useEffect(() => {
    ;(async () => {
      try {
        const res = await shipmentService.getAllShipments()
        setData(res)
      } catch (e) {}
    })()
  }, [])

  const columns = [
    { key: 'trackingNumber', title: 'Tracking Number' },
    { key: 'receiverName', title: 'Receiver' },
    { key: 'status', title: 'Status' },
    { key: 'createdAt', title: 'Created Date' }
  ]

  return (
    <div className="container">
      <h2 className="text-xl font-semibold mb-4">All Shipments</h2>
      <Table columns={columns} data={data} renderRowActions={row => <Link to={`/admin/shipments/${row.id}/update`} className="text-blue-600">Update</Link>} />
    </div>
  )
}

export default AllShipments

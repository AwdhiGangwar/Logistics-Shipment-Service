import React, { useEffect, useState } from 'react'
import Card from '../components/Card'
import shipmentService from '../services/shipmentService'
import Table from '../components/Table'
import { Link } from 'react-router-dom'

const Dashboard = () => {
  const [stats, setStats] = useState({ total: 0, delivered: 0, inTransit: 0, pending: 0 })
  const [recent, setRecent] = useState([])

  useEffect(() => {
    ;(async () => {
      try {
        const res = await shipmentService.getShipments()
        const all = res.content || res
        setRecent(all.slice(0, 6))
        setStats({
          total: all.length,
          delivered: all.filter(s => s.status === 'DELIVERED').length,
          inTransit: all.filter(s => s.status === 'IN_TRANSIT').length,
          pending: all.filter(s => s.status === 'PENDING').length
        })
      } catch (e) {
        // ignore for now
      }
    })()
  }, [])

  const columns = [
    { key: 'trackingNumber', title: 'Tracking Number' },
    { key: 'receiverId', title: 'Receiver ID' },
    { key: 'status', title: 'Status' },
    { key: 'createdAt', title: 'Created Date' }
  ]

  return (
    <div className="space-y-6 container">
      <div className="grid grid-cols-4 gap-4">
        <Card title="Total Shipments">{stats.total}</Card>
        <Card title="Delivered">{stats.delivered}</Card>
        <Card title="In Transit">{stats.inTransit}</Card>
        <Card title="Pending">{stats.pending}</Card>
      </div>

      <Card title="Recent Shipments">
        <Table columns={columns} data={recent} renderRowActions={row => <Link to={`/shipments/${row.id}`} className="text-blue-600">View</Link>} />
      </Card>
    </div>
  )
}

export default Dashboard

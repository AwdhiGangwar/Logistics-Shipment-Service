import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import shipmentService from '../services/shipmentService'
import Card from '../components/Card'

const ShipmentDetails = () => {
  const { id } = useParams()
  const [shipment, setShipment] = useState(null)

  useEffect(() => {
    ;(async () => {
      try {
        const res = await shipmentService.getShipmentById(id)
        setShipment(res)
      } catch (e) {}
    })()
  }, [id])

  if (!shipment) return <div className="container p-4">Loading...</div>

  return (
    <div className="container max-w-3xl space-y-4">
      <h2 className="text-xl font-semibold">Shipment {shipment.trackingNumber}</h2>
      <Card>
        <div className="grid grid-cols-2 gap-4">
          <div>
            <div className="text-sm text-gray-500">Sender ID</div>
            <div>{shipment.senderId}</div>
          </div>
          <div>
            <div className="text-sm text-gray-500">Receiver ID</div>
            <div>{shipment.receiverId}</div>
          </div>
          <div>
            <div className="text-sm text-gray-500">Source Address</div>
            <div>{shipment.sourceAddress}</div>
          </div>
          <div>
            <div className="text-sm text-gray-500">Destination Address</div>
            <div>{shipment.destinationAddress}</div>
          </div>
          <div>
            <div className="text-sm text-gray-500">Weight</div>
            <div>{shipment.weight}</div>
          </div>
          <div>
            <div className="text-sm text-gray-500">Shipment Type</div>
            <div>{shipment.shipmentType}</div>
          </div>
        </div>
      </Card>

      <Card title="Timeline">
        <ul className="space-y-2">
          {(shipment.timeline || []).map((t, i) => (
            <li key={i} className="flex items-start gap-3">
              <div className="text-sm text-gray-600 w-40">{t.date}</div>
              <div>{t.status}</div>
            </li>
          ))}
        </ul>
      </Card>
    </div>
  )
}

export default ShipmentDetails

import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import shipmentService from '../../services/shipmentService'
import Button from '../../components/Button'
import Input from '../../components/Input'

const UpdateShipmentStatus = () => {
  const { id } = useParams()
  const [status, setStatus] = useState('')
  const [loading, setLoading] = useState(false)

  useEffect(() => {
    ;(async () => {
      try {
        const s = await shipmentService.getShipmentById(id)
        setStatus(s.status)
      } catch (e) {}
    })()
  }, [id])

  const handleUpdate = async () => {
    setLoading(true)
    try {
      await shipmentService.updateStatus(id, status)
      alert('Updated')
    } catch (e) {
      alert('Failed')
    } finally { setLoading(false) }
  }

  return (
    <div className="container max-w-md">
      <h2 className="text-xl font-semibold mb-4">Update Shipment Status</h2>
      <div className="bg-white p-4 rounded-md shadow space-y-3">
        <Input label="Status" value={status} onChange={e => setStatus(e.target.value)} />
        <Button onClick={handleUpdate} disabled={loading}>{loading ? 'Updating...' : 'Update'}</Button>
      </div>
    </div>
  )
}

export default UpdateShipmentStatus

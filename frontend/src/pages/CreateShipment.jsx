import React, { useState } from 'react'
import Input from '../components/Input'
import Button from '../components/Button'
import shipmentService from '../services/shipmentService'

const shipmentTypes = ['DOCUMENT', 'PARCEL', 'FRAGILE', 'EXPRESS']

const CreateShipment = () => {
  const [form, setForm] = useState({ receiverId: '', sourceAddress: '', destinationAddress: '', weight: '', shipmentType: 'DOCUMENT' })
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [success, setSuccess] = useState(null)

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value })

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError(null)
    setLoading(true)
    try {
      await shipmentService.createShipment(form)
      setSuccess('Shipment created successfully')
      setForm({ receiverName: '', address: '', weight: '', description: '' })
    } catch (err) {
      setError(err?.response?.data?.message || 'Failed')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container max-w-2xl">
      <h2 className="text-xl font-semibold mb-4">Create Shipment</h2>
      {error && <div className="text-red-600 mb-2">{error}</div>}
      {success && <div className="text-green-600 mb-2">{success}</div>}
      <form onSubmit={handleSubmit} className="space-y-4 bg-white p-4 rounded-md shadow">
        <Input name="receiverId" label="Receiver ID" value={form.receiverId} onChange={handleChange} />
        <Input name="sourceAddress" label="Source Address" value={form.sourceAddress} onChange={handleChange} />
        <Input name="destinationAddress" label="Destination Address" value={form.destinationAddress} onChange={handleChange} />
        <Input name="weight" label="Weight" type="number" value={form.weight} onChange={handleChange} />
        <label className="block">
          <div className="text-sm mb-1 text-gray-700">Shipment Type</div>
          <select name="shipmentType" value={form.shipmentType} onChange={handleChange} className="border px-3 py-2 rounded-md w-full">
            {shipmentTypes.map(type => <option key={type} value={type}>{type}</option>)}
          </select>
        </label>
        <Button type="submit" disabled={loading}>{loading ? 'Creating...' : 'Create'}</Button>
      </form>
    </div>
  )
}

export default CreateShipment

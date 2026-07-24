import { shipmentApi } from './api'

const shipmentService = {
  createShipment: async (payload) => {
    const res = await shipmentApi.post('/api/shipments', payload)
    return res.data
  },
  getShipmentById: async (id) => {
    const res = await shipmentApi.get(`/api/shipments/${id}`)
    return res.data
  },
  getShipments: async (params = {}) => {
    const res = await shipmentApi.get('/api/shipments', { params })
    return res.data
  },
  getShipmentsBySenderId: async (senderId) => {
    const res = await shipmentApi.get(`/api/shipments/sender/${senderId}`)
    return res.data
  },
  updateStatus: async (id, status) => {
    const res = await shipmentApi.patch(`/api/shipments/${id}/status`, { status })
    return res.data
  }
}

export default shipmentService
